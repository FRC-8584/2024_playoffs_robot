package frc.robot;

public final class Constants {
  
  public static final double kRobotLength = 1.0;
  public static final double kRobotWidth = 1.0;

  public static class OperatorConstants {
    public static final int Player1Port = 0;

    public static final double kMove = 1.0;
    public static final double kTrun = 1.0;
    public static final double originRobotHeading = 0;
  }

  public static class MotorControllerID {
    public static final int IntakeID = 1;
    public static final int ShaftID = 2;
    public static final int TopShooterID = 3;
    public static final int BottomShooterID = 4;
    public static final int ClimberID = 9;

    public static final int LF_DriveID = 5;
    public static final int LR_DriveID = 6;
    public static final int RF_DriveID = 7;
    public static final int RR_DriveID = 8;

    public static final int LF_TurnID = 1;
    public static final int LR_TurnID = 2;
    public static final int RF_TurnID = 3;
    public static final int RR_TurnID = 4;
  }

  public static class MotorConstants {
    public static final double kIntakeSpeed = 1.0;
    public static final double kShooterSpeed = 1.0;
    public static final double kShaftSpeed = 0.5;
  }

}

