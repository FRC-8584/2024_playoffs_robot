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

  private final PID pid1 = new PID(0.05, 0, 0);
  private final PID pid2 = new PID(0.5, 1e-2, 0);

  private double LAngle;
  private double RAngle;

  public Shaft() {}

  @Override
  public void periodic() {
    LAngle = -Lmotor.getEncoder().getPosition() * 60.0 + Constants.MechanicalConstants.ShaftInitAngle;
    RAngle = Rmotor.getEncoder().getPosition() * 60.0 - Constants.MechanicalConstants.ShaftInitAngle;
    
    if(Math.abs(LAngle - RAngle) > 0.5) {
      if(LAngle > RAngle){
        double err = LAngle - RAngle;
        Lmotor.set(Tools.bounding(pid1.calculate(err)));
      }
      else {
        double err = RAngle - LAngle;
        Lmotor.set(Tools.bounding(pid1.calculate(err)));
      }
    }
  }

  public void setPosition(double degrees) {
    if((degrees > Constants.MechanicalConstants.ShaftMaxAngle) || (degrees < Constants.MechanicalConstants.ShaftMaxAngle)) return;
    double Lerr = degrees - LAngle;
    double Rerr = degrees - RAngle;

    Lmotor.set(-Tools.bounding(pid2.calculate(Lerr / (Constants.MechanicalConstants.ShaftMaxAngle - Constants.MechanicalConstants.ShaftMinAngle))));
    Rmotor.set(Tools.bounding(pid2.calculate(Rerr / (Constants.MechanicalConstants.ShaftMaxAngle - Constants.MechanicalConstants.ShaftMinAngle))));
  }

  public void setPower(double force) {
    if(LAngle > Constants.MechanicalConstants.ShaftMaxAngle && force > 0) Lmotor.set(0);
    else if(LAngle < Constants.MechanicalConstants.ShaftMinAngle && force < 0) Lmotor.set(0);
    else Lmotor.set(-force * 0.2);

    if(RAngle > Constants.MechanicalConstants.ShaftMaxAngle && force > 0) Rmotor.set(0);
    else if(RAngle < Constants.MechanicalConstants.ShaftMinAngle && force < 0) Rmotor.set(0);
    else Rmotor.set(force * 0.2);
  }

  public double[] getShaftAngle() {
    return new double[]{LAngle, RAngle};
  }

}
