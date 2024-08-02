package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.devices.Pixy;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Swerve;

public class GetNote extends Command {
  private Intake m_intake;
  private Swerve m_swerve;

  private double power, x;

  private final double kTurningSpeed = 0.5; 

  public GetNote(Intake intake, Swerve swerve) {
    m_swerve = swerve;
    m_intake = intake;
    addRequirements(m_intake, m_swerve);
  }

  @Override
  public void execute() {
    x = Pixy.getTX();

    if(!Pixy.isDetected() || (Pixy.getTA() < 5)) {
      m_intake.stop();
      return;
    }

    power = Math.abs(x) > 10 ? 1.0 : x/10.0;
    if(x > 0) power *= -1.0;

    power *= kTurningSpeed;
    
    if(Math.abs(x) < 10) m_swerve.move(power, 0, 0.5);
    else m_swerve.move(power, 0, 0);

    return;
  }

  @Override
  public void end(boolean interrupted) {
    m_intake.stop();
  }

}
