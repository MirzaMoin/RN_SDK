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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fontawsomeicon.FontAwesomeIC;
import com.example.robosdk.Activities.WebViewActivity;
import com.example.robosdk.Models.HomeScreenModel;
import com.example.robosdk.Models.MenuLinkModel;
import com.example.robosdk.R;
import com.example.robosdk.Utility.Utility;

import java.util.List;

import info.androidhive.fontawesome.FontDrawable;

public class HomeMenuLinkListAdapter extends RecyclerView.Adapter<HomeMenuLinkListAdapter.ViewHolder> {

    List<MenuLinkModel> menuLinks;
    static boolean isList;
    boolean isFirst;
    Context context;

    public HomeMenuLinkListAdapter(Context context, List<MenuLinkModel> menuLinks, boolean isList, boolean isFirst) {
        this.menuLinks = menuLinks;
        this.context = context;
        HomeMenuLinkListAdapter.isList = isList;
        this.isFirst = isFirst;

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
        if (isList) {
            listItem = layoutInflater.inflate(R.layout.content_list_home_menu_link, parent, false);

        } else {
            listItem = layoutInflater.inflate(R.layout.content_grid_menu_link, parent, false);

        }
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        final MenuLinkModel menu;

        if (!isFirst) {
            if (menuLinks.size() > 6) {
                menu = menuLinks.get(position + 6);
            } else {
                menu = menuLinks.get(position);
            }
        } else {
            menu = menuLinks.get(position);
        }
        HomeScreenModel home = Utility.response.responsedata.homeScreen;

        if (home.isHomePageBottomDisplayIcon()) {

//            FontDrawable drawable = new FontDrawable(context, Utility.getIcon(menu.getIcon()), true, false);
            Glide.with(context).load(Utility.getIcon(menu.getIcon())).into(holder.icMenuLinkIcon);
            holder.icMenuLinkIcon.setColorFilter(Utility.getColor(home.getHomePageBottomIconColor()));


            //.getView().setColorFilter(Utility.getColor(home.getHomePageBottomIconColor()));
//           holder.icMenuLinkIcon.setImageDrawable(drawable);

            Log.e("Color", "" + home.getHomePageBottomIconColor());


//           holder.icMenuLinkIcon.setColorFilter(Utility.getColor(home.getHomePageBottomIconColor()));
            holder.cardMenuLink.setVisibility(View.VISIBLE);
            holder.cardMenuLink.setCardBackgroundColor(Utility.getColor(home.getHomePageBottomIconBackgroundColor()));

            if (home.getHomePageBottomIconShape().equals("square")) {
                Log.e("Test", "Called Square");
                holder.cardMenuLink.setRadius(20);
            } else if (home.getHomePageBottomIconShape().equals("none")) {
                holder.cardMenuLink.setCardBackgroundColor(Utility.getColor("#FFFFFF00"));
                holder.cardMenuLink.setBackgroundColor(Utility.getColor("#FFFFFF00"));
                holder.cardMenuLink.setCardElevation(0);

            }

        }

        holder.textMenuLink.setTextColor(Utility.getColor(menu.getMenuTextColor()));

        if (isList) {
            if (home.getHomePageBottomTextAlign().equals("Center")) {
                RelativeLayout.LayoutParams params =
                        new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                                RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
                holder.textMenuLink.setLayoutParams(params);
            } else if (home.getHomePageBottomTextAlign().equals("Right")) {
                if (home.isHomePageBottomDisplayArrowIcon()) {
                    RelativeLayout.LayoutParams params =
                            new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                                    RelativeLayout.LayoutParams.WRAP_CONTENT);
                    params.addRule(RelativeLayout.LEFT_OF, R.id.icNextIconList);
                    params.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
                    holder.textMenuLink.setLayoutParams(params);
                } else {
                    RelativeLayout.LayoutParams params =
                            new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                                    RelativeLayout.LayoutParams.WRAP_CONTENT);
                    params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                    holder.textMenuLink.setLayoutParams(params);
                }

            }

            holder.textMenuLink.setText(menu.menuText);
            Glide.with(context).load(menu.getMenuBackgroudImage()).into(holder.imgListMenuLinkBackground);
            if (home.isHomePageBottomDisplayArrowIcon()) {
                holder.icNextIcon.setVisibility(View.VISIBLE);
                holder.icNextIcon.setColorFilter(Utility.getColor(home.getHomePageBottomArrowColor()));
            }


            int[] colors = {Utility.getColor(menu.getMenuTopColor()),
                    Utility.getColor(menu.getMenuBottomColor())};

            //create a new gradient color
            GradientDrawable gd = new GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM, colors);

            //apply the button background to newly created drawable gradient
            holder.relMenuLinkList.setBackground(gd);

        } else {
            if (isFirst) {
                if (position <= 5) {
                    holder.textMenuLink.setText(menu.menuText);

                }
            } else {


                holder.textMenuLink.setText(menu.menuText);

            }

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (menu.getMenuLinkType().equals("internal")) {
                    Utility.openNewActivity(context, menu.getMenuInternalLinkUrl(), menu.getId(), menu.getRewardProgramId());
                } else {
                    Intent i = new Intent(context, WebViewActivity.class);
                    i.putExtra("url", menu.getMenuExternalLinkUrl());
                    i.putExtra("id", menu.getId());
                    i.putExtra("rewardProgramId", menu.getRewardProgramId());

                    context.startActivity(i);
                    //open URL
                }

            }
        });


    }


    @Override
    public int getItemCount() {
        if (isList) {
            return menuLinks.size();

        } else {
            if (isFirst) {
                if (menuLinks.size() > 6) {
                    return 6;
                } else {
                    return menuLinks.size();
                }

            } else {
                if (menuLinks.size() > 6) {
                    return menuLinks.size() - 6;
                } else {
                    return 0;
                }
            }

        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textMenuLink;
        ImageView icMenuLinkIcon, icNextIcon, imgListMenuLinkBackground;
        CardView cardMenuLink;
        RelativeLayout relMenuLinkList;

        public ViewHolder(View itemView) {
            super(itemView);

            textMenuLink = itemView.findViewById(R.id.textMenuLinkText);
            icMenuLinkIcon = itemView.findViewById(R.id.icMenuLinkIcon);
            cardMenuLink = itemView.findViewById(R.id.cardMenuList);
            if (isList) {
                icNextIcon = itemView.findViewById(R.id.icNextIconList);
                relMenuLinkList = itemView.findViewById(R.id.relMenuLinkList);
                imgListMenuLinkBackground = itemView.findViewById(R.id.imgListMenuLinkBackground);

            }

        }
    }
}