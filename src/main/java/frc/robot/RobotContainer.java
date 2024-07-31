package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;

import edu.wpi.first.wpilibj.Joystick;
// import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.devices.Gyro;

import frc.robot.subsystems.Swerve;
// import frc.robot.subsystems.Intake;
// import frc.robot.subsystems.Shooter;
// import frc.robot.subsystems.Shaft;
// import frc.robot.subsystems.Turret;
// import frc.robot.subsystems.Climber;

import frc.robot.commands.JoystickSwerve;

public class RobotContainer {
  private final Swerve swerve = new Swerve();
  // private final Intake = new Intake();
  // private final Shooter = new Shooter();
  // private final Shaft = new Shaft();
  // private final Turret = new Turret();
  // private final Climber = new Climber();

  private final Joystick js1 = new Joystick(Constants.OperatorConstants.Player1Port);
  // private final Joystick js2 = new Joystick(Constants.OperatorConstants.Player2Port);

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
  }

  private void initialize(){
    Gyro.initialize();
  }

  public Command getAutonomousCommand() {
    return new PrintCommand("Auto");
  }
}
