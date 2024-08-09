package frc.robot.utils;

import java.util.TimerTask;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class LimelightHelpers {

  private java.util.Timer executor;

  private boolean isDetected = false;
  private double tx, ty, d;
  private long tid;

  private final long THREAD_PERIOD = 20;
  private final long DEFAULT = 0;

  private NetworkTable table;

  public LimelightHelpers() {
    table = NetworkTableInstance.getDefault().getTable("8584");
    executor = new java.util.Timer();
    executor.schedule(new LimelightUpdateTask(this) ,0L, THREAD_PERIOD);
  }

  private void update() {
    d = (Constants.FieldConstants.SpeakerTagHight - Constants.MechanicalConstants.LimelightHight) / Math.tan((ty + Constants.MechanicalConstants.LimelightAngle) / 180 * 3.1415926);
    ty = table.getEntry("ty").getDouble(0.0);
    tx = table.getEntry("tx").getDouble(0.0);
    tid = table.getEntry("tid").getInteger(DEFAULT);
    isDetected = tid == -1 ? false : true;

    SmartDashboard.putNumber("TID", tid);
    SmartDashboard.putBoolean("Detected speaker ?", (tid == 4 || tid == 8) ? true : false);
    SmartDashboard.putBoolean("Detected Amp ?", (tid == 5 || tid == 6) ? true : false);
    SmartDashboard.putNumber("TX", tx);
    SmartDashboard.putNumber("TY", ty);
  }

  public double getDistance() {
    return d;
  }

  public double getTX() {
    return tx;
  }

  public double getTY() {
    return ty;
  }

  public double getTid() {
    return tid;
  }

  private class LimelightUpdateTask extends TimerTask {
    private LimelightHelpers imu;

    private LimelightUpdateTask(LimelightHelpers imu) {
      if (imu == null) {
        throw new NullPointerException("Limelight pointer null");
      }
      this.imu = imu;
    }
  
    /**
     * Called periodically in its own thread
     */
    public void run() {
      imu.update();
    }
  }
  
  
}
