package org.usfirst.frc.team885.robot.subsystems;

import org.usfirst.frc.team885.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Subsystem for gear collector sensors and "sunroof"
 */
public class GearCollector extends Subsystem {
	
	private DoubleSolenoid piston = new DoubleSolenoid(RobotMap.Pneumatic.gearCollectorA, RobotMap.Pneumatic.gearCollectorB);
	private DigitalInput limitSwitch = new DigitalInput(RobotMap.Digital.gearSwitch);
    
    public void pistonOut() {
    	piston.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void pistonIn() {
    	piston.set(DoubleSolenoid.Value.kForward);
    }
    
    public boolean isTriggered() {
    	return !limitSwitch.get();
    }

    public void updateDashboard() {
    	SmartDashboard.putBoolean("Gear Collector piston out", piston.get() == DoubleSolenoid.Value.kForward);
    	SmartDashboard.putBoolean("Gear pin detected", isTriggered());
    }
    
    public void initDefaultCommand() {
    }
}

