package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shaft;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transfer;
import frc.robot.utils.Tools;
import frc.robot.Constants;
import frc.robot.devices.LimeLight;

public class ShootSpeaker extends Command {
  private Shooter m_shooter;
  private Transfer m_transfer;
  private Shaft m_shaft;

  private double pitch;
  private double robotDistance, robotYaw;

  private double angle;

  public ShootSpeaker(Shooter shooter, Transfer transfer, Shaft shaft) {
    m_shooter = shooter;
    m_transfer = transfer;
    m_shaft = shaft;

    addRequirements(m_shooter, m_transfer, m_shaft);
  }

  @Override
  public void execute() {
    //get value
    robotDistance = LimeLight.getSpeakerDistance();
    robotYaw = LimeLight.getSpeakerYawDegrees();

    //1. Is the robot's position able to shoot note into speaker?
    if(robotDistance > Constants.OperatorConstants.MaxShootSpeakerDistance){//distance
      m_shooter.shoot(0);
      m_transfer.stop();

      return;
    }
    if(robotYaw > Constants.OperatorConstants.MaxShootSpeakerYawDegrees && robotYaw < 360 - Constants.OperatorConstants.MaxShootSpeakerYawDegrees){//yaw
      m_shooter.shoot(0);
      m_transfer.stop();

      return;
    }

    //get value
    angle = Tools.toDegrees(Constants.Shooter_SpeakerHight, robotDistance);
    pitch = m_shaft.getShaftAngle()[0] - angle;

    //2. Is the shooter's direction able to shoot note into speaker?
    if(pitch > 3 || pitch < -3){//pitch
      m_shaft.setPosition(angle);
      m_shooter.shoot(Constants.MechanicalConstants.ShaftAngleRange / Math.abs(pitch));
      m_transfer.stop();
      
      return;
    }

    //get ready to shoot!
    m_shooter.shoot(1);
    m_transfer.front();
  }

  @Override
  public void end(boolean interrupted) {
    m_shooter.shoot(0);
  }

  @Override
  public boolean isFinished() {
    if(!LimeLight.isDetectedSpeaker()){
      return true;
    }

    else return false;
  }
}
