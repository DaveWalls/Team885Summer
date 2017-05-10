package org.usfirst.frc.team885.robot.commands;

import org.usfirst.frc.team885.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Shift to opposite of current gear
 */
public class ShifterSwitch extends InstantCommand {

    public ShifterSwitch() {
        super();
        requires(Robot.shifter);
    }

    // Called once when the command executes
    protected void initialize() {
    	if (Robot.shifter.gear == 1)
    		Robot.shifter.shiftLow();
    	else
    		Robot.shifter.shiftHigh();
    }

}
