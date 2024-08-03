package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class JoystickIntake extends Command {
  private Intake m_intake;

  private double m_power;
  
  public JoystickIntake(Intake intake, double power) {
    m_intake = intake; 
    m_power = power;
    addRequirements(m_intake);
  }

  @Override
  public void execute() {
    m_intake.set(m_power);
  }
}
