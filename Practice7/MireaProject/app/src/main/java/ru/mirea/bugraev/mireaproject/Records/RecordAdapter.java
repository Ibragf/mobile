package ru.mirea.bugraev.mireaproject.Records;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

import ru.mirea.bugraev.mireaproject.R;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {
    private List<RecordModel> recordList= new LinkedList<>();

    @NonNull
    @Override
    public RecordAdapter.RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record_layout,parent,false);

        return new RecordViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordAdapter.RecordViewHolder holder, int position) {
        TextView textView=holder.itemView.findViewById(R.id.recordTextView);
        textView.setText(recordList.get(position).text);
        textView=holder.itemView.findViewById(R.id.nameOfUser);
        textView.setText(recordList.get(position).name);
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

