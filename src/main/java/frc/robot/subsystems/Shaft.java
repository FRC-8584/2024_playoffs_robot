package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.PID;
import frc.robot.utils.Tools;

public class Shaft extends SubsystemBase {
  private final CANSparkMax Lmotor = new CANSparkMax(Constants.MotorControllerID.LShaftID, MotorType.kBrushless);
  private final CANSparkMax Rmotor = new CANSparkMax(Constants.MotorControllerID.RShaftID, MotorType.kBrushless);

  private final PID pid1 = new PID(0.05, 0, 0);
  private final PID pid2 = new PID(0.7, 2 *1e-3, 0);

  private final double InitLEnc;
  private final double InitREnc;

  private double LAngle;
  private double RAngle;

  public Shaft() {
    InitLEnc = Lmotor.getEncoder().getPosition();
    InitREnc = Rmotor.getEncoder().getPosition();
  }

  @Override
  public void periodic() {
    LAngle = -(Lmotor.getEncoder().getPosition() - InitLEnc) * 1.6 + Constants.MechanicalConstants.ShaftInitAngle;
    RAngle = (Rmotor.getEncoder().getPosition() - InitREnc) * 1.6 + Constants.MechanicalConstants.ShaftInitAngle;

    SmartDashboard.putNumber("L Shaft A", LAngle);
    SmartDashboard.putNumber("R Shaft A", RAngle);
    
    // double err = LAngle - RAngle;
    // if(err > 3) {
    //   Lmotor.set(-Tools.bounding(pid1.calculate(-err / Constants.MechanicalConstants.ShaftAngleRange)));
    //   Rmotor.set(Tools.bounding(pid1.calculate(err / Constants.MechanicalConstants.ShaftAngleRange)));
    // }
    // else if(err < -3){
    //   Lmotor.set(-Tools.bounding(pid1.calculate(err / Constants.MechanicalConstants.ShaftAngleRange)));
    //   Rmotor.set(Tools.bounding(pid1.calculate(-err / Constants.MechanicalConstants.ShaftAngleRange)));
    // }
  }

  public void setPosition(double degrees) {
    if((degrees < Constants.MechanicalConstants.ShaftMinAngle) || (degrees > Constants.MechanicalConstants.ShaftMaxAngle)) return;
    double Lerr = degrees - LAngle;
    double Rerr = degrees - RAngle;

    Lmotor.set(-Tools.bounding(pid2.calculate(Lerr / Constants.MechanicalConstants.ShaftAngleRange)));
    Rmotor.set(Tools.bounding(pid2.calculate(Rerr / Constants.MechanicalConstants.ShaftAngleRange)));
  }

  public void setPower(double force) {
    force = Tools.deadband(force * 0.5, 0.05);
    if(LAngle > Constants.MechanicalConstants.ShaftMaxAngle && force * 0.5 > 0) Lmotor.set(0);
    else if(LAngle < Constants.MechanicalConstants.ShaftMinAngle && force * 0.5 < 0) Lmotor.set(0);
    else Lmotor.set(-force * 0.5);

    if(RAngle > Constants.MechanicalConstants.ShaftMaxAngle && force * 0.5 > 0) Rmotor.set(0);
    else if(RAngle < Constants.MechanicalConstants.ShaftMinAngle && force * 0.5 < 0) Rmotor.set(0);
    else Rmotor.set(force * 0.5);
  }

  public double[] getShaftAngle() {
    return new double[]{LAngle, RAngle};
  }

}
