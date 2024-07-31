package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Transfer extends SubsystemBase {
  private final CANSparkMax topMotor = new CANSparkMax(Constants.MotorControllerID.TopTransferID, MotorType.kBrushed);
  private final CANSparkMax bottomMotor = new CANSparkMax(Constants.MotorControllerID.BottomTransferID, MotorType.kBrushed);

  public Transfer() {}

  @Override
  public void periodic() {}

  public void topMotorIn() {
    topMotor.set(Constants.MotorConstants.kTopTransferSpeed);
  }

  public void topMotorOut() {
    topMotor.set(-Constants.MotorConstants.kTopTransferSpeed);
  }

  public void bottomMotorIn() {
    bottomMotor.set(Constants.MotorConstants.kBottomTransferSpeed);
  }

  public void bottomMotorOut() {
    bottomMotor.set(-Constants.MotorConstants.kBottomTransferSpeed);
  }
}
