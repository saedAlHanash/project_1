package com.example.myapplication.UI.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group, container, false);

        return view;
    }

    @Override
    public void onGroupClickListener(View view, int position) {
        Toast.makeText(getContext(), "" + position, Toast.LENGTH_SHORT).show();
    }
}