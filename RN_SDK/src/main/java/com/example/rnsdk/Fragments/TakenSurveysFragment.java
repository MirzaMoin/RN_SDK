package com.example.rnsdk.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rnsdk.Adapter.TakeSurveyAdapter;
import com.example.rnsdk.Models.SurveysUnTakenModel;
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;

import java.util.List;


public class TakenSurveysFragment extends Fragment {



    RecyclerView rvFragmentTakenSurvey;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_taken_surveys, container, false);

        init(view);

        List<SurveysUnTakenModel> survey = Utility.response.responsedata.getCompleted();


        TakeSurveyAdapter adapter = new TakeSurveyAdapter(getContext(), survey, true);
        rvFragmentTakenSurvey.setHasFixedSize(true);


        rvFragmentTakenSurvey.setLayoutManager(new LinearLayoutManager(getContext()));

        rvFragmentTakenSurvey.setAdapter(adapter);


        return view;
    }

    private void init(View view) {
        rvFragmentTakenSurvey = view.findViewById(R.id.rvFragmentTakenSurvey);

    }
}