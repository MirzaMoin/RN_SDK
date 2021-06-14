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

import com.ebanx.swipebtn.SwipeButton;
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

public class TransferPointActivity extends AppCompatActivity implements View.OnClickListener {

    List<SliderItem> mSliderItems = new ArrayList<>();
    ImageView imgBackTransferPoints;
    RecyclerView rvFooterTransferPoint;
    SwipeButton swipeBtnTransferPoint;
    TextView textPointTransferPoints;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_point);

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
        rvFooterTransferPoint = findViewById(R.id.rvFooterTransferPoint);
        imgBackTransferPoints = findViewById(R.id.imgBackTransferPoints);
        swipeBtnTransferPoint = findViewById(R.id.swipeBtnTransferPoint);
        textPointTransferPoints = findViewById(R.id.textPointTransferPoints);
        textPointTransferPoints.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderPointDigitColor()));

        AppColorModel color = Utility.response.responsedata.appColor;
        swipeBtnTransferPoint.setBackgroundColor(Utility.getColor(color.getPrimaryButtonColor()));

        ChildPageSettingModel childPageSettings = Utility.response.responsedata.childPageSetting;

        if(childPageSettings.isChildPageTransaferPoint()) {
            List<ChildPageModel> childPage = new ArrayList<>();
            for (TransaferPointChildPageDataModel point : childPageSettings.transaferPointChildPageData) {
                childPage.add(new ChildPageModel(point.image, point.opacity, point.isClickable, point.linkType, point.internalLink, point.externalLink));
            }

            SliderView sliderView = findViewById(R.id.imageSliderTransferPoints);
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
        imgBackTransferPoints.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.imgBackTransferPoints){
            super.onBackPressed();
        }
    }
}