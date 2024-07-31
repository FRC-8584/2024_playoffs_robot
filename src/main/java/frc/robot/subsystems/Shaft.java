package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.PID;

public class Shaft extends SubsystemBase {
  private final CANSparkMax Lmotor = new CANSparkMax(Constants.MotorControllerID.LShaftID, MotorType.kBrushless);
  private final CANSparkMax Rmotor = new CANSparkMax(Constants.MotorControllerID.RShaftID, MotorType.kBrushless);

  private final PID pid = new PID(1.0, 1e-2, 0);

  private final double LInitEncValue;
  private final double RInitEncValue;
  private double LEncValue;
  private double REncValue;

  public Shaft() {
    LInitEncValue = Lmotor.getEncoder().getPosition();
    RInitEncValue = Rmotor.getEncoder().getPosition();
  }

  @Override
  public void periodic() {
    LEncValue = Lmotor.getEncoder().getPosition();
    REncValue = Rmotor.getEncoder().getPosition();
  }

  public void turnShaft(double degrees) {
    
    // Lmotor.set(Constants.MotorConstants.kShaftSpeed);
    // Rmotor.set(Constants.MotorConstants.kShaftSpeed);
  }

  public double[] getInitEncValue() {
    return new double[]{LInitEncValue, RInitEncValue};
  }

  public double[] getEncValue() {
    return new double[]{LEncValue, REncValue};
  }
}
