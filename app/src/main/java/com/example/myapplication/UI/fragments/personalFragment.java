package com.example.myapplication.UI.fragments;

import android.app.AlertDialog;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DataBases.DataBaseAccess;
import com.example.myapplication.ItemTask;
import com.example.myapplication.R;
import com.example.myapplication.adapters.AdapterRecyclerView;
import com.example.myapplication.dialogs.ShowTaskInfo;
import com.example.myapplication.dialogs.addingDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


@RequiresApi(api = Build.VERSION_CODES.N)
public class personalFragment extends Fragment
        implements AdapterRecyclerView.onRecyclerViewClickListener {
    View view;

    RecyclerView recyclerView;

    AdapterRecyclerView adapterRecyclerView;

    FloatingActionButton fab;

    ArrayList<ItemTask> list = new ArrayList<>();

    public static DataBaseAccess dataBase;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personal, container, false);

        dataBase = DataBaseAccess.getInstance(getContext());

        list = dataBase.restoreFromDataBase(false);

        recyclerView = view.findViewById(R.id.recycler_view_home_fragment);

        fab = view.findViewById(R.id.floatingActionButton);

        adapterRecyclerView = new AdapterRecyclerView(list, getContext(), this);

        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 3);

        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(adapterRecyclerView);

        fab.setOnClickListener(v -> {
            addTaskHelper(new ItemTask());
        });
        return view;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 0: {
                addingDialog addingDialog = new addingDialog(getContext(), R.layout.add_layout);
                addingDialog.setTvDialogTxt(list.get(item.getGroupId()).getText());
                addingDialog.setTvDialogTitle(list.get(item.getGroupId()).getTitle());
                addingDialog.addingListener(() -> {
                    list.get(item.getGroupId()).setColor(addingDialog.getItemColor());
                    list.get(item.getGroupId()).setTitle(addingDialog.getTvDialogTitle().getText().toString());
                    list.get(item.getGroupId()).setPositionIcon(addingDialog.getIconPosition());
                    list.get(item.getGroupId()).setIconColor(addingDialog.getIconColor());
                    list.get(item.getGroupId()).setText(addingDialog.getTvDialogTxt().getText().toString());
                    dataBase.updateDB(list.get(item.getGroupId()));
                    adapterRecyclerView.refresh(item.getGroupId());
                    Toast.makeText(getContext(), "save change", Toast.LENGTH_SHORT).show();
                });
                break;
            }
            case 1: {
                new AlertDialog.Builder(getContext())
                        .setTitle("Delete Alert ")
                        .setMessage("are you sure to delete this task ? ")
                        .setPositiveButton("Delet", (dialog, which) -> {
                            if (dataBase.deleteTask(list.get(item.getGroupId()))) {
                                Toast.makeText(getContext(), "Delete Done", Toast.LENGTH_SHORT).show();
                                adapterRecyclerView.deleteItem(item.getGroupId());
                            }
                        })
                        .setNegativeButton("No", (dialog, which) -> {
                        })
                        .show();

                break;
            }
            case 2: {
                new AlertDialog.Builder(getContext())
                        .setTitle("Confirm Done Task")
                        .setMessage("are you sure to done this task ?")
                        .setPositiveButton("ok", (dialog, which) -> {
                            Toast.makeText(getContext(), "task Done", Toast.LENGTH_SHORT).show();
                         ItemTask task= list.get(item.getGroupId());
                         task.setDone(true);
                            dataBase.updateDB(task);
                        })
                        .setNegativeButton("no", (dialog, which) -> {}).show();


                break;
            }
        }
        return true;
    }

    @Override
    public void onItemClickListener(ItemTask task, int position) {
        if (!task.isDone()) {
            ShowTaskInfo taskInfo = new ShowTaskInfo(R.layout.show_task, getContext(), task, adapterRecyclerView, position);
            taskInfo.show();
        } else {
            Toast.makeText(getContext(), "this task is Done", Toast.LENGTH_SHORT).show();
        }

    }

    private void addTaskHelper(ItemTask task) {

        addingDialog addingDialog = new addingDialog(getContext(), R.layout.add_layout);
        addingDialog.addingListener(() -> {
            task.setColor(addingDialog.getItemColor());
            task.setTitle(addingDialog.getTvDialogTitle().getText().toString());
            task.setPositionIcon(addingDialog.getIconPosition());
            task.setIconColor(addingDialog.getIconColor());
            task.setText(addingDialog.getTvDialogTxt().getText().toString());
            task.setDateEnd(addingDialog.getEndDate());
            if (dataBase.insertDB(task)) {
                Toast.makeText(getContext(), "Date inserted", Toast.LENGTH_SHORT).show();
                list = dataBase.restoreFromDataBase(false);
                adapterRecyclerView.refreshFromDB(list);
            }
        });
    }

}