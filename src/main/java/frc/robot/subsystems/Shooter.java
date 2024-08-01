package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  private final CANSparkMax topMotor = new CANSparkMax(Constants.MotorControllerID.TopShooterID, MotorType.kBrushed);
  private final CANSparkMax bottomMotor = new CANSparkMax(Constants.MotorControllerID.BottomShooterID, MotorType.kBrushed);

  public Shooter() {
    topMotor.set(0);
    bottomMotor.set(0);
  }

  public void shoot(double power) {
    topMotor.set(power);
    bottomMotor.set(power * -1.0);
  }

}
