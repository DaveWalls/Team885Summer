package org.usfirst.frc.team885.robot.subsystems;

import org.usfirst.frc.team885.robot.Robot;

import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class GearSwitch extends Trigger {
  
    public boolean get() {
        return !Robot.gearCollector.isTriggered();
    }
}
