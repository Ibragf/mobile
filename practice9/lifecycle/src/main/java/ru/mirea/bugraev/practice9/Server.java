package ru.mirea.bugraev.practice9;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class Server implements LifecycleObserver {
    private String TAG="lifecycle";

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void connect() {
        Log.d(TAG,"connect to web-server");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void disconnect() {
        Log.d(TAG,"disconnect");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void oncreate()
    {
        Log.d(TAG,"onCreate");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void ondestroy()
    {
        Log.d(TAG,"onDestroy");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onpause()
    {
        Log.d(TAG, "onPause");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onresume()
    {
        Log.d(TAG,"onResume");
    }
}
