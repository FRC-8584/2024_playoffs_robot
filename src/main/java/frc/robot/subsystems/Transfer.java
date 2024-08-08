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

  public void intakeIn() {
    topMotor.set(-1);
    bottomMotor.set(-1);
  }

  public void intakeOut() {
    topMotor.set(1);
    bottomMotor.set(1);
  }

  public void shooterIn() {
    topMotor.set(Constants.MotorConstants.kTopTransferSpeed);
    bottomMotor.set(Constants.MotorConstants.kBottomTransferSpeed);
  }

  public void shooterOut() {
    topMotor.set(-Constants.MotorConstants.kTopTransferSpeed);
    bottomMotor.set(-Constants.MotorConstants.kBottomTransferSpeed);
  }

  public void stop() {
    topMotor.set(0);
    bottomMotor.set(0);
  }

  public Command shooterOut = new StartEndCommand(()->this.shooterOut(), ()->this.stop(), this);
  public Command shooterIn = new StartEndCommand(()->this.shooterIn(), ()->this.stop(), this);
  public Command intakeOut = new StartEndCommand(()->this.intakeOut(), ()->this.stop(), this);
  public Command intakeIn = new StartEndCommand(()->this.intakeIn(), ()->this.stop(), this);
}
