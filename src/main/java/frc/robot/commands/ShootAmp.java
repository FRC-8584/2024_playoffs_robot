package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shaft;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.Transfer;
import frc.robot.subsystems.Turret;
import frc.robot.Constants;
import frc.robot.devices.LimeLight;

public class ShootAmp extends Command {
  private Shooter m_shooter;
  private Transfer m_transfer;
  private Shaft m_shaft;
  private Turret m_turret;
  private Swerve m_swerve;

  private double shooterPitch, shooterYaw;
  private double robotYaw;

  public ShootAmp(Shooter shooter, Transfer transfer, Shaft shaft, Turret turret) {
    m_shooter = shooter;
    m_transfer = transfer;
    m_shaft = shaft;
    m_turret = turret;

    addRequirements(m_shooter, m_transfer, m_shaft, m_turret);
  }

  @Override
  public void execute() {
    //1. Is the limelight detected speaker's tag?
    if(!LimeLight.isDetectedSpeaker()){
      m_shooter.shoot(0);
      return;
    }

    //get value
    robotYaw = LimeLight.getAmpYawDegrees();

    //2. Is the robot's position able to shoot note into speaker?
    if(robotYaw > Constants.OperatorConstants.MaxShootAmpYawDegrees){//yaw
      m_shooter.shoot(0);
      m_swerve.move(-0.3, 0, 0);
      return;
    }
    if(robotYaw < 360 - Constants.OperatorConstants.MaxShootAmpYawDegrees){//yaw
      m_shooter.shoot(0);
      m_swerve.move(0.3, 0, 0);
      return;
    }


    //get value
    shooterPitch = m_shaft.getEncValue()[0] - Constants.OperatorConstants.ShootAmpPitchDegrees;
    shooterYaw = m_turret.getEncValue();

    //3. Is the shooter's direction able to shoot note into speaker?
    if(shooterPitch > 3 || shooterPitch < -3){//pitch
      m_shaft.setPosition(Constants.OperatorConstants.ShootAmpPitchDegrees);
      m_shooter.shoot(0);
      return;
    }
    if(shooterYaw > 3 || shooterYaw < -3){//yaw
      m_turret.setPosition(0);
      m_shooter.shoot(0);
      return;
    }

    //get ready to shoot!
    m_shooter.shoot(Constants.OperatorConstants.ShootAmpPwr);
    m_transfer.topMotorShoot();
    m_transfer.bottomMotorShoot();
  }

  @Override
  public void end(boolean interrupted) {
    m_shooter.shoot(0);
  }
}
