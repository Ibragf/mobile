package ru.mirea.bugraev.mireaproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SettingFragment extends Fragment {
    private SharedPreferences preferences;
    private final String NAME="name";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_setting, null);

        preferences=getActivity().getPreferences(Context.MODE_PRIVATE);

        SharedPreferences.Editor editor=preferences.edit();
        Button saveButton=v.findViewById(R.id.saveNameButton);
        EditText editText=v.findViewById(R.id.nameEditText);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=editText.getText().toString();
                if(name.contentEquals("")) name="Default name";
                editor.putString(NAME, name);
                editor.apply();
            }
        });


        return v;
    }
}