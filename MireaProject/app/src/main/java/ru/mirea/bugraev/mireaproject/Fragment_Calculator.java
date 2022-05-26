package ru.mirea.bugraev.mireaproject;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;




public class Fragment_Calculator extends Fragment {
    private EditText first_num;
    private EditText second_num;
    private TextView result;
    private String string_r="=";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v=inflater.inflate(R.layout.fragment__calculator, null);

        first_num=v.findViewById(R.id.first_number);
        second_num=v.findViewById(R.id.second_number);

        result=(TextView) v.findViewById(R.id.result_txw);

        Button button_plus=(Button) v.findViewById(R.id.button_plus);
        Button button_minus=(Button) v.findViewById(R.id.button_minus);
        Button button_multi=(Button) v.findViewById(R.id.button_multi);
        Button button_div=(Button) v.findViewById(R.id.button_div);



        button_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    result.setText(string_r);
                    int num = Integer.valueOf(first_num.getText().toString()) + Integer.valueOf(second_num.getText().toString());
                    string_r += Integer.toString(num);
                    result.setText(string_r);
                    string_r = "=";
                } catch (Exception e) {
                    result.setText("Ошибка");
                }
            }
        });

        button_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    result.setText(string_r);
                    int num = Integer.valueOf(first_num.getText().toString()) - Integer.valueOf(second_num.getText().toString());
                    string_r += Integer.toString(num);
                    result.setText(string_r);
                    string_r = "=";
                } catch (Exception e) {
                    result.setText("Ошибка");
                }
            }
        });

        button_multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    result.setText(string_r);
                    int num = Integer.valueOf(first_num.getText().toString()) * Integer.valueOf(second_num.getText().toString());
                    string_r += Integer.toString(num);
                    result.setText(string_r);
                    string_r = "=";
                }
                catch (Exception e)
                {
                    result.setText("Ошибка");
                }
            }
        });

        button_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    result.setText(string_r);
                    int num = Integer.valueOf(first_num.getText().toString()) / Integer.valueOf(second_num.getText().toString());
                    string_r += Integer.toString(num);
                    result.setText(string_r);
                    string_r = "=";
                }
                catch (Exception e)
                {
                    result.setText("Ошибка");
                }
            }
        });





        return v;
        //return inflater.inflate(R.layout.fragment__calculator, container, false);
    }
}