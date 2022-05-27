package ru.mirea.bugraev.mireaproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.mirea.bugraev.mireaproject.hardware.AudioFragment;

public class RecordContainer extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_record_container,null);

        RecordsFragment recordsFragment=new RecordsFragment();
        FragmentTransaction ft= getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.record_container,recordsFragment);
        ft.commit();

        return v;
    }
}