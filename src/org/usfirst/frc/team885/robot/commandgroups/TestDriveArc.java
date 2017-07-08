package org.usfirst.frc.team885.robot.commandgroups;

import org.usfirst.frc.team885.robot.commands.ChassisDriveArc;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestDriveArc extends CommandGroup {

    public TestDriveArc() {
        addSequential(new ChassisDriveArc(200, 0.8, 1.0));
    }
}
