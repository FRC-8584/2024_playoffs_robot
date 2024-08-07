package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.devices.Pixy;
import frc.robot.devices.Sensor;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Swerve;
import frc.robot.utils.PID;

public class GetNote extends Command {
  private Intake m_intake;
  private Swerve m_swerve;

  private PID pid = new PID(0.4, 0, 0);

  public GetNote(Intake intake, Swerve swerve) {
    m_intake = intake;
    m_swerve = swerve;
    addRequirements(m_intake, m_swerve);
  }

  @Override
  public void execute() {
    if(Pixy.isDetected()) {
      m_swerve.move(0, 0.4, pid.calculate(Pixy.getTX() / (315 / 2)));
      m_intake.set(1.0);
    }
    else {
      m_swerve.move(0, 0.4, 0);
      m_intake.set(1.0);
    }
  }

  @Override
  public void end(boolean interrupted) {
    
  }

  @Override
  public boolean isFinished() {
    if (Sensor.TransferSensor.isDetected()) return true;
    else return true;
  }
}
