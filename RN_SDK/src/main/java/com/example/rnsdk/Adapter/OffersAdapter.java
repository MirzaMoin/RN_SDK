package com.example.rnsdk.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rnsdk.Activities.OfferActivity;
import com.example.rnsdk.Activities.OffersDetailActivity;
import com.example.rnsdk.R;

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

        public ViewHolder(View itemView) {
            super(itemView);

        }
    }
}