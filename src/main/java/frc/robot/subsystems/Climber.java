package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
  private final CANSparkMax motor = new CANSparkMax(Constants.MotorControllerConstants.ClimberSparkID, MotorType.kBrushed);

  public Climber() {

  }

}
