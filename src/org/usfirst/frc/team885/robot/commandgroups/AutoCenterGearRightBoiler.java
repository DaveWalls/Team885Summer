package org.usfirst.frc.team885.robot.commandgroups;

import org.usfirst.frc.team885.robot.commands.ChassisDriveDistance;
import org.usfirst.frc.team885.robot.commands.ChassisPIDDriveDistance;
import org.usfirst.frc.team885.robot.commands.ChassisTurnAngle;
import org.usfirst.frc.team885.robot.commands.LowShooterShootSeqTimed;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Deposit gear onto center peg, shoot into boiler on right
 * untested
 */
public class AutoCenterGearRightBoiler extends CommandGroup {

    public AutoCenterGearRightBoiler() {
        addSequential(new AutoCenterGear());
        addSequential(new ChassisDriveDistance(12, -0.5));
        addSequential(new ChassisTurnAngle(-90));
        addSequential(new ChassisPIDDriveDistance(140));
        addSequential(new ChassisTurnAngle(45));
        addSequential(new ChassisDriveDistance(36, -0.8));
        addSequential(new LowShooterShootSeqTimed(5.0));
    }
}
