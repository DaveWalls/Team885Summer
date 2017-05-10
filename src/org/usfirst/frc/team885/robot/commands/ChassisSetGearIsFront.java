package org.usfirst.frc.team885.robot.commands;

import org.usfirst.frc.team885.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Set whether the gear collector is the front of the robot
 * Used to facilitate driving in teleop
 */
public class ChassisSetGearIsFront extends InstantCommand {
	
	private boolean b;

    public ChassisSetGearIsFront(boolean b) {
        super();
        requires(Robot.chassis);
        this.b = b;
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.chassis.setGearCollectorFront(b);
    }

}
