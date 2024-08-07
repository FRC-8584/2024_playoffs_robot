package frc.robot.devices;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;

public class Sensor {

  public static void initailize() {
    TransferSensor.output.set(true);
  }

  public class TransferSensor {
    private static final DigitalOutput output = new DigitalOutput(1);
    private static final DigitalInput input = new DigitalInput(0);

    public static boolean isDetected() {
      return input.get();
    }
  }

}
