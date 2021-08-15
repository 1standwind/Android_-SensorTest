package jp.ac.hokurikupc.sushiblade02;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class LightData implements SensorEventListener {


    private SensorManager sensorManager;
    private Sensor lightmeter;
    private Spinner spinner;
    private Context context;

    private String[] darkList = new String[6];

    int flg = 0;

    LightData(SensorManager sensorManager, Spinner spinner, Context context) {
        this.sensorManager = sensorManager;
        this.spinner = spinner;
        this.context = context;

        darkList[0] = "Maguro";
        darkList[1] = "Hamachi";
        darkList[2] = "Salmon";
        darkList[3] = "Kohada";
        darkList[4] = "Ikura";
        darkList[5] = "Hamburg";

        lightmeter = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float lux = event.values[0];

        if(lux < 100 && flg == 0){
            flg = 1;
            ArrayAdapter adapter = new ArrayAdapter(context,R.layout.support_simple_spinner_dropdown_item,darkList);
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            Toast.makeText(context,"闇のSushiが解放されました",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void startSensor() {
        sensorManager.registerListener(this, lightmeter, sensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stopSensor() {
        sensorManager.unregisterListener(this);
    }
}
