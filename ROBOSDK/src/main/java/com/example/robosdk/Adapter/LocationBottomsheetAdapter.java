package com.example.robosdk.Adapter;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.robosdk.Models.LocationDataModel;
import com.example.robosdk.R;
import com.example.robosdk.Utility.PermissionUtils;
import com.example.robosdk.Utility.Utility;

import java.util.Locale;

public class LocationBottomsheetAdapter extends RecyclerView.Adapter<LocationBottomsheetAdapter.ViewHolder> {

    Context context;
    Activity activity;


    public LocationBottomsheetAdapter(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.content_location_bottomsheet, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.imgExpandLocation.setRotation(0);
        final LocationDataModel data = Utility.response.responsedata.locationData.get(position);


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
        } else {
//            Glide.with(context).load(R.drawable.ic_location).into(holder.imageLocationBottom);

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
                        holder.textURLLocationBottom.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse(data.getWebsiteUrl()));
                                context.startActivity(i);
                            }
                        });
                    }
                    if (data.getMobilePhone() != null && !data.getMobilePhone().isEmpty()) {
                        holder.linearPhone.setVisibility(View.VISIBLE);
                        holder.textPhoneBottom.setText(data.getMobilePhone());
                        holder.textPhoneBottom.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getLocationsLinkColor()));
                        holder.textPhoneBottom.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                checkPhonePermission(data.getMobilePhone());
                            }
                        });
                    }
                } else {
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

       int nullCount = 0;


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

            for(LocationDataModel location : Utility.response.responsedata.locationData){
                if(location.logoImage == null)
                {
                    nullCount++;
                }
            }
            if(nullCount == Utility.response.responsedata.locationData.size())
            {
                imageLocationBottom.setVisibility(View.GONE);
            }

        }
    }

    private static final int PHONE_PERMISSION_REQUEST_CODE = 888;


    protected void checkPhonePermission(String mobilePhone) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager
                .PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (PermissionUtils.neverAskAgainSelected(activity, Manifest.permission.CALL_PHONE)) {
                    displayNeverAskAgainDialog();
                } else {
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE},
                            PHONE_PERMISSION_REQUEST_CODE);
                }
            }
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobilePhone));
            context.startActivity(intent);

        }
    }

    private void displayNeverAskAgainDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setMessage("We need phone permission for performing necessary task. Please permit the permission through "
                + "Settings screen.\n\nSelect Permissions -> Enable permission");


        builder.setCancelable(false);
        builder.setPositiveButton("Permit Manually", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent();
                intent.setAction(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                intent.setData(uri);
                context.startActivity(intent);
                activity.finish();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

}