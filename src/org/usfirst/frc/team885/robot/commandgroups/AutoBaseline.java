package org.usfirst.frc.team885.robot.commandgroups;

import org.usfirst.frc.team885.robot.commands.ChassisDriveDistance;
import org.usfirst.frc.team885.robot.commands.ChassisDriveTimed;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Cross baseline
 * Working as of 2/21
 */
public class AutoBaseline extends CommandGroup {

    public AutoBaseline() {
        // Drive 9ft
    	addSequential(new ChassisDriveDistance(108, 1.0));
//    	addSequential(new ChassisDriveTimed(2.0, 1.0));
    }
}
