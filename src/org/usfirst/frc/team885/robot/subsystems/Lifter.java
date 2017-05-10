package org.usfirst.frc.team885.robot.subsystems;

import org.usfirst.frc.team885.robot.RobotMap;
import org.usfirst.frc.team885.robot.commands.LifterRun;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Device to scale rope
 */
public class Lifter extends Subsystem {

    private SpeedController motor = new VictorSP(RobotMap.PWM.lifter);
    
    public Lifter() {
    	motor.setInverted(true);
    }
    
    public void run(double speed) {
    	motor.set(speed);
    }
    
    public void updateDashboard() {
    	SmartDashboard.putNumber("Lifter motor", motor.get());
    	SmartDashboard.putData(this);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new LifterRun(0.0));
    }
}

