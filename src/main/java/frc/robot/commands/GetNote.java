package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Swerve;

public class GetNote extends Command {
  private Swerve m_swerve;
  private Intake m_intake;

  public GetNote(
    Swerve swerve,
    Intake intake
  ) 
  {
    m_intake = intake;
    m_swerve = swerve;
    addRequirements(m_intake, m_swerve);
  }

  @Override
  public void execute() {
    m_intake.in();
    m_swerve.move(0, 0.5, 0);
  }
  
}
