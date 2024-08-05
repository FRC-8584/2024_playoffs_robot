package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class JoystickIntake extends Command {
  private Intake m_intake;

  private Supplier<Double> m_power;
  
  public JoystickIntake(Intake intake, Supplier<Double> power) {
    m_intake = intake; 
    m_power = power;
    addRequirements(m_intake);
  }

  @Override
  public void execute() {
    m_intake.set(m_power.get());
  }
  
  @Override
  public void end(boolean interrupted) {
    m_intake.set(0);
  }
}
