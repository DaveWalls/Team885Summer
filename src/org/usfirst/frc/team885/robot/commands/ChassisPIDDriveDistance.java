package org.usfirst.frc.team885.robot.commands;

import org.usfirst.frc.team885.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drive a specified distance using encoders and distance PID
 * Accurate to within 2 inches
 */
public class ChassisPIDDriveDistance extends Command {

	private int inches;
    
    public ChassisPIDDriveDistance(int inches) {
    	requires(Robot.chassis);
    	this.inches = inches;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.startDistancePID(inches);
    	Robot.chassis.startHeadingPIDReset(0.0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.chassis.atSetDistance();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.disableHeadingPID();
    	Robot.chassis.disableDistancePID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.chassis.disableHeadingPID();
    	Robot.chassis.disableDistancePID();
    }
}
