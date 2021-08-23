package com.example.myapplication.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.RequiresApi;

import com.example.myapplication.DataBases.DataBaseAccess;
import com.example.myapplication.DateConverter;
import com.example.myapplication.ItemTask;
import com.example.myapplication.R;
import com.example.myapplication.adapters.AdapterRecyclerView;

import java.util.Date;

public class ShowTaskInfo {

    TextView dateStart, endDate, text, title, dateReminding;
    Button edit, ok;
    CheckBox doneTask;
    Dialog dialog;
    ItemTask task;
    AdapterRecyclerView adapterRecyclerView;
    int test = 2, position;
    Context context;
    DataBaseAccess database;
    @LayoutRes
    int res;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ShowTaskInfo(int res, Context context, ItemTask task, AdapterRecyclerView adapterRecyclerView, int position) {
        this.res = res;
        this.context = context;
        this.task = task;
        this.adapterRecyclerView = adapterRecyclerView;
        this.position = position;
        this.database = DataBaseAccess.getInstance(context);
        try {
            dialog = new Dialog(context);
            dialog.setContentView(res);
            inflateItem();
            onClick();
        } catch (Exception e) {
            Toast.makeText(context, "some thing is wrong", Toast.LENGTH_SHORT).show();
        }
    }

    void inflateItem() {
        endDate = dialog.findViewById(R.id.show_task_time_end);
        dateReminding = dialog.findViewById(R.id.show_task_time_reminding);
        dateStart = dialog.findViewById(R.id.show_task_date_start);
        edit = dialog.findViewById(R.id.show_task_edit_btn);
        doneTask = dialog.findViewById(R.id.show_task_checkBox_done_task);
        text = dialog.findViewById(R.id.show_task_text);
        title = dialog.findViewById(R.id.show_task_title);
        ok = dialog.findViewById(R.id.show_task_ok_btn);
    }

    public void show() {
        WindowManager.LayoutParams wlp = dialog.getWindow().getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        dialog.getWindow().setAttributes(wlp);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setItem();
        dialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void onClick() {
        doneTask.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                test = 0;
            } else if (!isChecked) {
                test = 2;
            }
        });
        ok.setOnClickListener(v -> {
            if (doneTask.isChecked() && test == 1) {
                task.setDone(true);
                Toast.makeText(context, "done Task", Toast.LENGTH_SHORT).show();
                adapterRecyclerView.refresh(position);
                task.setDone(true);
                database.updateDB(task);
                dialog.cancel();
            } else if (test == 0) {
                Toast.makeText(context, "click another on to confirm", Toast.LENGTH_SHORT).show();
                test++;
            } else {
                dialog.cancel();
            }
        });
        edit.setOnClickListener(v -> {
            dialog.cancel();
            addingDialog addingDialog = new addingDialog(context, R.layout.add_layout);
            addingDialog.setTvDialogTxt(task.getText());
            addingDialog.setTvDialogTitle(task.getTitle());
            addingDialog.addingListener(() -> {
                task.setColor(addingDialog.getItemColor());
                task.setTitle(addingDialog.getTvDialogTitle().getText().toString());
                task.setPositionIcon(addingDialog.getIconPosition());
                task.setIconColor(addingDialog.getIconColor());
                task.setText(addingDialog.getTvDialogTxt().getText().toString());
                String dateEnd = addingDialog.getEndDate();
                if (dateEnd != null)
                    task.setDateEnd(addingDialog.getEndDate());
                adapterRecyclerView.refresh(position);
                database.updateDB(task);
                Toast.makeText(context, "save change", Toast.LENGTH_SHORT).show();
            });
        });
    }

    public String saed() {
        Date date1 = DateConverter.stringToDate(task.getDateStart());
        Date date2 = DateConverter.stringToDate(task.getDateEnd());
        return DateConverter.findDifference(new Date(), date2);
    }

    void setItem() {
        endDate.setText(task.getDateEnd().substring(0, 10));
        dateStart.setText(task.getDateStart().substring(0, 10));
        dateReminding.setText(saed());
        text.setText(task.getText());
        title.setText(task.getTitle());
    }


    interface Listener {
        void editTaskListener(ItemTask task);
    }


}
