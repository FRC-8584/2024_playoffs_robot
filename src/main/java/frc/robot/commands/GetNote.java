package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.devices.Pixy;
import frc.robot.devices.Sensor;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.Transfer;

public class GetNote extends Command {
  private Intake m_intake;
  private Transfer m_transfer;
  private Swerve m_swerve;
  private double timer;

  private Supplier<Double> m_turn;

  public GetNote(Intake intake, 
    Swerve swerve, 
    Transfer transfer, 
    Supplier<Double> turn) 
  {
    timer = 0;
    m_intake = intake;
    m_swerve = swerve;
    m_transfer = transfer;
    m_turn = turn;
    addRequirements(m_intake, m_swerve);
  }

  @Override
  public void execute() {
    if(Sensor.isDetected()) {
      timer ++;
    }
    if(timer <= 2){
      if(Pixy.isDetected() && Pixy.getTA() > 700) {
        m_swerve.move(0, 0.87, Pixy.getTX() / (315 / 2));
        m_transfer.intakeForword();
        m_intake.set(1.0);
      }
      else {
        m_swerve.move(0, 0.87, m_turn.get());
        m_transfer.intakeForword();
        m_intake.set(1.0);
      }
    }
  }

  @Override
  public void end(boolean interrupted) {
    m_intake.set(0);
    m_transfer.stop();
  }
}
