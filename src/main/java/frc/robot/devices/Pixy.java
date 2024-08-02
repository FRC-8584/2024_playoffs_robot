package frc.robot.devices;

import edu.wpi.first.wpilibj.I2C;

public class Pixy {
  
  private static final I2C pixy = new I2C(I2C.Port.kMXP, 0x54);

  public static double getTX() {
    update();
    return 0;
  }

  public static double getTa() {
    update();
    return 0;
  }

  public static boolean isDetected() {
    update();
    return false;
  }

  private static void update() {
    
  }
}
