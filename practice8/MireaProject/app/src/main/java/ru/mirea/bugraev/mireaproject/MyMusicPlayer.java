package ru.mirea.bugraev.mireaproject;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

public class MyMusicPlayer extends Service {
    private MediaPlayer mediaPlayer;
    private File audioFile;
    private final String EXTRA_NAME="file";

    public MyMusicPlayer() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        boolean hasExtra=intent.hasExtra("audio");
        if(hasExtra)
        {
            mediaPlayer = MediaPlayer.create(this, R.raw.siuuu);
        }
        else {
            audioFile = new File(getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_MUSIC), "audioRecord.3gp");
            Log.d("audio", String.valueOf(getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_MUSIC)));
            if (audioFile.exists()) {
                mediaPlayer = MediaPlayer.create(this, Uri.fromFile(audioFile));
            }
        }
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        Toast.makeText(this,"Воспроизведение аудио",Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer=null;
        Toast.makeText(this,"Воспроизведение аудио закончено",Toast.LENGTH_SHORT).show();
    }
}