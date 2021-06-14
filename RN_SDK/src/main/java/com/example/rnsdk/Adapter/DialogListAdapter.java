package com.example.rnsdk.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rnsdk.R;

public class DialogListAdapter extends RecyclerView.Adapter<DialogListAdapter.ViewHolder>{

   public DialogListAdapter(){

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.content_list_dialog, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               holder.ivCorrect.setVisibility(View.VISIBLE);
           }
       });

    }


    @Override
    public int getItemCount() {
        return 28;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

       ImageView ivCorrect;

        public ViewHolder(View itemView) {
            super(itemView);

            ivCorrect = itemView.findViewById(R.id.imageCorrect);

        }
    }
}