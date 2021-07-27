package com.example.robosdk.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.robosdk.Adapter.TakeSurveyAdapter;
import com.example.robosdk.Models.SurveysUnTakenModel;
import com.example.robosdk.R;
import com.example.robosdk.Utility.Utility;

import java.util.List;


public class TakenSurveysFragment extends Fragment {



    RecyclerView rvFragmentTakenSurvey;
    TextView textNoSurveyFoundTaken;
    LinearLayout linearNoSurvey;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_taken_surveys, container, false);

        init(view);

        List<SurveysUnTakenModel> survey = Utility.response.responsedata.getCompleted();

        if(survey != null && survey.size() > 0)
        {

            TakeSurveyAdapter adapter = new TakeSurveyAdapter(getContext(), survey, true);
            rvFragmentTakenSurvey.setHasFixedSize(true);


            rvFragmentTakenSurvey.setLayoutManager(new LinearLayoutManager(getContext()));

            rvFragmentTakenSurvey.setAdapter(adapter);
            linearNoSurvey.setVisibility(View.GONE);

        }
        else
        {
            linearNoSurvey.setVisibility(View.VISIBLE);
        }

        return view;
    }

    private void init(View view) {
        rvFragmentTakenSurvey = view.findViewById(R.id.rvFragmentTakenSurvey);
        textNoSurveyFoundTaken = view.findViewById(R.id.textNoSurveyFoundTaken);
        linearNoSurvey = view.findViewById(R.id.linearNoSurvey);

    }
}