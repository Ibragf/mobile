package ru.mirea.bugraev.dialog;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class MyTimeDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        mListener.onTimeSet(timePicker,i,i1);
    }

    public interface TimePickerListener{
        void onTimeSet(TimePicker timePicker, int hour,int minute);
    }

    TimePickerListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            mListener=(TimePickerListener) context;
        }
        catch (Exception e)
        {
            throw new ClassCastException(getActivity().toString()+"must implements TimePickerListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Calendar calendar=Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR);
        int minute=calendar.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(),this,hour,minute,true);
    }


}
