package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  private final CANSparkMax motor = new CANSparkMax(Constants.MotorControllerConstants.IntakeSparkID, MotorType.kBrushed);

  public Intake() {

  }

  public void in(){
    motor.set(Constants.MotorConstants.kIntakeSpeed);
  }

  public void out(){
    motor.set(-Constants.MotorConstants.kIntakeSpeed);
  }
}
