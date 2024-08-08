package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transfer;

public class shoot extends Command {
  private Shooter m_shooter;
  private Transfer m_transfer;

  private Supplier<Double> m_Power;
  
  public shoot(Shooter shooter, Transfer transfer, Supplier<Double> Power) {
    m_shooter = shooter;
    m_transfer = transfer;
    m_Power = Power;
    addRequirements(m_shooter, m_transfer);
  }

  @Override
  public void execute() {
    m_shooter.shoot(m_Power.get());
    m_transfer.shooterOut();
  }

  @Override
  public void end(boolean interrupted) {
    m_shooter.shoot(0);
    m_transfer.stop();
  }
}
