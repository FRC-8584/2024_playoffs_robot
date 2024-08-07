package frc.robot.devices;

import frc.robot.utils.TransferSensor;

public class Sensor {

  private static TransferSensor transferSensor = new TransferSensor(0, 1);

  public static void init() {}

  public static boolean isDetected() {
    return transferSensor.isDetected();
  }

}
