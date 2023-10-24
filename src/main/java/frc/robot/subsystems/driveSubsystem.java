// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import javax.print.CancelablePrintJob;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class driveSubsystem extends SubsystemBase {
  /** Creates a new driveSubsystem. */

  private CANSparkMax leftMotorFront = new CANSparkMax(1, MotorType.kBrushless); 
  private CANSparkMax leftMotorBack = new CANSparkMax(1, MotorType.kBrushless); 
  private CANSparkMax rightMotorFront = new CANSparkMax(2, MotorType.kBrushless); 
  private CANSparkMax rightMotorBack = new CANSparkMax(3, MotorType.kBrushless); 

  private RelativeEncoder rightFrontEncoder = rightMotorFront.getEncoder(); 
  private RelativeEncoder leftFrontEncoder = leftMotorFront.getEncoder(); 

  private MotorControllerGroup leftControllerGroup = new MotorControllerGroup(leftMotorFront, leftMotorBack); 
  private MotorControllerGroup rightControllerGroup = new MotorControllerGroup(rightMotorFront, rightMotorBack); 

  DifferentialDrive differentialDrive = new DifferentialDrive(leftControllerGroup, rightControllerGroup); 

  public driveSubsystem() {

    leftMotorFront.restoreFactoryDefaults(); 
    leftMotorBack.restoreFactoryDefaults(); 
    rightMotorFront.restoreFactoryDefaults(); 
    rightMotorBack.restoreFactoryDefaults();  
  
    rightControllerGroup.setInverted(true);
    leftControllerGroup.setInverted(false);

    rightFrontEncoder.setPosition(0); 
    leftFrontEncoder.setPosition(0); 
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
    SmartDashboard.putNumber("right encoder",  rightEncoderPosition()); 
    SmartDashboard.putNumber("left encoder", leftEncoderPosition());    
  }

  public double leftEncoderPosition(){
    return leftFrontEncoder.getPosition();
  }

  public double rightEncoderPosition(){
    return rightFrontEncoder.getPosition();
  }

  public void set(double drive, double turn){
    differentialDrive.arcadeDrive(drive, turn);
  }

  public void tankMode(double left, double right){
    differentialDrive.tankDrive(left, right);
  }

  public void stop(){
    differentialDrive.stopMotor();
  }


}
