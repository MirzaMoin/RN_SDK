package com.example.robosdk.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.robosdk.Models.LBLeaderBoardReportModel;
import com.example.robosdk.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>{

    Context context;
    List<LBLeaderBoardReportModel> leaderBoardReport;
   public LeaderboardAdapter(Context context, List<LBLeaderBoardReportModel> leaderBoardReport){

       this.context = context;
       this.leaderBoardReport = leaderBoardReport;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.content_leaderboard_list, parent, false);
        return new ViewHolder(listItem);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LBLeaderBoardReportModel data = leaderBoardReport.get(position+3);
       holder.tvRank.setText(""+ data.getRank());
       holder.textNameContentLeaderBoard.setText(data.getFullName());
       holder.textPointContentLeaderBoard.setText(String.valueOf(data.getTotalPoints()));
        Glide.with(context).load(data.getProfilePitcure()).into(holder.winnerImageContentLeaderBoard);

    }


    @Override
    public int getItemCount() {
        return leaderBoardReport.size()-3;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

       TextView tvRank,
               textNameContentLeaderBoard,
               textPointContentLeaderBoard;
       ImageView winnerImageContentLeaderBoard;

        public ViewHolder(View itemView) {
            super(itemView);
            tvRank = itemView.findViewById(R.id.textRank);
            winnerImageContentLeaderBoard = itemView.findViewById(R.id.winnerImageContentLeaderBoard);
            textNameContentLeaderBoard = itemView.findViewById(R.id.textNameContentLeaderBoard);
            textPointContentLeaderBoard = itemView.findViewById(R.id.textPointContentLeaderBoard);



        }
    }
}