package ru.mirea.bugraev.networkstate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView txw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txw=findViewById(R.id.txw);
        LiveData<String> networkLiveData=NetworkLiveData.getInstance(this);
        networkLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txw.setText(s);
            }
        });
    }
}