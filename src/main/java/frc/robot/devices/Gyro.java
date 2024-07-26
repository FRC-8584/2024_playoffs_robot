package frc.robot.devices;

import edu.wpi.first.wpilibj.I2C;
import frc.robot.utils.BNO055;

public class Gyro {
  private static BNO055 gyro;

  public static void initialize() {
    gyro = BNO055.getInstance(
      BNO055.opmode_t.OPERATION_MODE_NDOF_FMC_OFF, BNO055.vector_type_t.VECTOR_EULER,
      I2C.Port.kMXP, BNO055.BNO055_ADDRESS_A
    );
  }

  public static double getVector(){
    return gyro.getVector()[0];
  }

  public static boolean isInitialized(){
    return gyro.isInitialized();
  }
}