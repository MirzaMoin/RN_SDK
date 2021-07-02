package com.example.rnsdk.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rnsdk.Activities.LeaderboardActivity;
import com.example.rnsdk.Models.LBFilterModel;
import com.example.rnsdk.Models.LocationDataModel;
import com.example.rnsdk.Models.URAddressModel;
import com.example.rnsdk.Models.URCategoryModel;
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;

import static com.example.rnsdk.Activities.LeaderboardActivity.textDateBottomSheetLeader;

public class DialogListAdapter extends RecyclerView.Adapter<DialogListAdapter.ViewHolder> {

    Context context;
    String activity;
    String selectedAddressID = "";
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
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (activity.equals("Leaderboard")) {
            LBFilterModel data = Utility.response.responsedata.filters.get(position);
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
            LocationDataModel data = Utility.response.responsedata.locationData.get(position);
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
                        if(location.getAddressId() == data.getAddressId())
                        {
                            location.setSelected(true);
                        }
                        else
                        {
                            location.setSelected(false);
                        }
                    }


                    notifyDataSetChanged();


//                            selectedAddressID = data.getAddressId();
//                        Constants.selectedAddressID = data.getAddressId();

                }
            });

        }
        else if(activity.equals("URReceipt"))
        {
            URCategoryModel data = Utility.response.responsedata.categories.get(position);
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
                        if(categoryModel.getId() == data.getId())
                        {
                            categoryModel.setSelected(true);
                        }
                        else
                        {
                            categoryModel.setSelected(false);
                        }
                    }
                    notifyDataSetChanged();
                }
            });

        }
        else if(activity.equals("URLocation"))
        {

            URAddressModel data = Utility.response.responsedata.categories.get(selectedReceiptIndex).addresses.get(position);
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
                        if(addressModel.getAddressID() == data.getAddressID())
                        {
                            addressModel.setSelected(true);
                        }
                        else
                        {
                            addressModel.setSelected(false);
                        }
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