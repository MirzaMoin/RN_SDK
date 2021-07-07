package com.example.robosdk.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.robosdk.Models.RPGListModel;
import com.example.robosdk.R;
import com.example.robosdk.Utility.Utility;

import java.util.List;

public class RewardEntryPointAdapter extends RecyclerView.Adapter<RewardEntryPointAdapter.ViewHolder>{

    Context context;
    List<RPGListModel> lstRPG;

    public RewardEntryPointAdapter(Context context, List<RPGListModel> lstRPG) {
        this.context = context;
        this.lstRPG = lstRPG;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.content_rewards_entry_goal, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        RPGListModel rpg = lstRPG.get(position);
        holder.textTitleRPG.setText(rpg.getTitle());
        holder.textDescRPG.setText(rpg.getDetails());

        holder.textDigitPointRPG.setText(String.valueOf(rpg.getPointValue()));
        Glide.with(context).load(rpg.getImage()).into(holder.imageRPG);


        if(rpg.isActive())
        {
            holder.linearPointRPG.setBackgroundColor(Utility.getColor("#1dc916ff"));
        }
        else
        {
            holder.linearPointRPG.setBackgroundColor(Utility.getColor("#999999ff"));

        }
    }


    @Override
    public int getItemCount() {
        return lstRPG.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearPointRPG;
        ImageView imageRPG;
        TextView textTitleRPG,textDescRPG,textDigitPointRPG;


        public ViewHolder(View itemView) {
            super(itemView);

            linearPointRPG = itemView.findViewById(R.id.linearPointRPG);
            imageRPG = itemView.findViewById(R.id.imageRPG);
            textTitleRPG = itemView.findViewById(R.id.textTitleRPG);
            textDescRPG = itemView.findViewById(R.id.textDescRPG);
            textDigitPointRPG = itemView.findViewById(R.id.textDigitPointRPG);


        }
    }
}