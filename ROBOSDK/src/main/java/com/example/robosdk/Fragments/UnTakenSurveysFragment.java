package com.example.robosdk.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.robosdk.Adapter.TakeSurveyAdapter;
import com.example.robosdk.Models.SurveysUnTakenModel;
import com.example.robosdk.R;
import com.example.robosdk.Utility.Utility;

import java.util.List;


public class UnTakenSurveysFragment extends Fragment {

    RecyclerView rvFragmentUnTakenSurvey;
    TextView textNoSurveyFoundUntaken;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_un_taken_surveys, container, false);


        init(view);

        List<SurveysUnTakenModel> survey = Utility.response.responsedata.getUnTaken();


        if(survey != null && survey.size() > 0)
        {
            TakeSurveyAdapter adapter = new TakeSurveyAdapter(getContext(), survey, false);
            rvFragmentUnTakenSurvey.setHasFixedSize(true);


            rvFragmentUnTakenSurvey.setLayoutManager(new LinearLayoutManager(getContext()));

            rvFragmentUnTakenSurvey.setAdapter(adapter);
            textNoSurveyFoundUntaken.setVisibility(View.GONE);

        }
        else
        {
            textNoSurveyFoundUntaken.setVisibility(View.VISIBLE);
        }


        return view;
    }

    private void init(View view) {
        rvFragmentUnTakenSurvey = view.findViewById(R.id.rvFragmentUnTakenSurvey);
        textNoSurveyFoundUntaken = view.findViewById(R.id.textNoSurveyFoundUntaken);

    }
}