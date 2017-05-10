package org.usfirst.frc.team885.robot.commandgroups;

import org.usfirst.frc.team885.robot.commands.ChassisDriveDistance;
import org.usfirst.frc.team885.robot.commands.ChassisTurnAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Spin 180 degrees at loading station to prepare for balls or gear
 * untested
 */
public class LoadingStationSpin extends CommandGroup {

    public LoadingStationSpin() {
        // Drive backwards 2ft
    	// Spin 180deg
    	// Drive backwards 2ft
    	addSequential(new ChassisDriveDistance(-24, 1.0));
    	addSequential(new ChassisTurnAngle(180.0));
    	addSequential(new ChassisDriveDistance(-24, 1.0));
    }
}
