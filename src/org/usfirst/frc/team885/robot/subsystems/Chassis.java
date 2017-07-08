package org.usfirst.frc.team885.robot.subsystems;

import org.usfirst.frc.team885.robot.RobotMap;
import org.usfirst.frc.team885.robot.commands.ChassisTeleop;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Six wheel chassis subsystem with dual CIM, two speed transmissions
 */
public class Chassis extends Subsystem {
	
	// Drive
	private SpeedController leftDrive = new VictorSP(RobotMap.PWM.leftDrive);
	private SpeedController rightDrive = new VictorSP(RobotMap.PWM.rightDrive);
	RobotDrive drivetrain = new RobotDrive(leftDrive, rightDrive);
	
	// Sensors
	private Encoder leftEncoder = new Encoder(RobotMap.Digital.leftDriveEncoderA, RobotMap.Digital.leftDriveEncoderB);
//	private Encoder rightEncoder = new Encoder(RobotMap.Digital.rightDriveEncoderA, RobotMap.Digital.rightDriveEncoderB);
	private AHRS navx = new AHRS(SPI.Port.kMXP);
	
	// Collision detection (not tested)
	private double lastAccel = 0;
	private double collisionThreshold = 0.5;
	public boolean hasCollided = false;
    
	// Preferences
	public double lowTurnFactor = 0.8; // sensitivity of turning in low gear (0 to 1)
	public double highTurnFactor = 0.4; // sensitivity in high gear (0 to 1)
    private double turnFactor; // current turning sensitivity
    private final double headingTolerance = 2.0; // Heading will read as "on target" if + or - this many degrees
    private final double distanceTolerance = 2.0; // Distance will read as "on target" if + or - this many inches
    public int forward = -1; // -1 for gear collector being front, 1 for low shooter being front of robot 
    						//(used for switching front and back while driving)
    
    // Encoder calculations
    // encoder should be rotating 7 times for every 1 drive wheel rotation
    private final double encoderCountsPerRev = 128.0; 				// Encoders' pulses per revolution
    private final double encoderRevsPerDriveRev = 1.0 / 7.0; 			// Ratio of encoder sprocket to drive sprocket
    private final double empiricalDiameter = 6.5;	// Circumference as measured by driving the robot a set distance
    private double distancePerPulse = encoderRevsPerDriveRev * Math.PI * empiricalDiameter / encoderCountsPerRev;
    
    // Arc drive calculations
    private final double track = 22.0;
    
    public double getTrack() {
		return track;
	}

	// Heading PID
    private HeadingPIDOutput headingPIDOut = new HeadingPIDOutput(); // Custom class to turn robot based on PID output
    private PIDController headingPID = new PIDController(0.04, 0.0, 0.0, navx, headingPIDOut);
    private final double heading_Kp = 0.8; // Limit on output of heading PID (0 to 1) to prevent oscillation
    private double aheadSpeed; // Desired linear speed of robot, updated by other drive methods
    
    // Distance PID
    private DistancePIDOutput distancePIDOut = new DistancePIDOutput(); // Another custom output class
    private DistancePIDInput distancePIDIn = new DistancePIDInput(); // Custom class to receive input from encoders
    private PIDController distancePID = new PIDController(0.04, 0.0, 0.0, distancePIDIn, distancePIDOut);
    private double distance_Kp = heading_Kp; // For convenience, distance_Kp is set to heading_Kp
    
    public Chassis() {
    	leftEncoder.setDistancePerPulse(distancePerPulse);
    	leftEncoder.setReverseDirection(true); // One of the encoders must measure in reverse
//    	rightEncoder.setDistancePerPulse(distancePerPulse);
//    	rightEncoder.setReverseDirection(false);
    	
    	headingPID.setInputRange(-180.0f, 180.0f); // navx will only give headings from -180 to 180
    	headingPID.setOutputRange(-heading_Kp, heading_Kp); // Restrict output
    	headingPID.setAbsoluteTolerance(headingTolerance); // Tolerance for reading as "on target"
    	headingPID.setContinuous(true); // Tells it -180 and 180 are the same point, which is true
    	
    	distancePID.setInputRange(-1000, 1000); // Probably a safe range
    	distancePID.setOutputRange(-distance_Kp, distance_Kp);
    	distancePID.setAbsoluteTolerance(distanceTolerance);
    	distancePID.setContinuous(false); // -1000 is NOT the same as 1000 in terms of distance
    	
    	turnFactor = lowTurnFactor; // Initialize with low turn factor
    }
    
    // Drive with 3-axis joystick
    public void teleopDrive(Joystick joystick) {
    	drivetrain.arcadeDrive(joystick.getY() * forward, -joystick.getTwist() * turnFactor);
    }
    
    public void teleopDriveTank(Joystick stickA, Joystick stickB) {
    	drivetrain.tankDrive(-stickA.getY(), -stickB.getY());
    }
    
    // Drive with joystick, ignoring turning input
    public void teleopDriveStraight(Joystick joystick) {
    	drivetrain.arcadeDrive(joystick.getY() * forward, 0);
    }
    
    /**
     * Drive robot straight at a set speed without PID control
     * @param speed
     */
    public void driveStraight(double speed) {
    	drivetrain.arcadeDrive(speed, 0);
    }
    
    // Turn robot at a set speed (for autonomous)
    public void turn(double speed) {
    	drivetrain.arcadeDrive(0, speed);
    }
    
    // Drive robot with specified speed and use heading PID for turning
    public void pidHeadingOnlyDrive(double aheadSpeed) {
    	this.aheadSpeed = aheadSpeed;
    }
    
    // Drive left and right wheels at specific speeds
    public void directDrive(double leftSpeed, double rightSpeed) {
    	drivetrain.setLeftRightMotorOutputs(leftSpeed, rightSpeed);
    }
    
