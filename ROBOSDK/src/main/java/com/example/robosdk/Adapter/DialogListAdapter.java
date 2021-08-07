package com.example.robosdk.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.robosdk.Activities.LeaderboardActivity;
import com.example.robosdk.Models.LBFilterModel;
import com.example.robosdk.Models.LocationDataModel;
import com.example.robosdk.Models.URAddressModel;
import com.example.robosdk.Models.URCategoryModel;
import com.example.robosdk.R;
import com.example.robosdk.Utility.Utility;

import static com.example.robosdk.Activities.LeaderboardActivity.textDateBottomSheetLeader;

public class DialogListAdapter extends RecyclerView.Adapter<DialogListAdapter.ViewHolder> {

    Context context;
    String activity;
    int selectedReceiptIndex;

    public DialogListAdapter(Context context, String activity, int selectedReceiptIndex) {
        this.context = context;
        this.activity = activity;
        this.selectedReceiptIndex = selectedReceiptIndex;

        if(activity.equals("Location"))
        {
            for(LocationDataModel location : Utility.response.responsedata.locationData)
            {
                location.setSelected(false);
            }
        }
        else if(activity.equals("UploadReceipt"))
        {

        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.content_list_dialog, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        if (activity.equals("Leaderboard")) {
            final LBFilterModel data = Utility.response.responsedata.filters.get(position);
            holder.textTitleListDialog.setText(data.getDisplay());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LeaderboardActivity.monthDialog.dismiss();
                    textDateBottomSheetLeader.setText(data.getDisplay());
                    LeaderboardActivity.month = data.getMonth();
                    LeaderboardActivity.year = data.getYear();
                }
            });

        }
        else if (activity.equals("Location")) {
            final LocationDataModel data = Utility.response.responsedata.locationData.get(position);
            holder.textTitleListDialog.setText(data.getLocationName());
            holder.viewContentLocationDialog.setVisibility(View.VISIBLE);
            if(data.isSelected())
            {
                holder.ivCorrect.setVisibility(View.VISIBLE);
            }
            else
            {
                holder.ivCorrect.setVisibility(View.GONE);
            }

            holder.textTitleListDialog.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getTitleTextColor()));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    for(LocationDataModel location : Utility.response.responsedata.locationData)
                    {
                        location.setSelected(location.getAddressId().equals(data.getAddressId()));
                    }
                    notifyDataSetChanged();

                }
            });
        }
        else if(activity.equals("URReceipt"))
        {
            final URCategoryModel data = Utility.response.responsedata.categories.get(position);
            holder.textTitleListDialog.setText(data.getName());
            holder.viewContentLocationDialog.setVisibility(View.VISIBLE);
            if(data.isSelected())
            {
                holder.ivCorrect.setVisibility(View.VISIBLE);
            }
            else
            {
                holder.ivCorrect.setVisibility(View.GONE);
            }

            holder.textTitleListDialog.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getTitleTextColor()));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    for(URCategoryModel categoryModel : Utility.response.responsedata.categories)
                    {
                        categoryModel.setSelected(categoryModel.getId().equals(data.getId()));
                    }
                    notifyDataSetChanged();
                }
            });

        }
        else if(activity.equals("URLocation"))
        {

            final URAddressModel data = Utility.response.responsedata.categories.get(selectedReceiptIndex).addresses.get(position);
            holder.textTitleListDialog.setText(data.getLocationName());
            holder.viewContentLocationDialog.setVisibility(View.VISIBLE);
            if(data.isSelected())
            {
                holder.ivCorrect.setVisibility(View.VISIBLE);
            }
            else
            {
                holder.ivCorrect.setVisibility(View.GONE);
            }

            holder.textTitleListDialog.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getTitleTextColor()));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    for(URAddressModel addressModel : Utility.response.responsedata.categories.get(selectedReceiptIndex).addresses)
                    {
                        addressModel.setSelected(addressModel.getAddressID().equals(data.getAddressID()));
                    }
                    notifyDataSetChanged();
                }
            });

        }
        else {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.ivCorrect.setVisibility(View.VISIBLE);
                }
            });
        }

    }


    @Override
    public int getItemCount() {
        if (activity.equals("Leaderboard")) {
            return Utility.response.responsedata.filters.size();
        } else if (activity.equals("Location")) {
            return Utility.response.responsedata.locationData.size();
        }
        else if(activity.equals("URReceipt"))
        {
            return Utility.response.responsedata.categories.size();
        }
        else if(activity.equals("URLocation"))
        {
            return Utility.response.responsedata.categories.get(selectedReceiptIndex).addresses.size();
        }
        else {
            return 28;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivCorrect;
        TextView textTitleListDialog;
        View viewContentLocationDialog;

        public ViewHolder(View itemView) {
            super(itemView);

            ivCorrect = itemView.findViewById(R.id.imageCorrect);
            textTitleListDialog = itemView.findViewById(R.id.textTitleListDialog);
            viewContentLocationDialog = itemView.findViewById(R.id.viewContentLocationDialog);
        }
    }
}