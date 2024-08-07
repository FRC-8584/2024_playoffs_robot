package frc.robot.devices;

import java.util.Timer;
import java.util.TimerTask;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;

public class Sensor {

  public class TransferSensor {
    private static final DigitalOutput output = new DigitalOutput(1);
    private static final DigitalInput input = new DigitalInput(0);

    private static boolean isDetected = false;
    private static boolean isInitailized = false;
    private static Timer executor;

    private final long THREAD_PERIOD = 20;

    private static void init() {
      TransferSensor.output.set(true);
    }

    public static boolean isDetected() {
      return isDetected;
    }

    private void update(){
      if(isInitailized == false) {
			  init();
			  executor = new Timer();
			  executor.schedule(new SensorUpdateTask(this), 0L, THREAD_PERIOD);
			  isInitailized = true;
		  }
      else{
        isDetected = input.get();
      }
    }

    private class SensorUpdateTask extends TimerTask {
		  private TransferSensor imu;
      
		  private SensorUpdateTask(TransferSensor imu) {
		  	if (imu == null) {
		  		throw new NullPointerException("Sensor pointer null");
		  	}
		  	this.imu = imu;
		  }
		  public void run() {
		  	imu.update();
		  }
	}
}
}
