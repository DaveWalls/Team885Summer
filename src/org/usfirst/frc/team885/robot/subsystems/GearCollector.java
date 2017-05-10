package org.usfirst.frc.team885.robot.subsystems;

import org.usfirst.frc.team885.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Subsystem for gear collector sensors and "sunroof"
 */
public class GearCollector extends Subsystem {
	
	// Gives false when gear is stowed
	private static DigitalInput stowedDetector = new DigitalInput(RobotMap.Digital.gearStowedDetector);
	private static DigitalInput spearedDetector = new DigitalInput(RobotMap.Digital.gearSpearedDetector);

	// Sensor pointed across gear bin
    public boolean getIsStowed() {
    	return !stowedDetector.get();
    }
    
    // Sensor pointed at flap on rear of bin
    public boolean getIsSpeared() {
    	return !spearedDetector.get();
    }

    public void updateDashboard() {
    	SmartDashboard.putBoolean("Stowed", getIsStowed());
    	SmartDashboard.putBoolean("Speared", getIsSpeared());
    }
    
    public void initDefaultCommand() {
    }
}

