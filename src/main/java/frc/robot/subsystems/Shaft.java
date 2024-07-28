package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.PID;

public class Shaft extends SubsystemBase {

  private final CANSparkMax motor = new CANSparkMax(Constants.MotorControllerID.ShaftID, MotorType.kBrushless);
  private final PID pid = new PID(1.0, 1e-2, 0);

  private double initEnc;

  private double enc;

  public Shaft() {
    initEnc = motor.getEncoder().getPosition();
  }
}
