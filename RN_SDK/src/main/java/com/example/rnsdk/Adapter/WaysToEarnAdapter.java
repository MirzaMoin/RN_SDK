package com.example.rnsdk.Adapter;


import android.content.Context;
import android.graphics.Color;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rnsdk.Models.ChildPageModel;
import com.example.rnsdk.Models.ChildPageSettingModel;
import com.example.rnsdk.Models.WaysToEarnChildPageDataModel;
import com.example.rnsdk.Models.WaysToEarnModel;
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;

public class WaysToEarnAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int LAYOUT_ONE= 0;
    private static final int LAYOUT_TWO= 1;
    Context context;
    List<WaysToEarnModel> waysList;
   public WaysToEarnAdapter(Context context, List<WaysToEarnModel> waysList){
       this.context = context;
       this.waysList = waysList;
    }
    @Override
    public int getItemViewType(int position)
    {
        if(position==0)
            return LAYOUT_ONE;
        else
            return LAYOUT_TWO;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view =null;
        RecyclerView.ViewHolder viewHolder = null;

        if(viewType==LAYOUT_ONE)
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_sliderview,parent,false);
            viewHolder = new ViewHolderOne(view);
        }
        else
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_ways_to_earn,parent,false);
            viewHolder= new ViewHolderTwo(view);
        }

        return viewHolder;

    }


    public class ViewHolderOne extends RecyclerView.ViewHolder {
        SliderView sliderView;

        public ViewHolderOne(View itemView) {
            super(itemView);
            sliderView = itemView.findViewById(R.id.imageContentSliderview);
               }
    }

    public class ViewHolderTwo extends RecyclerView.ViewHolder{
        LinearLayout linearContent;


        TextView textTitleWaysToEarnContent,
                textSubTitleWaysToEarnContent,
                textPointWaysToEarnContent;
        ExpandableTextView expTv;

        ImageView imageWaysToEarnContent;

        public ViewHolderTwo(View itemView) {
            super(itemView);
            linearContent = itemView.findViewById(R.id.linearWaysToEarn);
            textTitleWaysToEarnContent = itemView.findViewById(R.id.textTitleWaysToEarnContent);
            textSubTitleWaysToEarnContent = itemView.findViewById(R.id.textSubTitleWaysToEarnContent);
            textPointWaysToEarnContent = itemView.findViewById(R.id.textPointWaysToEarnContent);
            imageWaysToEarnContent = itemView.findViewById(R.id.imageWaysToEarnContent);
            expTv = (ExpandableTextView) itemView.findViewById(R.id.expand_text_view).findViewById(R.id.expand_text_view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        if(viewHolder.getItemViewType()== LAYOUT_ONE)
        {
            ViewHolderOne holder = (ViewHolderOne) viewHolder;

            ChildPageSettingModel childPageSettings = Utility.response.responsedata.childPageSetting;
            if (childPageSettings.isChildPageWte()) {
                holder.sliderView.setVisibility(View.VISIBLE);

                List<ChildPageModel> childPage = new ArrayList<>();
                for (WaysToEarnChildPageDataModel earn : childPageSettings.wteChildPageData) {
                    childPage.add(new ChildPageModel(earn.image, earn.opacity, earn.isClickable, earn.linkType, earn.internalLink, earn.externalLink));
                }

                CashbackImageSliderAdapter adapter = new CashbackImageSliderAdapter(context, childPage);

                holder.sliderView.setSliderAdapter(adapter);

                holder.sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                holder.sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                holder.sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                holder.sliderView.setIndicatorSelectedColor(Color.WHITE);
                holder.sliderView.setIndicatorUnselectedColor(Color.GRAY);
                holder.sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
                holder.sliderView.startAutoCycle();
            }

        }
        else {
            WaysToEarnModel ways = waysList.get(position-1);
            ViewHolderTwo holder = (ViewHolderTwo) viewHolder;
            holder.expTv.setText(ways.getDescription());
            holder.textTitleWaysToEarnContent.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getTitleTextColor()));
            holder.textTitleWaysToEarnContent.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getTitleTextColor()));
            holder.textTitleWaysToEarnContent.setText(ways.getTitle());
            holder.textSubTitleWaysToEarnContent.setText(ways.getSubtitle());
            holder.textPointWaysToEarnContent.setText(String.valueOf(ways.getPoints())+" ");
            if(ways.getImageURL() != null)
            {
                holder.imageWaysToEarnContent.setVisibility(View.VISIBLE);
                Glide.with(context).load(ways.getImageURL()).into(holder.imageWaysToEarnContent);

            }

            if(position % 2 == 0)
            {
                holder.linearContent.setBackgroundColor(Color.parseColor("#D3D3D3"));
            }
        }

    }

    @Override
    public int getItemCount() {
        return waysList.size()+1;
    }

  /*  public static class ViewHolder extends RecyclerView.ViewHolder {

       LinearLayout linearContent;
        // getting reference of ExpandableTextView
        ExpandableTextView expTv;

// calling setText on the ExpandableTextView so that
// text content will be displayed to the user


        public ViewHolder(View itemView) {
            super(itemView);
            linearContent = itemView.findViewById(R.id.linearWaysToEarn);
            expTv = (ExpandableTextView) itemView.findViewById(R.id.expand_text_view).findViewById(R.id.expand_text_view);
        }
    }*/
}