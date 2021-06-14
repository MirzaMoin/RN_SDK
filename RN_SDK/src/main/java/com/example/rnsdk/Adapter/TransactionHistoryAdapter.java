package com.example.rnsdk.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rnsdk.R;

public class TransactionHistoryAdapter extends RecyclerView.Adapter<TransactionHistoryAdapter.ViewHolder>{

   public TransactionHistoryAdapter(){

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.content_transaction_history, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

       holder.linearCollEx.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if(               holder.linearTotalSpend.getVisibility() == View.VISIBLE
               )
               {
                   holder.linearLocation.setVisibility(View.GONE);
                   holder.linearStatus.setVisibility(View.GONE);
                   holder.linearType.setVisibility(View.GONE);
                   holder.linearTotalSpend.setVisibility(View.GONE);
                   holder.imageExpand.setRotation(180);

               }
               else
               {
                   holder.linearLocation.setVisibility(View.VISIBLE);
                   holder.linearStatus.setVisibility(View.VISIBLE);
                   holder.linearType.setVisibility(View.VISIBLE);
                   holder.linearTotalSpend.setVisibility(View.VISIBLE);
                   holder.imageExpand.setRotation(0);

               }

           }
       });

    }


    @Override
    public int getItemCount() {
        return 5;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

       LinearLayout linearCollEx,linearLocation,linearType,linearStatus,linearTotalSpend;
       ImageView imageExpand;
        public ViewHolder(View itemView) {
            super(itemView);

            linearCollEx = itemView.findViewById(R.id.linearCollExTH);
            linearLocation = itemView.findViewById(R.id.linearLocationTH);
            linearStatus = itemView.findViewById(R.id.linearStatusTH);
            linearType = itemView.findViewById(R.id.linearTypeTH);
            linearTotalSpend = itemView.findViewById(R.id.linearTotalSpendTH);
            imageExpand = itemView.findViewById(R.id.imgExpand);

        }
    }
}