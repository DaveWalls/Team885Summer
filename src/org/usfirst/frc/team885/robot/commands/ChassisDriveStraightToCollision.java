package org.usfirst.frc.team885.robot.commands;

import org.usfirst.frc.team885.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drive until robot has collided (not tested)
 */
public class ChassisDriveStraightToCollision extends Command {
	
	private double aheadSpeed;

    public ChassisDriveStraightToCollision(double aheadSpeed) {
        requires(Robot.chassis);
        this.aheadSpeed = aheadSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.startHeadingPIDReset(0.0);
    	Robot.chassis.resetCollisionDetection();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.pidHeadingOnlyDrive(aheadSpeed);
    	Robot.chassis.updateCollisions();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.chassis.hasCollided;
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
