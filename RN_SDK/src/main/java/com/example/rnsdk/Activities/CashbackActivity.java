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
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.rnsdk.Adapter.CashbackImageSliderAdapter;
import com.example.rnsdk.Adapter.FooterAdapter;
import com.example.rnsdk.Adapter.SliderItem;
import com.example.rnsdk.Models.AppColorModel;
import com.example.rnsdk.Models.ChildPageModel;
import com.example.rnsdk.Models.ChildPageSettingModel;
import com.example.rnsdk.Models.HomeScreenModel;
import com.example.rnsdk.Models.RedeemCashBackChildPageDataModel;
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class CashbackActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
     List<SliderItem> mSliderItems = new ArrayList<>();
     ImageView imgBack;
     LinearLayout linearRPGCashback,linearHome;
     RecyclerView rvFooterCashback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashback);


        init();
    }

    private void init() {
        toolbar = findViewById(R.id.toolbarCashback);
        imgBack = findViewById(R.id.imgBackRedeemCashback);
        linearRPGCashback = findViewById(R.id.linearRPGCashback);
        linearHome = findViewById(R.id.linearHomeCashback);
        rvFooterCashback = findViewById(R.id.rvFooterCashback);
        imgBack.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle("");
            setSupportActionBar(toolbar);

        }
        ChildPageSettingModel childPageSettings = Utility.response.responsedata.childPageSetting;

        if(childPageSettings.isChildPageRedeemCashBack()) {
            List<ChildPageModel> childPage = new ArrayList<>();
            for (RedeemCashBackChildPageDataModel cashback : childPageSettings.redeemCashBackChildPageData) {
                childPage.add(new ChildPageModel(cashback.image, cashback.opacity, cashback.isClickable, cashback.linkType, cashback.internalLink, cashback.externalLink));
            }


            SliderView sliderView = findViewById(R.id.imageSliderCashback);

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
            rvFooterCashback.setVisibility(View.VISIBLE);
            rvFooterCashback.setBackgroundColor(Utility.getColor(appColor.getFooterBarColor()));

            FooterAdapter adapter = new FooterAdapter(this,homeScreenModel.footerLinks,"redeemCashback");
            rvFooterCashback.setHasFixedSize(true);


            rvFooterCashback.setLayoutManager(new GridLayoutManager(this,homeScreenModel.footerLinks.size()));

            rvFooterCashback.setAdapter(adapter);
        }
        else
        {
            rvFooterCashback.setVisibility(View.GONE);


        }


    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgBackRedeemCashback) {
            super.onBackPressed();
        }
        else if (v.getId() == R.id.linearRPGCashback) {
            startActivity(new Intent(CashbackActivity.this,RewardEntryGoalActivity.class));
        }
        else if (v.getId() == R.id.linearHomeCashback) {
            startActivity(new Intent(CashbackActivity.this,HomeActivity.class));
        }
    }
}