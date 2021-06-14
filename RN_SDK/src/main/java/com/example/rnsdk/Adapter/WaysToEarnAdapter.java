package com.example.rnsdk.Adapter;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rnsdk.R;
import com.ms.square.android.expandabletextview.ExpandableTextView;

public class WaysToEarnAdapter extends RecyclerView.Adapter<WaysToEarnAdapter.ViewHolder>{

   public WaysToEarnAdapter(){

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.content_ways_to_earn, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.expTv.setText("Introduction paragraph that engages the reader and establishes the thesis. The thesis may be a question that you will later answer in the essay content, or it can be a statement that you support in the body paragraphs. If you are writing a story, your “thesis” may not be as apparent.");

        if(position % 2 != 0)
       {
           holder.linearContent.setBackgroundColor(Color.parseColor("#D3D3D3"));
       }

    }


    @Override
    public int getItemCount() {
        return 8;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

       LinearLayout linearContent;
        // getting reference of ExpandableTextView
        ExpandableTextView expTv;

// calling setText on the ExpandableTextView so that
// text content will be displayed to the user


        public ViewHolder(View itemView) {
            super(itemView);
            linearContent = itemView.findViewById(R.id.linearWaysToEarn);
            expTv = (ExpandableTextView) itemView.findViewById(R.id.expand_text_view).findViewById(R.id.expand_text_view);
        }
    }
}