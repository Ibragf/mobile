package ru.mirea.bugraev.multiactivity;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.w3c.dom.Text;

import ru.mirea.bugraev.multiactivity.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivitySecondBinding binding;
    private TextView txw;
    private String TAG=MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_second);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        txw=(TextView) findViewById(R.id.textview_first);
        String s=(String)getIntent().getSerializableExtra("key");
        txw.setText(s);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart()");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG,"onRestoreInstanceState");
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        Log.i(TAG,"onPostCreate()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume()");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i(TAG,"onPostResume()");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i(TAG,"onAttachedToWindow()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause()");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.i(TAG,"onSaveInstanceState()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy()");
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i(TAG,"onDetachedFromWindow()");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"onRestart()");
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_second);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}