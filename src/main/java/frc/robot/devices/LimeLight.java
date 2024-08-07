package frc.robot.devices;
import frc.robot.utils.LimelightHelpers;

public class LimeLight {
  private static LimelightHelpers limelight = new LimelightHelpers();

  public static void init() {}

  public static double getTX(){
    return limelight.getTX();
  }

  public static double getTY(){
    return limelight.getTY();
  }

  public static double getDistance(){
    return limelight.getDistance();
  }

  public static double getTid(){
    return limelight.getTid();
  }

  public static boolean isDetectedSpeaker() {
    if(limelight.getTid() == 8 || limelight.getTid() == 4) return true ;
    else return false;
  }

  public static boolean isDetectedAmp() {
    if(limelight.getTid() == 5 || limelight.getTid() == 6) return true ;
    else return false;
  }
}
