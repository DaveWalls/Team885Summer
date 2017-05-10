package org.usfirst.frc.team885.robot.commandgroups;

import org.usfirst.frc.team885.robot.commands.ChassisDriveDistanceTimeout;
import org.usfirst.frc.team885.robot.commands.ChassisPIDDriveDistance;
import org.usfirst.frc.team885.robot.commands.ChassisTurnAngleTimed;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Deposit gear on left peg, back away
 * Tested at competition, didn't work
 */
public class AutoLeftGear extends CommandGroup {

    public AutoLeftGear() {
        // Drive forward 91 inches
    	// Turn clockwise 60 degrees
    	// Drive forward 30 inches slowly for at most 2 seconds
    	// Reverse about 2ft
    	addSequential(new ChassisPIDDriveDistance(91));
    	addSequential(new ChassisTurnAngleTimed(60, 2.0));
    	addSequential(new ChassisDriveDistanceTimeout(35, 0.25, 2.0));
    	addSequential(new ChassisPIDDriveDistance(-32));
    }
}
