package com.example.myapplication.UI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.APIs.API;
import com.example.myapplication.APIs.ApiClint;
import com.example.myapplication.APIs.ApiResponse;
import com.example.myapplication.AppConfig;
import com.example.myapplication.DateConverter;
import com.example.myapplication.ItemTask;
import com.example.myapplication.R;
import com.example.myapplication.adapters.AdapterRecyclerView;
import com.example.myapplication.dialogs.ShowTaskInfo;
import com.example.myapplication.dialogs.addingDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowGroup extends AppCompatActivity implements AdapterRecyclerView.onRecyclerViewClickListener {

    RecyclerView recyclerView;
    FloatingActionButton fab;
    ArrayList<ItemTask> list = new ArrayList<>();
    int adminGroupID, groupID;
    AdapterRecyclerView adapter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_group);
        Intent intent = getIntent();

        adminGroupID = intent.getIntExtra("admin_id", -2);

        groupID = intent.getIntExtra("group_id", -3);

        recyclerView = findViewById(R.id.show_group_act_recycler_view);

        adapter = new AdapterRecyclerView(list, getBaseContext(), this);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 3));

        fab = findViewById(R.id.show_group_act_fab);

        if (adminGroupID == AppConfig.sp.getInt("user_id", -1)) {
            fab.setVisibility(View.VISIBLE);
        }
        listener();
        returnTasks();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void listener() {
        fab.setOnClickListener(v -> {
            ItemTask task = new ItemTask();
            addingDialog addingDialog = new addingDialog(ShowGroup.this, R.layout.add_layout);

            addingDialog.addingListener(() -> {
                task.setColor(addingDialog.getItemColor());
                task.setTitle(addingDialog.getTvDialogTitle().getText().toString());
                task.setPositionIcon(addingDialog.getIconPosition());
                task.setIconColor(addingDialog.getIconColor());
                task.setText(addingDialog.getTvDialogTxt().getText().toString());
                if (addingDialog.getEndDate() != null)
                    task.setDateEnd(addingDialog.getEndDate());
                else
                    task.setDateEnd(DateConverter.now());
                task.setUserID(11);
                task.setGroupID(groupID);
                task.setDateStart(DateConverter.now());
                adapter.addItem(task);

                API api = ApiClint.getRetrofitInstance().create(API.class);
                Call<ApiResponse> call = api.insertTask(task);
                call.enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        Toast.makeText(ShowGroup.this, "" + response.body().getStatus(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        Toast.makeText(ShowGroup.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            },new DatePickerDialog(ShowGroup.this));
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onItemClickListener(ItemTask task, int position) {

        ShowTaskInfo taskInfo = new ShowTaskInfo(R.layout.show_task, ShowGroup.this, task, adapter, position);
        taskInfo.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addTaskHelper(ItemTask task) {

    }

    private void returnTasks() {

        API api = ApiClint.getRetrofitInstance().create(API.class);
        Call<ArrayList<ItemTask>> call = api.getAllTasksForGroup(groupID);
        call.enqueue(new Callback<ArrayList<ItemTask>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemTask>> call, Response<ArrayList<ItemTask>> response) {
                Toast.makeText(ShowGroup.this, "" + response.body().size(), Toast.LENGTH_SHORT).show();
                list = response.body();
                adapter.refreshFromDB(list);
            }

            @Override
            public void onFailure(Call<ArrayList<ItemTask>> call, Throwable t) {
                Toast.makeText(ShowGroup.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}