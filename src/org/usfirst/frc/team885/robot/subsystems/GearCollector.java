package org.usfirst.frc.team885.robot.subsystems;

import org.usfirst.frc.team885.robot.RobotMap;
import org.usfirst.frc.team885.robot.commands.GearIntakeRun;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Subsystem for gear collector sensors and "sunroof"
 */
public class GearCollector extends Subsystem {
	
	private VictorSP intake = new VictorSP(RobotMap.PWM.gearIntake);
	private DoubleSolenoid piston = new DoubleSolenoid(RobotMap.Pneumatic.gearCollectorA, RobotMap.Pneumatic.gearCollectorB);
    
    public void runIntake(double speed) {
    	intake.set(speed);
    }
    
    public void setUp() {
    	piston.set(DoubleSolenoid.Value.kForward);
    }
    
    public void setDown() {
    	piston.set(DoubleSolenoid.Value.kReverse);
    }

    public void updateDashboard() {
    	SmartDashboard.putBoolean("Gear Collector Up", piston.get() == DoubleSolenoid.Value.kForward);
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(new GearIntakeRun(0.0));
    }
}

