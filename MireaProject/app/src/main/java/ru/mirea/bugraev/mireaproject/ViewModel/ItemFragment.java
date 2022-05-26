package ru.mirea.bugraev.mireaproject.ViewModel;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ru.mirea.bugraev.mireaproject.App;
import ru.mirea.bugraev.mireaproject.R;
import ru.mirea.bugraev.mireaproject.Records.AppDataBase;
import ru.mirea.bugraev.mireaproject.Records.RecordDao;
import ru.mirea.bugraev.mireaproject.Records.RecordModel;

public class ItemFragment extends Fragment {
    private TextView txwName;
    private TextView txwContent;
    private AppDataBase db;
    private static RecordDao recordDao;
    private RecordModel recordModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_item, null);
        db= App.getInstance().getDatabase();
        recordDao=db.recordDao();

        txwName=view.findViewById(R.id.item_name);
        txwContent=view.findViewById(R.id.item_content);
        Button deleteBtn=view.findViewById(R.id.itemDeleteButton);

        SharedViewModel viewModel=new SharedViewModel();
        viewModel.getSelected().observe(getViewLifecycleOwner(), new Observer<RecordModel>() {
            @Override
            public void onChanged(RecordModel Model) {
                recordModel=Model;
                txwName.setText(recordModel.name);
                txwContent.setText(recordModel.text);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recordModel!=null)
                {
                    recordDao.delete(recordModel);
                }
                getActivity().onBackPressed();
            }
        });

        return view;
    }
}