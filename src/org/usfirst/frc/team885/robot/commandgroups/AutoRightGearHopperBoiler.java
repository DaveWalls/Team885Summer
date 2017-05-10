package org.usfirst.frc.team885.robot.commandgroups;

import org.usfirst.frc.team885.robot.commands.ChassisDriveDistance;
import org.usfirst.frc.team885.robot.commands.ChassisPIDDriveDistance;
import org.usfirst.frc.team885.robot.commands.ChassisTurnAngle;
import org.usfirst.frc.team885.robot.commands.LowShooterShootSeqTimed;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Deposit gear on right peg, collect from near hopper, shoot into boiler on right
 * untested
 */
public class AutoRightGearHopperBoiler extends CommandGroup {

    public AutoRightGearHopperBoiler() {
        // Do right gear auto
    	// Back up so center is 76" from airship wall (reverse 36")
    	// Turn anticlockwise 30 degrees
    	// Reverse to hopper (50")
    	// Wait 4s for balls
    	// Drive forward 63" away from hopper
    	// Turn clockwise 45 degrees
    	// Drive backward 75" to boiler
    	// Shoot!
    	addSequential(new AutoRightGear());
    	addSequential(new ChassisPIDDriveDistance(-36));
    	addSequential(new ChassisTurnAngle(-30.0));
    	addSequential(new ChassisDriveDistance(50, -1.0));
    	addSequential(new WaitCommand(4.0));
    	addSequential(new ChassisPIDDriveDistance(63));
    	addSequential(new ChassisTurnAngle(45.0));
    	addSequential(new ChassisDriveDistance(75, -1.0));
    	addSequential(new LowShooterShootSeqTimed(10.0));
    }
}
