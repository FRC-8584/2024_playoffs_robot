package frc.robot.devices;

import java.util.Timer;
import java.util.TimerTask;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;

public class Sensor {

  public class TransferSensor {
    private static final DigitalOutput output = new DigitalOutput(1);
    private static final DigitalInput input = new DigitalInput(0);

    public static void init() {
      TransferSensor.output.set(true);
    }

    public static boolean isDetected() {
      return input.get();
    }
  }
}
