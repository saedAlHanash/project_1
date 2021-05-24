package com.example.myapplication.UI.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapters.groupsRecyclerViewAdapter;

import java.util.ArrayList;

public class groupFragment extends Fragment implements groupsRecyclerViewAdapter.onRecyclerViewClickListener {

    RecyclerView groupsRecyclerView;
    ArrayList<String> list = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group,container,false);
        list.add(" ");
        list.add(" ");
        list.add(" ");
        list.add(" ");
        list.add(" ");
        list.add(" ");
        list.add(" ");
        list.add(" ");
        list.add(" ");
        list.add(" ");
        System.out.println(list.size());
        groupsRecyclerView = view.findViewById(R.id.groups_RecyclerView);
        groupsRecyclerViewAdapter adapter = new groupsRecyclerViewAdapter(list,getContext(),this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        Toast.makeText(getContext(), list.size()+"", Toast.LENGTH_SHORT).show();
        groupsRecyclerView.setAdapter(adapter);
        groupsRecyclerView.setLayoutManager(manager);
        return view;
    }

    @Override
    public void onGroupClickListener(View view, int position) {
        Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();
    }
}