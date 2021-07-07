package com.example.robosdk.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.robosdk.Adapter.CashbackImageSliderAdapter;
import com.example.robosdk.Adapter.FooterAdapter;
import com.example.robosdk.Models.AppColorModel;
import com.example.robosdk.Models.ChildPageModel;
import com.example.robosdk.Models.ChildPageSettingModel;
import com.example.robosdk.Models.HomeScreenModel;
import com.example.robosdk.Models.ReferFriendChildPageDataModel;
import com.example.robosdk.R;
import com.example.robosdk.Utility.Utility;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class ReferFriendActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    ImageView imgBackReferFriend;

    TextView textPointReferFriend;
    Button btnGetInviteLink;

    RecyclerView rvFooterReferFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_friend);

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
        toolbar = findViewById(R.id.toolbarReferFriend);
        rvFooterReferFriend = findViewById(R.id.rvFooterReferFriend);
        imgBackReferFriend = findViewById(R.id.imgBackReferFriend);
        btnGetInviteLink = findViewById(R.id.btnGetInviteLink);
        textPointReferFriend = findViewById(R.id.textPointReferFriend);

        textPointReferFriend.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderPointDigitColor()));
        textPointReferFriend.setText(String.valueOf(Utility.response.responsedata.contactData.getPointBalance())+ " PTS");

        imgBackReferFriend.setOnClickListener(this);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle("");
            setSupportActionBar(toolbar);

        }

        ChildPageSettingModel childPageSettings = Utility.response.responsedata.childPageSetting;

        if(childPageSettings.isChildPageReferFriend()) {
            List<ChildPageModel> childPage = new ArrayList<>();
            for (ReferFriendChildPageDataModel refer : childPageSettings.referFriendChildPageData) {
                childPage.add(new ChildPageModel(refer.image, refer.opacity, refer.isClickable, refer.linkType, refer.internalLink, refer.externalLink));
            }


            SliderView sliderView = findViewById(R.id.imageSliderReferFriend);
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

        AppColorModel color = Utility.response.responsedata.appColor;
        btnGetInviteLink.setBackgroundColor(Utility.getColor(color.getPrimaryButtonColor()));
        setFooter();
    }
    private void setFooter() {
        AppColorModel appColor = Utility.response.responsedata.appColor;

        HomeScreenModel homeScreenModel = Utility.response.responsedata.homeScreen;
        if(homeScreenModel.isHomePageDisplayFooter())
        {
            rvFooterReferFriend.setVisibility(View.VISIBLE);
            rvFooterReferFriend.setBackgroundColor(Utility.getColor(appColor.getFooterBarColor()));

            FooterAdapter adapter = new FooterAdapter(this,homeScreenModel.footerLinks,"profileScreen");
            rvFooterReferFriend.setHasFixedSize(true);


            rvFooterReferFriend.setLayoutManager(new GridLayoutManager(this,homeScreenModel.footerLinks.size()));

            rvFooterReferFriend.setAdapter(adapter);
        }
        else
        {
            rvFooterReferFriend.setVisibility(View.GONE);


        }


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.imgBackReferFriend){
            super.onBackPressed();
        }
    }
}