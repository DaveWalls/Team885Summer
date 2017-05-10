package org.usfirst.frc.team885.robot.commands;

import org.usfirst.frc.team885.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Drive a specified distance using encoders, abort if timeout is reached
 * Useful in auto when a wall impedes forward motion
 */
public class ChassisDriveDistanceTimeout extends Command {

	private int inches;
	private double speed, timeout;
	private Timer timer;
    
    public ChassisDriveDistanceTimeout(int inches, double speed, double timeout) {
    	requires(Robot.chassis);
    	this.inches = inches;
    	this.speed = speed;
    	this.timeout = timeout;
    	timer = new Timer();
    	speed *= Math.signum(inches);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.resetEncoders();
    	Robot.chassis.startHeadingPIDReset(0.0);
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.pidHeadingOnlyDrive(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Robot.chassis.getAverageDistance()) >= Math.abs(inches) || timer.get() > timeout;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.disableHeadingPID();
    	timer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.chassis.disableHeadingPID();
    	timer.stop();
    }
}
