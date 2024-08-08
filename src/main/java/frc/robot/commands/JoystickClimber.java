package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber;

public class JoystickClimber extends Command {

  public Climber m_climber;

  public JoystickClimber(Climber climber) {
    m_climber = climber;
    addRequirements(m_climber);
  }

  @Override
  public boolean runsWhenDisabled() {
    return true;
  }

  @Override
    pubic void execute()
    {
        // Do what you need to do ..
    }
}
