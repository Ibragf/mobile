package ru.mirea.bugraev.mireaproject.hardware;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import ru.mirea.bugraev.mireaproject.MyMusicPlayer;
import ru.mirea.bugraev.mireaproject.R;


public class AudioFragment extends Fragment {
    private static final int REQUEST_CODE_PERMISSION = 100;
    private boolean isWork;
    private boolean isPLay;
    private String[] PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO
    };
    private MediaRecorder mediaRecorder;
    private File audioFile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_audio,null);
        Button startRecord=v.findViewById(R.id.audioStartRecordBtn);
        Button stopRecord=v.findViewById(R.id.audioStopRecordBtn);
        Button pauseRecord=v.findViewById(R.id.audioPausePlayBtn);
        Button playerStart=v.findViewById(R.id.startPlayerBtn);
        Button playerStop=v.findViewById(R.id.stopPlayerBtn);

        playerStart.setEnabled(false);
        playerStop.setEnabled(false);
        pauseRecord.setEnabled(false);
        stopRecord.setEnabled(false);

        isWork=hasPermession(PERMISSIONS);
        if(!isWork)
        {
            ActivityCompat.requestPermissions(getActivity(),PERMISSIONS,REQUEST_CODE_PERMISSION);
        }

        startRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    playerStart.setEnabled(false);
                    playerStop.setEnabled(false);

                    startRecord.setEnabled(false);
                    stopRecord.setEnabled(true);
                    pauseRecord.setEnabled(true);
                    stopRecord.requestFocus();
                    StartRecording();
                    isPLay = true;
                } catch (Exception e) {
                    Log.e("Ошибочка", "Caught io exception " + e.getMessage());
                }
            }
        });
        stopRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopRecord.setEnabled(false);
                pauseRecord.setEnabled(false);
                startRecord.setEnabled(true);
                startRecord.requestFocus();
                StopRecording();
                isPLay = false;

                playerStart.setEnabled(true);
            }
        });
        pauseRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                {
                    if (isPLay) {
                        mediaRecorder.pause();
                        isPLay = false;
                        pauseRecord.setText("Продл.");
                    } else {
                        mediaRecorder.resume();
                        isPLay = true;
                        pauseRecord.setText("Пауза");
                    }
                }
            }
        });
        playerStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRecord.setEnabled(false);

                playerStart.setEnabled(false);
                playerStop.setEnabled(true);
                /*Intent intent=new Intent(getContext(),MyMusicPlayer.class);
                intent.putExtra("file",audioFile.getAbsolutePath());
                getActivity().startService(intent);*/
                getActivity().startService(new Intent(getContext(),MyMusicPlayer.class));

            }
        });
        playerStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRecord.setEnabled(true);

                playerStop.setEnabled(false);
                playerStart.setEnabled(true);
                getActivity().stopService(new Intent(getContext(),MyMusicPlayer.class));
            }
        });

        return v;
    }

    private void StartRecording() throws IOException {
        mediaRecorder=new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        // выбор формата данных
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        // выбор кодека
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        audioFile=new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_MUSIC),"audioRecord.3gp");
        mediaRecorder.setOutputFile(audioFile.getAbsolutePath());
        mediaRecorder.prepare();
        mediaRecorder.start();
        Toast.makeText(getContext(),"Запись началась",Toast.LENGTH_SHORT).show();
    }

    private void StopRecording()
    {
        if(mediaRecorder!=null)
        {
            mediaRecorder.stop();
            mediaRecorder.reset();
            mediaRecorder.release();
            Toast.makeText(getContext(),"Запись закончена", Toast.LENGTH_SHORT).show();
            mediaRecorder=null;
            //audioFile.delete();
        }
    }

    private boolean hasPermession(String[] permissions)
    {
        if(permissions!=null)
        {
            for(String permission : permissions)
            {
                if(ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(),permission)!= PackageManager.PERMISSION_GRANTED)
                {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_CODE_PERMISSION)
        {
            if(grantResults.length>0)
            {
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED &&
                        grantResults[1]==PackageManager.PERMISSION_GRANTED)
                {
                    isWork=true;
                }
                else
                {
                    isWork=false;
                }
            }
        }
    }
}