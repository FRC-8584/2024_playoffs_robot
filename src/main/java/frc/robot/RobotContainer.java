package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import frc.robot.devices.Gyro;

public class RobotContainer {

  public RobotContainer() {
    initialize();
    configureBindings();
  }

  private void configureBindings() {
  }

  private void initialize(){
    Gyro.initialize();
  }

  public Command getAutonomousCommand() {
    return new PrintCommand("Auto");
  }
}
