package com.example.rnsdk.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rnsdk.Adapter.FooterAdapter;
import com.example.rnsdk.Adapter.HomeMenuLinkListAdapter;
import com.example.rnsdk.Adapter.OffersAdapter;
import com.example.rnsdk.Models.AppColorModel;
import com.example.rnsdk.Models.HomeScreenModel;
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;

public class OfferActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView rvOffer,rvFooterOffers;
    LinearLayout linearHome,linearRPG,linearCashback;
    ImageView ivBack;
    TextView textPointOffers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R
                .layout.activity_offer);

        init();

        OffersAdapter adapter = new OffersAdapter(this);
        rvOffer.setHasFixedSize(true);
        rvOffer.setLayoutManager(new LinearLayoutManager(this));
        rvOffer.setAdapter(adapter);

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
        rvOffer =  findViewById(R.id.rvOffers);
        textPointOffers =  findViewById(R.id.textPointOffers);

        ivBack = findViewById(R.id.imgBackOffers);
        rvFooterOffers = findViewById(R.id.rvFooterOffers);


        ivBack.setOnClickListener(this);
        textPointOffers.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderPointDigitColor()));


        setFooter();

    }
    private void setFooter() {
        AppColorModel appColor = Utility.response.responsedata.appColor;

        HomeScreenModel homeScreenModel = Utility.response.responsedata.homeScreen;
        if(homeScreenModel.isHomePageDisplayFooter())
        {
            rvFooterOffers.setVisibility(View.VISIBLE);
            rvFooterOffers.setBackgroundColor(Utility.getColor(appColor.getFooterBarColor()));

            FooterAdapter adapter = new FooterAdapter(this,homeScreenModel.footerLinks,"offer");
            rvFooterOffers.setHasFixedSize(true);


            rvFooterOffers.setLayoutManager(new GridLayoutManager(this,homeScreenModel.footerLinks.size()));

            rvFooterOffers.setAdapter(adapter);
        }
        else
        {
            rvFooterOffers.setVisibility(View.GONE);


        }


    }

    @Override
    public void onClick(View v) {
     if(v.getId() == R.id.imgBackOffers)
        {
            super.onBackPressed();
        }

    }
}