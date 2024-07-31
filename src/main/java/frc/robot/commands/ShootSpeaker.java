package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shaft;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transfer;
import frc.robot.subsystems.Turret;

public class ShootSpeaker extends Command {
  private Shooter m_shooter;
  private Transfer m_transfer;
  private Shaft m_shaft;
  private Turret m_turret;

  public ShootSpeaker() {}

  @Override
  public void execute() {
    m_shooter.shoot(1);
    m_transfer.topMotorShoot();
    m_transfer.bottomMotorShoot();
  }

  @Override
  public void end(boolean interrupted) {}
}
