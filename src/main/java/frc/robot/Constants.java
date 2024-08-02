package frc.robot;

public final class Constants {
  public static final double Shooter_SpeakerHight = MechanicalConstants.ShooterHight - FieldConstants.SpeakerHight;//mm


  public static final double Shooter_SpeakerHight      = ShooterHight - OperatorConstants.SpeakerHight;//mm
  public static final double Shooter_Limelight         = 150; //mm    
                         
  public static final double MaxShootSpeakerDistance   = 3000;//mm  
  public static final double MaxShootSpeakerYawDegrees = 60;  //degrees

  public static final double MaxShootSpeakerDistance = 3000;//mm
  public static final double MaxShootSpeakerYawDegrees = 60;//degrees


  public static class MechanicalConstants {
    public static final double RobotLength = 1.0;
    public static final double RobotWidth = 1.0;
    public static final double r = Math.sqrt(Math.pow(RobotLength ,2) + Math.pow(RobotWidth ,2));
    public static final double ShooterHight = 300;//mm
  }

  public static class FieldConstants {
    public static final double SpeakerHight = 1980;//mm
  }

  public static class OperatorConstants {
    public static final int Player1Port = 0;
    public static final int Player2Port = 1;

    public static final double kMove = 1;
    public static final double kTrun = 1;


    public static final double SpeakerHight = 9487;//mm
    public static final double TagHight = 0;   //mm
    public static final double LimelightAngle = 30;  //degrees
    public static final double LimelightHight = 150; //mm 

    public static final double OriginRobotHeading = 0;
    public static final double DriverHeading = 0;

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
    public static final int kInrakeInputPort = 0;
    public static final int kInrakeOutputPort = 1;
    public static final int kTransferInputPort = 2;
    public static final int kTransferOutputPort = 3;
  }
}
