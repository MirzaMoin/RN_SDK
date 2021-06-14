package com.example.rnsdk.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
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

public class TakeSurveyActivity extends AppCompatActivity {

    TextView textTaken,textUntaken;
    Boolean isTaken = true;
    List<SliderItem> mSliderItems = new ArrayList<>();
    RecyclerView rvFooterTakeSurvey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_survey);

        init();
    }

    private void init() {
        ChildPageSettingModel childPageSettings = Utility.response.responsedata.childPageSetting;

        if(childPageSettings.isChildPageTakeSurvey()) {
            List<ChildPageModel> childPage = new ArrayList<>();
            for (TakeSurveyChildPageDataModel survey : childPageSettings.takeSurveyChildPageData) {
                childPage.add(new ChildPageModel(survey.image, survey.opacity, survey.isClickable, survey.linkType, survey.internalLink, survey.externalLink));
            }

            SliderView sliderView = findViewById(R.id.imageSliderSurvey);

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

        textTaken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isTaken)
                {

                    textTaken.setTextColor(Color.parseColor("#FFFFFF"));
                    textTaken.setBackgroundColor(Color.parseColor("#14538e"));

                    textUntaken.setTextColor(Color.parseColor("#14538e"));
                    textUntaken.setBackgroundColor(Color.parseColor("#FFFFFF"));

                    isTaken = true;
                }
            }
        });

        textUntaken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isTaken)
                {

                    textUntaken.setTextColor(Color.parseColor("#FFFFFF"));
                    textUntaken.setBackgroundColor(Color.parseColor("#14538e"));

                    textTaken.setTextColor(Color.parseColor("#14538e"));
                    textTaken.setBackgroundColor(Color.parseColor("#FFFFFF"));

                    isTaken = false;
                }
            }
        });
        setFooter();
    }
    private void setFooter() {
        AppColorModel appColor = Utility.response.responsedata.appColor;

        HomeScreenModel homeScreenModel = Utility.response.responsedata.homeScreen;
        if(homeScreenModel.isHomePageDisplayFooter())
        {
            rvFooterTakeSurvey.setVisibility(View.VISIBLE);
            rvFooterTakeSurvey.setBackgroundColor(Utility.getColor(appColor.getFooterBarColor()));

            FooterAdapter adapter = new FooterAdapter(this,homeScreenModel.footerLinks,"takeSurvey");
            rvFooterTakeSurvey.setHasFixedSize(true);


            rvFooterTakeSurvey.setLayoutManager(new GridLayoutManager(this,homeScreenModel.footerLinks.size()));

            rvFooterTakeSurvey.setAdapter(adapter);
        }
        else
        {
            rvFooterTakeSurvey.setVisibility(View.GONE);


        }


    }
}