package ru.mirea.bugraev.mireaproject;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.bugraev.mireaproject.Records.AppDataBase;
import ru.mirea.bugraev.mireaproject.Records.RecordAdapter;
import ru.mirea.bugraev.mireaproject.Records.RecordDao;
import ru.mirea.bugraev.mireaproject.Records.RecordModel;
import ru.mirea.bugraev.mireaproject.databinding.ActivityMainBinding;
import ru.mirea.bugraev.mireaproject.hardware.HardwareActivity;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private TextView result;
    SharedPreferences preferences;
    AppDataBase db;
    RecordDao recordDao;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferences=getPreferences(MODE_PRIVATE);
        db=App.getInstance().getDatabase();
        recordDao= db.recordDao();
        name=preferences.getString("name","empty");

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_gallery,
                R.id.nav_slideshow,
                R.id.nav_brouser,
                R.id.nav_calculator,
                R.id.nav_music,
                R.id.nav_settings,
                R.id.nav_records,
                R.id.nav_weather)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public void onClickHardwareActivity(View view) {
        Intent intent=new Intent(MainActivity.this, HardwareActivity.class);
        startActivity(intent);
    }


    public void onClickRecordAdd(View view) {
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_layout);

        Button addRecord=dialog.findViewById(R.id.addRecordButton_);
        EditText editText=dialog.findViewById(R.id.addRecordEditText_);
        addRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecordModel record=new RecordModel(name,editText.getText().toString());
                recordDao.insert(record);
            }
        });

        dialog.setTitle("Добавить историю");
        dialog.show();
    }
}