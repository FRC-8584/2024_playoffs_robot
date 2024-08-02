package frc.robot.devices;

import org.opencv.core.Mat;

import frc.robot.Constants;

public class LimeLight {
  public static void initialize() {}

  private static double tx, ty, ta, d;

  public static boolean isDetectedSpeaker() {
    update();
    if (ta > 1.0)
      return true;
    else
      return false;
  }

  public static double getSpeakerDistance() {
    if(!isDetectedSpeaker()) return -1;
    // if(not detected) return -1;
    d = (Constants.OperatorConstants.TagHight - Constants.OperatorConstants.LimelightHight) / Math.tan((ty + Constants.OperatorConstants.LimelightAngle) / 180 * 3.1415926);
    return d;//mm
  }

  public static double getSpeakerYawDegrees() {
    if(!isDetectedSpeaker()) return 9487;
    return Math.atan( Constants.Shooter_SpeakerHight / (d + Constants.Shooter_Limelight)) * 180 / 3.1415926;//degrees
  }

  private static void update() {
    d = 0;
  }
}
