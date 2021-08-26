package com.example.myapplication.UI.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.APIs.API;
import com.example.myapplication.APIs.ApiClint;
import com.example.myapplication.APIs.ApiResponse;
import com.example.myapplication.AppConfig;
import com.example.myapplication.Group;
import com.example.myapplication.R;
import com.example.myapplication.UI.ShowGroup;
import com.example.myapplication.adapters.groupsRecyclerViewAdapter;
import com.example.myapplication.dialogs.AddGroupDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class groupFragment extends Fragment implements groupsRecyclerViewAdapter.onRecyclerViewClickListener {
    RecyclerView groupsRecyclerView;
    ArrayList<Group> list = new ArrayList<>();
    FloatingActionButton fab;
    groupsRecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group, container, false);

        this.groupsRecyclerView = view.findViewById(R.id.groups_RecyclerView);

        this.fab = view.findViewById(R.id.groub_fragment_fab);

        this.adapter = new groupsRecyclerViewAdapter(list, getContext(), this);

        this.groupsRecyclerView.setAdapter(adapter);

        this.groupsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        listeners();

        return view;
    }

    @Override
    public void onGroupClickListener(View view, int position) {
        Intent intent = new Intent(getContext(), ShowGroup.class);
        intent.putExtra("admin_id",list.get(position).getAdminID());
        intent.putExtra("group_id",list.get(position).getGroupID());
        startActivity(intent);
    }

    void listeners() {
        getGroupFromDB();
        fab.setOnClickListener(v -> {
            AddGroupDialog groupDialog = new AddGroupDialog(getContext(), R.layout.add_group);
            groupDialog.listener(() -> {
                String groupName = groupDialog.getTvDialogTxt().getText().toString();
                API api = ApiClint.getRetrofitInstance().create(API.class);
                Call<ApiResponse> call = api.insertGroup(groupName, AppConfig.sp.getInt("user_id", -1));
                call.enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        Toast.makeText(getContext(), "" + response.body().getStatus(), Toast.LENGTH_SHORT).show();
                        groupDialog.dialog.cancel();
                        getGroupFromDB();
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            });
        });

    }

    void getGroupFromDB() {
        API api = ApiClint.getRetrofitInstance().create(API.class);
        Call<ArrayList<Group>> call = api.getGroups(AppConfig.sp.getInt("user_id", -1));
        call.enqueue(new Callback<ArrayList<Group>>() {
            @Override
            public void onResponse(Call<ArrayList<Group>> call, Response<ArrayList<Group>> response) {
                list = response.body();
                adapter.refreshFromDB(list);
            }

            @Override
            public void onFailure(Call<ArrayList<Group>> call, Throwable t) {

                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}