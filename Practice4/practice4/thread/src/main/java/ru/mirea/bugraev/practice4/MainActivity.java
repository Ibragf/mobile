package ru.mirea.bugraev.practice4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String TAG=MainActivity.class.getSimpleName();
    int counter=0;
    EditText lessonsCount;
    TextView infoTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        infoTextView = findViewById(R.id.textView);
        lessonsCount=findViewById(R.id.editTextLessons);
        Thread mainThread = Thread.currentThread();
        infoTextView.setText("Текущий поток: " + mainThread.getName());
        //Меняем имя и выводим в текстовом поле
        mainThread.setName("MireaThread");
        infoTextView.append("\n Новое имя потока: " + mainThread.getName());
        infoTextView.append("\nПриоритет: "+mainThread.getPriority());
    }

    public void onClick(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    infoTextView.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                double countOfLessons=Double.valueOf(lessonsCount.getText().toString());
                                double result=countOfLessons/30;
                                infoTextView.setText(String.valueOf(result));
                            }
                            catch (Exception e)
                            {
                                Log.e(TAG,e.getLocalizedMessage());
                            }
                        }
                    });
                }
                catch (Exception e)
                {
                    Log.e(TAG,e.getLocalizedMessage());
                }
            }
        }).start();
    }
}