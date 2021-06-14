package com.example.rnsdk.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rnsdk.Adapter.CashbackImageSliderAdapter;
import com.example.rnsdk.Adapter.FooterAdapter;
import com.example.rnsdk.Adapter.HomeMenuLinkListAdapter;
import com.example.rnsdk.Adapter.WaysToEarnAdapter;
import com.example.rnsdk.Models.AppColorModel;
import com.example.rnsdk.Models.ChildPageModel;
import com.example.rnsdk.Models.ChildPageSettingModel;
import com.example.rnsdk.Models.HomeScreenModel;
import com.example.rnsdk.Models.ProfileEditChildPageDataModel;
import com.example.rnsdk.Models.WaysToEarnChildPageDataModel;
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class WaysToEarnActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rvList, rvFooterUploadWaysToEarn;
    ImageView imgBackWaysToEarn;
    TextView textPointWaysToEarn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ways_to_earn);


        init();

        WaysToEarnAdapter adapter = new WaysToEarnAdapter();
        rvList.setHasFixedSize(true);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(adapter);


    }

    private void init() {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Utility.getColor(Utility.response.responsedata.appColor.getPhoneNotificationBar()));
        }
        if(Utility.response.responsedata.appColor.getPhoneNotificationBarTextColor().equals("Black")){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        rvList = findViewById(R.id.rvWaysToEarn);
        textPointWaysToEarn = findViewById(R.id.textPointWaysToEarn);
        rvFooterUploadWaysToEarn = findViewById(R.id.rvFooterUploadWaysToEarn);
        imgBackWaysToEarn = findViewById(R.id.imgBackWaysToEarn);
        textPointWaysToEarn.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderPointDigitColor()));


        imgBackWaysToEarn.setOnClickListener(this);

        SliderView sliderView = findViewById(R.id.imageWaysToEarn);
        ChildPageSettingModel childPageSettings = Utility.response.responsedata.childPageSetting;
        if (childPageSettings.isChildPageWte()) {
            sliderView.setVisibility(View.VISIBLE);

            List<ChildPageModel> childPage = new ArrayList<>();
            for (WaysToEarnChildPageDataModel earn : childPageSettings.wteChildPageData) {
                childPage.add(new ChildPageModel(earn.image, earn.opacity, earn.isClickable, earn.linkType, earn.internalLink, earn.externalLink));
            }



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
        if (homeScreenModel.isHomePageDisplayFooter()) {
            rvFooterUploadWaysToEarn.setVisibility(View.VISIBLE);
            rvFooterUploadWaysToEarn.setBackgroundColor(Utility.getColor(appColor.getFooterBarColor()));

            FooterAdapter adapter = new FooterAdapter(this, homeScreenModel.footerLinks, "waysToEarn");
            rvFooterUploadWaysToEarn.setHasFixedSize(true);


            rvFooterUploadWaysToEarn.setLayoutManager(new GridLayoutManager(this, homeScreenModel.footerLinks.size()));

            rvFooterUploadWaysToEarn.setAdapter(adapter);
        } else {
            rvFooterUploadWaysToEarn.setVisibility(View.GONE);


        }


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgBackWaysToEarn) {
            super.onBackPressed();
        }
    }
}