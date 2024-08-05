package frc.robot.devices;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import frc.robot.utils.pixy2api.Pixy2;
import frc.robot.utils.pixy2api.Pixy2.LinkType;
import frc.robot.utils.pixy2api.Pixy2CCC;
import frc.robot.utils.pixy2api.Pixy2CCC.Block;

public class Pixy {
  public static  int t_width, t_height;
  public static  int t_x, t_y;

  private Block biggestBlock;

  private Pixy2 pixy;

  private Timer executor;
  private final long THREAD_PERIOD = 20;
  
  public void initailize() {
		pixy = Pixy2.createInstance(LinkType.SPI);
    pixy.init();
		executor = new Timer();
		executor.schedule(new PixyUpdateTask(this), 0L, THREAD_PERIOD);
  }
  
  private void update() {
    biggestBlock = getBiggestBlock();
    if(biggestBlock != null) {
      t_height = biggestBlock.getHeight();
      t_width = biggestBlock.getWidth();
      t_x = biggestBlock.getX();
      t_y = biggestBlock.getY();
    }
    return;
  }

	public static int getTX() {
		return t_x;
	}
	public static  int getTY() {
		return t_y;
	}
	public static  int getWidth() {
		return t_width;
	}
	public static  int getHeight() {
		return t_height;
	}

  private Block getBiggestBlock() {
    // how many
		int blockCount = pixy.getCCC().getBlocks(false, Pixy2CCC.CCC_SIG1, 25);
		if (blockCount <= 0) {
      // if the blocks were not found, return null
			return null; 
		}
		ArrayList<Block> blocks = pixy.getCCC().getBlockCache(); // get a list of all blocks which were found 
		Block largestBlock = null;
		for (Block block : blocks) {
			if (largestBlock == null) {
				largestBlock = block;
			} else if (block.getWidth() > largestBlock.getWidth()) {
				largestBlock = block;
			}
		}
		return largestBlock;
	}

  private class PixyUpdateTask extends TimerTask {
		private Pixy imu;

		private PixyUpdateTask(Pixy imu) {
			if (imu == null) {
				throw new NullPointerException("BNO055 pointer null");
			}
			this.imu = imu;
		}

		/**
		 * Called periodically in its own thread
		 */
		public void run() {
			imu.update();
		}
	}
}
