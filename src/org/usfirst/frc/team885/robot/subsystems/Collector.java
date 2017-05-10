package org.usfirst.frc.team885.robot.subsystems;

import org.usfirst.frc.team885.robot.RobotMap;
import org.usfirst.frc.team885.robot.commands.CollectorRun;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Collects fuel from floor
 */
public class Collector extends Subsystem {
	
	private VictorSP motor = new VictorSP(RobotMap.PWM.collector);
	
	public boolean runningIn = false;
	public boolean runningOut = false;
	
	public void runIn(double speed) {
		motor.set(speed);
		runningIn = true;
		runningOut = false;
	}
	
	public void runOut(double speed) {
		motor.set(-speed);
		runningOut = true;
		runningIn = false;
	}
	
	public void stop() {
		motor.set(0);
		runningIn = false;
		runningOut = false;
	}
	
	

    public void initDefaultCommand() {
        setDefaultCommand(new CollectorRun(0.0));
    }
    
    public void updateDashboard() {
    	SmartDashboard.putNumber("Collector motor", motor.get());
    	SmartDashboard.putData(this);
    }
}

