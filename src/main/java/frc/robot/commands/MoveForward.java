package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveDrive;

public class MoveForward extends Command{
  private SwerveDrive m_swerve;

  public MoveForward(
    SwerveDrive swerve)
  {
    m_swerve = swerve;
    addRequirements(m_swerve);
  }
  
  @Override
  public void execute() {
    m_swerve.move(0, 0.6, 0);
  }
}
