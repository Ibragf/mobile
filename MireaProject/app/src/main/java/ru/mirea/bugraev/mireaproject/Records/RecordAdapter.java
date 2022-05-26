package ru.mirea.bugraev.mireaproject.Records;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

import ru.mirea.bugraev.mireaproject.R;
import ru.mirea.bugraev.mireaproject.ViewModel.ItemFragment;
import ru.mirea.bugraev.mireaproject.ViewModel.SharedViewModel;
import ru.mirea.bugraev.mireaproject.hardware.AudioFragment;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {
    private List<RecordModel> recordList= new LinkedList<>();
    private SharedViewModel viewModel;
    private Context context;
    private FragmentActivity activity;

    public RecordAdapter(Context context, FragmentActivity activity)
    {
        this.activity=activity;
        this.context=context;
    }

    @NonNull
    @Override
    public RecordAdapter.RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record_layout,parent,false);
        viewModel=new SharedViewModel();

        return new RecordViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordAdapter.RecordViewHolder holder, @SuppressLint("RecyclerView") int position) {
        TextView textView=holder.itemView.findViewById(R.id.recordTextView);
        textView.setText(recordList.get(position).text);
        textView=holder.itemView.findViewById(R.id.nameOfUser);
        textView.setText(recordList.get(position).name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.select(recordList.get(position));
                ItemFragment itemFragment=new ItemFragment();
                FragmentTransaction ft=activity.getSupportFragmentManager().beginTransaction();
                ft.replace()
                ft.show(itemFragment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    protected class RecordViewHolder extends RecyclerView.ViewHolder{
        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void setList(List<RecordModel> list)
    {
        recordList=list;
        notifyDataSetChanged();
    }

}

