package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  private final CANSparkMax motor = new CANSparkMax(Constants.MotorControllerID.IntakeID, MotorType.kBrushed);

  public Intake() {}

  public void in(){
    motor.set(Constants.MotorConstants.kIntakeSpeed);
  }

  public void out(){
    motor.set(-Constants.MotorConstants.kIntakeSpeed);
  }

  public void stop(){
    motor.set(0);
  }

  public void set(double power) {
    motor.set(power);
  }

  public Command IntakeOut = new StartEndCommand(()->this.out(), ()->this.stop(), this);
  public Command IntakeIn = new StartEndCommand(()->this.in(), ()->this.stop(), this);
}
