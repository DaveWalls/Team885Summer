package org.usfirst.frc.team885.robot.commands;

import org.usfirst.frc.team885.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * 
 */
public class GearUp extends InstantCommand {

    public GearUp() {
        super();
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.gearCollector.setUp();
    }

}
