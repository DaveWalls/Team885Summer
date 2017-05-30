package org.usfirst.frc.team885.robot.subsystems;

import org.usfirst.frc.team885.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Subsystem for gear collector sensors and "sunroof"
 */
public class GearCollector extends Subsystem {
	
	private DoubleSolenoid piston = new DoubleSolenoid(RobotMap.Pneumatic.gearCollectorA, RobotMap.Pneumatic.gearCollectorB);
    
    public void pistonOut() {
    	piston.set(DoubleSolenoid.Value.kForward);
    }
    
    public void pistonIn() {
    	piston.set(DoubleSolenoid.Value.kReverse);
    }

    public void updateDashboard() {
    	SmartDashboard.putBoolean("Gear Collector piston out", piston.get() == DoubleSolenoid.Value.kForward);
    }
    
    public void initDefaultCommand() {
    }
}

