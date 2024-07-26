package frc.robot.modules;

import com.revrobotics.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.utils.PID;
import frc.robot.utils.Tools;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.*;

public class SwerveModule {
	public final TalonSRX turningMotor;
	public final CANSparkMax driveMotor;

	public String name = "";

	private PID pid;

	private double turnValue;
	private double lastAngle;

	/**********functions**********/

	//init
	public SwerveModule(int turningMotorID, int driveMotorID){
		turningMotor = new TalonSRX(turningMotorID);
		driveMotor = new CANSparkMax(driveMotorID, CANSparkLowLevel.MotorType.kBrushed);
		turningMotor.configSelectedFeedbackSensor(FeedbackDevice.Analog);
	}

	//set motor power
	public void setpoint(double speed, double angle){
		double error = angle - turnValue;//SP - PV 

		if(error > 180) error -= 360;
		else if(error < -180) error += 360;

		if(-0.25 < error && error < 0.25){
			pid.resetIntergral();
			error = 0;
		}

		final double turnPower = 
			Tools.bounding(pid.calculate(Tools.bounding(error / 45.0, -1, 1)), -1, 1);

		final double drivePower = 
			speed * Tools.bounding(Math.abs(error / -90.0 + 1.5) * 0.5, 0, 1);

		turningMotor.set(TalonSRXControlMode.PercentOutput, -turnPower);
		driveMotor.set(drivePower);

		lastAngle = angle;
	}

	public void coastMove(){
		double error = lastAngle - turnValue;//SP - PV 

		if(error > 180) error -= 360;
		else if(error < -180) error += 360;

		if(-0.25 < error && error < 0.25){
			pid.resetIntergral();
			error = 0;
		}

		final double turnPower = 
			Tools.bounding(pid.calculate(Tools.bounding(error / 45.0, -1, 1)), -1, 1);

		turningMotor.set(TalonSRXControlMode.PercentOutput, -turnPower);
		driveMotor.set(0);
	}

	public void setPID(double kP, double kI, double kD) {
		pid = new PID(kP, kI, kD);
		pid.resetIntergral();
	}

	public void setName(String name) {
		this.name = name;
	}

	//get encoder value
	public void getEncValue(){
		turnValue = -(double)((int)turningMotor.getSelectedSensorPosition() & 0x03ff) * 0.3515625;

		if(turnValue < 0) turnValue += 360;
		if(turnValue >= 360) turnValue -= 360;

		SmartDashboard.putNumber(name, turnValue);
	}

}