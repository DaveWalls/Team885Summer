package org.usfirst.frc.team885.robot.commandgroups;

import org.usfirst.frc.team885.robot.commands.ChassisDriveDistance;
import org.usfirst.frc.team885.robot.commands.ChassisTurnAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive left or right a bit while facing forward, similar to mecanum strafing
 * Useful for aligning to hoppers or goals
 * untested
 */
public class Shimmy extends CommandGroup {
	
	private int direction = 1; // +1 for right, -1 for left
	private final int angle = 30;

    public Shimmy(boolean goingRight) {
    	if (!goingRight)
    		direction = -1;
    	
    	addSequential(new ChassisDriveDistance(-2, 0.5)); 					// back up a bit
    	addSequential(new ChassisTurnAngle(-angle * direction)); 		// turn a bit
    	addSequential(new ChassisDriveDistance(-2, 0.5)); 					// back up a bit more
    	addSequential(new ChassisTurnAngle(angle * direction)); 		// turn back
    	addSequential(new ChassisDriveDistance(42, 1.0)); 					// drive forward
    }
}
