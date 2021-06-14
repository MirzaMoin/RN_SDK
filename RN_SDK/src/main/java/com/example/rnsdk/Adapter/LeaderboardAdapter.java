package com.example.rnsdk.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rnsdk.Activities.OffersDetailActivity;
import com.example.rnsdk.R;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>{

    Context context;
   public LeaderboardAdapter(Context context){

       this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.content_leaderboard_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

       holder.tvRank.setText(""+(position + 2));

    }


    @Override
    public int getItemCount() {
        return 5;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

       TextView tvRank;
        public ViewHolder(View itemView) {
            super(itemView);
            tvRank = itemView.findViewById(R.id.textRank);


        }
    }
}