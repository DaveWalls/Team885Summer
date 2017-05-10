package org.usfirst.frc.team885.robot.commands;

import org.usfirst.frc.team885.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 * Drive straight for a set period of time (not tested)
 */
public class ChassisDriveStraightTimed extends TimedCommand {
	
	double speed, time, startHeading;

    public ChassisDriveStraightTimed(double speed, double seconds) {
    	super(seconds);
        requires(Robot.chassis);
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startHeading = Robot.chassis.getHeading();
    	Robot.chassis.startHeadingPID(startHeading);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.pidHeadingOnlyDrive(speed);
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
