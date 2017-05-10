package org.usfirst.frc.team885.robot.subsystems;

import org.usfirst.frc.team885.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Pneumatic shifter for drivetrain
 */
public class Shifter extends Subsystem {

	private DoubleSolenoid shifter = new DoubleSolenoid(RobotMap.Pneumatic.shifterA, RobotMap.Pneumatic.shifterB);
	
	boolean highGearBoolean = true; // Solenoid value that shifts to high gear
	
	public int gear = 0; // 1 for high gear, -1 for low gear, 0 for no gear set
	
	public void shiftHigh() {
		shifter.set(DoubleSolenoid.Value.kReverse);
		gear = 1;
	}

	public void shiftLow() {
		shifter.set(DoubleSolenoid.Value.kForward);
		gear = -1;
	}

	public void updateDashboard() {
		SmartDashboard.putBoolean("High gear", gear == 1);
		SmartDashboard.putBoolean("Low Gear", gear == -1);
		SmartDashboard.putData(this);
	}

	@Override
	protected void initDefaultCommand() {
	}
}
