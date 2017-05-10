package org.usfirst.frc.team885.robot.commandgroups;

import org.usfirst.frc.team885.robot.commands.ChassisDriveStraightTimed;
import org.usfirst.frc.team885.robot.commands.ChassisTurnAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive in a square
 */
public class TestSquare extends CommandGroup {

    public TestSquare() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	for (int i = 0; i < 4; i++) {
	    	addSequential(new ChassisTurnAngle(90));
	    	addSequential(new ChassisDriveStraightTimed(12, 1.0));
    	}
    }
}
