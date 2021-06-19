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
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    Button btnCleanButton, btnCleanLogin, btnVideoMotion, btnHome, btnProfile, btnWaystoEarn, btnRewardEntryGoal, btnRedeemCashback, btnLeaderboard, btnTransactionHistory, btnOffer, btnOfferDetail, btnTransferrPoint, btnUploadReceipt, btnReferFriends, btnContactUs, btnChangePassword, btnLocation, btnTakeSurvey;

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
                Log.e("Test", "" + response.body().statusMessage);
                if (response.isSuccessful()) {


                    ResponseModel responseModel = response.body();
                    Utility.response = responseModel;
//                    btnHome.setText(Utility.response.responsedata.homeScreen.homePageHeaderMenuText);


                    Call<ResponseModel> callLogin = service.Login(ApiJsonMap("UW5c2c0MTT43HbVcKeu54rh8Nf77Fu",
                            "8000333022",
                            "123456789"));


                    callLogin.enqueue(new Callback<ResponseModel>() {
                        @Override
                        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                            if (response.isSuccessful()) {

                                Utility.response.responsedata.contactData = response.body().responsedata.contactData;
                                Utility.response.responsedata.webFormData = response.body().responsedata.webFormData;

                                Log.e("Test:","Response : "+response.body().responsedata.contactData.emailAddress);

                                Toast.makeText(SplashActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SplashActivity.this, HomeActivity.class));


                            } else {
                                Log.e("TEST", "Error: " + response.message());
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseModel> call, Throwable test) {

                            Log.e("Test:::", test.getMessage().toString());
                        }
                    });


                } else {
                    btnHome.setText("Error" + response.message());

                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                progressDialog.dismiss();
                btnHome.setText(t.getLocalizedMessage());
                Toast.makeText(SplashActivity.this, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

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

    private JsonObject ApiJsonMap(String RPToken, String userName, String password) {

        JsonObject gsonObject = new JsonObject();
        try {
            JSONObject jsonObj_ = new JSONObject();
            jsonObj_.put("rewardProgramToken", RPToken);
            jsonObj_.put("userName", userName);
            jsonObj_.put("password", password);


            JsonParser jsonParser = new JsonParser();
            gsonObject = (JsonObject) jsonParser.parse(jsonObj_.toString());

            //print parameter
            Log.e("Request Body:  ", "" + gsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return gsonObject;
    }
}