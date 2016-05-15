package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class oneang extends OpMode {

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

	double power1=0,power2=0,powerl1=0,powerl2=0,powerr1=0,powerr2=0;
	boolean pl1=true,pl2 = true,pr1=true, pr2=true,fl1=true,fl2 = true,fr1=true, fr2=true; //switch the direction of base motors
	public oneang() {

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
		if (gamepad1.a) pl1 = true; else {
			if (pl1) powerl1+=0.1;
			pl1 = false;
		}
		if (gamepad1.b) fl1 = true; else {
			if (fl1) powerl1-=0.1;
			fl1 = false;
		}
		if (gamepad1.x) pl2 = true; else {
			if (pl2) powerl2+=0.1;
			pl2 = false;
		}
		if (gamepad1.y) fl2 = true; else {
			if (fl2) powerl2-=0.1;
			fl2 = false;
		}


		if (gamepad2.a) pr1 = true; else {
			if (pr1) powerr1+=0.1;
			pr1 = false;
		}
		if (gamepad2.b) fr1 = true; else {
			if (fr1) powerr1-=0.1;
			fr1 = false;
		}
		if (gamepad2.x) pr2 = true; else {
			if (pr2) powerr2+=0.1;
			pr2 = false;
		}
		if (gamepad2.y) fr2 = true; else {
			if (fr2) powerr2-=0.1;
			fr2 = false;
		}

		powerl1 = Range.clip(powerl1, -1, 1);
		powerl2 = Range.clip(powerl2, -1, 1);
		powerr1 = Range.clip(powerr1, -1, 1);
		powerr2 = Range.clip(powerr2, -1, 1);

		motorRight.setPower(powerr1);
		motorLeft.setPower(powerl1);
		motorRightt.setPower(powerr2);
		motorLeftt.setPower(powerl2);

		telemetry.addData("左前轮动力", ": " + String.format("%.2f", powerl1));
		telemetry.addData("右前轮动力", ": " + String.format("%.2f", powerr1));
		telemetry.addData("左后轮动力", ": " + String.format("%.2f", powerl2));
		telemetry.addData("右后轮动力", ": " + String.format("%.2f", powerr2));
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
