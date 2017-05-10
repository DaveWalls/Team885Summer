package org.usfirst.frc.team885.robot.commands;

import org.usfirst.frc.team885.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	Back up from gear peg and decide what to do based on if the gear has been ejected
 *	untested
 */
public class ReverseFromPeg extends Command {

    public ReverseFromPeg() {
        requires(Robot.chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.driveStraight(-0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Robot.chassis.getAverageDistance()) >= 24;
    }

    // Called once after isFinished returns true
    protected void end() {
    	if (Robot.gearCollector.getIsStowed()) {
    		new DepositGear().start();
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
