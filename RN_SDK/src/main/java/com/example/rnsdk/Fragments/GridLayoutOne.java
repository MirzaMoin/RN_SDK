package com.example.rnsdk.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rnsdk.Adapter.HomeMenuLinkListAdapter;
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;

public class GridLayoutOne extends Fragment {


    RecyclerView rvGrid1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_grid_layout_one, container, false);

        rvGrid1 = view.findViewById(R.id.rvGrid1);


        HomeMenuLinkListAdapter adapter = new HomeMenuLinkListAdapter(getContext(), Utility.response.responsedata.homeScreen.menuLinks,false,true);
        rvGrid1.setHasFixedSize(true);
        rvGrid1.setLayoutManager(new GridLayoutManager(getContext(),3));
        rvGrid1.setAdapter(adapter);



        return view;
    }
}