package com.example.rnsdk.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
        rvOffer = (RecyclerView) findViewById(R.id.rvOffers);

        ivBack = findViewById(R.id.imgBackOffers);
        rvFooterOffers = findViewById(R.id.rvFooterOffers);


//        ivBack.setOnClickListener(this);


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