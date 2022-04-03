package ru.mirea.bugraev.loadermanger;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class MyLoader extends AsyncTaskLoader<String> {
    private String edittext;
    public static final String ARG_WORD = "word";

    public MyLoader(@NonNull Context context, Bundle args) {
        super(context);
        if (args != null)
            edittext = args.getString(ARG_WORD);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        // emulate long-running operation
        //SystemClock.sleep(5000);
        edittext=shuffle(edittext);
        return edittext;
    }

    private String shuffle(String str)
    {
        char[] chars=str.toCharArray();
        double random=0.4;
        while(random>0.3)
        {
            for (int i=0;i<chars.length-1;i++)
            {
                random=Math.random();
                if(random>0.4)
                {
                    char t=chars[i];
                    chars[i]=chars[i+1];
                    chars[i+1]=t;
                }
            }
            random=Math.random();
        }
        str="";
        for (char item:chars) {
            str+=item;
        }
        return str;
    }
}
