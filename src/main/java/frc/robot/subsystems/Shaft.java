package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.PID;
import frc.robot.utils.Tools;

public class Shaft extends SubsystemBase {

  private final CANSparkMax motor = new CANSparkMax(Constants.MotorControllerConstants.ShaftSparkID, MotorType.kBrushless);
  private final PID pid = new PID(1.0, 1e-2, 0);

  private double initEnc;

  private double enc;

  public Shaft() {
    initEnc = motor.getEncoder().getPosition();
  }

  public void setAngle(double aimAngle) {
    enc = motor.getEncoder().getPosition();
    double aimEnc = aimAngle / 360.0 * 4096.0;
    double power = Tools.bounding(pid.calculate(aimEnc - (enc - initEnc)), -1.0, 1.0);
    motor.set(power);
  }

}
