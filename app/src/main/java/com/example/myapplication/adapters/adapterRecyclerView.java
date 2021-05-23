package com.example.myapplication.adapters;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.itemTask;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class adapterRecyclerView extends RecyclerView.Adapter<adapterRecyclerView.holder> {
    ArrayList<itemTask> list;
    onRecyclerViewClickListener RecyclerListener;
    Context context;

    public adapterRecyclerView(ArrayList<itemTask> list, Context context, onRecyclerViewClickListener recyclerListener) {
        this.list = list;
        this.context = context;
        this.RecyclerListener = recyclerListener;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.textView.setText(list.get(position).getTitle());
        holder.imageView.setVisibility(list.get(position).getIconVisibility());
        holder.imageView.getBackground().setColorFilter(list.get(position).getColor(), PorterDuff.Mode.SRC_ATOP);
        holder.imageView.setImageDrawable(list.get(position).getDrawable());
        holder.point.setVisibility(list.get(position).getPointVisibility());
        holder.imageView.setColorFilter(list.get(position).getIconColor(), PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public void deleteItem(int i) {
        list.remove(i);
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, list.size());
    }



    public itemTask getItem(int i) {
        return list.get(i);
    }

    public void addItem(itemTask item) {
        list.add(item);
        notifyDataSetChanged();
    }

    public void changeBackgroundColor(int i,int color)
    {
        getItem(i).setColor(color);
        notifyItemChanged(i);
    }

    //do inflate for item in layout
    public class holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        ImageView imageView;
        CircleImageView point;

        onRecyclerViewClickListener listener;

        public holder(@NonNull final View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_task_tv_under_ImgV);
            imageView = itemView.findViewById(R.id.item_task_recycler_view);
            point = itemView.findViewById(R.id.item_task_point);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            RecyclerListener.onItemClickListener(v, getAdapterPosition());
        }
    }



    public interface onRecyclerViewClickListener {
        void onItemClickListener(View view, int position);
    }
}
