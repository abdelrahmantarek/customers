package todriver.sendrequest.Library.Clasess;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


public class SensorClass implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    public float rotation = 0.0f;


    private Activity activity;

    public SensorClass(Activity activity) {
        this.activity = activity;
    }

    // Start Sensor ---------------------

    public void start() {

        mSensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);

        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        mSensorManager.registerListener(this,mSensor,SensorManager.SENSOR_DELAY_GAME);

    }


    public void stop(){
        if (mSensorManager!=null){
            mSensorManager.unregisterListener(this);
        }

    }



    @Override
    public void onSensorChanged(SensorEvent event) {

        if (mSensor == null){

            mSensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);

            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

            mSensorManager.registerListener(this,mSensor,SensorManager.SENSOR_STATUS_NO_CONTACT);
        }

        float degress = event.values[0];


        rotation = degress - 360;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {



    }





}
