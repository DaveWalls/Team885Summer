package org.usfirst.frc.team885.robot.subsystems;

import org.usfirst.frc.team885.robot.RobotMap;
import org.usfirst.frc.team885.robot.commands.LowShooterStopped;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Shoots into low boiler goal
 */
public class LowShooter extends Subsystem {
	
	private VictorSP shooterMotor = new VictorSP(RobotMap.PWM.lowShooter);
	private VictorSP feederMotor = new VictorSP(RobotMap.PWM.lowFeeder);
	
	public LowShooter() {
		feederMotor.setInverted(true);
	}
	
	public void runShooter(double speed) {
		shooterMotor.set(speed);
	}
	
	public void runFeeder(double speed) {
		feederMotor.set(speed);
	}
	
	public void updateDashboard() {
    	SmartDashboard.putNumber("LS Shooter Motor", shooterMotor.get());
    	SmartDashboard.putNumber("LS Feeder Motor", feederMotor.get());
    	SmartDashboard.putData(this);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new LowShooterStopped());
    }
}

