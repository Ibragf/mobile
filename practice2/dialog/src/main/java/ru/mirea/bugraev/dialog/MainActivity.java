package ru.mirea.bugraev.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements MyTimeDialogFragment.TimePickerListener, MyDateDialogFragment.DatePickerListener {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView) findViewById(R.id.date_picker);
    }

    public void onCliclShowDialog(View view) {
        MyDialogFragment dialogFragment = new MyDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "mirea");

    }

    public void onOkClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Иду дальше\"!",
                Toast.LENGTH_LONG).show();
    }
    public void onCancelClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Нет\"!",
                Toast.LENGTH_LONG).show();
    }
    public void onNeutralClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"На паузе\"!",
                Toast.LENGTH_LONG).show();
    }

    public void onClickTimeDialog(View view) {
        MyTimeDialogFragment timeDialogFragment=new MyTimeDialogFragment();
        timeDialogFragment.show(getSupportFragmentManager(),"timePicker");
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        String s=hour+":"+minute;
        Date date = null;
        try {
            date=new SimpleDateFormat("hh:mm").parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        s=new SimpleDateFormat("hh:mm",Locale.getDefault()).format(date);

        textView.setText(s);
    }

    @Override
    public void onDataSet(DatePicker datePicker, int year, int month, int day) {
        String s=year+"-"+month+"-"+day;
        Date date=null;
        try{
            date=new SimpleDateFormat("yyyy-mm-dd").parse(s);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        s=new SimpleDateFormat("dd.MM.yyyy",Locale.getDefault()).format(date);
        textView.setText(s);
    }

    public void onClickDateDialog(View view) {
        MyDateDialogFragment dateDialogFragment=new MyDateDialogFragment();
        dateDialogFragment.show(getSupportFragmentManager(),"datepicker");
    }

    public void onClickProgressDialog(View view) {
        MyProgressDialogFragment progressDialogFragment=new MyProgressDialogFragment();
        progressDialogFragment.show(getSupportFragmentManager(),"progressdialog");
    }
}