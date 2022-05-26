package ru.mirea.bugraev.mireaproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import ru.mirea.bugraev.mireaproject.Records.AppDataBase;
import ru.mirea.bugraev.mireaproject.Records.RecordAdapter;
import ru.mirea.bugraev.mireaproject.Records.RecordDao;
import ru.mirea.bugraev.mireaproject.Records.RecordModel;

public class RecordsFragment extends Fragment {
    private static RecordAdapter adapter;
    private RecyclerView recyclerView;
    private String name;
    SharedPreferences preferences;
    private AppDataBase db;
    private static RecordDao recordDao;
    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_records,null);
        preferences=getActivity().getPreferences(Context.MODE_PRIVATE);
        name=preferences.getString("name","empty");
        setInitialSavedState();
        return v;
    }

    private void setInitialSavedState() {
        recyclerView=v.findViewById(R.id.recyclerView);
        adapter=new RecordAdapter(getContext(), getActivity());
        recyclerView.setAdapter(adapter);
        db=App.getInstance().getDatabase();
        recordDao= db.recordDao();
        adapter.setList(addList());
    }

    private List<RecordModel> addList()
    {
        List<RecordModel> recordList=recordDao.getAll();
        //List<RecordModel> recordFromDao=recordDao.getAll();
        /*RecordModel record=new RecordModel("","asd");
        recordList.add(record);*/

        return recordList;
    }

    public static void refresh()
    {
        List<RecordModel> recordList=recordDao.getAll();
        adapter.setList(recordList);
    }

}