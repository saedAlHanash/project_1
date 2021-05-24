package com.example.myapplication.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class adapterIconPicker extends RecyclerView.Adapter<adapterIconPicker.holder> {

    Context context;
    onIconPickerClickListener listener;
    TypedArray list1;

    public adapterIconPicker(Context context, onIconPickerClickListener listener, int list1) {
        this.context = context;
        this.listener = listener;
        this.list1= context.getResources().obtainTypedArray(list1);
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.icon_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.imgButton.setBackground(list1.getDrawable(position));
    }

    @Override
    public int getItemCount() {
        return list1.length();
    }

    public class holder extends RecyclerView.ViewHolder  {
        ImageButton imgButton;
        public holder(@NonNull View itemView) {
            super(itemView);
            imgButton = itemView.findViewById(R.id.icon_button);
            imgButton.setOnClickListener(v -> listener.onIconClickListener(getAdapterPosition(),list1));
        }
    }



    public interface onIconPickerClickListener {
        void onIconClickListener( int position,TypedArray array);
    }
}
