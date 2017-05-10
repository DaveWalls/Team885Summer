package org.usfirst.frc.team885.robot.commands;

import org.usfirst.frc.team885.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Run low shooter, feeder, and collector to feed balls through system
 */
public class LowShooterShootSequence extends Command {

	private double shooterSpeed, feederSpeed, collectorSpeed;

    public LowShooterShootSequence(double shooterSpeed, double feederSpeed, double collectorSpeed) {
        requires(Robot.lowShooter);
        this.shooterSpeed = shooterSpeed;
        this.feederSpeed = feederSpeed;
        this.collectorSpeed = collectorSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.lowShooter.runFeeder(shooterSpeed);
    	Robot.lowShooter.runShooter(feederSpeed);
    	Robot.collector.runIn(collectorSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
