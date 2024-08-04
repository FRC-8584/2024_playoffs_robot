package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class JoystickShoter extends Command {
  private Shooter m_shooter;

  private double m_power;
  
  public JoystickShoter(Shooter shooter, double power) {
    m_shooter = shooter;
    m_power = power;
    addRequirements(m_shooter);
  }

  @Override
  public void execute() {
    m_shooter.shoot(m_power);
  }

  @Override
  public void end(boolean interrupted) {
    m_shooter.shoot(0);
  }
}
