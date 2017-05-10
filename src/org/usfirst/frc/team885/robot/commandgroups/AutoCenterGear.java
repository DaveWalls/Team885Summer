package org.usfirst.frc.team885.robot.commandgroups;

import org.usfirst.frc.team885.robot.commands.ChassisDriveDistanceTimeout;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Deposit gear on center peg and back away
 * Working as of 3/25
 */
public class AutoCenterGear extends CommandGroup {

    public AutoCenterGear() {
    	// Drive forward 64in fast to approach gear peg
    	// Drive slower until speared
    	// Back off 2ft
    	// Wait for pilot
    	// Recross base line
    	addSequential(new ChassisDriveDistanceTimeout(64, 0.5, 3.0));
    	addSequential(new ChassisDriveDistanceTimeout(28, 0.25, 1.0));	
    	addSequential(new ChassisDriveDistanceTimeout(24, -0.5, 3.0));
    }
}
