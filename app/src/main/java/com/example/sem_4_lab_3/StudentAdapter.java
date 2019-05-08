package com.example.sem_4_lab_3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private ArrayList<ItemStudent> mItemArrayList;

    public StudentAdapter(ArrayList<ItemStudent> itemStudents){
        mItemArrayList = itemStudents;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_student, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemStudent currentItem = mItemArrayList.get(position);

        holder.mTextView_name.setText(currentItem.getmText_name());
        holder.mTextView_date.setText(currentItem.getmDate_Insert());
    }

    @Override
    public int getItemCount() {
        return mItemArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView_name;
        public TextView mTextView_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView_name = itemView.findViewById(R.id.textView_row);
            mTextView_date = itemView.findViewById(R.id.description);
        }
    }
}
