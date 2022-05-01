package ru.mirea.bugraev.mireaproject.hardware;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import ru.mirea.bugraev.mireaproject.R;

public class HardwareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hardware);
    }

    public void onSensorClick(View view) {
        SensorFragment sensorFragment=new SensorFragment();
        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container,sensorFragment);
        ft.commit();
    }

    public void onCameraClick(View view) {
        CameraFragment cameraFragment=new CameraFragment();
        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container,cameraFragment);
        ft.commit();
    }


    public void onAudioButtonClick(View view) {
        AudioFragment audioFragment=new AudioFragment();
        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container,audioFragment);
        ft.commit();
    }
}