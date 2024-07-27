package frc.robot.modules;

import com.revrobotics.*;

import frc.robot.utils.PID;
import frc.robot.utils.Tools;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.*;

public class SwerveModule {
	public final TalonSRX turningMotor;
	public final CANSparkMax driveMotor;

	private String name = "";
	private PID pid;

	private double turnValue;

	/**********functions**********/

	public SwerveModule(int turningMotorID, int driveMotorID, PID pid){
		turningMotor = new TalonSRX(turningMotorID);
		driveMotor = new CANSparkMax(driveMotorID, CANSparkLowLevel.MotorType.kBrushed);
		this.pid = pid;

		turningMotor.configSelectedFeedbackSensor(FeedbackDevice.Analog);
	}

	/*** motor ***/

	public void setpoint(double speed, double angle) {
		getEncValue();
		double error = angle - turnValue;//SP - PV 

		error = error > 180 ? error - 360 : error;
		error = error < -180 ? error + 360 : error;

		if(-3 < error && error < 3){
			pid.resetIntergral();
			error = 0;
		}
		error /= 180.0;

		final double turnPower = Tools.bounding(pid.calculate(error), -1, 1);
		final double drivePower = speed * (1 - Math.abs(error));

		turningMotor.set(TalonSRXControlMode.PercentOutput, -turnPower);
		driveMotor.set(drivePower);
	}

	public void stop() {
		turningMotor.set(TalonSRXControlMode.PercentOutput, 0);
		driveMotor.set(0);
	}

	/*** encoder ***/

	public void getEncValue() {
		double turnValue = -((int)turningMotor.getSelectedSensorPosition() & 0x03ff) * 0.3515625;

		turnValue = turnValue < 0 ? turnValue + 360 : turnValue;
		turnValue = turnValue >= 360 ? turnValue - 360 : turnValue;

		this.turnValue = turnValue;
	}

	/*** PID ***/

	public void setPID(double kP, double kI, double kD) {
		pid = new PID(kP, kI, kD);
		pid.resetIntergral();
	}

	/*** name ***/

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}



}