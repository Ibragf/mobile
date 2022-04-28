package ru.mirea.bugraev.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText filename;
    private EditText fileText;
    private SharedPreferences preferences;
    private final String FILE_PATH="filename";
    private String filepath;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filename=findViewById(R.id.FileNameEditText);
        fileText=findViewById(R.id.FileTextEditText);
        preferences=getPreferences(MODE_PRIVATE);
        editor=preferences.edit();

        filepath=preferences.getString(FILE_PATH,FILE_PATH);
        if(filepath!=FILE_PATH);
        {
            File file=new File(filepath);
            FileInputStream inputStream = null;
            try {
                inputStream= new FileInputStream(file);
                byte[] bytes=new byte[inputStream.available()];
                inputStream.read(bytes);
                String text=new String(bytes);

                filename.setText(file.getName().toString());
                fileText.setText(text);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if(inputStream!=null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void onSaveClick(View view) throws IOException {
        String FileName=filename.getText().toString();
        if(FileName.contentEquals("")) FileName="defaultName.txt";
        filepath=String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS))+"/"+FileName;
        Log.d("PATH",filepath);
        File file=new File(filepath);
        Log.d("PATH",file.getCanonicalPath());
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        editor.putString(FILE_PATH,filepath);
        editor.apply();

        FileOutputStream outputStream;
        try {
            outputStream=new FileOutputStream(file);
            outputStream.write(fileText.getText().toString().getBytes());
            outputStream.close();
        }
        catch (Exception e)
        {
            Log.d("PATH",e.getMessage());
        }
    }
}