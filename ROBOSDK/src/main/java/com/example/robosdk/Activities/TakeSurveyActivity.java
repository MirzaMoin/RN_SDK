package com.example.robosdk.Activities;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.robosdk.Adapter.CashbackImageSliderAdapter;
import com.example.robosdk.Adapter.FooterAdapter;
import com.example.robosdk.Adapter.TakeSurveyPagerAdapter;
import com.example.robosdk.Fragments.TakenSurveysFragment;
import com.example.robosdk.Fragments.UnTakenSurveysFragment;
import com.example.robosdk.Models.AppColorModel;
import com.example.robosdk.Models.ChildPageModel;
import com.example.robosdk.Models.ChildPageSettingModel;
import com.example.robosdk.Models.HomeScreenModel;
import com.example.robosdk.Models.ResponsedataModel;
import com.example.robosdk.Models.TakeSurveyChildPageDataModel;
import com.example.robosdk.R;
import com.example.robosdk.Utility.Utility;
import com.google.android.material.tabs.TabLayout;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TakeSurveyActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textPointSurvey;
    RecyclerView rvFooterTakeSurvey;
    ImageView imgBackSurvey;
    private ViewPager mPager;
    ResponsedataModel responseData;
    TabLayout tabLayout;
    TableLayout tableLayoutSurveys;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.myLibTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_survey);

        init();
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void init() {
        responseData = Utility.response.responsedata;
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Utility.getColor(responseData.appColor.getPhoneNotificationBar()));
        }
        if (responseData.appColor.getPhoneNotificationBarTextColor().equals("Black")) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        ChildPageSettingModel childPageSettings = responseData.childPageSetting;
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
        mPager = findViewById(R.id.viewPagerTakeSurvey);
        tableLayoutSurveys = findViewById(R.id.tableLayoutSurveys);
        tableLayoutSurveys.setBackgroundColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderBarColor()));
        textPointSurvey.setTextColor(Utility.getColor(responseData.appColor.getHeaderPointDigitColor()));
        textPointSurvey.setText(Utility.getRoundData(responseData.contactData.getPointBalance())+ " PTS");
        imgBackSurvey.setOnClickListener(this);
        Utility.setFooter(TakeSurveyActivity.this,rvFooterTakeSurvey,"takeSurvey");
        createTabs();
    }

    private void createTabs() {
        createViewPager(mPager);
        tabLayout = findViewById(R.id.tabs);
        TakeSurveyPagerAdapter pagerAdapter = new TakeSurveyPagerAdapter(getSupportFragmentManager(), this);
        mPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(mPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            assert tab != null;
            tab.setCustomView(pagerAdapter.getTabView(i));
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                assert view != null;
                TextView tv = view.findViewById(R.id.textTitleTabBar);
                tv.setBackgroundColor(Utility.getColor("#14538eff"));
                tv.setTextColor(Utility.getColor("#ffffffff"));
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                assert view != null;
                TextView tv = view.findViewById(R.id.textTitleTabBar);
                tv.setTextColor(Utility.getColor("#14538eff"));
                tv.setBackgroundColor(Utility.getColor("#ffffffff"));
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgBackSurvey) {
            super.onBackPressed();
        }
    }
    private void createViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new UnTakenSurveysFragment(), "Tab 1");
        adapter.addFrag(new TakenSurveysFragment(), "Tab 2");
        viewPager.setAdapter(adapter);
    }
    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @NotNull
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            if(mFragmentList != null && mFragmentList.size() > 0)
            {
                return mFragmentList.size();
            }
            else
            {
                return 0;
            }
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