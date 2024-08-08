package frc.robot.commands.autos;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.devices.Sensor;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Transfer;

public class AutoGetNote extends Command {
  private Intake m_intake;
  private Transfer m_transfer;

  public AutoGetNote(Intake intake, Transfer transfer) {
    m_intake = intake;
    m_transfer = transfer;
    addRequirements(m_intake, m_transfer);
  }

  @Override
  public void execute() {
    if (Sensor.isDetected()){
      m_intake.set(0);
      m_transfer.stop();
    }
    else {
      m_intake.in();
      m_transfer.front();
    }
  }

  @Override
  public void end(boolean interrupted) {
    m_intake.stop();
    m_transfer.stop();
  }
  
}
