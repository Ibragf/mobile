package ru.mirea.bugraev.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class MyDateDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    public interface DatePickerListener{
        void onDataSet(DatePicker datePicker, int year, int month, int day);
    }

    DatePickerListener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar calendar=Calendar.getInstance();
        int day=Calendar.DAY_OF_MONTH;
        int month=Calendar.MONTH;
        int year=Calendar.YEAR;
        return new DatePickerDialog(getActivity(),this,year,month,day);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        mListener.onDataSet(datePicker,i,i1,i2);
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            mListener=(DatePickerListener) context;
        }
        catch (Exception e)
        {
            throw new ClassCastException(getActivity().toString()+"must implements DatePickerListener");
        }
    }
}
