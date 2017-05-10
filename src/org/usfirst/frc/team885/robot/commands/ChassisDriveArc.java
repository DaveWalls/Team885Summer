package org.usfirst.frc.team885.robot.commands;

import org.usfirst.frc.team885.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drive chassis along a curve (a circle segment) described by radius and arc length (not tested)
 */
public class ChassisDriveArc extends Command {
	
	private int distance; // Distance to drive, in inches
	private double turnFactor; // used for internal calculations
	private double startHeading;

    /**
     * @param distance The distance to drive, in inches
     * @param radius The radius of the circle to turn along, in inches
     * @param speed Speed to drive at, 0 to 1
     */
    public ChassisDriveArc(int distance, double radius) {
        requires(Robot.chassis);
        this.distance = distance;
        this.turnFactor = 180.0 / Math.PI / radius;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startHeading = Robot.chassis.getHeading();
    	Robot.chassis.startDistancePID(distance);
    	Robot.chassis.startHeadingPID(startHeading);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Repeatedly adjust heading
    	Robot.chassis.setHeading(startHeading + Robot.chassis.getAverageDistance() * turnFactor);
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
