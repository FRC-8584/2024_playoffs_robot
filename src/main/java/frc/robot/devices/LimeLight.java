package frc.robot.devices;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Constants;

public class LimeLight {
  private static NetworkTable table;

  private static double ty, ta, d;
  private static double[] tid;

  public static void initialize() {
    table = NetworkTableInstance.getDefault().getTable("8584");
  }

  public static boolean isDetectedSpeaker() {
    update();
    if (ta > 0.5 && (tid[0] == 5 || tid[0] == 6))
      return true;
    else
      return false;
  }

  public static double getSpeakerDistance() {
    if(!isDetectedSpeaker()) return -1;
    // if(not detected) return -1;
    return d; //mm
  }

  public static double getSpeakerYawDegrees() {
    if(!isDetectedSpeaker()) return -1;
    return Math.atan(Constants.Shooter_SpeakerHight / (d + Constants.Shooter_LimelightDiscance)) * 180 / 3.1415926; //degrees
  }

  public static double getAmpYawDegrees() {
    if(!isDetectedSpeaker()) return -1;
    return Math.atan(Constants.Shooter_AmpHight / (d + Constants.Shooter_LimelightDiscance)) * 180 / 3.1415926; //degrees
  }

  private static void update() {
    d = (Constants.FieldConstants.SpeakerTagHight - Constants.OperatorConstants.LimelightHight) / Math.tan((ty + Constants.OperatorConstants.LimelightAngle) / 180 * 3.1415926);
    ty = table.getEntry("ty").getDouble(0.0);
    ta = table.getEntry("ta").getDouble(0.0);
    tid = table.getEntry("tid").getDoubleArray(new double[6]);
  }
}
