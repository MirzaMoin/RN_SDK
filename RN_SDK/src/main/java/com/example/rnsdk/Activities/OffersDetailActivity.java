package com.example.rnsdk.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.rnsdk.R;

public class OffersDetailActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imgback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers_detail);

        init();
    }

    private void init() {

        imgback = findViewById(R.id.imgBackOfferDetail);

        imgback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.imgBackOfferDetail)
        {
            super.onBackPressed();
        }
    }
}