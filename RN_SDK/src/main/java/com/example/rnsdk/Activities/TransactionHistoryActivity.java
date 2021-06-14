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

import com.example.rnsdk.Adapter.CashbackImageSliderAdapter;
import com.example.rnsdk.Adapter.FooterAdapter;
import com.example.rnsdk.Adapter.SliderItem;
import com.example.rnsdk.Adapter.TransactionHistoryAdapter;
import com.example.rnsdk.Constants;
import com.example.rnsdk.Models.AppColorModel;
import com.example.rnsdk.Models.ChildPageModel;
import com.example.rnsdk.Models.ChildPageSettingModel;
import com.example.rnsdk.Models.HomeScreenModel;
import com.example.rnsdk.Models.TransactionHistoryChildPageDataModel;
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistoryActivity extends AppCompatActivity implements View.OnClickListener {

    List<SliderItem> mSliderItems = new ArrayList<>();
    RecyclerView rvTransactionHistory,rvFooterTransactionHistory;

    TextView textPointTransactionHistory;
    ImageView imgBackTransactionHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);


        init();

        TransactionHistoryAdapter adapter = new TransactionHistoryAdapter();
        rvTransactionHistory.setHasFixedSize(true);
        rvTransactionHistory.setLayoutManager(new LinearLayoutManager(this));
        rvTransactionHistory.setAdapter(adapter);
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

        textPointTransactionHistory = findViewById(R.id.textPointTransactionHistory);
        textPointTransactionHistory.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderPointDigitColor()));

        ChildPageSettingModel childPageSettings = Utility.response.responsedata.childPageSetting;

        if(childPageSettings.isChildPageTransactionHistory()) {
            List<ChildPageModel> childPage = new ArrayList<>();
            for (TransactionHistoryChildPageDataModel transaction : childPageSettings.transactionHistoryChildPageData) {
                childPage.add(new ChildPageModel(transaction.image, transaction.opacity, transaction.isClickable, transaction.linkType, transaction.internalLink, transaction.externalLink));
            }


            SliderView sliderView = findViewById(R.id.imageSliderTransactionHistory);
            rvTransactionHistory = findViewById(R.id.rvTransactionHistory);
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

        rvFooterTransactionHistory = findViewById(R.id.rvFooterTransactionHistory);
        imgBackTransactionHistory = findViewById(R.id.imgBackTransactionHistory);

        imgBackTransactionHistory.setOnClickListener(this);
        setFooter();
    }
    private void setFooter() {
        AppColorModel appColor = Utility.response.responsedata.appColor;

        HomeScreenModel homeScreenModel = Utility.response.responsedata.homeScreen;
        if(homeScreenModel.isHomePageDisplayFooter())
        {
            rvFooterTransactionHistory.setVisibility(View.VISIBLE);
            rvFooterTransactionHistory.setBackgroundColor(Utility.getColor(appColor.getFooterBarColor()));

            FooterAdapter adapter = new FooterAdapter(this,homeScreenModel.footerLinks,"transactionHistory");
            rvFooterTransactionHistory.setHasFixedSize(true);


            rvFooterTransactionHistory.setLayoutManager(new GridLayoutManager(this,homeScreenModel.footerLinks.size()));

            rvFooterTransactionHistory.setAdapter(adapter);
        }
        else
        {
            rvFooterTransactionHistory.setVisibility(View.GONE);


        }


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.imgBackTransactionHistory){
            super.onBackPressed();
        }
    }
}