package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class JoystickShooter extends Command {
  private Shooter m_shooter;

  private Supplier<Double> m_inPower;
  private Supplier<Double> m_outPower;
  
  public JoystickShooter(Shooter shooter, Supplier<Double> inPower, Supplier<Double> outPower) {
    m_shooter = shooter;
    m_inPower = inPower;
    m_outPower = outPower;
    addRequirements(m_shooter);
  }

  @Override
  public void execute() {
    m_shooter.shoot(m_inPower.get() - m_outPower.get());
  }

  @Override
  public void end(boolean interrupted) {
    m_shooter.shoot(0);
  }
}
