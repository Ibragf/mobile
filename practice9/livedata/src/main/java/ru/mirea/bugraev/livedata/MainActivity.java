package ru.mirea.bugraev.livedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements Observer<Long> {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.txw);
        TimeLiveData.getTime().observe(this,this);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TimeLiveData.setTime();
            }
        }, 5000);
    }


    @Override
    public void onChanged(Long aLong) {
        Log.d(MainActivity.class.getSimpleName(), aLong + "");
        textView.setText("" + aLong);
    }
}