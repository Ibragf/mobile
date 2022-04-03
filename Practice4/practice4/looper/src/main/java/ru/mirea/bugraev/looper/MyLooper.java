package ru.mirea.bugraev.looper;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;


public class MyLooper extends Thread{
    private int age = 20;
    Handler handler;

    @SuppressLint("HandlerLeak")
    @Override
    public void run()
    {
        Log.d("MyLooper","run");
        Looper.prepare();

        handler=new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                Log.d("MyLooper","runHandler");
                SystemClock.sleep(age);
                Log.d("MyLooper", msg.getData().getString("KEY") +",Возраст:"+age);
                //number++;
            }
        };
        Looper.loop();
    }
}
