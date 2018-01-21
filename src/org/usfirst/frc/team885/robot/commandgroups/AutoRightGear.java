package org.usfirst.frc.team885.robot.commandgroups;

import org.usfirst.frc.team885.robot.commands.ChassisDriveDistanceTimeout;
import org.usfirst.frc.team885.robot.commands.ChassisPIDDriveDistance;
import org.usfirst.frc.team885.robot.commands.ChassisTurnAngle;
import org.usfirst.frc.team885.robot.commands.ChassisTurnAngleTimed;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Deposit gear on right peg, back away
 * Working as of 3/25
 */
public class AutoRightGear extends CommandGroup {

	public AutoRightGear() {
		// Drive forward 91 inches
		// Turn anticlockwise 60 degrees
		// Drive forward 30 inches slowly for at most 2 seconds
		// Reverse 32in.
		addSequential(new ChassisDriveDistanceTimeout(91, .625, 8));
		addSequential(new ChassisTurnAngle(60));
		addSequential(new ChassisDriveDistanceTimeout(90, .625, 8));
		addSequential(new ChassisDriveDistanceTimeout(90, -.625, 4));
		addSequential(new ChassisTurnAngle(-30));
		addSequential(new ChassisDriveDistanceTimeout(50, .75, 6));
		addSequential(new ChassisTurnAngle(180));
		addSequential(new ChassisTurnAngle(180));

	}
}
