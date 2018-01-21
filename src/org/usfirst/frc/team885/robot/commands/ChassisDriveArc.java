package org.usfirst.frc.team885.robot.commands;

import org.usfirst.frc.team885.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 * Drive chassis along a curve (a circle segment) described by radius
 */
public class ChassisDriveArc extends TimedCommand {
	
	double turnFactor, outsideSpeed, leftSpeed, rightSpeed;

    /**
     * @param distance The distance to drive, in inches
     * @param radius The radius of the circle to turn along, in inches (positive radius is clockwise)
     * @param speed Speed to drive at, 0 to 1
     */
    public ChassisDriveArc(int radius, double outsideSpeed, double timeout) {
    	super(timeout);
        requires(Robot.chassis);
        this.turnFactor = radius / (radius * Robot.chassis.getTrack());
        if(radius > 0) {
        	leftSpeed = outsideSpeed;
        	rightSpeed = turnFactor * outsideSpeed;
        } else if (radius < 0) {
        	rightSpeed = outsideSpeed;
        	leftSpeed = outsideSpeed * turnFactor;
        } else {
        	leftSpeed = rightSpeed = outsideSpeed;
        }
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	Robot.chassis.directDrive(leftSpeed, rightSpeed);
    	Robot.chassis.directDrive(7.9, 8);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
