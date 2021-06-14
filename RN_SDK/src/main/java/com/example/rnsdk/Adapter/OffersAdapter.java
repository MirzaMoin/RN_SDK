package com.example.rnsdk.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rnsdk.Activities.OfferActivity;
import com.example.rnsdk.Activities.OffersDetailActivity;
import com.example.rnsdk.Models.AppColorModel;
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;

import okhttp3.internal.Util;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.ViewHolder>{

    Context context;
   public OffersAdapter(Context context){

       this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.content_offers, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        AppColorModel color = Utility.response.responsedata.appColor;

        holder.btnPointsOffer.setTextColor(Utility.getColor(color.getOfferTopRibbonTextColor()));
        holder.btnPointsOffer.setBackgroundColor(Utility.getColor(color.getOfferTopRibbonColor()));

        holder.textOfferNameOffer.setTextColor(Utility.getColor(color.getOfferBottomRibbonTextColor()));
        holder.textOfferNameOffer.setBackgroundColor(Utility.getColor(color.getOfferBootomRibbonColor()));


       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               context.startActivity(new Intent(context, OffersDetailActivity.class));
           }
       });
    }


    @Override
    public int getItemCount() {
        return 5;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

       Button btnPointsOffer;
       TextView textOfferNameOffer;
        public ViewHolder(View itemView) {
            super(itemView);

            btnPointsOffer = itemView.findViewById(R.id.btnPointsOffer);
            textOfferNameOffer = itemView.findViewById(R.id.textOfferNameOffer);

        }
    }
}