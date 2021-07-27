package com.example.robosdk.Adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.robosdk.Activities.WebViewActivity;
import com.example.robosdk.Models.HomeScreenModel;
import com.example.robosdk.Models.MenuLinkModel;
import com.example.robosdk.R;
import com.example.robosdk.Utility.Utility;

import java.util.List;

import info.androidhive.fontawesome.FontDrawable;

public class LocationFilterAdapter extends RecyclerView.Adapter<LocationFilterAdapter.ViewHolder> {


    Context context;
    String[] list = {"Hotel","Bar","Club","Hospital","Hostel"};

    public LocationFilterAdapter(Context context) {
        this.context = context;


    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem;

            listItem = layoutInflater.inflate(R.layout.content_filter, parent, false);


        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.textContentFilter.setText(list[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.cardFilter.setCardBackgroundColor(Utility.getColor("#000000ff"));
                holder.textContentFilter.setTextColor(Utility.getColor("#ffffffff"));
                holder.linearFilter.setBackground(ContextCompat.getDrawable(context,R.drawable.background_selected_filter));
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        TextView textContentFilter;
        CardView cardFilter;
        LinearLayout linearFilter;

        public ViewHolder(View itemView) {
            super(itemView);
            textContentFilter = itemView.findViewById(R.id.textContentFilter);
            cardFilter = itemView.findViewById(R.id.cardFilter);
            linearFilter = itemView.findViewById(R.id.linearFilter);


        }
    }
}