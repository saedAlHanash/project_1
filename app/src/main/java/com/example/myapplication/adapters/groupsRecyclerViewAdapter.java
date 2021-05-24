package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class groupsRecyclerViewAdapter extends RecyclerView.Adapter<groupsRecyclerViewAdapter.holder> {

    ArrayList<String> list;
    Context context;
    onRecyclerViewClickListener listener;

    public groupsRecyclerViewAdapter(ArrayList<String> list, Context context ,onRecyclerViewClickListener listener) {
        this.list = list;
        this.context = context;
        this.listener =listener;
    }

    @NonNull
    @Override
    public groupsRecyclerViewAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.groups_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull groupsRecyclerViewAdapter.holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public holder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            listener.onGroupClickListener(v,getAdapterPosition());
        }
    }

    public interface onRecyclerViewClickListener {
        void onGroupClickListener(View view, int position);
    }
}
