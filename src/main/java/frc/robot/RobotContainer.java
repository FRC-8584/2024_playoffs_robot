package frc.robot;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;

import frc.robot.subsystems.*;
import frc.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.devices.Gyro;

public class RobotContainer {
  private final Swerve swerve = new Swerve();

  private final Joystick driver1 = new Joystick(Constants.OperatorConstants.Player1Port);

  public RobotContainer() {
    swerve.setDefaultCommand(new JoystickSwerve(
      swerve, 
      () -> driver1.getX(), 
      () -> -driver1.getY(), 
      () -> driver1.getRawAxis(4)));
    
    initialize();
    configureBindings();
  }

  private void configureBindings() {}

  private void initialize(){
    Gyro.initialize();
  }

  public Command getAutonomousCommand() {
    return new PrintCommand("Auto");
  }
}
