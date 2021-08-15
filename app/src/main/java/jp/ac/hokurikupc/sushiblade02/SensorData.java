package jp.ac.hokurikupc.sushiblade02;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SensorData implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private ImageView imageView;
    private TextView textView;
    private ProgressBar progressBar;
    private Context context;


    int max = 0;
    int flg = 0;


    SensorData(SensorManager sensorManager, ImageView imageView, TextView textView, ProgressBar progressBar, Context context) {
        this.sensorManager = sensorManager;
        this.imageView = imageView;
        this.textView = textView;
        this.progressBar = progressBar;
        this.context = context;

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        sensorManager.registerListener(this, accelerometer, sensorManager.SENSOR_DELAY_NORMAL);
        float fx = event.values[0];

        if(flg == 0){
            textView.setText("spd = " + fx);
            progressBar.setProgress((int)fx * 2);
        }

        if(fx > 10 && fx > max){
            startRotationXml();
            flg = 1;
        }

    }

    private void startRotationXml() {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.rotation);
        imageView.startAnimation(animation);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void startSensor() {
        sensorManager.registerListener(this, accelerometer, sensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stopSensor() {
        sensorManager.unregisterListener(this);
    }
}