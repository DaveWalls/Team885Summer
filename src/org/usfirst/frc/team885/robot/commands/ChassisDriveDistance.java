package org.usfirst.frc.team885.robot.commands;

import org.usfirst.frc.team885.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drive a specified distance using encoders
 * May overshoot distance! Use ChassisPIDDriveDistance when distance accuracy is critical
 */
public class ChassisDriveDistance extends Command {

	private int inches;
	private double speed;
    
    public ChassisDriveDistance(int inches, double speed) {
    	requires(Robot.chassis);
    	this.inches = inches;
    	this.speed = speed;
    	speed *= Math.signum(inches);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.resetEncoders();
    	Robot.chassis.startHeadingPIDReset(0.0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.pidHeadingOnlyDrive(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Robot.chassis.getAverageDistance()) >= Math.abs(inches);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.disableHeadingPID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.chassis.disableHeadingPID();
    }
}
