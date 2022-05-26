package ru.mirea.bugraev.mireaproject;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textservice.TextInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

public class WeatherFragment extends Fragment {
    private static final String key="9ea0de1bdb116b86df055e01bb0695f9";
    private String url;
    private EditText cityEditText;
    private TextView temp;
    private TextView pressure;
    private TextView humidity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_weather, null);

        temp=v.findViewById(R.id.tempTextView);
        pressure=v.findViewById(R.id.pressureTextView);
        humidity=v.findViewById(R.id.humidityTextView);

        cityEditText=v.findViewById(R.id.cityEditText);
        Button OkButton=v.findViewById(R.id.WeatherOkButton);
        OkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city=cityEditText.getText().toString();
                if(TextUtils.isEmpty(city))
                {
                    cityEditText.setError("Required");
                }
                else
                {
                    url="https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+key+"&units=metric";

                    try {
                        new GetWeatherData().execute(url);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });

        return v;
    }

    private class GetWeatherData extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection=null;
            BufferedReader reader=null;

            try {
                URL url =new URL(strings[0]);
                connection=(HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream=connection.getInputStream();
                //Log.d(MainActivity.class.getSimpleName(),stream.toString());
                reader=new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer=new StringBuffer();
                String line="";

                while((line=reader.readLine())!=null)
                {
                    buffer.append(line).append("\n");
                    return buffer.toString();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if(connection!=null)
                {
                    connection.disconnect();
                }
                if(reader!=null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            JSONObject jsonObject=null;
            if(s!=null)
            {
                try {
                    jsonObject=new JSONObject(s);
                    temp.setText("Температура : "+String.valueOf(jsonObject.getJSONObject("main").getDouble("temp"))+"° C");
                    DecimalFormat myFormat=new DecimalFormat("###.##");
                    String output=myFormat.format(jsonObject.getJSONObject("main").getDouble("pressure")/1.3332239);
                    pressure.setText("Давление : "+output+" мм.тр.ст.");
                    humidity.setText("Влажность : "+String.valueOf(jsonObject.getJSONObject("main").getInt("humidity"))+" %");

                } catch (JSONException e) {
                    Log.d(MainActivity.class.getSimpleName(),e.getMessage());
                }
            }
        }
    }
}



















