package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class JoystickIntake extends Command {
    private Intake m_intake;
    private Supplier<Integer> m_pov;

    public JoystickIntake(Intake intake, Supplier<Integer> pov) {
        m_intake = intake;
        m_pov = pov;
        addRequirements(m_intake);
    }

    @Override
    public void execute() {
        if(m_pov.get() == 180) 
            m_intake.reverse();
        else if(m_pov.get() == 0)
            m_intake.forword();
        else
            m_intake.stop();
    }

    @Override
    public void end(boolean interrupted) {
        m_intake.stop();
    }
}
