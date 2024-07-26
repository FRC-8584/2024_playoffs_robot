package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import frc.robot.Constants;
import frc.robot.utils.PID;

public class Turret {
  private final CANSparkMax motor = new CANSparkMax(Constants.MotorControllerID.ShaftID, MotorType.kBrushless);
  private final PID pid = new PID(1.0, 1e-2, 0);

  private double initEnc;

  private double enc;

  public Turret() {
    initEnc = motor.getEncoder().getPosition();
  }
  
}
