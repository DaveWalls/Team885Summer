package org.usfirst.frc.team885.robot.commands;

import org.usfirst.frc.team885.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Shift to low gear
 */
public class ShifterSetLow extends InstantCommand {

    public ShifterSetLow() {
        super();
        requires(Robot.shifter);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.shifter.shiftLow();
    	Robot.chassis.setTurnFactor(Robot.chassis.lowTurnFactor);
    }

}
