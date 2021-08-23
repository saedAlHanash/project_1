package com.example.myapplication.dialogs;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.LayoutRes;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.adapters.adapterIconPicker;
import java.util.Calendar;

import petrov.kristiyan.colorpicker.ColorPicker;

public class addingDialog {
    Context context;
    Dialog dialog, dialogIcons;
    Button dialogAdd, dialogDate;
    ImageButton dialogColorButton, dialogIconButton;
    TextView tvDialogTxt, tvDialogTitle;
    TypedArray colorArray,iconArray,iconColorArray;
    int iconPosition;
    int itemColor, iconColor;
    String endDate;
    Calendar calendar = Calendar.getInstance();
    @LayoutRes
    int res;

    public String getEndDate() {
        return endDate;
    }

    public int getIconPosition() {
        return iconPosition;
    }

    public void setIconPosition(int iconPosition) {
        this.iconPosition = iconPosition;
    }

    public int getItemColor() {
        return itemColor;
    }

    public int getIconColor() {
        return iconColor;
    }

    public Dialog getDialog() {
        return dialog;
    }


    public TextView getTvDialogTxt() {
        return tvDialogTxt;
    }

    public void setTvDialogTxt(String tvDialogTxt) {
        this.tvDialogTxt.setText(tvDialogTxt);
    }

    public TextView getTvDialogTitle() {
        return tvDialogTitle;
    }

    public void setTvDialogTitle(String tvDialogTitle) {
        this.tvDialogTitle.setText(tvDialogTitle);
    }

    public addingDialog(Context context, @LayoutRes int res) {
        this.context = context;
        this.res = res;
        this.colorArray = context.getResources().obtainTypedArray(R.array.colors_aray);
        this.iconArray = context.getResources().obtainTypedArray(R.array.iconArray);
        this.iconColorArray = context.getResources().obtainTypedArray(R.array.colors_Icons_aray);
        this.iconColor = this.iconColorArray.getColor(0,Color.CYAN);
        this.itemColor = this.colorArray.getColor(0,Color.CYAN);
        this.iconPosition =0;
        try {
            dialog = new Dialog(context);
            dialog.setContentView(R.layout.add_layout);
            dialogInflateItem();
        } catch (Exception e) {
            Toast.makeText(context, "some thing is wrong", Toast.LENGTH_SHORT).show();
        }
        dialogIconButton.setImageDrawable(this.iconArray.getDrawable(iconPosition));
        dialogIconButton.setColorFilter(iconColor);
        dialogIconButton.getBackground().setTint(itemColor);
        dialogColorButton.getBackground().setTint(itemColor);
        show();
    }

    public void dialogInflateItem() {
        dialogAdd = dialog.findViewById(R.id.dialog_add_add_btn);
        dialogColorButton = dialog.findViewById(R.id.dialog_color_btn);
        dialogIconButton = dialog.findViewById(R.id.dialog_icon_btn);
        dialogDate = dialog.findViewById(R.id.dialog_add_date_btn);
        tvDialogTitle = dialog.findViewById(R.id.dialog_add_title_task);
        tvDialogTxt = dialog.findViewById(R.id.dialog_add_text);
    }

    public void show() {
        WindowManager.LayoutParams wlp = dialog.getWindow().getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        dialog.getWindow().setAttributes(wlp);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addingListener(listener listener) {
        dialogAdd.setOnClickListener(v -> {
            listener.onAddButtonListener();
            dialog.cancel();
        });
        dialogColorButton.setOnClickListener(v -> colorDialog());
        dialogIconButton.setOnClickListener(v -> iconsDialog());
        dialogDate.setOnClickListener(v -> {
            int hour= calendar.get(Calendar.HOUR_OF_DAY);
            int minute= calendar.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    context,
                    (view, hourOfDay, minute1) -> endDate +=" "+hourOfDay+":"+ minute1,
                    hour,
                    minute,
                    true) ;
            DatePickerDialog datePickerDialog = new DatePickerDialog(context);
            datePickerDialog.setOnDateSetListener((view, year, month, dayOfMonth) -> {
                month+=1;
                endDate = ""+dayOfMonth+"-"+ month+"-"+year;
                timePickerDialog.show();
            });
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
            datePickerDialog.show();
        });
    }

    public void colorDialog() {
        final ColorPicker colorPicker = new ColorPicker((Activity) context);
        colorPicker
                .setTitle("colors")
                .setDefaultColorButton(Color.parseColor("#f84c44"))
                .setColors(R.array.colors_Icons_aray)
                .setColumns(5)
                .setRoundColorButton(true)
                .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {

                    @Override
                    public void onChooseColor(int position, int color) {

                        itemColor = colorArray.getColor(position, Color.CYAN);
                        iconColor = color;
                        dialogColorButton.getBackground().setTint(itemColor);
                        dialogIconButton.getBackground().setTint(itemColor);
                        dialogIconButton.setColorFilter(color);

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
        dialogIcons = new Dialog(context);
        dialogIcons.setContentView(R.layout.icon_recycler_view);
        RecyclerView recyclerView = dialogIcons.findViewById(R.id.icon_RecyclerView);
        adapterIconPicker adapterIconPicker = new adapterIconPicker(context, (position, array) -> {
            iconPosition = position;
            dialogIcons.dismiss();
            dialogIconButton.setImageDrawable(array.getDrawable(position));
        }
        , R.array.iconArray);
        RecyclerView.LayoutManager manager = new GridLayoutManager(context, 4);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapterIconPicker);
        dialogIcons.show();
    }
    public void randomIconAndColor(){
    }

    public interface listener {
        void onAddButtonListener();
    }
}
