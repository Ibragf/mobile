package ru.mirea.bugraev.practice6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private TextView textView;
    private SharedPreferences preferences;
    final String SAVED_TEXT="saved_text";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.editTextTextPersonName);
        textView=findViewById(R.id.textView);
        preferences=getPreferences(MODE_PRIVATE);
    }

    public void onSaveText(View view) {
        SharedPreferences.Editor editor=preferences.edit();
        String text=editText.getText().toString();
        if(text.contentEquals("")) text="string is empty";
        Log.d("TextSaved",text);
        editor.putString(SAVED_TEXT,text);
        editor.apply();
    }

    public void onLoadText(View view) {
        String text=preferences.getString(SAVED_TEXT,"empty");
        textView.setText(text);
    }
}