package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.Command.InterruptionBehavior;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;

/*** subsystems ***/

import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Transfer;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Shaft;

/*** commands ***/

import frc.robot.commands.JoystickSwerve;
import frc.robot.commands.Move;
import frc.robot.commands.WallShoot;
import frc.robot.commands.JoystickIntake;
import frc.robot.Constants.AutoActions;
import frc.robot.commands.GetNote;
import frc.robot.commands.JoystickShooter;
import frc.robot.commands.JoystickShaft;

public class RobotContainer {
  private final Swerve swerve = new Swerve();
  private final Intake intake = new Intake();
  private final Transfer transfer = new Transfer();
  private final Shooter shooter = new Shooter();
  private final Shaft shaft = new Shaft();

  private final Joystick js1 = new Joystick(Constants.OperatorConstants.Player1Port);
  private final Joystick js2 = new Joystick(Constants.OperatorConstants.Player2Port);

  public RobotContainer() {
    player1CommandList();
    player2CommandList();
  }

  private void player1CommandList() {
    // swerve
    swerve.setDefaultCommand(new JoystickSwerve(
      swerve, 
      ()->js1.getX(),
      ()->js1.getY(), 
      ()->js1.getRawAxis(4))
    );

    // get note
    new JoystickButton(js1, 1).whileTrue(new GetNote(intake, transfer).withInterruptBehavior(InterruptionBehavior.kCancelIncoming));

    // shoot note
    new JoystickButton(js1, 2).whileTrue(new WallShoot(shooter, shaft, transfer).withInterruptBehavior(InterruptionBehavior.kCancelIncoming));
  }

  private void player2CommandList() {
    // intake
    intake.setDefaultCommand(new JoystickIntake(intake, ()->js2.getPOV()));

    // transfer
    new JoystickButton(js2, 1).whileTrue(transfer.intakeForword.withInterruptBehavior(InterruptionBehavior.kCancelIncoming));
    new JoystickButton(js2, 3).whileTrue(transfer.intakeReverse.withInterruptBehavior(InterruptionBehavior.kCancelIncoming));
    new JoystickButton(js2, 2).whileTrue(transfer.shooterForword.withInterruptBehavior(InterruptionBehavior.kCancelIncoming));
    new JoystickButton(js2, 4).whileTrue(transfer.shooterReverse.withInterruptBehavior(InterruptionBehavior.kCancelIncoming));

    // shooter
    shooter.setDefaultCommand(new JoystickShooter(shooter, ()->js2.getRawAxis(2), ()->js2.getRawAxis(3)));

    // shaft
    shaft.setDefaultCommand(new JoystickShaft(shaft, ()->js2.getY()));
  }

  // shoot only
  public Command getAutonomousCommand(AutoActions action) {
    switch (action) {

      // shoot only
      case ShootOnly:
        return Commands.sequence(
          new WallShoot(shooter, shaft, transfer).withTimeout(5)
        );

      // none
      default:
        return null;
    }
  }

}
