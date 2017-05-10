package org.usfirst.frc.team885.robot.commands;

import org.usfirst.frc.team885.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Deposit gear on peg from approximate distance of 2ft (probably not tested)
 */
public class DepositGear extends Command {
	
	Timer timer;
	double timeout = 2.0;

    public DepositGear() {
        requires(Robot.chassis);
        timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.driveStraight(0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.gearCollector.getIsSpeared() || timer.get() >= timeout;
    }

    // Called once after isFinished returns true
    protected void end() {
    	timer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	timer.stop();
    }
}
