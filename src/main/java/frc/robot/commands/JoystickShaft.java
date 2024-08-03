package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shaft;

public class JoystickShaft extends Command {
  private Shaft m_shaft;
  private double m_power;

  public JoystickShaft(Shaft shaft, double power) {
    m_shaft = shaft;
    m_power = power;
    addRequirements(m_shaft);
  }

  @Override
  public void execute() {
    m_shaft.setPower(m_power);
  }

  @Override
  public void end(boolean interrupted) {
    m_shaft.setPower(0);
  }
}