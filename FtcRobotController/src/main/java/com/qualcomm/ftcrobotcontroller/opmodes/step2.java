package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class step2 extends OpMode {

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

	double x1=1,x2=1, t1=1,t2=-1,power1=0,power2=0;
	boolean f = true, x1C = false, x2C = false, fC = false, t1C = false, t2C = false; //switch the direction of base motors
	public step2() {

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
		if (gamepad1.a) fC = true; else {
			if (fC) f = !f;
			fC = false;
		}
		double throttle = -gamepad1.left_stick_y;
		double direction = gamepad1.right_stick_x;
		double right = throttle - direction;
		double left = throttle + direction;
		right = Range.clip(right, -1, 1);
		left = Range.clip(left, -1, 1);
		right = (double)scaleInput(right);
		left =  (double)scaleInput(left);
		if (f) {
			motorRight.setPower(right);
			motorLeft.setPower(left);
			motorRightt.setPower(right);
			motorLeftt.setPower(left);
		} else {
			motorRight.setPower(-left);
			motorLeft.setPower(-right);
			motorRightt.setPower(-left);
			motorLeftt.setPower(-right);
		}
		telemetry.addData("左轮动力", ": " + String.format("%.2f", left));
		telemetry.addData("右轮动力", ": " + String.format("%.2f", right));
	}
	void Arms(){
		if (gamepad2.left_stick_y<-0.2) x1=gamepad2.left_stick_y+0.2;
		else if (gamepad2.left_stick_y>0.2) x1=gamepad2.left_stick_y-0.2;
		else x1=0;
		if (gamepad2.right_stick_y<-0.2) x2=gamepad2.right_stick_y+0.2;
		else if (gamepad2.right_stick_y>0.2) x2=gamepad2.right_stick_y-0.2;
		else x2=0;
		motorArm1.setPower(x1);
		motorArm2.setPower(x2);
	}
	void Hands(){
		if (gamepad2.dpad_down) handLeftPosition += handDelta;
		if (gamepad2.dpad_up) handLeftPosition -= handDelta;
		if (gamepad2.dpad_right) handRightPosition += handDelta;
		if (gamepad2.dpad_left) 	handRightPosition -= handDelta;
		handLeftPosition = Range.clip(handLeftPosition, HAND_MIN_RANGE, HAND_MAX_RANGE);
		handRightPosition = Range.clip(handRightPosition, HAND_MIN_RANGE, HAND_MAX_RANGE);
		handLeft.setPosition(handLeftPosition);
		handRight.setPosition(handRightPosition);
		telemetry.addData("左手位置", ": " + String.format("%.2f", handLeftPosition));
		telemetry.addData("右手位置", ": " + String.format("%.2f", handRightPosition));
	}
	void Tail(){
		if (gamepad2.a) tailPosition = 1;
		if (gamepad2.b) tailPosition = 0;
		tail.setPosition(tailPosition);
	}
	void Coiling(){
		if (gamepad1.right_bumper) t1C = true; else {
			if (t1C) t1 = -t1;
			t1C = false;
		}
		motorT1.setPower(gamepad1.right_trigger * t1);
		if (gamepad1.left_bumper) t2C = true; else{
			if (t2C) t2 = -t2;
			t2C = false;
		}
		motorT2.setPower(gamepad1.left_trigger * t2);
		telemetry.addData("卷线1", ": " + String.format("%.2f", gamepad1.right_trigger * t1));
		telemetry.addData("卷线2", ": " + String.format("%.2f",gamepad1.left_trigger * t2  ));
	}
	@Override
	public void loop() {
		telemetry.addData("Text", "*** Robot Data***");
		Movement();
		Arms();
		Hands();
		Tail();
		Coiling();
	}

	@Override
	public void stop() {

	}

	double scaleInput(double dVal)  {
		double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
				0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

		int index = (int) (dVal * 16.0);

		if (index < 0) {
			index = -index;
		}

		if (index > 16) {
			index = 16;
		}

		double dScale = 0.0;
		if (dVal < 0) {
			dScale = -scaleArray[index];
		} else {
			dScale = scaleArray[index];
		}

		return dScale;
	}

}
