package ru.mirea.bugraev.mireaproject.hardware;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import ru.mirea.bugraev.mireaproject.R;


public class SensorFragment extends Fragment{
    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private Sensor gravity;
    private Sensor pressure;
    SensorEventListener pressureListener;
    SensorEventListener gravityListener;
    private AccelerometrSensorListener accelerometrSensorListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_sensor,null);

        sensorManager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gravity=sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        pressure=sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

        pressureListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                TextView pressureValue=v.findViewById(R.id.pressure_value);
                pressureValue.setText(String.valueOf(sensorEvent.values[0]));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        gravityListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(sensorEvent.sensor.getType()==Sensor.TYPE_GRAVITY)
                {
                    TextView x=v.findViewById(R.id.gravity1);
                    TextView y=v.findViewById(R.id.gravity2);
                    TextView z=v.findViewById(R.id.gravity3);
                    x.setText("x:"+sensorEvent.values[0]);
                    y.setText("y:"+sensorEvent.values[1]);
                    z.setText("z:"+sensorEvent.values[2]);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };


        accelerometrSensorListener=new AccelerometrSensorListener(v);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(accelerometrSensorListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(gravityListener,gravity,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(pressureListener,pressure,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        sensorManager.unregisterListener(accelerometrSensorListener);
        sensorManager.unregisterListener(gravityListener);
        sensorManager.unregisterListener(pressureListener);
    }
}