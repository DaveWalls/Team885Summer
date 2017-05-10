package org.usfirst.frc.team885.robot.commands;

import org.usfirst.frc.team885.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 * Drive left and right motors at set speeds for a set time (possibly useful for turning in auto)
 */
public class ChassisDriveDirectTimed extends TimedCommand {
	
	double leftSpeed, rightSpeed;

    public ChassisDriveDirectTimed(double leftSpeed, double rightSpeed, double timeout) {
    	super(timeout);
        requires(Robot.chassis);
        this.leftSpeed = leftSpeed;
        this.rightSpeed = rightSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.directDrive(leftSpeed, rightSpeed);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
