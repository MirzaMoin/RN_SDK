package com.example.rnsdk.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rnsdk.API.GetAPIData;
import com.example.rnsdk.API.RetrofitClientInstance;
import com.example.rnsdk.Activities.LoginActivities.CleanButtonsLoginActivity;
import com.example.rnsdk.Activities.LoginActivities.CleanLoginActivity;
import com.example.rnsdk.Activities.LoginActivities.VideoMotionLoginActivity;
import com.example.rnsdk.Models.ResponseModel;
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCleanButton,btnCleanLogin,btnVideoMotion,btnHome,btnProfile,btnWaystoEarn,btnRewardEntryGoal,btnRedeemCashback,btnLeaderboard,btnTransactionHistory,btnOffer,btnOfferDetail,btnTransferrPoint,btnUploadReceipt,btnReferFriends,btnContactUs,btnChangePassword,btnLocation,btnTakeSurvey;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        btnCleanButton = findViewById(R.id.btnCleanButtons);


        btnProfile = findViewById(R.id.btnProfile);
        btnWaystoEarn = findViewById(R.id.btnWaysToEan);
        btnRewardEntryGoal = findViewById(R.id.btnRewardsEntryGoal);
        btnRedeemCashback = findViewById(R.id.btnCashback);
        btnLeaderboard = findViewById(R.id.btnLeaderboard);
        btnTransactionHistory = findViewById(R.id.btnTransactionHistory);
        btnOffer = findViewById(R.id.btnOffer);
        btnOfferDetail = findViewById(R.id.btnOfferDetail);
        btnTransferrPoint = findViewById(R.id.btnTransferPoint);
        btnUploadReceipt = findViewById(R.id.btnUploadReceipt);
        btnReferFriends = findViewById(R.id.btnReferFriends);
        btnContactUs = findViewById(R.id.btnContactUs);
        btnChangePassword = findViewById(R.id.btnChangePassword);
        btnLocation = findViewById(R.id.btnLocation);
        btnTakeSurvey = findViewById(R.id.btnTakeSurvey);


        btnProfile.setOnClickListener(this);
        btnProfile.setOnClickListener(this);
        btnWaystoEarn.setOnClickListener(this);
        btnRewardEntryGoal.setOnClickListener(this);
        btnRedeemCashback.setOnClickListener(this);
        btnLeaderboard.setOnClickListener(this);
        btnTransactionHistory.setOnClickListener(this);
        btnOffer.setOnClickListener(this);
        btnOfferDetail.setOnClickListener(this);
        btnTransferrPoint.setOnClickListener(this);
        btnUploadReceipt.setOnClickListener(this);
        btnReferFriends.setOnClickListener(this);
        btnContactUs.setOnClickListener(this);
        btnChangePassword.setOnClickListener(this);
        btnLocation.setOnClickListener(this);
        btnTakeSurvey.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        /*Create handle for the RetrofitInstance interface*/
        GetAPIData service = RetrofitClientInstance.getRetrofitInstance().create(GetAPIData.class);
        Call<ResponseModel> call = service.getAllData("UW5c2c0MTT43HbVcKeu54rh8Nf77Fu");
        call.enqueue(new Callback<ResponseModel>() {

            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                progressDialog.dismiss();
                if(response.isSuccessful())
                {

                    ResponseModel responseModel = response.body();
                    Utility.response = responseModel;
                    Log.e("Error",responseModel.statusMessage);
                    btnHome.setText(Utility.response.responsedata.homeScreen.homePageHeaderMenuText);


                    startActivity(new Intent(SplashActivity.this,HomeActivity.class));
                    Toast.makeText(SplashActivity.this, ""+responseModel.statusMessage, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    btnHome.setText("Error"+response.message());

                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                progressDialog.dismiss();
                btnHome.setText(t.getLocalizedMessage());
                Log.e("Error",t.getMessage());
                Toast.makeText(SplashActivity.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });




        btnCleanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, CleanButtonsLoginActivity.class));
            }
        });

        btnCleanLogin = findViewById(R.id.btnCleanLogin);
        btnCleanLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, CleanLoginActivity.class));
            }
        });

        btnVideoMotion = findViewById(R.id.btnVideoMotion);
        btnVideoMotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, VideoMotionLoginActivity.class));
            }
        });

        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            }
        });




    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnProfile)
        {
//            startActivity(new Intent(this, ProfileActivity.class));
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            /*Create handle for the RetrofitInstance interface*/
            GetAPIData service = RetrofitClientInstance.getRetrofitInstance().create(GetAPIData.class);
            Call<ResponseModel> call = service.getAllData("UW5c2c0MTT43HbVcKeu54rh8Nf77Fu");
            call.enqueue(new Callback<ResponseModel>() {

                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    progressDialog.dismiss();
                    if(response.isSuccessful())
                    {

                        ResponseModel responseModel = response.body();
                        Utility.response = responseModel;
                        Log.e("Error",responseModel.statusMessage);
                        btnHome.setText(Utility.response.responsedata.homeScreen.homePageHeaderMenuText);


                        startActivity(new Intent(SplashActivity.this,HomeActivity.class));
                        Toast.makeText(SplashActivity.this, ""+responseModel.statusMessage, Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        btnHome.setText("Error"+response.message());

                    }
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    progressDialog.dismiss();
                    btnHome.setText(t.getLocalizedMessage());
                    Log.e("Error",t.getMessage());
                    Toast.makeText(SplashActivity.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }
            });


        }
        else if(v.getId() == R.id.btnWaysToEan)
        {
            startActivity(new Intent(this, WaysToEarnActivity.class));
        }
        else if(v.getId() == R.id.btnRewardsEntryGoal)
        {
            startActivity(new Intent(this, RewardEntryGoalActivity.class));
        }
        else if(v.getId() == R.id.btnCashback)
        {
            startActivity(new Intent(this, CashbackActivity.class));
        }
        else if(v.getId() == R.id.btnLeaderboard)
        {
            startActivity(new Intent(this, LeaderboardActivity.class));
        }
        else if(v.getId() == R.id.btnTransactionHistory)
        {
            startActivity(new Intent(this, TransactionHistoryActivity.class));
        }
        else if(v.getId() == R.id.btnOffer)
        {
            startActivity(new Intent(this, OfferActivity.class));
        }
        else if(v.getId() == R.id.btnOfferDetail)
        {
            startActivity(new Intent(this, OffersDetailActivity.class));
        }
        else if(v.getId() == R.id.btnTransferPoint)
        {
            startActivity(new Intent(this, TransferPointActivity.class));
        }
        else if(v.getId() == R.id.btnUploadReceipt)
        {
            startActivity(new Intent(this, UploadReceiptActivity.class));
        }
        else if(v.getId() == R.id.btnReferFriends)
        {
            startActivity(new Intent(this, ReferFriendActivity.class));
        }
        else if(v.getId() == R.id.btnContactUs)
        {
            startActivity(new Intent(this, ContactUsActivity.class));
        }
        else if(v.getId() == R.id.btnChangePassword)
        {
            startActivity(new Intent(this, ChangePasswordActivity.class));
        }
        else if(v.getId() == R.id.btnLocation)
        {
            startActivity(new Intent(this, LocationActivity.class));
        }
        else if(v.getId() == R.id.btnTakeSurvey)
        {
            startActivity(new Intent(this, TakeSurveyActivity.class));
        }


    }
}