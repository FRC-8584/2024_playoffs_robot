package frc.robot.devices;

import frc.robot.utils.PixyUtil;

public class Pixy {
  private static PixyUtil pixy = new PixyUtil();
  
  public static void init() {}
  
  public static int getTX(){
    return pixy.getValue()[1];
  }

  public static int getTA() {
    return pixy.getValue()[0];
  }

  public static boolean isDetected() {
    return pixy.isDetected();
  }
}
