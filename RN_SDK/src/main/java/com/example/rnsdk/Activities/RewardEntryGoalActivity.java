package com.example.rnsdk.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rnsdk.API.GetAPIData;
import com.example.rnsdk.API.RetrofitClientInstance;
import com.example.rnsdk.Adapter.CashbackImageSliderAdapter;
import com.example.rnsdk.Adapter.FooterAdapter;
import com.example.rnsdk.Adapter.HomeMenuLinkListAdapter;
import com.example.rnsdk.Adapter.RewardEntryPointAdapter;
import com.example.rnsdk.Models.AppColorModel;
import com.example.rnsdk.Models.ChildPageModel;
import com.example.rnsdk.Models.ChildPageSettingModel;
import com.example.rnsdk.Models.HomeScreenModel;
import com.example.rnsdk.Models.RPGChildPageDataModel;
import com.example.rnsdk.Models.ResponseModel;
import com.example.rnsdk.Models.WaysToEarnChildPageDataModel;
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RewardEntryGoalActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rvRPG, rvFooterRPG;
    ImageView imgBack;
    LinearLayout linearCashbackRPG, linearHome;
    TextView textPointRPG;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward_entry_goal);

        init();

        getData();


    }

    private void init() {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Utility.getColor(Utility.response.responsedata.appColor.getPhoneNotificationBar()));
        }
        if (Utility.response.responsedata.appColor.getPhoneNotificationBarTextColor().equals("Black")) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        rvRPG = findViewById(R.id.rvRewardEntryGoal);
        rvFooterRPG = findViewById(R.id.rvFooterRPG);
        imgBack = findViewById(R.id.imgBackRPG);
        textPointRPG = findViewById(R.id.textPointRPG);
        textPointRPG.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderPointDigitColor()));
        textPointRPG.setText(Utility.getRoundData(Utility.response.responsedata.contactData.getPointBalance()) + " PTS");

        imgBack.setOnClickListener(this);

        SliderView sliderView = findViewById(R.id.imageSliderRPG);
        ChildPageSettingModel childPageSettings = Utility.response.responsedata.childPageSetting;
        if (childPageSettings.isChildPageRpg()) {
            sliderView.setVisibility(View.VISIBLE);

            List<ChildPageModel> childPage = new ArrayList<>();
            for (RPGChildPageDataModel rpg : childPageSettings.rpgChildPageData) {
                childPage.add(new ChildPageModel(rpg.image, rpg.opacity, rpg.isClickable, rpg.linkType, rpg.internalLink, rpg.externalLink));
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
            rvFooterRPG.setVisibility(View.VISIBLE);
            rvFooterRPG.setBackgroundColor(Utility.getColor(appColor.getFooterBarColor()));

            FooterAdapter adapter = new FooterAdapter(this, homeScreenModel.footerLinks, "rpg");
            rvFooterRPG.setHasFixedSize(true);


            rvFooterRPG.setLayoutManager(new GridLayoutManager(this, homeScreenModel.footerLinks.size()));

            rvFooterRPG.setAdapter(adapter);
        } else {
            rvFooterRPG.setVisibility(View.GONE);


        }


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgBackRPG) {
            super.onBackPressed();
        }
    }

    private void getData() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        GetAPIData service = RetrofitClientInstance.getRetrofitInstance().create(GetAPIData.class);
        Log.e("Request", "RP Token: " + Utility.RPToken +
                ", Contact ID: " + Utility.response.responsedata.contactData.contactID);

        Call<ResponseModel> call = service.getRPGList(Utility.RPToken
                , Utility.response.responsedata.contactData.contactID);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                progressDialog.dismiss();

                if (response.isSuccessful()) {

                    Utility.response.responsedata.lstRPG = response.body().responsedata.lstRPG;


                    Log.e("TEST", "onResponse: " + Utility.response.responsedata.lstRPG.size());

                    RewardEntryPointAdapter adapter = new RewardEntryPointAdapter(RewardEntryGoalActivity.this, Utility.response.responsedata.lstRPG);
                    rvRPG.setHasFixedSize(true);
                    rvRPG.setLayoutManager(new LinearLayoutManager(RewardEntryGoalActivity.this));
                    rvRPG.setAdapter(adapter);


                } else {
                    Log.e("TEST", "Error Sub: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable test) {
                progressDialog.dismiss();

                test.printStackTrace();
                Log.e("Test", "Error Main: " + test.toString());
            }
        });

    }
}