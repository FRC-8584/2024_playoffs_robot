package frc.robot.commands.omg;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;

import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Transfer;

public class ControlIntake extends Command {
    private Intake m_intake;
    private Transfer m_transfer;

    public ControlIntake(Intake intake, Transfer transfer) {
        m_intake = intake;
        m_transfer = transfer;
        addRequirements(m_intake, m_transfer);
    }

    @Override
    public void execute() {
        m_intake.set(Constants.MotorConstants.kIntakeSpeed);
        m_transfer.intakeForword();
    }

    @Override
    public void end(boolean interrupted) {
        m_intake.stop();
        m_transfer.stop();
    }
}
