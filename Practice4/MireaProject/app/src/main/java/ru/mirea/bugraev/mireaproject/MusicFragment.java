package ru.mirea.bugraev.mireaproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MusicFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_music,null);

        Button playButton=v.findViewById(R.id.buttonMusicPlay);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startService(new Intent(getActivity().getApplicationContext(),MyMusicPlayer.class));
            }
        });

        Button stopButton=v.findViewById(R.id.buttonMusicStop);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().stopService(new Intent(getActivity().getApplicationContext(),MyMusicPlayer.class));
            }
        });

        return v;
        //Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_music, container, false);
    }
}