package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.modules.SwerveModule;
import frc.robot.utils.PID;
import frc.robot.utils.Tools;
import frc.robot.Constants;
import frc.robot.devices.Gyro;

public class Swerve extends SubsystemBase{
  /**********swerve motor modules**********/

	public final SwerveModule lf;
  public final SwerveModule lr;
  public final SwerveModule rf;
  public final SwerveModule rr;

  /**********variables**********/

  private double robotHeading;
  private final double originRobotHeading;
  private final double driverHeading = 0;

  /**********constants**********/

  private static final double r = Math.sqrt(Math.pow(Constants.kRobotLength ,2)+ Math.pow(Constants.kRobotWidth ,2));
  private static final double a = Constants.kRobotLength / r, b = Constants.kRobotWidth / r;

  /**********functions**********/

  public Swerve(final double originRobotHeading) {
    lf = new SwerveModule(Constants.MotorControllerID.LF_TurnID, Constants.MotorControllerID.LF_DriveID, new PID(8, 1e-3, 0));
    lr = new SwerveModule(Constants.MotorControllerID.LR_TurnID, Constants.MotorControllerID.LR_DriveID, new PID(8, 1e-3, 0));
    rf = new SwerveModule(Constants.MotorControllerID.RF_TurnID, Constants.MotorControllerID.RF_DriveID, new PID(8, 1e-3, 0));
    rr = new SwerveModule(Constants.MotorControllerID.RR_TurnID, Constants.MotorControllerID.RR_DriveID, new PID(8, 1e-3, 0));

    lf.setName("Left_front");
    lr.setName("Left_rear");
    rf.setName("Right_front");
    rr.setName("Right_rear");

    this.originRobotHeading = originRobotHeading;

    move(0, 0, 0);
  }

  @Override
  public void periodic() {
    getRobotHeading();
  }

  /**
   * Move the robot.
   * 
   * @param x : vector x force (driver heading)
   * @param y : vector y force (driver heading)
   * @param turn : turn force
   */
  public void move(double x, double y, double turn) {
    if(x == 0 && y == 0 && turn == 0){//doesn't move
      lf.stop();
      lr.stop();
      rf.stop();
      rr.stop();

      return;
    }

    //convert the vector of driver's heading to the vector of robot's heading

    final double tempVector[] = convertHeading(x, y);

    x = tempVector[0];
    y = tempVector[1];

    x *= Constants.OperatorConstants.kMove;
    y *= Constants.OperatorConstants.kMove;
    turn *= Constants.OperatorConstants.kTrun;

    //calculate vector (x, y)

    final double lf_x = x + turn * a, lf_y = y + turn * b;
    final double lr_x = x - turn * a, lr_y = y + turn * b;
    final double rf_x = x + turn * a, rf_y = y - turn * b;
    final double rr_x = x - turn * a, rr_y = y - turn * b;

    //calculate turn degrees

    final double lf_degrees = Tools.toDegrees(lf_x, lf_y);
    final double lr_degrees = Tools.toDegrees(lr_x, lr_y);
    final double rf_degrees = Tools.toDegrees(rf_x, rf_y);
    final double rr_degrees = Tools.toDegrees(rr_x, rr_y);

    //calculate motor speed

    double lf_speed = Math.sqrt(lf_x*lf_x + lf_y*lf_y);
    double lr_speed = Math.sqrt(lr_x*lr_x + lr_y*lr_y);
    double rf_speed = Math.sqrt(rf_x*rf_x + rf_y*rf_y);
    double rr_speed = Math.sqrt(rr_x*rr_x + rr_y*rr_y);

    //bounding

    double max = 1;
    max = lf_speed > max ? lf_speed : max;
    max = lr_speed > max ? lr_speed : max;
    max = rf_speed > max ? rf_speed : max;
    max = rr_speed > max ? rr_speed : max;

    lf_speed /= max;
    lr_speed /= max;
    rf_speed /= max;
    rr_speed /= max;

		//setpoint

    lf.setpoint(lf_speed, lf_degrees);
    lr.setpoint(lr_speed, lr_degrees);
    rf.setpoint(rf_speed, rf_degrees);
    rr.setpoint(rr_speed, rr_degrees);
		
    return;
  }

  /**
   * Get robot heading.
   * 
   * @return robot heading (0 =< value < 360 degrees)
   */
  private void getRobotHeading() {
    if(Gyro.isInitialized()){
      double temp = Gyro.getVector() + originRobotHeading;

      if(temp >= 360) temp -= 360;

      robotHeading = temp;
    }
    else{
      robotHeading = driverHeading;
    }
  }

  /**
   * Change the vector of driver's heading to the vector of robot's heading.
   * 
   * @param x : vector x (driver heading)
   * @param y : vector y (driver heading)
   * 
   * @return a vector {x, y} (robot heading)
   */
  private double[] convertHeading(double x, double y) {
    return Tools.toVector(
      Math.sqrt(x*x + y*y),
      Tools.toDegrees(x, y) - (robotHeading - driverHeading)
    );//radius, angle
  }
}
