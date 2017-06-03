package org.usfirst.frc.team885.robot;

import org.usfirst.frc.team885.robot.commands.GearIn;
import org.usfirst.frc.team885.robot.commands.GearOut;
import org.usfirst.frc.team885.robot.commands.ScalerRun;
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
	Button gearOutButton = new JoystickButton(driverStick, 2); // driver thumb button

	// Operator controls
	POVButton lifterUpButtonA = new POVButton(operatorStick, 0);
	Button lifterUpButtonB = new JoystickButton(operatorStick, 5); // left top bumper
	Button lifterDownButtonA = new JoystickButton(operatorStick, 1);
	Button lifterDownButtonB = new JoystickButton(operatorStick, 3);
	DoubleButton lifterDownButton = new DoubleButton(lifterDownButtonA, lifterDownButtonB);

	public OI() {
		super();

		// Gear collector
		gearOutButton.whenPressed(new GearOut());
		gearOutButton.whenReleased(new GearIn());
		
		// Toggle low/high gear
		shifterSwitchButton.whenPressed(new ShifterSwitch());

		// Run lifter (momentary)
		lifterUpButtonA.whileActive(new ScalerRun(1.0));
		lifterUpButtonA.whileActive(new ScalerRun(1.0));

		// Run lifter in reverse (momentary)
		lifterDownButton.whileActive(new ScalerRun(-0.5));

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
