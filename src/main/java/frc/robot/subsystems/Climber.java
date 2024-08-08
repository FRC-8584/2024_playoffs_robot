package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  private CANSparkMax Lmotor = new CANSparkMax(16, MotorType.kBrushed);
  private CANSparkMax Rmotor = new CANSparkMax(17, MotorType.kBrushed);
    private VictorSPX Backmotor = new VictorSPX(15);

  public Climber() {
  }

  public void up() {
    Backmotor.set(VictorSPXControlMode.PercentOutput, 0.1);
    Lmotor.set(1);
    Rmotor.set(1);
  }

  public void down() {
    Backmotor.set(VictorSPXControlMode.PercentOutput, 0);
    Lmotor.set(1);
    Rmotor.set(1);
  }

  public void stop() {
    Backmotor.set(VictorSPXControlMode.PercentOutput, 0);
    Lmotor.set(0);
    Rmotor.set(0);
  }

}
