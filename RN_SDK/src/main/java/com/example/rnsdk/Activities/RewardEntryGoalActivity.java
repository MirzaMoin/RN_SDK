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
import com.example.rnsdk.Adapter.RewardEntryPointAdapter;
import com.example.rnsdk.Models.AppColorModel;
import com.example.rnsdk.Models.HomeScreenModel;
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;

public class RewardEntryGoalActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rvRPG,rvFooterRPG;
    ImageView imgBack;
    LinearLayout linearCashbackRPG,linearHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward_entry_goal);

        init();

        RewardEntryPointAdapter adapter = new RewardEntryPointAdapter();
        rvRPG.setHasFixedSize(true);
        rvRPG.setLayoutManager(new LinearLayoutManager(this));
        rvRPG.setAdapter(adapter);


     /*   imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RewardEntryGoalActivity.super.onBackPressed();
            }
        });*/
    }

    private void init() {
        rvRPG = findViewById(R.id.rvRewardEntryGoal);
        rvFooterRPG = findViewById(R.id.rvFooterRPG);


        setFooter();


    }
    private void setFooter() {
        AppColorModel appColor = Utility.response.responsedata.appColor;

        HomeScreenModel homeScreenModel = Utility.response.responsedata.homeScreen;
        if(homeScreenModel.isHomePageDisplayFooter())
        {
            rvFooterRPG.setVisibility(View.VISIBLE);
            rvFooterRPG.setBackgroundColor(Utility.getColor(appColor.getFooterBarColor()));

            FooterAdapter adapter = new FooterAdapter(this,homeScreenModel.footerLinks,"rpg");
            rvFooterRPG.setHasFixedSize(true);


            rvFooterRPG.setLayoutManager(new GridLayoutManager(this,homeScreenModel.footerLinks.size()));

            rvFooterRPG.setAdapter(adapter);
        }
        else
        {
            rvFooterRPG.setVisibility(View.GONE);


        }


    }

    @Override
    public void onClick(View v) {
    }
}