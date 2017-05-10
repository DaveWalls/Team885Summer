package org.usfirst.frc.team885.robot;

import org.usfirst.frc.team885.robot.commandgroups.AutoBaseline;
import org.usfirst.frc.team885.robot.commandgroups.AutoCenterGear;
import org.usfirst.frc.team885.robot.commandgroups.AutoDoNothing;
import org.usfirst.frc.team885.robot.commandgroups.AutoLeftGear;
import org.usfirst.frc.team885.robot.commandgroups.AutoLeftGearHopperBoiler;
import org.usfirst.frc.team885.robot.commandgroups.AutoLeftGearLeftBoiler;
import org.usfirst.frc.team885.robot.commandgroups.AutoLeftHopperBoiler;
import org.usfirst.frc.team885.robot.commandgroups.AutoRightGear;
import org.usfirst.frc.team885.robot.commandgroups.AutoRightGearHopperBoiler;
import org.usfirst.frc.team885.robot.commandgroups.AutoRightGearRightBoiler;
import org.usfirst.frc.team885.robot.commandgroups.AutoRightHopperBoiler;
import org.usfirst.frc.team885.robot.commandgroups.TestDrivePIDDistance;
import org.usfirst.frc.team885.robot.commands.ShifterSetLow;
import org.usfirst.frc.team885.robot.subsystems.Chassis;
import org.usfirst.frc.team885.robot.subsystems.Collector;
import org.usfirst.frc.team885.robot.subsystems.GearCollector;
import org.usfirst.frc.team885.robot.subsystems.Lifter;
import org.usfirst.frc.team885.robot.subsystems.LowShooter;
import org.usfirst.frc.team885.robot.subsystems.Shifter;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static Chassis chassis;
	public static Collector collector;
	public static GearCollector gearCollector;
	public static Lifter lifter;
	public static LowShooter lowShooter;
	public static Shifter shifter;

	public static OI oi;

	Command autonomousCommand;
	SendableChooser<Command> autoChooser;

	UsbCamera camera1;
	UsbCamera camera2;

	@Override
	public void robotInit() {
		chassis = new Chassis();
		collector = new Collector();
		gearCollector = new GearCollector();
		lifter = new Lifter();
		lowShooter = new LowShooter();
		shifter = new Shifter();

		// Camera initialization: 2 cameras streaming to SmartDashboard

		camera1 = CameraServer.getInstance().startAutomaticCapture(0);
		camera1.setResolution(240, 180);
		camera1.setFPS(45);

		camera2 = CameraServer.getInstance().startAutomaticCapture(1);
		camera2.setResolution(240, 180);
		camera2.setFPS(45);

		DriverStation.reportWarning(DSMessaging.ENABLE_CAMERA, false);

		oi = new OI();

		autoChooser = new SendableChooser<Command>();
		autoChooser.addDefault("Do nothing", new AutoDoNothing());
		autoChooser.addObject("Cross baseline", new AutoBaseline());
		autoChooser.addObject("Center Gear", new AutoCenterGear());
		autoChooser.addObject("Drive 5ft PID", new TestDrivePIDDistance());
		autoChooser.addObject("Right Gear", new AutoRightGear());
		autoChooser.addObject("Left Gear", new AutoLeftGear());
		autoChooser.addObject("Right Gear + Shoot Low", new AutoRightGearRightBoiler());
		autoChooser.addObject("Left Gear + Shoot Low", new AutoLeftGearLeftBoiler());
		autoChooser.addObject("Right Gear + Trigger Hopper + Shoot", new AutoRightGearHopperBoiler());
		autoChooser.addObject("Left Gear + Trigger Hopper + Shoot", new AutoLeftGearHopperBoiler());
		autoChooser.addObject("Right Hopper + Shoot Low", new AutoRightHopperBoiler());
		autoChooser.addObject("Left Hopper + Shoot Low", new AutoLeftHopperBoiler());
		SmartDashboard.putData("Autonomous mode chooser", autoChooser);

		SmartDashboard.putData(Scheduler.getInstance());
		DriverStation.reportWarning(DSMessaging.ROBOT_INIT, false);
	}

	// Collects data from subsystems and sends it to the SmartDashboard
	private void updateDashboard() {
		chassis.updateDashboard();
		collector.updateDashboard();
		gearCollector.updateDashboard();
		lifter.updateDashboard();
		lowShooter.updateDashboard();
		shifter.updateDashboard();
	}

	@Override
	public void disabledInit() {
		DriverStation.reportWarning(DSMessaging.DISABLED_INIT, false);
	}

	@Override
	public void disabledPeriodic() {
		updateDashboard();
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		autonomousCommand = autoChooser.getSelected();
		new ShifterSetLow().start();
		if (autonomousCommand != null)
			autonomousCommand.start();
		DriverStation.reportWarning(DSMessaging.AUTONOMOUS_INIT, false);
	}

	@Override
	public void autonomousPeriodic() {
		updateDashboard();
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		new ShifterSetLow().start();
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		DriverStation.reportWarning(DSMessaging.TELEOP_INIT, false);
	}

	@Override
	public void teleopPeriodic() {
		updateDashboard();
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
