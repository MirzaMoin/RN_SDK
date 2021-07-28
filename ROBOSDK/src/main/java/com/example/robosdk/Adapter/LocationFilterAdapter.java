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
import com.example.robosdk.Activities.LocationActivity;
import com.example.robosdk.Activities.WebViewActivity;
import com.example.robosdk.Models.HomeScreenModel;
import com.example.robosdk.Models.LocationDataModel;
import com.example.robosdk.Models.MenuLinkModel;
import com.example.robosdk.R;
import com.example.robosdk.Utility.Utility;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.fontawesome.FontDrawable;
import okhttp3.internal.Util;

import static com.example.robosdk.Utility.Utility.isSelected;

public class LocationFilterAdapter extends RecyclerView.Adapter<LocationFilterAdapter.ViewHolder> {


    Context context;

    public List<LocationDataModel> originalLocationData = new ArrayList<>();
    public List<LocationDataModel> tempLocationData = new ArrayList<>();



    public LocationFilterAdapter(Context context) {
        this.context = context;

        for(int i=0; i < Utility.response.responsedata.categoryList.size();i++)
        {
            isSelected.add(false);
        }

        originalLocationData.clear();
        originalLocationData.addAll(Utility.response.responsedata.locationData);

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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
          final List<String> categoryList =Utility.response.responsedata.categoryList;

        holder.textContentFilter.setText(categoryList.get(position));

        for(int i=0;i<isSelected.size();i++)
        {
            if(isSelected.get(position))
            {
                holder.cardFilter.setCardBackgroundColor(Utility.getColor("#000000ff"));
                holder.textContentFilter.setTextColor(Utility.getColor("#ffffffff"));
                holder.linearFilter.setBackground(ContextCompat.getDrawable(context,R.drawable.background_selected_filter));
            }
            else
            {
                holder.cardFilter.setCardBackgroundColor(Utility.getColor("#ffffffff"));
                holder.textContentFilter.setTextColor(Utility.getColor("#888888ff"));
                holder.linearFilter.setBackground(ContextCompat.getDrawable(context,R.drawable.background_filter));
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i=0;i<Utility.response.responsedata.categoryList.size();i++)
                {
                    if(i == position && !isSelected.get(position))
                    {
                        isSelected.set(i,true);
                        Log.e("Test","Selected : "+Utility.response.responsedata.categoryList.get(i));
                        tempLocationData.clear();
                        LocationActivity.etSearchLocationBottom.setText("");

                        for(LocationDataModel l : originalLocationData)
                        {
                            if(l.locationCategory.contains(categoryList.get(i)))
                            {
                                tempLocationData.add(l);
                            }
                        }
                        Utility.response.responsedata.locationData.clear();
                        Utility.response.responsedata.locationData.addAll(tempLocationData);

                    }
                    else if(i == position && isSelected.get(position))
                    {
                        isSelected.set(i,false);
                        LocationActivity.etSearchLocationBottom.setText("");
                        Utility.response.responsedata.locationData.clear();
                        Utility.response.responsedata.locationData.addAll(originalLocationData);
                        Log.e("Test","Original Location Data List : "+originalLocationData.size());
                    }
                    else
                    {
                        isSelected.set(i,false);

                        Log.e("Test","Unselected : "+Utility.response.responsedata.categoryList.get(i));

                    }
                }
                notifyDataSetChanged();
                LocationActivity.adapter.notifyDataSetChanged();
                    if(Utility.response.responsedata.locationData.size() == 0)
        {
            LocationActivity.rvLocation.setVisibility(View.GONE);
            LocationActivity.textNoLocation.setVisibility(View.VISIBLE);
        }
        else
        {
                LocationActivity.rvLocation.setVisibility(View.VISIBLE);
                LocationActivity.textNoLocation.setVisibility(View.GONE);
        }
            }
        });

        Log.e("IsSelected",isSelected.toString());


    }


    @Override
    public int getItemCount() {
        return Utility.response.responsedata.categoryList.size();
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