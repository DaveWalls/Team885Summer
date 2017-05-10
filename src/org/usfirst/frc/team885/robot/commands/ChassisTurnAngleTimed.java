package org.usfirst.frc.team885.robot.commands;

import org.usfirst.frc.team885.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Turn chassis to angle using gyro
 */
public class ChassisTurnAngleTimed extends Command {

	private double degrees;
	
    public ChassisTurnAngleTimed(double degrees, double seconds) {
    	super(seconds);
        requires(Robot.chassis);
        this.degrees = degrees;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.startHeadingPIDReset(degrees);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.pidHeadingOnlyDrive(0.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.chassis.atSetHeading() && this.isTimedOut();
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
