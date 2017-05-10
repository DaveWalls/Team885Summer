package org.usfirst.frc.team885.robot.commands;

import org.usfirst.frc.team885.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 * Run feeder, shooter and collector for a certain length of time
 * Useful in auto
 */
public class LowShooterShootSeqTimed extends TimedCommand {

    public LowShooterShootSeqTimed(double timeout) {
        super(timeout);
        requires(Robot.lowShooter);
        requires(Robot.collector);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.lowShooter.runFeeder(1.0);
    	Robot.lowShooter.runShooter(1.0);
    	Robot.collector.runIn(1.0);
    }

    // Called once after timeout
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
