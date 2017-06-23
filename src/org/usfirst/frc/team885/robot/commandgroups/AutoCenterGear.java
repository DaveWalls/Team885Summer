package org.usfirst.frc.team885.robot.commandgroups;

import org.usfirst.frc.team885.robot.commands.ChassisDriveDistanceTimeout;
import org.usfirst.frc.team885.robot.commands.ChassisDriveTimed;
import org.usfirst.frc.team885.robot.commands.ChassisDriveToPeg;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Deposit gear on center peg and back away
 */
public class AutoCenterGear extends CommandGroup {

    public AutoCenterGear() {
    	addSequential(new ChassisDriveToPeg(5.0, 0.8)); // 5.0 seconds max, 0.5 speed
    	addSequential(new WaitCommand(1.0)); // wait 1.0 seconds
    	addSequential(new ChassisDriveTimed(1.0, -0.8)); // 1.0 seconds, -0.5 speed
    }
}
