package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Transfer extends SubsystemBase {
  private final CANSparkMax topMotor = new CANSparkMax(Constants.MotorControllerID.TopTransferID, MotorType.kBrushless);
  private final CANSparkMax bottomMotor = new CANSparkMax(Constants.MotorControllerID.BottomTransferID, MotorType.kBrushed);

  public Transfer() {}

  @Override
  public void periodic() {}

  public void intakeForword() {
    topMotor.set(-1);
    bottomMotor.set(-1);
  }

  public void intakeReverse() {
    topMotor.set(1);
    bottomMotor.set(1);
  }

  public void shooterReverse() {
    topMotor.set(1);
    bottomMotor.set(0.6);
  }

  public void shooterForword() {
    topMotor.set(-Constants.MotorConstants.kTopTransferSpeed);
    bottomMotor.set(-Constants.MotorConstants.kBottomTransferSpeed);
  }

  public void stop() {
    topMotor.set(0);
    bottomMotor.set(0);
  }

  public Command shooterForword = new StartEndCommand(()->this.shooterForword(), ()->this.stop(), this);
  public Command shooterReverse = new StartEndCommand(()->this.shooterReverse(), ()->this.stop(), this);
  public Command intakeReverse = new StartEndCommand(()->this.intakeReverse(), ()->this.stop(), this);
  public Command intakeForword = new StartEndCommand(()->this.intakeForword(), ()->this.stop(), this);
}
