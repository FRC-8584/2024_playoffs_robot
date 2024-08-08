package frc.robot.utils;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.utils.pixy2api.Pixy2;
import frc.robot.utils.pixy2api.Pixy2.LinkType;
import frc.robot.utils.pixy2api.Pixy2CCC;
import frc.robot.utils.pixy2api.Pixy2CCC.Block;

public class PixyUtil {
  public int t_x, t_a;

  private Block biggestBlock;

  private Pixy2 pixy = Pixy2.createInstance(LinkType.SPI);

  private final long THREAD_PERIOD = 20;

  private Timer executor;

  public PixyUtil() {
    pixy.init();
    executor = new java.util.Timer();
    executor.schedule(new PixyUpdateTask(this), 0L, THREAD_PERIOD);
  }

  private void update() {
    biggestBlock = getBiggestBlock();
    if(biggestBlock != null) {
      t_a = biggestBlock.getHeight() * biggestBlock.getWidth();
      t_x = biggestBlock.getX() - 315/2 + biggestBlock.getWidth()/2;
    }
    else {
      t_a = 0;
      t_x = 0;
    }
    SmartDashboard.putNumber("Pixy ta", t_a);
    SmartDashboard.putNumber("Pixy tx", t_x);
    return;
  }

	public  int[] getValue() {
		return new int[] {t_a, t_x};
	}

	public  boolean isDetected() {
		return biggestBlock == null ? false : true;
	}

  private  Block getBiggestBlock() {
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
		private PixyUtil imu;

		private PixyUpdateTask(PixyUtil imu) {
			if (imu == null) {
				throw new NullPointerException("Pixy pointer null");
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
