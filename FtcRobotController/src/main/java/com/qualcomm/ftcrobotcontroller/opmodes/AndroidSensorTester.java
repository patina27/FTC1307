package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by patin on 2016/5/17.
 */


        import com.qualcomm.ftcrobotcontroller.FtcRobotControllerActivity;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

        import android.content.Context;
        import android.hardware.Sensor;
        import android.hardware.SensorManager;


public class AndroidSensorTester extends LinearOpMode {
    private SensorManager mSensorManager;
    private Sensor mSensor;
    public AndroidSensorTester{

    }
    private void initialize(){
        mSensorManager = (SensorManager) FtcRobotControllerActivity.mContext.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }
    @Override
    public void runOpMode() throws InterruptedException {
        initialize();

    }
}
