package ru.mirea.bugraev.resultactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DataActivity extends AppCompatActivity {
    private EditText plaintext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        plaintext=findViewById(R.id.editUniversity);
    }

    public void onClickSendData(View view) {
        Intent intent=new Intent();
        intent.putExtra("name", plaintext.getText().toString());
        setResult(RESULT_OK,intent);
        finish();
    }
}