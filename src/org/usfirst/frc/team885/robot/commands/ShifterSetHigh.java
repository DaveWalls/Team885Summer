package org.usfirst.frc.team885.robot.commands;

import org.usfirst.frc.team885.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Shift to high gear
 */
public class ShifterSetHigh extends InstantCommand {

    public ShifterSetHigh() {
        super();
        requires(Robot.shifter);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.shifter.shiftHigh();
    	Robot.chassis.setTurnFactor(Robot.chassis.highTurnFactor);
    }

}
