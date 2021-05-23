package com.example.myapplication.UI.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.adapterIconPicker;
import com.example.myapplication.adapters.adapterRecyclerView;
import com.example.myapplication.itemTask;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import petrov.kristiyan.colorpicker.ColorPicker;
public class personalFragment extends Fragment   implements com.example.myapplication.adapters.adapterRecyclerView.onRecyclerViewClickListener
        , adapterIconPicker.onIconPickerClickListener {
    View view;
    RecyclerView recyclerView;
    adapterRecyclerView adapterRecyclerView;
    Dialog dialog, dialogIcons;
    FloatingActionButton fab;
    Button dialogAdd, dialogDate;
    ImageButton DialogColorButton, dialogIconButton;
    TextView tvDialogTxt, tvDialogTitle;
    Drawable drawable;
    TypedArray array;
    ArrayList<itemTask> list  = new ArrayList<>();
    int itemColor, iconColor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personal, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_home_fragment);
        fab = view.findViewById(R.id.floatingActionButton);
        array = getResources().obtainTypedArray(R.array.colors_aray);

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.add_layout);
        adapterRecyclerView = new adapterRecyclerView(list, getContext(), this);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapterRecyclerView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAdder();
                dialogAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemTask task = new itemTask();
                        task.setColor(itemColor);
                        task.setTitle(tvDialogTitle.getText().toString());
                        task.setDrawable(drawable);
                        task.setIconColor(iconColor);
                        adapterRecyclerView.addItem(task);
                        restDialogItem();
                        dialog.cancel();
                    }

                });
            }
        });
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onItemClickListener(View view, int position) {

    }

    @Override
    public void onIconClickListener(int position, TypedArray array) {
        drawable = array.getDrawable(position);
        dialogIcons.dismiss();
        dialogIconButton.setImageDrawable(drawable);
    }

    public void dialogAdder() {

        WindowManager.LayoutParams wlp = dialog.getWindow().getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        dialog.getWindow().setAttributes(wlp);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogInflateItem();

        dialog.show();

        DialogColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorDialog();
            }
        });
        dialogIconButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iconsDialog();
            }
        });
    }

    public void dialogInflateItem() {
        dialogAdd = dialog.findViewById(R.id.dialog_add_add_btn);
        DialogColorButton = dialog.findViewById(R.id.dialog_color_btn);
        dialogIconButton = dialog.findViewById(R.id.dialog_icon_btn);
        dialogDate = dialog.findViewById(R.id.dialog_add_date_btn);
        tvDialogTitle = dialog.findViewById(R.id.dialog_add_title_task);
        tvDialogTxt = dialog.findViewById(R.id.dialog_add_text);
    }

    private void restDialogItem() {
        tvDialogTxt.setText("");
        tvDialogTitle.setText("");
        drawable = null;
        dialogIconButton.setImageDrawable(null);
    }

    public void colorDialog() {
        final ColorPicker colorPicker = new ColorPicker((Activity) requireContext());
        colorPicker
                .setTitle("colors")
                .setDefaultColorButton(Color.parseColor("#f84c44"))
                .setColors(R.array.colors_Icons_aray)
                .setColumns(5)
                .setRoundColorButton(true)
                .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {


                    @Override
                    public void onChooseColor(int position, int color) {
                        itemColor = array.getColor(position, Color.WHITE);
                        iconColor = color;
                        DialogColorButton.getBackground().setTint(itemColor);
                        dialogIconButton.getBackground().setTint(itemColor);
                        dialogIconButton.setColorFilter(iconColor);

                    }


                    @Override
                    public void onCancel() {
                    }

                })
                .addListenerButton("newButton", new ColorPicker.OnButtonListener() {
                    @Override
                    public void onClick(View v, int position, int color) {

                    }
                }).show();
    }

    public void iconsDialog() {
        dialogIcons = new Dialog(getContext());
        dialogIcons.setContentView(R.layout.icon_recycler_view);
        RecyclerView recyclerView = dialogIcons.findViewById(R.id.icon_RecyclerView);
        adapterIconPicker adapterIconPicker = new adapterIconPicker(getContext(), this, R.array.iconArray);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 4);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapterIconPicker);
        dialogIcons.show();

    }
}