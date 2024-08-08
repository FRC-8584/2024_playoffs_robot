package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.devices.Pixy;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.Transfer;
import frc.robot.utils.PID;

public class GetNote extends Command {
  private Intake m_intake;
  private Transfer m_transfer;
  private Swerve m_swerve;

  private Supplier<Double> m_x;
  private Supplier<Double> m_y;
  private Supplier<Double> m_turn;

  private PID pid = new PID(0.4, 0, 0);

  public GetNote(Intake intake, 
    Swerve swerve, 
    Transfer transfer, 
    Supplier<Double> x, 
    Supplier<Double> y, 
    Supplier<Double> turn) 
  {
    m_intake = intake;
    m_swerve = swerve;
    m_transfer = transfer;
    m_x = x;
    m_y = y;
    m_turn = turn;
    addRequirements(m_intake, m_swerve);
  }

  @Override
  public void execute() {
    if(Pixy.isDetected()) {
      m_swerve.move(0, 0.5, pid.calculate(Pixy.getTX() / (315 / 2)));
      m_transfer.front();
      m_intake.set(1.0);
    }
    else {
      m_swerve.move(m_x.get(), m_y.get(), m_turn.get());
      m_transfer.front();
      m_intake.set(1.0);
    }
  }

  @Override
  public void end(boolean interrupted) {
    m_intake.set(0);
    m_transfer.stop();
  }
}
