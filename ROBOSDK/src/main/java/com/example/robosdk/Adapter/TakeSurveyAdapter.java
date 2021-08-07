package com.example.robosdk.Adapter;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.robosdk.Activities.WebViewActivity;
import com.example.robosdk.Models.SurveysUnTakenModel;
import com.example.robosdk.R;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TakeSurveyAdapter extends RecyclerView.Adapter<TakeSurveyAdapter.ViewHolder> {

    Context context;
    List<SurveysUnTakenModel> surveyList;
    boolean isTaken;

    public TakeSurveyAdapter(Context context, List<SurveysUnTakenModel> surveyList, boolean isTaken) {

        this.context = context;
        this.surveyList = surveyList;
        this.isTaken = isTaken;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.content_survey_list, parent, false);
        return new ViewHolder(listItem);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {

        final SurveysUnTakenModel survey = surveyList.get(position);

        if(isTaken)
        {
            holder.linearStartSurvey.setVisibility(View.GONE);
        }
        else
        {
            holder.linearAmountSurvey.setVisibility(View.GONE);

            holder.linearStartSurvey.setVisibility(View.VISIBLE);
        }
        holder.textTitleSurvey.setText(survey.getSurveyTitle());
        holder.textAmountSurvey.setText( String.valueOf(survey.getSurveyPoints()));
        Date date = null;
        String dtStart = survey.getSurveySendDate();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatterOut = new SimpleDateFormat("dd MMM yyyy");
        try {
            date = format.parse(dtStart);
            assert date != null;
            formatterOut.format(date);
            holder.textDateSurvey.setText("" + formatterOut.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(!isTaken) {

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProgressDialog progressDialog;
                    progressDialog = new ProgressDialog(context);
                    progressDialog.setTitle("Loading...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    Intent i = new Intent(context, WebViewActivity.class);
                    i.putExtra("url",survey.getSurveyLink());
                    i.putExtra("isSurvey",true);
                    context.startActivity(i);
                    progressDialog.dismiss();
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return surveyList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearStartSurvey,
                linearAmountSurvey;

        TextView textTitleSurvey,
                textDateSurvey,
                textAmountSurvey;

        public ViewHolder(View itemView) {
            super(itemView);
            linearStartSurvey = itemView.findViewById(R.id.linearStartSurvey);
            linearAmountSurvey = itemView.findViewById(R.id.linearAmountSurvey);

            textTitleSurvey = itemView.findViewById(R.id.textTitleSurvey);
            textDateSurvey = itemView.findViewById(R.id.textDateSurvey);
            textAmountSurvey = itemView.findViewById(R.id.textAmountSurvey);

        }
    }
}