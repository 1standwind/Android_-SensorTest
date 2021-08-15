package jp.ac.hokurikupc.sushiblade02;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private Button button;
    private ImageView imageView;
    private SensorManager sensorManager;
    private SensorData sensorData;
    private LightData lightData;
    private ProgressBar progressBar;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        spinner  = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new SpinnerClickListener());
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new ButtonClickListener());
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.maguro);

        textView = (TextView)findViewById(R.id.textView);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensorData = new SensorData(sensorManager,imageView,textView,progressBar,this);
        lightData = new LightData(sensorManager,spinner,this);

    }

    class ButtonClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            sensorData.max = 0;
            sensorData.flg = 0;
            textView.setText("spd = " + 0);
            progressBar.setProgress(0);
        }
    }

    class SpinnerClickListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            imageView.setImageURI(null);
            if(id == 0) { imageView.setImageResource(R.drawable.maguro);
            }else if(id == 1){ imageView.setImageResource(R.drawable.hamachi);
            }else if(id == 2){ imageView.setImageResource(R.drawable.salmon);
            }else if(id == 3){ imageView.setImageResource(R.drawable.kohada);
            }else if(id == 4){ imageView.setImageResource(R.drawable.ikura);
            }else if(id == 5){ imageView.setImageResource(R.drawable.hamburg);}
            sensorData.max = 0;
            sensorData.flg = 0;
            textView.setText("spd = " + 0);
            progressBar.setProgress(0);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }

    protected  void onResume(){
        super.onResume();
        sensorData.startSensor();
        lightData.startSensor();
    }

    @Override
    protected void onPause(){
        super.onPause();
        sensorData.stopSensor();
        lightData.startSensor();
    }
}
