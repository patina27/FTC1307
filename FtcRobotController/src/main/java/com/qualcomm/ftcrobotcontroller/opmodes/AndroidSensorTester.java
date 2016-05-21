package com.qualcomm.ftcrobotcontroller.opmodes;


        import com.qualcomm.ftcrobotcontroller.FtcRobotControllerActivity;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

        import android.content.Context;
        import android.hardware.Sensor;
        import android.hardware.SensorEventListener;
        import android.hardware.SensorManager;
        import android.hardware.SensorEvent;


public class AndroidSensorTester extends LinearOpMode implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private double[] gravity = new double[3];
    private double[] linear_acceleration = new double[3];
    public AndroidSensorTester(){

    }

    @Override
    public void runOpMode() throws InterruptedException {
        mSensorManager = (SensorManager) FtcRobotControllerActivity.mContext.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        while (true) {
            telemetry.addData("0. x", String.format("%03d", linear_acceleration[0]));
            telemetry.addData("1. y", String.format("%03d", linear_acceleration[1]));
            telemetry.addData("2. z", String.format("%03d", linear_acceleration[2]));
        }
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        // alpha is calculated as t / (t + dT)
        // with t, the low-pass filter's time-constant
        // and dT, the event delivery rate

        final double alpha = 0.8;

        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

        linear_acceleration[0] = event.values[0] - gravity[0];
        linear_acceleration[1] = event.values[1] - gravity[1];
        linear_acceleration[2] = event.values[2] - gravity[2];
    }


}
