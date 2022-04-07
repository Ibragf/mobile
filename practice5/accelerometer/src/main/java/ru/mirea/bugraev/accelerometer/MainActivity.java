package ru.mirea.bugraev.accelerometer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    private TextView azimuthTextView;
    private TextView pitchTextView;
    private TextView rollTextView;
    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private MySensorListener mySensorListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager =
                (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        accelerometerSensor = sensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        azimuthTextView = findViewById(R.id.textViewAzimuth);
        pitchTextView = findViewById(R.id.textViewPitch);
        rollTextView = findViewById(R.id.textViewRoll);

        mySensorListener=new MySensorListener(new TextView[]{azimuthTextView,pitchTextView,rollTextView});
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(mySensorListener);
    }
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(mySensorListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }


}