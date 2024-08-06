package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shaft;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transfer;
import frc.robot.subsystems.Turret;
import frc.robot.utils.Tools;
import frc.robot.Constants;
import frc.robot.devices.LimeLight;

public class ShootSpeaker extends Command {
  private Shooter m_shooter;
  private Transfer m_transfer;
  private Shaft m_shaft;
  private Turret m_turret;

  private double shooterPitch, shooterYaw;
  private double robotDistance, robotYaw;

  private double angle;

  public ShootSpeaker(Shooter shooter, Transfer transfer, Shaft shaft, Turret turret) {
    m_shooter = shooter;
    m_transfer = transfer;
    m_shaft = shaft;
    m_turret = turret;

    addRequirements(m_shooter, m_transfer, m_shaft, m_turret);
  }

  @Override
  public void execute() {
    //get value
    robotDistance = LimeLight.getSpeakerDistance();
    robotYaw = LimeLight.getSpeakerYawDegrees();

    //1. Is the robot's position able to shoot note into speaker?
    if(robotDistance > Constants.OperatorConstants.MaxShootSpeakerDistance){//distance
      m_shooter.shoot(0);
      return;
    }
    if(robotYaw > Constants.OperatorConstants.MaxShootSpeakerYawDegrees && robotYaw < 360 - Constants.OperatorConstants.MaxShootSpeakerYawDegrees){//yaw
      m_shooter.shoot(0);
      return;
    }

    //get value
    angle = Tools.toDegrees(Constants.Shooter_SpeakerHight, robotDistance);
    shooterPitch = m_shaft.getEncValue()[0] - angle;
    shooterYaw = m_turret.getEncValue();

    //2. Is the shooter's direction able to shoot note into speaker?
    if(shooterPitch > 3 || shooterPitch < -3){//pitch
      m_shaft.setPosition(angle);
      m_shooter.shoot(0);
      return;
    }
    if(shooterYaw > 3 || shooterYaw < -3){//yaw
      m_turret.setPosition(0);
      m_shooter.shoot(0);
      return;
    }

    //get ready to shoot!
    m_shooter.shoot(1);
    m_transfer.topMotorShoot();
    m_transfer.bottomMotorShoot();
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
