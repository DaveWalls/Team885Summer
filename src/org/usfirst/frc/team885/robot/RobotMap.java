package org.usfirst.frc.team885.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static class PWM {
		public static final int leftDrive = 0;
		public static final int rightDrive = 1;
		public static final int lifter = 2;
		public static final int lowShooter = 3;
		public static final int lowFeeder = 4;
		public static final int collector = 5;
	}

	public static class Digital {
		public static final int leftDriveEncoderA = 0;
		public static final int leftDriveEncoderB = 1;
		public static final int rightDriveEncoderA = 2;
		public static final int rightDriveEncoderB = 3;
		public static final int feederDetector = 4;
		public static final int gearSpearedDetector = 7;
		public static final int gearStowedDetector = 8;
	}

	public static class Pneumatic {
		public static final int shifterA = 0;
		public static final int shifterB = 1;
	}
}
