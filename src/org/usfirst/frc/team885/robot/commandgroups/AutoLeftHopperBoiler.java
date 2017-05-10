package org.usfirst.frc.team885.robot.commandgroups;

import org.usfirst.frc.team885.robot.commands.ChassisDriveDistance;
import org.usfirst.frc.team885.robot.commands.ChassisPIDDriveDistance;
import org.usfirst.frc.team885.robot.commands.ChassisTurnAngle;
import org.usfirst.frc.team885.robot.commands.LowShooterShootSeqTimed;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * On boiler side, left in this case, collect from hopper and shoot into boiler
 * untested
 */
public class AutoLeftHopperBoiler extends CommandGroup {

    public AutoLeftHopperBoiler() {
    	// Go forward 77"
    	// Turn clockwise 90 degrees
    	// Go backward 76" to hopper
    	// Wait 4s for balls
    	// Drive forward 63" away from hopper
    	// Turn anticlockwise 45 degrees
    	// Drive backward 75" to boiler
    	// Shoot!
    	addSequential(new ChassisPIDDriveDistance(77));
    	addSequential(new ChassisTurnAngle(90));
    	addSequential(new ChassisDriveDistance(76, -1.0));
    	addSequential(new WaitCommand(4.0));
    	addSequential(new ChassisPIDDriveDistance(63));
    	addSequential(new ChassisTurnAngle(-45.0));
    	addSequential(new ChassisDriveDistance(75, -1.0));
    	addSequential(new LowShooterShootSeqTimed(10.0));
    }
}
