package org.usfirst.frc.team885.robot.commandgroups;

import org.usfirst.frc.team885.robot.commands.ChassisDriveDistance;
import org.usfirst.frc.team885.robot.commands.ChassisTurnAngleTimed;
import org.usfirst.frc.team885.robot.commands.LowShooterShootSeqTimed;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Deposit gear on right peg and shoot into boiler on right
 * Working as of Southern NH event
 */
public class AutoRightGearRightBoiler extends CommandGroup {

    public AutoRightGearRightBoiler() {
    	// Do right gear auto, bot has reversed so center is 4' from peg
    	// Turn clockwise 21 degrees
    	// Drive backward full speed 90 inches
    	// Spit into low goal for 4 seconds
        addSequential(new AutoRightGear());
        addSequential(new ChassisTurnAngleTimed(21.0, 2.0));
        addSequential(new ChassisDriveDistance(90, -1.0));
        addSequential(new LowShooterShootSeqTimed(4.0));
    }
}
