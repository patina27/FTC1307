package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class TURN extends OpMode {

	final static double HAND_MIN_RANGE  = 0.00;
	final static double HAND_MAX_RANGE  = 1;

	double handLeftPosition;
	double handRightPosition;
	double tailPosition;

	double handDelta = 0.002;
	Servo handLeft;
	Servo handRight;
	Servo tail;
	DcMotor motorRight;
	DcMotor motorLeft;
	DcMotor motorRightt;
	DcMotor motorLeftt;
	DcMotor motorArm1;
	DcMotor motorArm2;
	DcMotor motorT1;
	DcMotor motorT2;

	double power;
	public TURN() {

	}

	@Override
	public void init() {
		motorRight = hardwareMap.dcMotor.get("motor_1");
		motorLeft = hardwareMap.dcMotor.get("motor_2");
		motorRightt = hardwareMap.dcMotor.get("motor_3");
		motorLeftt = hardwareMap.dcMotor.get("motor_4");
		motorArm1 = hardwareMap.dcMotor.get("motor_5");
		motorArm2 = hardwareMap.dcMotor.get("motor_6");
		motorT1 = hardwareMap.dcMotor.get("motor_7");
		motorT2 = hardwareMap.dcMotor.get("motor_8");
		motorLeft.setDirection(DcMotor.Direction.REVERSE);
		motorLeftt.setDirection(DcMotor.Direction.REVERSE);
		handLeft = hardwareMap.servo.get("servo_1");
		handRight = hardwareMap.servo.get("servo_2");
		tail = hardwareMap.servo.get("servo_3");
		handLeftPosition = 0.3;
		handRightPosition = 0.6;
		tailPosition =1;
		handLeft.setPosition(handLeftPosition);
		handRight.setPosition(handRightPosition);
		tail.setPosition(tailPosition);
		motorRight.setPower(0);
		motorLeft.setPower(0);
		motorRightt.setPower(0);
	 	motorLeftt.setPower(0);
		motorArm1.setPower(0);
		motorArm2.setPower(0);
		motorT1.setPower(0);
		motorT2.setPower(0);
	}

	@Override
	public void start(){
		handLeftPosition = 0.3;
		handRightPosition = 0.6;
		tailPosition =1;
		handLeft.setPosition(handLeftPosition);
		handRight.setPosition(handRightPosition);
		tail.setPosition(tailPosition);
		motorRight.setPower(0);
		motorLeft.setPower(0);
		motorRightt.setPower(0);
		motorLeftt.setPower(0);
		motorArm1.setPower(0);
		motorArm2.setPower(0);
		motorT1.setPower(0);
		motorT2.setPower(0);
	}
	void Movement(){

		power=gamepad1.left_stick_y;
		motorRight.setPower(0.1*power);
		motorLeft.setPower(-0.2*power);
		motorRightt.setPower(0.3*power);
		motorLeftt.setPower(-0.5*power);

	}
	@Override
	public void loop() {
		telemetry.addData("Text", "*** Robot Data***");
		Movement();

	}

	@Override
	public void stop() {

	}

}
