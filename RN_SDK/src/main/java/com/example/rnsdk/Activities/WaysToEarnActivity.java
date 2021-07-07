package com.example.rnsdk.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rnsdk.API.GetAPIData;
import com.example.rnsdk.API.RetrofitClientInstance;
import com.example.rnsdk.Adapter.CashbackImageSliderAdapter;
import com.example.rnsdk.Adapter.FooterAdapter;
import com.example.rnsdk.Adapter.HomeMenuLinkListAdapter;
import com.example.rnsdk.Adapter.TransactionHistoryAdapter;
import com.example.rnsdk.Adapter.WaysToEarnAdapter;
import com.example.rnsdk.Models.AppColorModel;
import com.example.rnsdk.Models.ChildPageModel;
import com.example.rnsdk.Models.ChildPageSettingModel;
import com.example.rnsdk.Models.HomeScreenModel;
import com.example.rnsdk.Models.ProfileEditChildPageDataModel;
import com.example.rnsdk.Models.ResponseModel;
import com.example.rnsdk.Models.ResponseModelTransactionHistory;
import com.example.rnsdk.Models.ResponsedataModel;
import com.example.rnsdk.Models.WaysToEarnChildPageDataModel;
import com.example.rnsdk.Models.WaysToEarnModel;
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WaysToEarnActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rvList, rvFooterUploadWaysToEarn;
    ImageView imgBackWaysToEarn,
            imageWTE,
            imageLogoWTE;
    TextView textPointWaysToEarn;

    RelativeLayout relLoadingWTE;

    TableLayout tableLayoutWTE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ways_to_earn);

        init();
        getData();

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
        rvList = findViewById(R.id.rvWaysToEarn);
        tableLayoutWTE = findViewById(R.id.tableLayoutWTE);
        tableLayoutWTE.setBackgroundColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderBarColor()));

        textPointWaysToEarn = findViewById(R.id.textPointWaysToEarn);
        rvFooterUploadWaysToEarn = findViewById(R.id.rvFooterUploadWaysToEarn);
        imgBackWaysToEarn = findViewById(R.id.imgBackWaysToEarn);
        relLoadingWTE = findViewById(R.id.relLoadingWTE);
        imageWTE = findViewById(R.id.imageWTE);
        imageLogoWTE = findViewById(R.id.imageLogoWTE);
        textPointWaysToEarn.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderPointDigitColor()));
        textPointWaysToEarn.setText(Utility.getRoundData(Utility.response.responsedata.contactData.getPointBalance())+ " PTS");


        imgBackWaysToEarn.setOnClickListener(this);



        setFooter();
    }

    private void setFooter() {
        AppColorModel appColor = Utility.response.responsedata.appColor;

        HomeScreenModel homeScreenModel = Utility.response.responsedata.homeScreen;
        if (homeScreenModel.isHomePageDisplayFooter()) {
            rvFooterUploadWaysToEarn.setVisibility(View.VISIBLE);
            rvFooterUploadWaysToEarn.setBackgroundColor(Utility.getColor(appColor.getFooterBarColor()));

            FooterAdapter adapter = new FooterAdapter(this, homeScreenModel.footerLinks, "waysToEarn");
            rvFooterUploadWaysToEarn.setHasFixedSize(true);


            rvFooterUploadWaysToEarn.setLayoutManager(new GridLayoutManager(this, homeScreenModel.footerLinks.size()));

            rvFooterUploadWaysToEarn.setAdapter(adapter);
        } else {
            rvFooterUploadWaysToEarn.setVisibility(View.GONE);


        }


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgBackWaysToEarn) {
            super.onBackPressed();
        }
    }

    private void getData() {

        relLoadingWTE.setVisibility(View.VISIBLE);
        Glide.with(this).load(Utility.response.responsedata.appIntakeImages.loadingImages.get(0).imageUrl).into(imageWTE);
        Glide.with(this).load(Utility.response.responsedata.appIntakeImages.companyLogo).into(imageLogoWTE);

        ResponsedataModel responseData = Utility.response.responsedata;

        GetAPIData service = RetrofitClientInstance.getRetrofitInstance().create(GetAPIData.class);
        Log.e("Request", "RP Token: " + Utility.RPToken +
                ", WebFormID: " + responseData.appDetails.webFormID+
                ", Contact ID: " + responseData.contactData.contactID);

        Call<ResponseModel> call = service.getWaysToEarn(Utility.RPToken,
                responseData.appDetails.webFormID,
                responseData.contactData.contactID);
        call.enqueue(new Callback<ResponseModel>() {

            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                relLoadingWTE.setVisibility(View.GONE);

                if (response.isSuccessful()) {

                    if(response.body() != null)
                    {
                        Utility.response.responsedata.totalPoints = response.body().responsedata.getTotalPoints();
                        Utility.response.responsedata.purchasePoints = response.body().responsedata.getPurchasePoints();
                        Utility.response.responsedata.socialShare = response.body().responsedata.getSocialShare();
                        Utility.response.responsedata.referFriends = response.body().responsedata.getReferFriends();
                        Utility.response.responsedata.leaderboard = response.body().responsedata.getLeaderboard();
                        Utility.response.responsedata.surveys = response.body().responsedata.getSurveys();
                        Utility.response.responsedata.completeProfile = response.body().responsedata.getCompleteProfile();

                        ResponsedataModel data =  Utility.response.responsedata;

                        List<WaysToEarnModel> waysList = new ArrayList<>();
                        waysList.add(data.getTotalPoints());
                        waysList.add(data.getPurchasePoints());
                        waysList.add(data.getSocialShare());
                        waysList.add(data.getReferFriends());
                        waysList.add(data.getLeaderboard());
                        waysList.add(data.getSurveys());
                        waysList.add(data.getCompleteProfile());


                        WaysToEarnAdapter adapter = new WaysToEarnAdapter(WaysToEarnActivity.this,waysList);
                        rvList.setHasFixedSize(true);
                        rvList.setLayoutManager(new LinearLayoutManager(WaysToEarnActivity.this));
                        rvList.setAdapter(adapter);



                    }
                    else
                    {
                        Utility.showAlertDialog(WaysToEarnActivity.this,"Oops...","Something went wrong");
                    }





                } else {
                    Utility.showAlertDialog(WaysToEarnActivity.this,"Oops...","Something went wrong");

                    Log.e("Test Error: ", "" + response.message());


                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                relLoadingWTE.setVisibility(View.GONE);
                Utility.showAlertDialog(WaysToEarnActivity.this,"Oops...","Something went wrong");

                Log.e("Test Error: ", "" + t.getMessage());


            }
        });
    }
}