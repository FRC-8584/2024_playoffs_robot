package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  private final Victor Lmotor;
  private final Victor Rmotor;

  public Climber() {
    Lmotor = new Victor(0);
    Rmotor = new Victor(1);
    Lmotor.setSafetyEnabled(true);
    Rmotor.setSafetyEnabled(true);
  }

  public void up() {
    Lmotor.set(0.8);
    Rmotor.set(-0.8);
  }

  public void down() {
    Lmotor.set(-0.8);
    Rmotor.set(0.8);
  }

}
