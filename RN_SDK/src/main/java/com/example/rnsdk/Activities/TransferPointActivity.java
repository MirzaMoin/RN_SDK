package com.example.rnsdk.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.rnsdk.Adapter.CashbackImageSliderAdapter;
import com.example.rnsdk.Adapter.FooterAdapter;
import com.example.rnsdk.Adapter.SliderItem;
import com.example.rnsdk.Models.AppColorModel;
import com.example.rnsdk.Models.ChildPageModel;
import com.example.rnsdk.Models.ChildPageSettingModel;
import com.example.rnsdk.Models.HomeScreenModel;
import com.example.rnsdk.Models.TransaferPointChildPageDataModel;
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class TransferPointActivity extends AppCompatActivity {

    List<SliderItem> mSliderItems = new ArrayList<>();
    ImageView imgBack;
    RecyclerView rvFooterTransferPoint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_point);

        init();
    }

    private void init() {

        rvFooterTransferPoint = findViewById(R.id.rvFooterTransferPoint);

        ChildPageSettingModel childPageSettings = Utility.response.responsedata.childPageSetting;

        if(childPageSettings.isChildPageTransaferPoint()) {
            List<ChildPageModel> childPage = new ArrayList<>();
            for (TransaferPointChildPageDataModel point : childPageSettings.transaferPointChildPageData) {
                childPage.add(new ChildPageModel(point.image, point.opacity, point.isClickable, point.linkType, point.internalLink, point.externalLink));
            }

            SliderView sliderView = findViewById(R.id.imageSliderTransferPoints);

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
            rvFooterTransferPoint.setVisibility(View.VISIBLE);
            rvFooterTransferPoint.setBackgroundColor(Utility.getColor(appColor.getFooterBarColor()));

            FooterAdapter adapter = new FooterAdapter(this,homeScreenModel.footerLinks,"transferPoint");
            rvFooterTransferPoint.setHasFixedSize(true);


            rvFooterTransferPoint.setLayoutManager(new GridLayoutManager(this,homeScreenModel.footerLinks.size()));

            rvFooterTransferPoint.setAdapter(adapter);
        }
        else
        {
            rvFooterTransferPoint.setVisibility(View.GONE);


        }


    }

}