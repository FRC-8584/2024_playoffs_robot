package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import frc.robot.Constants;
import frc.robot.utils.PID;

public class Turret extends SubsystemBase {
  private final CANSparkMax motor = new CANSparkMax(Constants.MotorControllerID.TurretID, MotorType.kBrushless);

  private final PID pid = new PID(1.0, 1e-2, 0);

  private final double initEncValue;
  private double encValue;

  public Turret() {
    initEncValue = motor.getEncoder().getPosition();
  }

  @Override
  public void periodic() {
    encValue = motor.getEncoder().getPosition();
  }

  public void setPosition(double degrees) {
    // motor.set(Constants.MotorConstants.kTurretSpeed);
  }

  public double getInitEncValue() {
    return initEncValue;
  }

  public double getEncValue() {
    return encValue;
  }
}
