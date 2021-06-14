package com.example.rnsdk.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.rnsdk.Adapter.CashbackImageSliderAdapter;
import com.example.rnsdk.Adapter.FooterAdapter;
import com.example.rnsdk.Adapter.SliderItem;
import com.example.rnsdk.Models.AppColorModel;
import com.example.rnsdk.Models.ChildPageModel;
import com.example.rnsdk.Models.ChildPageSettingModel;
import com.example.rnsdk.Models.HomeScreenModel;
import com.example.rnsdk.Models.ReferFriendChildPageDataModel;
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class ReferFriendActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;


    RecyclerView rvFooterReferFriend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_friend);

        init();
    }

    private void init() {
        toolbar = findViewById(R.id.toolbarReferFriend);
        rvFooterReferFriend = findViewById(R.id.rvFooterReferFriend);




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle("");
            setSupportActionBar(toolbar);

        }

        ChildPageSettingModel childPageSettings = Utility.response.responsedata.childPageSetting;

        if(childPageSettings.isChildPageReferFriend()) {
            List<ChildPageModel> childPage = new ArrayList<>();
            for (ReferFriendChildPageDataModel refer : childPageSettings.referFriendChildPageData) {
                childPage.add(new ChildPageModel(refer.image, refer.opacity, refer.isClickable, refer.linkType, refer.internalLink, refer.externalLink));
            }


            SliderView sliderView = findViewById(R.id.imageSliderReferFriend);

            CashbackImageSliderAdapter adapter = new CashbackImageSliderAdapter(this, childPage);


            sliderView.setSliderAdapter(adapter);

            sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
            sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
            sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
            sliderView.setIndicatorSelectedColor(Color.WHITE);
            sliderView.setIndicatorUnselectedColor(Color.GRAY);
            sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
            sliderView.startAutoCycle();
        }
        setFooter();
    }
    private void setFooter() {
        AppColorModel appColor = Utility.response.responsedata.appColor;

        HomeScreenModel homeScreenModel = Utility.response.responsedata.homeScreen;
        if(homeScreenModel.isHomePageDisplayFooter())
        {
            rvFooterReferFriend.setVisibility(View.VISIBLE);
            rvFooterReferFriend.setBackgroundColor(Utility.getColor(appColor.getFooterBarColor()));

            FooterAdapter adapter = new FooterAdapter(this,homeScreenModel.footerLinks,"profileScreen");
            rvFooterReferFriend.setHasFixedSize(true);


            rvFooterReferFriend.setLayoutManager(new GridLayoutManager(this,homeScreenModel.footerLinks.size()));

            rvFooterReferFriend.setAdapter(adapter);
        }
        else
        {
            rvFooterReferFriend.setVisibility(View.GONE);


        }


    }

    @Override
    public void onClick(View v) {
    }
}