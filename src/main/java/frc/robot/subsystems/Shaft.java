package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.PID;
import frc.robot.utils.Tools;

public class Shaft extends SubsystemBase {
  private final CANSparkMax Lmotor = new CANSparkMax(Constants.MotorControllerID.LShaftID, MotorType.kBrushless);
  private final CANSparkMax Rmotor = new CANSparkMax(Constants.MotorControllerID.RShaftID, MotorType.kBrushless);

  private final PID pid1 = new PID(0.1, 1e-2, 0);
  private final PID pid2 = new PID(1.0, 1e-2, 0);

  private final double LInitEncValue;
  private final double RInitEncValue;

  private final double kInitAngle = 20.0;
  private final double kMinAngle = 20.0;
  private final double kMaxAngle = 90.0;

  private double LEncValue;
  private double REncValue;

  public Shaft() {
    LInitEncValue = Lmotor.getEncoder().getPosition();
    RInitEncValue = Rmotor.getEncoder().getPosition();
  }

  @Override
  public void periodic() {
    LEncValue = Lmotor.getEncoder().getPosition();
    REncValue = Rmotor.getEncoder().getPosition();
    
    if(Math.abs(LEncValue - REncValue) > 0.5) {
      if(LEncValue > REncValue){
        double err = LEncValue - REncValue;
        Lmotor.set(Tools.bounding(pid1.calculate(err), -1, 1));
      }
      else {
        double err = REncValue - LEncValue;
        Lmotor.set(Tools.bounding(pid1.calculate(err), -1, 1));
      }
    }
  }

  public void setPosition(double degrees) {
    if((degrees > kMaxAngle) || (degrees < kMinAngle)) return;
    double Lerr = degrees - (LEncValue / 60.0 * 360.0 + kInitAngle);
    double Rerr = degrees - (REncValue / 60.0 * 360.0 + kInitAngle);

    Lmotor.set(Tools.bounding(pid2.calculate(Lerr / (kMaxAngle - kMinAngle)), -1, 1));
    Rmotor.set(Tools.bounding(pid2.calculate(Rerr / (kMaxAngle - kMinAngle)), -1, 1));
  }

  public double[] getInitEncValue() {
    return new double[]{LInitEncValue, RInitEncValue};
  }

  public double[] getEncValue() {
    return new double[]{LEncValue, REncValue};
  }
}
