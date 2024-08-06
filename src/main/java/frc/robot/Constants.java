package frc.robot;

public final class Constants {
  public static final double Shooter_SpeakerHight = FieldConstants.SpeakerHight  - MechanicalConstants.ShooterHight; //mm
  public static final double Shooter_AmpHight = FieldConstants.AmpHight - MechanicalConstants.ShooterHight; //mm
  public static final double Shooter_LimelightDiscance = 87; //mm

  public static class MechanicalConstants {
    public static final double RobotLength = 1.0;
    public static final double RobotWidth = 1.0;
    public static final double r = Math.sqrt(Math.pow(RobotLength ,2) + Math.pow(RobotWidth ,2));
    public static final double ShooterHight = 300; //mm

    public static final double ShaftInitAngle = 0;
    public static final double ShaftMinAngle = 10;
    public static final double ShaftMaxAngle = 90;
    public static final double ShaftAngleRange = ShaftMaxAngle - ShaftMinAngle;
  }

  public static class FieldConstants {
    public static final double SpeakerHight = 2000; //mm
    public static final double SpeakerTagHight = 9487; //mm
    public static final double AmpHight = 2000; //mm
    public static final double AmpTagHight = 2000; //mm
  }

  public static class OperatorConstants {
    public static final int Player1Port = 0;
    public static final int Player2Port = 1;

    public static final double kMove = 0.5;
    public static final double kTrun = 0.5;

    public static final double LimelightAngle = 9487; //degrees
    public static final double LimelightHight = 9487; //mm 

    public static final double OriginRobotHeading = 0; //degrees
    public static final double DriverHeading = 0; //degrees

    public static final double MaxShootSpeakerDistance   = 3000; //mm
    public static final double MaxShootSpeakerYawDegrees = 60; //degrees
    public static final double MaxShootAmpYawDegrees = 15; //degrees

    public static final double ShootAmpPitchDegrees = 45; //degrees
    public static final double ShootAmpPwr = 0.5;
  }

  //motor controller ID
  public static class MotorControllerID {
    public static final int LF_TurnID         = 1;
    public static final int LR_TurnID         = 4;
    public static final int RF_TurnID         = 2;
    public static final int RR_TurnID         = 3;

    public static final int LF_DriveID        = 5;
    public static final int LR_DriveID        = 8;
    public static final int RF_DriveID        = 6;
    public static final int RR_DriveID        = 7;

    public static final int IntakeID          = 9;
    public static final int TopTransferID     = 10;
    public static final int BottomTransferID  = 11;
    public static final int TopShooterID      = 12;
    public static final int BottomShooterID   = 13;

    public static final int LShaftID          = 14;
    public static final int RShaftID          = 15;
    public static final int TurretID          = 16;
    public static final int ClimberID         = 17;
  }

  //motor speed constants
  public static class MotorConstants {
    public static final double kIntakeSpeed = 1.0;
    public static final double kTopTransferSpeed = 1.0;
    public static final double kBottomTransferSpeed = 1.0;
    public static final double kShooterSpeed = 1.0;
    public static final double kShaftSpeed = 0.5;
    public static final double kTurretSpeed = 0.5;
  }

  public static class SensorConstants {
    public static final int kIntakeInputPort = 0;
    public static final int kIntakeOutputPort = 1;
    public static final int kTransferInputPort = 2;
    public static final int kTransferOutputPort = 3;
  }
}
