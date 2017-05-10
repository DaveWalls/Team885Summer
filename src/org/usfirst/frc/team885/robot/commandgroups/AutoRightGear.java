package org.usfirst.frc.team885.robot.commandgroups;

import org.usfirst.frc.team885.robot.commands.ChassisDriveDistanceTimeout;
import org.usfirst.frc.team885.robot.commands.ChassisPIDDriveDistance;
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
		addSequential(new ChassisPIDDriveDistance(91));
		addSequential(new ChassisTurnAngleTimed(-60, 2.0));
		addSequential(new ChassisDriveDistanceTimeout(35, 0.25, 2.0));
		addSequential(new ChassisPIDDriveDistance(-32));
	}
}
