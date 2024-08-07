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

  public void front() {
    topMotor.set(-Constants.MotorConstants.kTopTransferSpeed);
    bottomMotor.set(-Constants.MotorConstants.kBottomTransferSpeed);
  }

  public void back() {
    topMotor.set(Constants.MotorConstants.kTopTransferSpeed);
    bottomMotor.set(Constants.MotorConstants.kBottomTransferSpeed);
  }

  public void stop() {
    topMotor.set(0);
    bottomMotor.set(0);
  }

  public Command TransferFront = new StartEndCommand(()->this.front(), ()->this.stop(), this);
  public Command TransferBack = new StartEndCommand(()->this.back(), ()->this.stop(), this);
}
