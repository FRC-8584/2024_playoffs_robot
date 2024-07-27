// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.GetNote;
import frc.robot.commands.JoystickSwerve;
import frc.robot.devices.Gyro;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shaft;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.Turret;

public class RobotContainer {

  private Swerve swerve = new Swerve();
  private Intake intake = new Intake();
  private Climber climber = new Climber();
  private Shaft shaft = new Shaft();
  private Turret turret = new Turret();

  private Joystick js1 = new Joystick(Constants.OperatorConstants.Player1Port);
  private Joystick js2 = new Joystick(Constants.OperatorConstants.Player2Port);

  public RobotContainer() {
    swerve.setDefaultCommand(new JoystickSwerve(
      swerve, 
      ()->js1.getX(), 
      ()->js1.getY(), 
      ()->js1.getRawAxis(4))
    );

    initialize();
    configureBindings();
  }

  private void configureBindings() {
    new JoystickButton(js1, 1).whileTrue(new GetNote(swerve, intake));
  }

  private void initialize(){
    Gyro.initialize();
  }

  public Command getAutonomousCommand() {
    return new PrintCommand("Auto");
  }
}
