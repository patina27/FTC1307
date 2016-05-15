package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class autored2 extends LinearOpMode {


	DcMotor motorRight;
	DcMotor motorLeft;
	DcMotor motorRightt;
	DcMotor motorLeftt;
	Servo tail;
	Servo handl;
	Servo handr;
	public autored2() {
	}

	void initialize () {
		motorRight = hardwareMap.dcMotor.get("motor_1");
		motorLeft = hardwareMap.dcMotor.get("motor_2");
		motorRightt = hardwareMap.dcMotor.get("motor_3");
		motorLeftt = hardwareMap.dcMotor.get("motor_4");
		tail = hardwareMap.servo.get("servo_3");
		handl = hardwareMap.servo.get("servo_1");
		handr = hardwareMap.servo.get("servo_2");
		motorRight.setDirection(DcMotor.Direction.REVERSE);
		motorRightt.setDirection(DcMotor.Direction.REVERSE);
		motorRight.setPower(0);
		motorLeft.setPower(0);
		motorRightt.setPower(0);
		motorLeftt.setPower(0);
	}
	void Movement() throws InterruptedException {
		motorRight.setPower(0.5);
		motorLeft.setPower(0.5);
		motorRightt.setPower(0.5);
		motorLeftt.setPower(0.5);
		for (int i = 1; i <= 10; ++i) {
			sleep(650);
			tail.setPosition(1);
			handl.setPosition(0.3);
			handr.setPosition(0.6);
		}
		motorRight.setPower(0.0);
		motorLeft.setPower(0.5);
		motorRightt.setPower(0.0);
		motorLeftt.setPower(0.5);
		for (int i = 1; i <= 10; ++i) {
			sleep(200);
			tail.setPosition(1);
			handl.setPosition(0.3);
			handr.setPosition(0.6);
		}
		motorRight.setPower(0);
		motorLeft.setPower(0);
		motorRightt.setPower(0);
		motorLeftt.setPower(0);
	}
	public void runOpMode() throws InterruptedException{
		initialize();
		waitForStart();
		Movement();
	}

}
