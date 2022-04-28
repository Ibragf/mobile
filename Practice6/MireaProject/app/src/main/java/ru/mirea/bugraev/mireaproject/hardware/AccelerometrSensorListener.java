package ru.mirea.bugraev.mireaproject.hardware;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.view.View;
import android.widget.TextView;

import ru.mirea.bugraev.mireaproject.R;

public class AccelerometrSensorListener implements SensorEventListener {
    TextView first;
    TextView second;
    TextView third;

    public AccelerometrSensorListener(View view)
    {
        first= view.findViewById(R.id.AccelerometrTxw1);
        second=view.findViewById(R.id.AccelerometrTxw2);
        third=view.findViewById(R.id.AccelerometrTxw3);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float valueAzimuth = event.values[0];
            float valuePitch = event.values[1];
            float valueRoll = event.values[2];
            first.setText("Azimuth: " + valueAzimuth);
            second.setText("Pitch: " + valuePitch);
            third.setText("Roll: " + valueRoll);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
