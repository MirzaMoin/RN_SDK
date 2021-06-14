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


public class GridLayoutTwo extends Fragment {

    RecyclerView rvGrid2;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_grid_layout_two, container, false);

        rvGrid2 = view.findViewById(R.id.rvGrid2);
        HomeMenuLinkListAdapter adapter = new HomeMenuLinkListAdapter(getContext(), Utility.response.responsedata.homeScreen.menuLinks,false,false);
        rvGrid2.setHasFixedSize(true);
        rvGrid2.setLayoutManager(new GridLayoutManager(getContext(),3));
        rvGrid2.setAdapter(adapter);

        return view;
    }
}