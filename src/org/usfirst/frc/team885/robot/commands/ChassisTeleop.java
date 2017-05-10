package org.usfirst.frc.team885.robot.commands;

import org.usfirst.frc.team885.robot.OI;
import org.usfirst.frc.team885.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drive chassis using a joystick
 */
public class ChassisTeleop extends Command {

    public ChassisTeleop() {
        requires(Robot.chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.resetHeading();
    	Robot.chassis.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.teleopDrive(OI.driverStick);
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
