package com.example.robosdk.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.robosdk.Activities.CashbackActivity;
import com.example.robosdk.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RedeemCashbackAdapter extends RecyclerView.Adapter<RedeemCashbackAdapter.ViewHolder>{

    Context context;
    ArrayList<String> list;

   public RedeemCashbackAdapter(Context context, ArrayList<String> list){

       this.context = context;
       this.list= list;

    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.content_redeem_cashback, parent, false);
        return new ViewHolder(listItem);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


       holder.textTitle.setText(list.get(position));
       holder.textTitle.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               CashbackActivity.etAmountRC.setText(list.get(position));
           }
       });

    }


    @Override
    public int getItemCount() {
        return list.size() - 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

       TextView textTitle;


        public ViewHolder(View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitleCashback);


        }
    }
}