package com.example.smartrestro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;
    List<String> menuList;
    public RecyclerViewAdapter (Context context,List<String> TempList){
        this.menuList = TempList;
        this.context = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);

        RecyclerView.ViewHolder viewHolder = new RecyclerView.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        String string = menuList.get(position);

        holder.

        holder.StudentNumberTextView.setText(studentDetails.getStudentPhoneNumber());

    }

}
