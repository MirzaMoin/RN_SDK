package com.example.rnsdk.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rnsdk.Activities.WebViewActivity;
import com.example.rnsdk.Models.FooterLinkModel;
import com.example.rnsdk.Models.HomeScreenModel;
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;

import java.util.List;

import info.androidhive.fontawesome.FontDrawable;

public class FooterAdapter extends RecyclerView.Adapter<FooterAdapter.ViewHolder>{

    List<FooterLinkModel> footerLinks;
    String currentActivity;

    Context context;
   public FooterAdapter(Context context, List<FooterLinkModel> footerLinks, String currentActivity){
       this.footerLinks = footerLinks;
       this.context = context;

       this.currentActivity = currentActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.content_footer, parent, false);

        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

       holder.setIsRecyclable(false);
       FooterLinkModel footer = footerLinks.get(position);
        HomeScreenModel home = Utility.response.responsedata.homeScreen;

        FontDrawable drawable = new FontDrawable(context, Utility.getIcon(footer.getFooterIcon()), true, false);
        holder.icFooterLinkIcon.setImageDrawable(drawable);

            holder.textFooterLink.setText(footer.getFooterText());
            if(currentActivity.equals(footer.getFooterInternalLinkUrl())){
                holder.textFooterLink.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getFooterBarActionIconAndTextColor()));
                holder.icFooterLinkIcon.setColorFilter(Utility.getColor(Utility.response.responsedata.appColor.getFooterBarActionIconAndTextColor()));
            }
            else
            {
                holder.textFooterLink.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getFooterBarInactiveIconColor()));
                holder.icFooterLinkIcon.setColorFilter(Utility.getColor(Utility.response.responsedata.appColor.getFooterBarInactiveIconColor()));

            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(footer.getFooterLinkType().equals("internal")){
                        Utility.openNewActivity(context, footer.getFooterInternalLinkUrl(), footer.getId(), footer.getFooterInternalLinkUrl());
                    }
                    else
                    {
                        Intent i = new Intent(context, WebViewActivity.class);
                        i.putExtra("url",footer.getFooterExternalLinkUrl());
                        i.putExtra("id",footer.getId());
                        i.putExtra("rewardProgramId",footer.getRewardProgramId());
                        context.startActivity(i);
                    }

                }
            });





    }


    @Override
    public int getItemCount() {

                   return footerLinks.size();


    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

       TextView textFooterLink;
       ImageView icFooterLinkIcon;
       CardView cardMenuLink;
       RelativeLayout relMenuLinkList;
        public ViewHolder(View itemView) {
            super(itemView);

            textFooterLink = itemView.findViewById(R.id.textFooterLink);
            icFooterLinkIcon = itemView.findViewById(R.id.icFooterLinkIcon);
//            cardMenuLink = itemView.findViewById(R.id.cardMenuList);


        }
    }
}