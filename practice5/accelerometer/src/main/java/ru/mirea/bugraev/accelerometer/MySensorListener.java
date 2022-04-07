package ru.mirea.bugraev.accelerometer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

public class MySensorListener implements SensorEventListener {
    private TextView azimuthTextView;
    private TextView pitchTextView;
    private TextView rollTextView;

    public MySensorListener(TextView[] txw)
    {
        azimuthTextView=txw[0];
        pitchTextView=txw[1];
        rollTextView=txw[2];
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float valueAzimuth = event.values[0];
            float valuePitch = event.values[1];
            float valueRoll = event.values[2];
            azimuthTextView.setText("Azimuth: " + valueAzimuth);
            pitchTextView.setText("Pitch: " + valuePitch);
            rollTextView.setText("Roll: " + valueRoll);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
