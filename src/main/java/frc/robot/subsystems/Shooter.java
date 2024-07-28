package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  private final CANSparkMax upMotor = new CANSparkMax(Constants.MotorControllerID.TopShooterID, MotorType.kBrushed);
  private final CANSparkMax downMotor = new CANSparkMax(Constants.MotorControllerID.TopShooterID, MotorType.kBrushed);

  public Shooter() {
    upMotor.set(0);
    downMotor.set(0);
  }

  public void shoot(double speed){
    upMotor.set(Constants.MotorConstants.kShooterSpeed * speed);
    downMotor.set(-Constants.MotorConstants.kShooterSpeed * speed);
  }

}
