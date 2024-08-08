package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber;

public class JoystickClimber extends Command {
    private Climber m_climber;

    Supplier<Integer> m_power;

    public JoystickClimber(Climber climber, Supplier<Integer> power) {
        m_climber = climber;
        m_power = power;
        addRequirements(m_climber);
    }

    @Override
    public void execute() {
        if(m_power.get() == 180) 
            m_climber.down();
        else if (m_power.get() == 0) 
            m_climber.up();
    }

    @Override
    public void end(boolean interrupted) {
        m_climber.stop();
    }
}
