package com.example.rnsdk.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rnsdk.Adapter.FooterAdapter;
import com.example.rnsdk.Adapter.TakeSurveyAdapter;
import com.example.rnsdk.Models.SurveysUnTakenModel;
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;

import java.util.List;

import static android.content.ContentValues.TAG;


public class UnTakenSurveysFragment extends Fragment {

    RecyclerView rvFragmentUnTakenSurvey;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_un_taken_surveys, container, false);


        init(view);

        List<SurveysUnTakenModel> survey = Utility.response.responsedata.getUnTaken();


        TakeSurveyAdapter adapter = new TakeSurveyAdapter(getContext(), survey, false);
        rvFragmentUnTakenSurvey.setHasFixedSize(true);


        rvFragmentUnTakenSurvey.setLayoutManager(new LinearLayoutManager(getContext()));

        rvFragmentUnTakenSurvey.setAdapter(adapter);
        return view;
    }

    private void init(View view) {
        rvFragmentUnTakenSurvey = view.findViewById(R.id.rvFragmentUnTakenSurvey);

    }
}