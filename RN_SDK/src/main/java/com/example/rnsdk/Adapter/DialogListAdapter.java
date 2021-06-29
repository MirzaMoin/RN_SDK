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
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;

import static com.example.rnsdk.Activities.LeaderboardActivity.textDateBottomSheetLeader;

public class DialogListAdapter extends RecyclerView.Adapter<DialogListAdapter.ViewHolder> {

    Context context;
    String activity;

    public DialogListAdapter(Context context, String activity) {
        this.context = context;
        this.activity = activity;
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

        } else {
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
        } else {
            return 28;
        }


    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivCorrect;
        TextView textTitleListDialog;

        public ViewHolder(View itemView) {
            super(itemView);

            ivCorrect = itemView.findViewById(R.id.imageCorrect);
            textTitleListDialog = itemView.findViewById(R.id.textTitleListDialog);

        }
    }
}