package frc.robot.devices;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;

public class Sensor {

  public void initailize() {
    IntakeSensor.output.set(true);
    TransferSensor.output.set(true);
  }
  
  public class IntakeSensor {
    private static final DigitalOutput output = new DigitalOutput(0);
    private static final DigitalInput input = new DigitalInput(1);

    public static boolean getValue() {
      return input.get();
    }
  }

  public class TransferSensor {
    private static final DigitalOutput output = new DigitalOutput(2);
    private static final DigitalInput input = new DigitalInput(3);

    public static boolean getValue() {
      return input.get();
    }
  }

}
