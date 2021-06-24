package com.example.rnsdk.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.rnsdk.API.GetAPIData;
import com.example.rnsdk.API.RetrofitClientInstance;
import com.example.rnsdk.Adapter.CashbackImageSliderAdapter;
import com.example.rnsdk.Adapter.FooterAdapter;
import com.example.rnsdk.Adapter.ScreenSlidePagerAdapter;
import com.example.rnsdk.Adapter.SliderItem;
import com.example.rnsdk.Adapter.TakeSurveyPagerAdapter;
import com.example.rnsdk.Adapter.TransactionHistoryAdapter;
import com.example.rnsdk.Fragments.TakenSurveysFragment;
import com.example.rnsdk.Fragments.UnTakenSurveysFragment;
import com.example.rnsdk.Models.AppColorModel;
import com.example.rnsdk.Models.ChildPageModel;
import com.example.rnsdk.Models.ChildPageSettingModel;
import com.example.rnsdk.Models.HomeScreenModel;
import com.example.rnsdk.Models.ResponseModel;
import com.example.rnsdk.Models.ResponseModelTransactionHistory;
import com.example.rnsdk.Models.TakeSurveyChildPageDataModel;
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;

import com.google.android.material.tabs.TabLayout;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TakeSurveyActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textPointSurvey;
    Boolean isTaken = true;
    List<SliderItem> mSliderItems = new ArrayList<>();
    RecyclerView rvFooterTakeSurvey;
    ImageView imgBackSurvey;
    ProgressDialog progressDialog;
    private ViewPager mPager;
    private TakeSurveyPagerAdapter pagerAdapter;

    TabLayout tabLayout;


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
        if (Utility.response.responsedata.appColor.getPhoneNotificationBarTextColor().equals("Black")) {
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

        imgBackSurvey = findViewById(R.id.imgBackSurvey);
        textPointSurvey = findViewById(R.id.textPointSurvey);
        mPager = (ViewPager) findViewById(R.id.viewPagerTakeSurvey);

        textPointSurvey.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderPointDigitColor()));
        textPointSurvey.setText(String.valueOf(Utility.response.responsedata.contactData.getPointBalance())+ " PTS");


        imgBackSurvey.setOnClickListener(this);
        setFooter();
        createTabs();
//        getData();

    }

    private void createTabs() {
        createViewPager(mPager);

        tabLayout = findViewById(R.id.tabs);
        pagerAdapter = new TakeSurveyPagerAdapter(getSupportFragmentManager(), this);
        mPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(mPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(pagerAdapter.getTabView(i));
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                TextView tv = view.findViewById(R.id.textTitleTabBar);
                if (view != null) {

                    tv.setBackgroundColor(Utility.getColor("#14538eff"));
                    tv.setTextColor(Utility.getColor("#ffffffff"));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                TextView tv = view.findViewById(R.id.textTitleTabBar);

                if (view != null) {
                    tv.setTextColor(Utility.getColor("#14538eff"));
                    tv.setBackgroundColor(Utility.getColor("#ffffffff"));
                }

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

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
        }

    }

   /* private void getData() {


        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        GetAPIData service = RetrofitClientInstance.getRetrofitInstance().create(GetAPIData.class);
        Log.e("Request", "RP ID: " + Utility.response.responsedata.appDetails.rewardProgramId +
                ", Contact ID: " + Utility.response.responsedata.contactData.contactID);

        Call<ResponseModel> call = service.getSurveyList(Utility.response.responsedata.appDetails.rewardProgramId
                , Utility.response.responsedata.contactData.contactID);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                progressDialog.dismiss();

                if (response.isSuccessful()) {

                    Utility.response.responsedata.unTaken = response.body().responsedata.unTaken;
                    Utility.response.responsedata.completed = response.body().responsedata.completed;

                    createTabs();

                } else {
                    Log.e("TEST", "Error Sub: " + response.message());
                    createTabs();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable test) {
                progressDialog.dismiss();

                test.printStackTrace();
                Log.e("Test", "Error Main: " + test.toString());
                createTabs();
            }
        });

    }*/

    private void createViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new UnTakenSurveysFragment(), "Tab 1");
        adapter.addFrag(new TakenSurveysFragment(), "Tab 2");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }
}