package com.example.robosdk.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.robosdk.API.GetAPIData;
import com.example.robosdk.API.RetrofitClientInstance;
import com.example.robosdk.Adapter.FooterAdapter;
import com.example.robosdk.Adapter.OffersAdapter;
import com.example.robosdk.Constants;
import com.example.robosdk.Models.AppColorModel;
import com.example.robosdk.Models.HomeScreenModel;
import com.example.robosdk.Models.ResponseModel;
import com.example.robosdk.Models.ResponsedataModel;
import com.example.robosdk.R;
import com.example.robosdk.Utility.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfferActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView rvOffer, rvFooterOffers;
    ImageView ivBack,
            imageOffers,
            imageLogoOffers;
    TextView textPointOffers;
    RelativeLayout relLoadingOffers;
    TableLayout tableLayoutOffer;
    LinearLayout linearNoOffer;

    @Override
    protected void onResume() {
        super.onResume();
        if(Constants.isOfferRedeem)
        {
            Constants.isOfferRedeem = false;
            getData();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.myLibTheme);
        super.onCreate(savedInstanceState);
        setContentView(R
                .layout.activity_offer);

        init();

        getData();

    }
    private void getData() {
        relLoadingOffers.setVisibility(View.VISIBLE);
        Glide.with(this).load(Utility.response.responsedata.appIntakeImages.loadingImages.get(0).imageUrl).into(imageOffers);
        Glide.with(this).load(Utility.response.responsedata.appIntakeImages.companyLogo).into(imageLogoOffers);

        GetAPIData service = RetrofitClientInstance.getRetrofitInstance().create(GetAPIData.class);
        Log.e("Request", "RP ID: " + Utility.response.responsedata.appDetails.rewardProgramId + ", Contact ID: " + Utility.response.responsedata.contactData.contactID);
        Call<ResponseModel> call =
                service.getOffers(Utility.response.responsedata.appDetails.rewardProgramId
                        , Utility.response.responsedata.contactData.contactID);
        call.enqueue(new Callback<ResponseModel>() {

            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if(response.body().statusCode == 1)
                    {
                        ResponseModel responseModel = response.body();
                        ResponsedataModel responseData = Utility.response.responsedata;
                        responseData.offerList = responseModel.responsedata.getOfferList();
                        responseData.addressDetails = responseModel.responsedata.getAddressDetails();
                        responseData.userDetails = responseModel.responsedata.getUserDetails();
                        responseData.redeemSetting = responseModel.responsedata.getRedeemSetting();

                        if(responseData.redeemSetting.isAskWhereAreYou())
                        {
                            Log.e("Test","AskWhere is "+(responseData.redeemSetting.isAskWhereAreYou()));
                            getLocations();
                            setLayout();

                        }
                        else
                        {
                            Log.e("Test","AskWhere is "+(responseData.redeemSetting.isAskWhereAreYou()));
                            setLayout();

                            relLoadingOffers.setVisibility(View.GONE);

                        }

                        Log.e("Test", "onResponse: " + responseData.redeemSetting.isAskWhereAreYou());



                    }
                    else
                    {
                        relLoadingOffers.setVisibility(View.GONE);
                        linearNoOffer.setVisibility(View.VISIBLE);

//                        Utility.showAlertDialog(OfferActivity.this,"Hmmm...", response.body().statusMessage);
                    }


                } else {

                    Utility.showAlertDialog(OfferActivity.this,"Oops...", "Something went wrong");

                    Log.e("Test Error: ", "" + response.message());


                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                relLoadingOffers.setVisibility(View.GONE);

                Utility.showAlertDialog(OfferActivity.this,"Oops...", "Something went wrong");
                Log.e("Test Error: ", "" + t.getMessage());


            }
        });
    }

    private void getLocations() {
        GetAPIData service = RetrofitClientInstance.getRetrofitInstance().create(GetAPIData.class);

        Log.e("Request GetLocationData", "RP ID: " + Utility.response.responsedata.appDetails.rewardProgramId + ", Contact ID: " + Utility.response.responsedata.contactData.contactID);
        Call<ResponseModel> callGetLocation =
                service.getLocationData(Utility.response.responsedata.appDetails.rewardProgramId);
        callGetLocation.enqueue(new Callback<ResponseModel>() {

            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful()) {
                    relLoadingOffers.setVisibility(View.GONE);

                    if(response.code() == 200)
                    {
                        if(response.body() != null)
                        {
                            ResponseModel responseModel = response.body();
                            ResponsedataModel responseData = Utility.response.responsedata;

                            responseData.locationData = responseModel.responsedata.getLocationData();

                            Log.e("GetLocationData", "onResponse - Location List Size: " + responseModel.responsedata.locationData.size());

                            setLayout();
                        }
                        else
                        {
                            Utility.showAlertDialog(OfferActivity.this,"Oops...", "Something went wrong");
                        }
                    }
                    else
                    {
                        Utility.showAlertDialog(OfferActivity.this,"Oops...", "Something went wrong");

                        Log.e("GetLocationData", "Status code - Location List Size: " + response.code() );

                        setLayout();
                    }

                } else {
                    relLoadingOffers.setVisibility(View.GONE);

                    Log.e("Offer Error: ", "" + response.message());
                }
            }
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                relLoadingOffers.setVisibility(View.GONE);

                Log.e("Test Error: ", "" + t.getMessage());


            }
        });
    }
    @SuppressLint("SetTextI18n")
    private void setLayout() {
        if(Utility.response.responsedata.contactData.getPointBalance() > 0)
        {
            textPointOffers.setVisibility(View.VISIBLE);
            textPointOffers.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderPointDigitColor()));
            textPointOffers.setText(Utility.getRoundData(Utility.response.responsedata.contactData.getPointBalance()) + " PTS");
        }

        OffersAdapter adapter = new OffersAdapter(this, Utility.response.responsedata.offerList);
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
        if (Utility.response.responsedata.appColor.getPhoneNotificationBarTextColor().equals("Black")) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        rvOffer = findViewById(R.id.rvOffers);
        textPointOffers = findViewById(R.id.textPointOffers);
        ivBack = findViewById(R.id.imgBackOffers);
        rvFooterOffers = findViewById(R.id.rvFooterOffers);
        imageOffers = findViewById(R.id.imageOffers);
        imageLogoOffers = findViewById(R.id.imageLogoOffers);
        relLoadingOffers = findViewById(R.id.relLoadingOffers);
        tableLayoutOffer = findViewById(R.id.tableLayoutOffer);
        linearNoOffer = findViewById(R.id.linearNoOffer);
        tableLayoutOffer.setBackgroundColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderBarColor()));
        ivBack.setOnClickListener(this);
        Utility.setFooter(OfferActivity.this,rvFooterOffers,"offer");
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgBackOffers) {
            super.onBackPressed();
        }
    }
}