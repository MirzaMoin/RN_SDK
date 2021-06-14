package com.example.rnsdk.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;

public class OffersDetailActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imgback;
    TextView textPointOfferDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers_detail);

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
        imgback = findViewById(R.id.imgBackOfferDetail);
        textPointOfferDetail = findViewById(R.id.textPointOfferDetail);

        imgback.setOnClickListener(this);
        textPointOfferDetail.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderPointDigitColor()));


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.imgBackOfferDetail)
        {
            super.onBackPressed();
        }
    }
}