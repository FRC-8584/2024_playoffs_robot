// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {
  
  public static final double kRobotLength = 1.0;
  public static final double kRobotWidth = 1.0;

  public static class OperatorConstants {
    public static final int kJoystickPort = 0;

    public static final double kMove = 1.0;
    public static final double kTrun = 1.0;
  }

  public static class MotorControllerConstants {
    public static final int IntakeSparkID = 1;
    public static final int ShaftSparkID = 2;
    public static final int UpShooterSparkID = 3;
    public static final int DownShooterSparkID = 4;
    public static final int ClimberSparkID = 9;

    public static final int LF_DriveSparkID = 5;
    public static final int LR_DriveSparkID = 6;
    public static final int RF_DriveSparkID = 7;
    public static final int RR_DriveSparkID = 8;

    public static final int LF_TurnTalonID = 1;
    public static final int LR_TurnTalonID = 2;
    public static final int RF_TurnTalonID = 3;
    public static final int RR_TurnTalonID = 4;
  }

  public static class MotorConstants {
    public static final double kIntakeSpeed = 1.0;
    public static final double kShooterSpeed = 1.0;
    public static final double kShaftSpeed = 0.5;
  }

}