    public void stop() {
    	drivetrain.arcadeDrive(0, 0);
    }
    
    // Switch between gear collector being the front and low shooter being the front (may be crashing robot currently)
    public void setGearCollectorFront(boolean b) {
    	if (b)
    		forward = -1;
    	else
    		forward = 1;
    }
    
    // Get current heading, -180 to 180
    public double getHeading() {
    	return navx.getYaw();
    }
    
    // Get current heading with range beyond 360 degrees
    public double getHeadingContinuous() {
    	return navx.getAngle();
    }
    
    // Get distance measured by right encoder (inches)
//    public double getRightDistance() {
//		return rightEncoder.getDistance();
//	}
	
    // Get distance measured by left encoder (inches)
	public double getLeftDistance() {
		return leftEncoder.getDistance();
	}
	
	// Get average of both encoders
	public double getAverageDistance() {
//		return (getRightDistance() + getLeftDistance()) / 2.0;
		return getLeftDistance();
	}
	
	// Not used currently--would allow for turning a set angle using encoders, not navx
	public double getAngleToDistance(double degrees) {
		return degrees / 360.0 * Math.PI * track;
	}
	
	// Get speed as measured by encoders in inches per second
	public double getSpeed() {
//		return (rightEncoder.getRate() + leftEncoder.getRate()) / 2;
		return leftEncoder.getRate();
	}
	
	// Not tested
	public void resetCollisionDetection() {
		lastAccel = 0;
		hasCollided = false;
	}
	
	// Not tested -- would update hasCollided if a collision is detected by navx
	public void updateCollisions() {
		double currentAccel = navx.getWorldLinearAccelX();
		double jerk = currentAccel - lastAccel;
		lastAccel = currentAccel;
		hasCollided = Math.abs(jerk) > collisionThreshold;
	}

	// Zero the heading readout of the navx
	public void resetHeading() {
		navx.zeroYaw();
	}
	
	// Start using heading PID to control robot
	public void enableHeadingPID() {
		headingPID.enable();
	}
	
	public void disableHeadingPID() {
		headingPID.disable();
		aheadSpeed = 0;
	}
	
	// Change the turn factor (used when switching gears)
	public void setTurnFactor(double turnFactor) {
		this.turnFactor = turnFactor;
	}
	
	/**
	 * @param degrees angle to set PID
	 * Sets heading PID, resets NavX heading, enables PID
	 */
	public void startHeadingPIDReset(double degrees) {
		setHeading(degrees);
		resetHeading();
		enableHeadingPID();
	}
	
	// Starts heading PID without resetting heading (buggy)
	public void startHeadingPID(double degrees) {
		setHeading(degrees);
		enableHeadingPID();
	}
	
	// Change the target heading
	public void setHeading(double degrees) {
		headingPID.setSetpoint(degrees);
	}
	
	// Returns true if current heading matches target heading within set tolerance
	public boolean atSetHeading() {
		return headingPID.onTarget();
	}
	
	// Zero the encoder readouts
	public void resetEncoders() {
		leftEncoder.reset();
//		rightEncoder.reset();
	}
	
	public void enableDistancePID() {
		distancePID.enable();
	}
	
	public void disableDistancePID() {
		distancePID.disable();
		aheadSpeed = 0;
	}
	
	// Zero out encoders, start distance PID with a target distance
	public void startDistancePID(double inches) {
		distancePID.setSetpoint(inches);
		resetEncoders();
		enableDistancePID();
	}
	
	// Change the target distance
	public void setDistance(double inches) {
		distancePID.setSetpoint(inches);
	}
	
	// Returns true if current distance matches target distance within set tolerance
	public boolean atSetDistance() {
		return distancePID.onTarget();
	}
    
	// Push readouts to the SmartDashboard
    public void updateDashboard() {
    	SmartDashboard.putNumber("Distance PID", distancePID.getError());
    	SmartDashboard.putNumber("Heading", getHeading());
    	SmartDashboard.putNumber("Left Drive", leftDrive.get());
    	SmartDashboard.putNumber("Right Drive", rightDrive.get());
    	SmartDashboard.putNumber("Left Encoder", getLeftDistance());
//    	SmartDashboard.putNumber("Right Encoder", getRightDistance());
    	SmartDashboard.putNumber("Average of encoders", getAverageDistance());
    	SmartDashboard.putNumber("Speed", Math.abs(getSpeed() / 12.0));
    	SmartDashboard.putBoolean("Gear collector is forwards", forward == -1);
    	updateCollisions();
    	SmartDashboard.putBoolean("Collided", hasCollided);
    	SmartDashboard.putData(this);
    }

    // Chassis defaults to ChassisTeleop when no other command is controlling it
    public void initDefaultCommand() {
        setDefaultCommand(new ChassisTeleop());
    }
    
    public class HeadingPIDOutput implements PIDOutput {
    	
    	@Override
    	public void pidWrite(double output) {
    		// output is the output of the PID controller, a value from -heading_Kp to heading_Kp
    		// This method is efficient while restricting motor outputs from -1 to 1
    		drivetrain.setLeftRightMotorOutputs(output + aheadSpeed, -output + aheadSpeed);
    	}
    }
    
    public class DistancePIDOutput implements PIDOutput {

		@Override
		public void pidWrite(double output) {
			// Update aheadSpeed with output of distance PID controller, a value from -distance_Kp to distance_Kp
			aheadSpeed = output;
		}
    }
    
    public class DistancePIDInput implements PIDSource {

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}

		@Override
		public double pidGet() {
			// Feed the PID loop the distance measured by the encoders
			// I don't know why it's negative, it wasn't working otherwise
//			return -getAverageDistance();
			return getLeftDistance();
		}
    	
    }

}
