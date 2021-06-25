package com.example.rnsdk.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.ebanx.swipebtn.SwipeButton;
import com.example.rnsdk.Adapter.CashbackImageSliderAdapter;
import com.example.rnsdk.Models.AppColorModel;
import com.example.rnsdk.Models.ChangePasswordChildPageDataModel;
import com.example.rnsdk.Models.ChildPageModel;
import com.example.rnsdk.Models.ChildPageSettingModel;
import com.example.rnsdk.Models.RPGChildPageDataModel;
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imgBackChangePassword;
    SwipeButton swipe_btn;
    TextInputLayout textInputLayout;
    TextInputEditText etCurrentPasswordCP,
            etNewPasswordCP,
            etConfirmPasswordCP;

    SwipeButton swipeBtnChangePassword;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        init();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
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

        imgBackChangePassword = findViewById(R.id.imgBackChangePassword);
        swipe_btn = findViewById(R.id.swipe_btn);

        etCurrentPasswordCP = findViewById(R.id.etCurrentPasswordCP);
        etNewPasswordCP = findViewById(R.id.etNewPasswordCP);
        etConfirmPasswordCP = findViewById(R.id.etConfirmPasswordCP);

        AppColorModel color = Utility.response.responsedata.appColor;
        imgBackChangePassword.setOnClickListener(this);
        swipe_btn.setBackgroundColor(Utility.getColor(color.getPrimaryButtonColor()));

        SliderView sliderView = findViewById(R.id.imageSliderChangePassword);
        ChildPageSettingModel childPageSettings = Utility.response.responsedata.childPageSetting;
        if (childPageSettings.isChildPageChangePassword()) {
            sliderView.setVisibility(View.VISIBLE);

            List<ChildPageModel> childPage = new ArrayList<>();
            for (ChangePasswordChildPageDataModel change : childPageSettings.changePasswordChildPageData) {
                childPage.add(new ChildPageModel(change.image, change.opacity, change.isClickable, change.linkType, change.internalLink, change.externalLink));
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


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgBackChangePassword) {
            super.onBackPressed();
        }

    }
}