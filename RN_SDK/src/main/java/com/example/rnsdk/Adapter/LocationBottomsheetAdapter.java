package com.example.rnsdk.Adapter;


import android.content.Context;
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


        if (data.storeAddress.getLatitude() != null && data.storeAddress.getLatitude() != null && !data.storeAddress.getLatitude().isEmpty() && !data.storeAddress.getLatitude().isEmpty()) {
            holder.linearDirection.setVisibility(View.VISIBLE);

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

        if (data.getLogoImage() != null && data.getLogoImage() != null) {
            Glide.with(context).load(data.getLogoImage()).into(holder.imageLocationBottom);
        }


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

        ImageView imageLocationBottom;


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


        }
    }
}