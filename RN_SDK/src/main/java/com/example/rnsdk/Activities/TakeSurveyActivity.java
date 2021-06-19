package com.example.rnsdk.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rnsdk.Adapter.CashbackImageSliderAdapter;
import com.example.rnsdk.Adapter.FooterAdapter;
import com.example.rnsdk.Adapter.SliderItem;
import com.example.rnsdk.Models.AppColorModel;
import com.example.rnsdk.Models.ChildPageModel;
import com.example.rnsdk.Models.ChildPageSettingModel;
import com.example.rnsdk.Models.HomeScreenModel;
import com.example.rnsdk.Models.TakeSurveyChildPageDataModel;
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class TakeSurveyActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textTaken, textUntaken,textPointSurvey;
    Boolean isTaken = true;
    List<SliderItem> mSliderItems = new ArrayList<>();
    RecyclerView rvFooterTakeSurvey;
    ImageView imgBackSurvey;

    @Override
    protected void onResume() {
        super.onResume();
        setFooter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_survey);

        init();
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
        ChildPageSettingModel childPageSettings = Utility.response.responsedata.childPageSetting;

        if (childPageSettings.isChildPageTakeSurvey()) {
            List<ChildPageModel> childPage = new ArrayList<>();
            for (TakeSurveyChildPageDataModel survey : childPageSettings.takeSurveyChildPageData) {
                childPage.add(new ChildPageModel(survey.image, survey.opacity, survey.isClickable, survey.linkType, survey.internalLink, survey.externalLink));
            }

            SliderView sliderView = findViewById(R.id.imageSliderSurvey);
            sliderView.setVisibility(View.VISIBLE);

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

        rvFooterTakeSurvey = findViewById(R.id.rvFooterTakeSurvey);
        textTaken = findViewById(R.id.textTakenTab);
        textUntaken = findViewById(R.id.textUntakenTab);
        imgBackSurvey = findViewById(R.id.imgBackSurvey);
        textPointSurvey = findViewById(R.id.textPointSurvey);
        textPointSurvey.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderPointDigitColor()));


        textTaken.setOnClickListener(this);
        textUntaken.setOnClickListener(this);
        imgBackSurvey.setOnClickListener(this);
        setFooter();
    }

    private void setFooter() {
        AppColorModel appColor = Utility.response.responsedata.appColor;

        HomeScreenModel homeScreenModel = Utility.response.responsedata.homeScreen;
        if (homeScreenModel.isHomePageDisplayFooter()) {
            rvFooterTakeSurvey.setVisibility(View.VISIBLE);
            rvFooterTakeSurvey.setBackgroundColor(Utility.getColor(appColor.getFooterBarColor()));

            FooterAdapter adapter = new FooterAdapter(this, homeScreenModel.footerLinks, "takeSurvey");
            rvFooterTakeSurvey.setHasFixedSize(true);


            rvFooterTakeSurvey.setLayoutManager(new GridLayoutManager(this, homeScreenModel.footerLinks.size()));

            rvFooterTakeSurvey.setAdapter(adapter);
        } else {
            rvFooterTakeSurvey.setVisibility(View.GONE);


        }


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgBackSurvey) {
            super.onBackPressed();
        } else if (v.getId() == R.id.textTakenTab) {

            if (!isTaken) {

                textTaken.setTextColor(Color.parseColor("#FFFFFF"));
                textTaken.setBackgroundColor(Color.parseColor("#14538e"));

                textUntaken.setTextColor(Color.parseColor("#14538e"));
                textUntaken.setBackgroundColor(Color.parseColor("#FFFFFF"));

                isTaken = true;
            }
        }
        else if (v.getId() == R.id.textUntakenTab) {

            if (isTaken) {

                textUntaken.setTextColor(Color.parseColor("#FFFFFF"));
                textUntaken.setBackgroundColor(Color.parseColor("#14538e"));

                textTaken.setTextColor(Color.parseColor("#14538e"));
                textTaken.setBackgroundColor(Color.parseColor("#FFFFFF"));

                isTaken = false;
            }
        }
    }
}