package com.example.rnsdk.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.rnsdk.Adapter.DialogListAdapter;
import com.example.rnsdk.Adapter.FooterAdapter;
import com.example.rnsdk.Adapter.HomeMenuLinkListAdapter;
import com.example.rnsdk.Adapter.LeaderboardAdapter;
import com.example.rnsdk.Models.AppColorModel;
import com.example.rnsdk.Models.HomeScreenModel;
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class LeaderboardActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imgFilter,imgBackLeaderboard;
    RecyclerView rvLeader,rvFooterLeaderboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        init();

        LeaderboardAdapter adapter = new LeaderboardAdapter(this);
        rvLeader.setHasFixedSize(true);
        rvLeader.setLayoutManager(new LinearLayoutManager(this));
        rvLeader.setAdapter(adapter);
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
        imgFilter = findViewById(R.id.imgCompareLeader);
        rvLeader = findViewById(R.id.rvLeaderboard);
        rvFooterLeaderboard = findViewById(R.id.rvFooterLeaderboard);
        imgBackLeaderboard = findViewById(R.id.imgBackLeaderboard);

        imgFilter.setOnClickListener(this);
        imgBackLeaderboard.setOnClickListener(this);
        setFooter();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.imgCompareLeader)
        {
            showFilterDialog();
        }
        else if(v.getId() == R.id.imgBackLeaderboard){
            super.onBackPressed();
        }
    }
    private void setFooter() {
        AppColorModel appColor = Utility.response.responsedata.appColor;

        HomeScreenModel homeScreenModel = Utility.response.responsedata.homeScreen;
        if(homeScreenModel.isHomePageDisplayFooter())
        {
            rvFooterLeaderboard.setVisibility(View.VISIBLE);
            rvFooterLeaderboard.setBackgroundColor(Utility.getColor(appColor.getFooterBarColor()));

            FooterAdapter adapter = new FooterAdapter(this,homeScreenModel.footerLinks,"leaderboard");
            rvFooterLeaderboard.setHasFixedSize(true);


            rvFooterLeaderboard.setLayoutManager(new GridLayoutManager(this,homeScreenModel.footerLinks.size()));

            rvFooterLeaderboard.setAdapter(adapter);
        }
        else
        {
            rvFooterLeaderboard.setVisibility(View.GONE);


        }


    }
    void showMonthDialog(){
        // create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.content_month_dialog, null);
        builder.setView(customLayout);
        // add a button
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
        RecyclerView rvList = dialog.findViewById(R.id.rvMonthList);
        DialogListAdapter adapter = new DialogListAdapter();
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(adapter);
     }
    private void showFilterDialog() {

        // create an alert builder
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.content_bottomsheet_leaderboard, null);
        dialog.setContentView(customLayout);
        // add a button
        // create and show the alert dialog
        dialog.show();
          RelativeLayout relMonth = dialog.findViewById(R.id.relMonth);
          relMonth.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  showMonthDialog();
              }
          });
    }
}