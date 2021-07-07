package com.example.robosdk.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.robosdk.API.GetAPIData;
import com.example.robosdk.API.RetrofitClientInstance;
import com.example.robosdk.Models.ResponseModel;
import com.example.robosdk.R;
import com.example.robosdk.Utility.Utility;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {


    ImageView imageSplash,
            imageLogoSplash;
    int errorCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        imageSplash = findViewById(R.id.imageSplash);
        imageLogoSplash = findViewById(R.id.imageLogoSplash);

        callAPI();





    }

    private void callAPI() {
        /*Create handle for the RetrofitInstance interface*/
        final GetAPIData service = RetrofitClientInstance.getRetrofitInstance().create(GetAPIData.class);
        Call<ResponseModel> call = service.getAllData("UW5c2c0MTT43HbVcKeu54rh8Nf77Fu");
        call.enqueue(new Callback<ResponseModel>() {

            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {


                if (response.isSuccessful()) {
                    if(response.body() != null)
                    {
                        Log.e("Response - getAllData", "" + response.body().statusMessage);


                        ResponseModel responseModel = response.body();
                        Utility.response = responseModel;

                        Glide.with(SplashActivity.this).load(Utility.response.responsedata.appIntakeImages.loadingImages.get(0).imageUrl).into(imageSplash);
                        Glide.with(SplashActivity.this).load(Utility.response.responsedata.appIntakeImages.companyLogo).into(imageLogoSplash);


                        Call<ResponseModel> callLogin = service.Login(ApiJsonMap("UW5c2c0MTT43HbVcKeu54rh8Nf77Fu",
                                "8000333022",
                                "987654321"));


                        callLogin.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                if (response.isSuccessful()) {
                                    if (response.body().getStatusCode() == 1) {
                                        Utility.response.responsedata.contactData = response.body().responsedata.contactData;
                                        Utility.response.responsedata.webFormData = response.body().responsedata.webFormData;

                                        Log.e("Response - Login", "Response : " + response.body().responsedata.contactData.emailAddress);


                                        Call<ResponseModel> callGetContactData = service.getContactData(Utility.response.responsedata.appDetails.rewardProgramId,
                                                Utility.response.responsedata.contactData.getContactID()
                                        );


                                        callGetContactData.enqueue(new Callback<ResponseModel>() {
                                            @Override
                                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                                if (response.isSuccessful()) {


                                                    Log.e("Response-getContactData", "Response : "+response.body());

                                                    Call<ResponseModel> callGetContactData = service.getAllPoints(Utility.response.responsedata.appDetails.rewardProgramId,
                                                            Utility.response.responsedata.contactData.getContactID()
                                                    );


                                                    callGetContactData.enqueue(new Callback<ResponseModel>() {
                                                        @Override
                                                        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                                            if (response.isSuccessful()) {


                                                                Log.e("Response - getAllPoints", "Response : "+response.body());
                                                                Utility.response.responsedata.totalEarnedThisMonth = response.body().responsedata.getTotalEarnedThisMonth();
                                                                Utility.response.responsedata.totalReedemed = response.body().responsedata.getTotalReedemed();
                                                                Utility.response.responsedata.lifeTimePoints = response.body().responsedata.getLifeTimePoints();
                                                                Utility.response.responsedata.pointBalance = response.body().responsedata.getPointBalance();
                                                                Utility.response.responsedata.pointBalance = response.body().responsedata.getPointBalance();

                                                                startActivity(new Intent(SplashActivity.this, HomeActivity.class));


                                                            } else {
                                                                Log.e("Response - getAllPoints", "Response : "+ response.message());
                                                                Utility.showAlertDialog(SplashActivity.this,"Oops...","Something went wrong");

                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<ResponseModel> call, Throwable test) {
                                                            Utility.showAlertDialog(SplashActivity.this,"Oops...","Something went wrong");

                                                            Log.e("Response - getAllPoints", "Response : "+ test.getMessage().toString());

                                                        }
                                                    });

                                                } else {
                                                    Utility.showAlertDialog(SplashActivity.this,"Oops...","Something went wrong");

                                                    Log.e("Response-getContactData", "Error: " + response.message());
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<ResponseModel> call, Throwable test) {
                                                Utility.showAlertDialog(SplashActivity.this,"Oops...","Something went wrong");
                                                Log.e("Response-getContactData", "Error: " +  test.getMessage().toString());

                                            }
                                        });


                             /*   Toast.makeText(SplashActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SplashActivity.this, HomeActivity.class));

*/

                                    }
                                    else
                                    {
                                        Utility.showAlertDialog(SplashActivity.this,"Oops...",response.message());
                                        Log.e("Test","Login Response : "+response.body().getStatusMessage());
                                    }


                                } else {
                                    Utility.showAlertDialog(SplashActivity.this,"Oops...","Something went wrong");

                                    Log.e("TEST", "Login Error: " + response.message());
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable error) {
                                if(errorCount > 2)
                                {
                                    Utility.showAlertDialog(SplashActivity.this,"Oops...","Something went wrong");

                                }
                                else
                                {
                                    callAPI();

                                    errorCount++;
                                }

//                                Utility.showAlertDialog(SplashActivity.this,"Oops...","Something went wrong");
                                Log.e("Test:::","Login : "+ error.getMessage().toString());
                            }
                        });

                    }
                    else
                    {
                        Log.e("Test","Login : "+  response.message()
                        );


                    }


                } else {
                    Log.e("Test","Login : "+  response.message());



                }



            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.e("Test","getAllData : "+t.getLocalizedMessage() );

                Toast.makeText(SplashActivity.this, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

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