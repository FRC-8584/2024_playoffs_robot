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

  public void forword(){
    motor.set(Constants.MotorConstants.kIntakeSpeed);
  }

  public void reverse(){
    motor.set(-Constants.MotorConstants.kIntakeSpeed);
  }

  public void stop(){
    motor.set(0);
  }

  public void set(double power) {
    motor.set(power);
  }

  public Command IntakeOut = new StartEndCommand(()->this.reverse(), ()->this.stop(), this);
  public Command IntakeIn = new StartEndCommand(()->this.forword(), ()->this.stop(), this);
}
