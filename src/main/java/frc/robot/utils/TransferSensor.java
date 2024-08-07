package frc.robot.utils;

import java.util.Timer;
import java.util.TimerTask;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TransferSensor {
    private final DigitalOutput output;
    private final DigitalInput input;
    
    private Timer executor;

    private boolean isdetected = false;

    private final long THREAD_PERIOD = 20;

    public  TransferSensor(int port_in, int port_out) {
      executor = new Timer();
      input = new DigitalInput(port_in);
      output = new DigitalOutput(port_out);
      output.set(true);
      executor.schedule(new TransferSensorUpdateTask(this), 0L, THREAD_PERIOD);
    }

    public void update() {
      isdetected = !input.get();
      SmartDashboard.putBoolean("Sensor", isdetected);
    }

    public boolean isDetected() {
        return isdetected;
    }

    private class TransferSensorUpdateTask extends TimerTask {
		  private TransferSensor imu;
      
		  private TransferSensorUpdateTask(TransferSensor imu) {
		  	if (imu == null) {
		  		throw new NullPointerException("TransferSensor pointer null");
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