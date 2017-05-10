package org.usfirst.frc.team885.robot;

import org.usfirst.frc.team885.robot.commands.CollectorRun;
import org.usfirst.frc.team885.robot.commands.LifterRun;
import org.usfirst.frc.team885.robot.commands.LowShooterFeed;
import org.usfirst.frc.team885.robot.commands.LowShooterShoot;
import org.usfirst.frc.team885.robot.commands.LowShooterShootSequence;
import org.usfirst.frc.team885.robot.commands.LowShooterStopped;
import org.usfirst.frc.team885.robot.commands.ShifterSwitch;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public static Joystick driverStick = new Joystick(0);
	public static Joystick operatorStick = new Joystick(1);

	// Driver controls
	Button shifterSwitchButton = new JoystickButton(driverStick, 1);

	// Operator controls
	Button feederInButton = new JoystickButton(operatorStick, 8);
	Button shooterOutButton = new JoystickButton(operatorStick, 7);
	Button shooterSequenceButton = new JoystickButton(operatorStick, 6);
	Button collectorToggleButton = new JoystickButton(operatorStick, 2);
	Button collectorMomentaryButton = new JoystickButton(operatorStick, 5);
	Button collectorBackwardsButton = new JoystickButton(operatorStick, 4);
	POVButton lifterUpButton = new POVButton(operatorStick, 0);
	Button lifterDownButtonA = new JoystickButton(operatorStick, 1);
	Button lifterDownButtonB = new JoystickButton(operatorStick, 3);
	DoubleButton lifterDownButton = new DoubleButton(lifterDownButtonA, lifterDownButtonB);

	public OI() {
		super();

		// Toggle low/high gear
		shifterSwitchButton.whenPressed(new ShifterSwitch());

		// Run feeder in (momentary)
		feederInButton.whileHeld(new LowShooterFeed(1.0));

		// Run feeder, shooter and collector (momentary)
		shooterSequenceButton.whenPressed(new LowShooterShootSequence(1.0, 1.0, 1.0));
		shooterSequenceButton.whenReleased(new LowShooterStopped());

		// Run shooter (momentary)
		shooterOutButton.whileHeld(new LowShooterShoot(1.0));

		// Run collector (momentary)
		collectorMomentaryButton.whileHeld(new CollectorRun(1.0));

		// Toggle collector on/off
		collectorToggleButton.toggleWhenPressed(new CollectorRun(1.0));

		// Run collector in reverse (momentary)
		collectorBackwardsButton.whileHeld(new CollectorRun(-1.0));

		// Run lifter (momentary)
		lifterUpButton.whileActive(new LifterRun(1.0));

		// Run lifter in reverse (momentary)
		lifterDownButton.whileActive(new LifterRun(-0.5));

		DriverStation.reportWarning(DSMessaging.OI_CREATED, false);
	}

	/**
	 * Trigger an action by pressing two buttons at once
	 * 
	 * Useful for actions that should not be easy to accidentally trigger
	 */
	public class DoubleButton extends Trigger {

		Button buttonA, buttonB;

		public DoubleButton(Button buttonA, Button buttonB) {
			this.buttonA = buttonA;
			this.buttonB = buttonB;
		}

		@Override
		public boolean get() {
			return buttonA.get() && buttonB.get();
		}
	}

	/**
	 * Trigger an action by pushing the POV a certain direction
	 */
	public class POVButton extends Trigger {

		Joystick stick;
		int index;

		public POVButton(Joystick joystick, int index) {
			this.stick = joystick;
			this.index = index;
		}

		@Override
		public boolean get() {
			return stick.getPOV() == index;
		}

	}
}
