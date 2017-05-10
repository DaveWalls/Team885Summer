package org.usfirst.frc.team885.robot.commands;

import org.usfirst.frc.team885.robot.OI;
import org.usfirst.frc.team885.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drive chassis in a straight line with joystick input
 */
public class ChassisTeleopStraight extends Command {

    public ChassisTeleopStraight() {
        requires(Robot.chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.startHeadingPIDReset(0.0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.pidHeadingOnlyDrive(Robot.chassis.forward * OI.driverStick.getY());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
