package org.usfirst.frc.team885.robot.commandgroups;

import org.usfirst.frc.team885.robot.commands.ChassisDriveDistance;
import org.usfirst.frc.team885.robot.commands.ChassisPIDDriveDistance;
import org.usfirst.frc.team885.robot.commands.ChassisTurnToHeading;
import org.usfirst.frc.team885.robot.commands.LowShooterShootSeqTimed;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Deposit gear on center peg, shoot into boiler on left
 * untested
 */
public class AutoCenterGearLeftBoiler extends CommandGroup {

    public AutoCenterGearLeftBoiler() {
        addSequential(new AutoCenterGear());
        addSequential(new ChassisDriveDistance(12, -0.5));
        addSequential(new ChassisTurnToHeading(90));
        addSequential(new ChassisPIDDriveDistance(-140));
        addSequential(new ChassisTurnToHeading(45));
        addSequential(new ChassisDriveDistance(36, -0.8));
        addSequential(new LowShooterShootSeqTimed(5.0));
    }
}
