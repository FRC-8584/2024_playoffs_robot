package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Shaft;
import frc.robot.subsystems.Shooter;

import frc.robot.subsystems.Transfer;

public class WallShoot extends Command {
    private Shooter m_shooter;
    private Shaft m_Shaft;
    private Transfer m_transfer;

    public WallShoot(Shooter shooter, Shaft shaft, Transfer transfer) {
        m_shooter = shooter;
        m_Shaft = shaft;
        m_transfer = transfer;
        addRequirements(m_shooter, m_Shaft, m_transfer);
    }

    @Override
    public void execute() {
        double pitch = m_Shaft.getShaftAngle()[0] - 80;

        //3. Is the shooter's direction able to shoot note into speaker?
        if(pitch > 3 || pitch < -3){//pitch
            m_Shaft.setPosition(75);
            m_shooter.shoot(Constants.MechanicalConstants.ShaftAngleRange / Math.abs(pitch));
            m_transfer.stop();
      
            return;
        }

    //get ready to shoot!
    m_shooter.shoot(1);
    m_transfer.shooterForword();
    }

    @Override
    public  void end(boolean interrupted) {
        m_Shaft.setPower(0);
        m_transfer.stop();
        m_shooter.shoot(0);
    }
}
