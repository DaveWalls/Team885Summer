package org.usfirst.frc.team885.robot.commandgroups;

import org.usfirst.frc.team885.robot.commands.ChassisDriveDistance;
import org.usfirst.frc.team885.robot.commands.ChassisTurnAngleTimed;
import org.usfirst.frc.team885.robot.commands.LowShooterShootSeqTimed;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Deposit gear on left peg, shoot into boiler on left
 * Tested at competition, gear part didn't work, balls are okay
 */
public class AutoLeftGearLeftBoiler extends CommandGroup {

    public AutoLeftGearLeftBoiler() {
    	// Do left gear auto, bot has reversed so center is 4' from peg
    	// Turn anticlockwise 21 degrees
    	// Drive backward full speed 90 inches
    	// Spit into low goal for 4 seconds
        addSequential(new AutoLeftGear());
        addSequential(new ChassisTurnAngleTimed(-21.0, 2.0));
        addSequential(new ChassisDriveDistance(90, -1.0));
        addSequential(new LowShooterShootSeqTimed(4.0));
    }
}