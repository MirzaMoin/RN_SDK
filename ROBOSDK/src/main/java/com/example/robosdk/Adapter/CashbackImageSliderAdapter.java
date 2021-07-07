package com.example.robosdk.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.robosdk.Activities.WebViewActivity;
import com.example.robosdk.Models.ChildPageModel;
import com.example.robosdk.R;
import com.example.robosdk.Utility.Utility;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class CashbackImageSliderAdapter extends
        SliderViewAdapter<CashbackImageSliderAdapter.SliderAdapterVH> {

    private Context context;
    private List<ChildPageModel> mSliderItems = new ArrayList<>();

    public CashbackImageSliderAdapter(Context context,List<ChildPageModel> mSliderItems) {
        this.context = context;
        this.mSliderItems = mSliderItems;

    }



    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

        final ChildPageModel child = mSliderItems.get(position);

        Glide.with(viewHolder.itemView)
                .load(child.getImage())
                .fitCenter()
                .into(viewHolder.imageViewBackground);

        if(child.getOpacity() >0){
            viewHolder.imageViewBackground.setAlpha(child.getOpacity());

        }


        if(child.isClickable()) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(child.getLinkType().equals("internal")){
                        Utility.openNewActivity(context,child.getInternalLink(),0,"");
                    }
                    else
                    {
                        Intent i = new Intent(context, WebViewActivity.class);
                        i.putExtra("url",child.getExternalLink());
                        i.putExtra("id",0);
                        i.putExtra("rewardProgramId","");

                        context.startActivity(i);
                    }
                }
            });
        }
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mSliderItems.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        ImageView imageGifContainer;
        TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.imgSliderCashback);
            this.itemView = itemView;
        }
    }

}
