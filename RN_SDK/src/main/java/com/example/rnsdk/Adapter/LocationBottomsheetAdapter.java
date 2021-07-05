package com.example.rnsdk.Adapter;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rnsdk.Models.LocationDataModel;
import com.example.rnsdk.Models.TransactionHistoryChildMenuModel;
import com.example.rnsdk.Models.TransactionHistoryModel;
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class LocationBottomsheetAdapter extends RecyclerView.Adapter<LocationBottomsheetAdapter.ViewHolder> {

    Context context;


    public LocationBottomsheetAdapter(Context context) {
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.content_location_bottomsheet, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.imgExpandLocation.setRotation(0);
        LocationDataModel data = Utility.response.responsedata.locationData.get(position);

        String address = "";
        if (data.storeAddress.getAddress() != null && !data.storeAddress.getAddress().isEmpty()) {
            address = address + data.storeAddress.getAddress();
        }
        if (data.storeAddress.getAddress2() != null && !data.storeAddress.getAddress2().isEmpty()) {
            address = address + " " + data.storeAddress.getAddress2();
        }
        if (data.storeAddress.getAddress3() != null && !data.storeAddress.getAddress3().isEmpty()) {
            address = address + " " + data.storeAddress.getAddress3();
        }
        if (data.storeAddress.getCity() != null && !data.storeAddress.getCity().isEmpty()) {
            address = address + " " + data.storeAddress.getCity();
        }
        if (data.storeAddress.getState() != null && !data.storeAddress.getState().isEmpty()) {
            address = address + " " + data.storeAddress.getState();
        }
        if (data.storeAddress.getZipCode() != null && !data.storeAddress.getZipCode().isEmpty()) {
            address = address + " " + data.storeAddress.getZipCode();
        }
        holder.textAddressLocationBottom.setText(address);
        if (data.getLocationName() != null && !data.getLocationName().isEmpty()) {
            holder.textLocationTitleBottom.setText(data.getLocationName());

        }
        if (data.getLogoImage() != null && data.getLogoImage() != null) {
            Glide.with(context).load(data.getLogoImage()).into(holder.imageLocationBottom);
        }
        else
        {
            Glide.with(context).load(R.drawable.ic_location).into(holder.imageLocationBottom);

        }

        holder.linearCollExLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.imgExpandLocation.getRotation() == 0
                ) {
                    holder.imgExpandLocation.setRotation(180);

                    if (data.storeAddress.getLatitude() != null && data.storeAddress.getLatitude() != null && !data.storeAddress.getLatitude().isEmpty() && !data.storeAddress.getLatitude().isEmpty()) {
                        holder.linearDirection.setVisibility(View.VISIBLE);
                        holder.linearDirection.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", Float.parseFloat(data.storeAddress.getLatitude()), Float.parseFloat(data.storeAddress.getLongitude()));
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                context.startActivity(intent);
                            }
                        });
                    }
                    if (data.getWebsiteUrl() != null && !data.getWebsiteUrl().isEmpty()) {
                        holder.linearURL.setVisibility(View.VISIBLE);
                        holder.textURLLocationBottom.setText(data.getWebsiteUrl());
                        holder.textURLLocationBottom.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getLocationsLinkColor()));
                    }
                    if (data.getMobilePhone() != null && !data.getMobilePhone().isEmpty()) {
                        holder.linearPhone.setVisibility(View.VISIBLE);
                        holder.textPhoneBottom.setText(data.getMobilePhone());
                        holder.textPhoneBottom.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getLocationsLinkColor()));

                    }



                }
                else
                {
                    holder.linearDirection.setVisibility(View.GONE);
                    holder.linearURL.setVisibility(View.GONE);
                    holder.linearPhone.setVisibility(View.GONE);
                    holder.imgExpandLocation.setRotation(0);

                }

            }
        });



    }


    @Override
    public int getItemCount() {
        return Utility.response.responsedata.locationData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textLocationTitleBottom,
                textAddressLocationBottom,
                textGetDirectionLocationBottom,
                textURLLocationBottom,
                textPhoneBottom;

        LinearLayout linearCollExLocation,
                linearDirection,
                linearURL,
                linearPhone;

        ImageView imageLocationBottom,
                imgExpandLocation;


        public ViewHolder(View itemView) {
            super(itemView);

            textLocationTitleBottom = itemView.findViewById(R.id.textLocationTitleBottom);
            textAddressLocationBottom = itemView.findViewById(R.id.textAddressLocationBottom);
            textGetDirectionLocationBottom = itemView.findViewById(R.id.textGetDirectionLocationBottom);
            textURLLocationBottom = itemView.findViewById(R.id.textURLLocationBottom);
            linearCollExLocation = itemView.findViewById(R.id.linearCollExLocation);
            linearDirection = itemView.findViewById(R.id.linearDirection);
            linearURL = itemView.findViewById(R.id.linearURL);
            linearPhone = itemView.findViewById(R.id.linearPhone);
            textPhoneBottom = itemView.findViewById(R.id.textPhoneBottom);
            imageLocationBottom = itemView.findViewById(R.id.imageLocationBottom);
            imgExpandLocation = itemView.findViewById(R.id.imgExpandLocation);


        }
    }
}