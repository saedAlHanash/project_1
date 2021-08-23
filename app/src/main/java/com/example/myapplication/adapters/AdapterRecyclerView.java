package com.example.myapplication.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ItemTask;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.holder> {
    ArrayList<ItemTask> list;
    onRecyclerViewClickListener RecyclerListener;
    Context context;
   TypedArray iconArray;

    public AdapterRecyclerView(ArrayList<ItemTask> list, Context context, onRecyclerViewClickListener recyclerListener) {
        this.list = list;
        this.context = context;
        this.RecyclerListener = recyclerListener;
        this.iconArray = context.getResources().obtainTypedArray(R.array.iconArray);
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
        holder.imageView.setImageDrawable(iconArray.getDrawable(list.get(position).getPositionIcon()));
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


    public ItemTask getItem(int i) {
        return list.get(i);
    }


    public void  refreshFromDB(ArrayList<ItemTask> list){
        this.list =list;
        notifyDataSetChanged();
    }

    public void refresh(int position) {
        notifyItemChanged(position);
    }

    public class holder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener {
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
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View v) {
            RecyclerListener.onItemClickListener(getItem(getAdapterPosition()), getAdapterPosition());
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(getAdapterPosition(), 0, 0, "Edit");
            menu.add(getAdapterPosition(), 1, 1, "Delete");
            menu.add(getAdapterPosition(), 2, 2, "Done Task");
        }
    }


    public interface onRecyclerViewClickListener {
        void onItemClickListener(ItemTask task, int position);
    }
}
