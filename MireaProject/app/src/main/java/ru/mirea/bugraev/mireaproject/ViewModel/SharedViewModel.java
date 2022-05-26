package ru.mirea.bugraev.mireaproject.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.mirea.bugraev.mireaproject.Records.RecordModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<RecordModel> selected=new MutableLiveData<>();

    public void select(RecordModel recordModel)
    {
        selected.setValue(recordModel);
    }

    public LiveData<RecordModel> getSelected()
    {
        return selected;
    }
}
