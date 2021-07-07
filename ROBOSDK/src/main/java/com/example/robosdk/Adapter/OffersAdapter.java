package com.example.robosdk.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.robosdk.Activities.OffersDetailActivity;
import com.example.robosdk.Models.AppColorModel;
import com.example.robosdk.Models.OfferListModel;
import com.example.robosdk.R;
import com.example.robosdk.Utility.Utility;

import java.util.List;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.ViewHolder>{

    Context context;
    List<OfferListModel> offerList;
   public OffersAdapter(Context context, List<OfferListModel> offerList){

       this.context = context;
       this.offerList = offerList;
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

        OfferListModel offer = offerList.get(position);

        AppColorModel color = Utility.response.responsedata.appColor;

        holder.btnOfferImageLabel.setTextColor(Utility.getColor(color.getOfferTopRibbonTextColor()));
        holder.btnOfferImageLabel.setBackgroundColor(Utility.getColor(color.getOfferTopRibbonColor()));

        holder.offerTitle.setTextColor(Utility.getColor(color.getOfferBottomRibbonTextColor()));
        holder.offerTitle.setBackgroundColor(Utility.getColor(color.getOfferBootomRibbonColor()));

        holder.offerTitle.setText(offer.getOfferTitle());
//        holder.offerTitle.setTextColor(Utility.getColor(offer.getTitleColor()));

        holder.textOfferDescription.setText(offer.getOfferDescription());
        holder.textOfferDescription.setTextColor(Utility.getColor(offer.getDescColor()));

        holder.textOfferType.setText(offer.getOfferType());
        Glide.with(context).load(offer.getOfferImage()).into(holder.imageOfferImage);
        holder.btnOfferImageLabel.setText(offer.getOfferImagelabel());

        holder.textOfferExpire.setText(offer.getOfferExpire());
        holder.textOfferExpire.setTextColor(Utility.getColor(color.getTitleTextColor()));





       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(context,OffersDetailActivity.class);
               i.putExtra("offerID",offer.getOfferID());
               i.putExtra("offerSendID",offer.getOfferSendID());

               context.startActivity(i);
           }
       });
    }


    @Override
    public int getItemCount() {
        return offerList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

       Button btnOfferImageLabel;
       TextView offerTitle,textOfferDescription,
               textOfferType,
               textOfferExpire;

       ImageView imageOfferImage;
        public ViewHolder(View itemView) {
            super(itemView);

            btnOfferImageLabel = itemView.findViewById(R.id.btnOfferImageLabel);
            offerTitle = itemView.findViewById(R.id.textOfferTitle);
            textOfferDescription = itemView.findViewById(R.id.textOfferDescription);
            textOfferType = itemView.findViewById(R.id.textOfferType);
            imageOfferImage = itemView.findViewById(R.id.imageOfferImage);
            textOfferExpire = itemView.findViewById(R.id.textOfferExpire);

        }
    }
}