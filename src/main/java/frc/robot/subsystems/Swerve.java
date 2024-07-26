package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.modules.SwerveModule;
import frc.robot.utils.Tools;
import frc.robot.Constants;
import frc.robot.devices.Gyro;

public class Swerve extends SubsystemBase{
  /**********swerve motor modules**********/

	public static final SwerveModule lf = new SwerveModule(1, 5);
  public static final SwerveModule lr = new SwerveModule(4, 8);
  public static final SwerveModule rf = new SwerveModule(2, 6);
  public static final SwerveModule rr = new SwerveModule(3, 7);

  /**********variables**********/

  private static volatile double robotHeading;
  private static double driverHeading = 0;

  /**********constants**********/

  private static final double r = Math.sqrt(Math.pow(Constants.kRobotLength ,2)+ Math.pow(Constants.kRobotWidth ,2));

  /**********functions**********/

  public Swerve(){
    lf.setName("Left_front");
    lr.setName("Left_rear");
    rf.setName("Right_front");
    rr.setName("Right_rear");

    lf.setPID(1, 1e-3, 0);
    lr.setPID(1, 1e-3, 0);
    rf.setPID(1, 1e-3, 0);
    rr.setPID(1, 1e-3, 0);

    move(0, 0, 0);
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
      lf.coastMove();
      lr.coastMove();
      rf.coastMove();
      rr.coastMove();

      return;
    }

    //convert the vector of driver's heading to the vector of robot's heading

    // final double tempVector[] = convertHeading(x, y);

    // x = tempVector[0];
    // y = tempVector[1];

    x *= Constants.OperatorConstants.kMove;
    y *= Constants.OperatorConstants.kMove;
    turn *= Constants.OperatorConstants.kTrun;

    //calculate vector (x, y)

    final double lf_x = x + turn*(Constants.kRobotLength / r), lf_y = y + turn*(Constants.kRobotWidth / r);
    final double lr_x = x - turn*(Constants.kRobotLength / r), lr_y = y + turn*(Constants.kRobotWidth / r);
    final double rf_x = x + turn*(Constants.kRobotLength / r), rf_y = y - turn*(Constants.kRobotWidth / r);
    final double rr_x = x - turn*(Constants.kRobotLength / r), rr_y = y - turn*(Constants.kRobotWidth / r);

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
    if(lf_speed > max) max = lf_speed;
    if(lr_speed > max) max = lr_speed;
    if(rf_speed > max) max = rf_speed;
    if(rr_speed > max) max = rr_speed;

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
   * Get turnmotor encoder value.
   */
  public void getEncValue() {
    lf.getEncValue();
    lr.getEncValue();
    rf.getEncValue();
    rr.getEncValue();
  }

  /**
   * Get robot heading.
   * 
   * @return robot heading (0 =< value < 360 degrees)
   */
  public void getRobotHeading() {
    if(Gyro.isInitialized()){
      robotHeading = Gyro.getVector();
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
