package com.example.robosdk.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.example.robosdk.API.GetAPIData;
import com.example.robosdk.API.RetrofitClientInstance;
import com.example.robosdk.Adapter.CashbackImageSliderAdapter;
import com.example.robosdk.Adapter.FooterAdapter;
import com.example.robosdk.Adapter.RedeemCashbackAdapter;
import com.example.robosdk.Adapter.SliderItem;
import com.example.robosdk.Models.AppColorModel;
import com.example.robosdk.Models.ChildPageModel;
import com.example.robosdk.Models.ChildPageSettingModel;
import com.example.robosdk.Models.HomeScreenModel;
import com.example.robosdk.Models.RedeemCashBackChildPageDataModel;
import com.example.robosdk.Models.ResponseModel;
import com.example.robosdk.Models.ResponsedataModel;
import com.example.robosdk.R;
import com.example.robosdk.Utility.Utility;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CashbackActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    List<SliderItem> mSliderItems = new ArrayList<>();
    ImageView imgBack,
            imageCashback,
            imageLogoCashback;
    LinearLayout linearRPGCashback, linearHome,
            linearAmountRC;
    RecyclerView rvFooterCashback;
    TextView textPointCashback,
            textBalanceRC;
    public static EditText etAmountRC;
    SwipeButton swipeButtonRC;

    RelativeLayout relLoadingCashback;
    double amount = 0;
    boolean isAllowPartialCashbackRedemption = true,
            isRequireWholeNumberRedemption = true;

    RecyclerView rvCashback;
    TableLayout tableLayoutCashback;
    boolean isTap = false;

    CardView cardTapRC;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashback);
        init();
        relLoadingCashback.setVisibility(View.VISIBLE);
        Glide.with(this).load(Utility.response.responsedata.appIntakeImages.loadingImages.get(0).imageUrl).into(imageCashback);
        Glide.with(this).load(Utility.response.responsedata.appIntakeImages.companyLogo).into(imageLogoCashback);

        getData();
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
        toolbar = findViewById(R.id.toolbarCashback);
        imgBack = findViewById(R.id.imgBackRedeemCashback);
        textPointCashback = findViewById(R.id.textPointCashback);
        relLoadingCashback = findViewById(R.id.relLoadingCashback);
        imageCashback = findViewById(R.id.imageCashback);
        imageLogoCashback = findViewById(R.id.imageLogoCashback);
        tableLayoutCashback = findViewById(R.id.tableLayoutCashback);

        tableLayoutCashback.setBackgroundColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderBarColor()));


        cardTapRC = findViewById(R.id.cardTapRC);
        rvFooterCashback = findViewById(R.id.rvFooterCashback);
        textBalanceRC = findViewById(R.id.textBalanceRC);
        etAmountRC = findViewById(R.id.etAmountRC);
        swipeButtonRC = findViewById(R.id.swipeBtnRC);
        linearAmountRC = findViewById(R.id.linearAmountRC);
        rvCashback = findViewById(R.id.rvCashback);
        imgBack.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle("");
            setSupportActionBar(toolbar);

        }

        textPointCashback.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderPointDigitColor()));
        textPointCashback.setText(Utility.getRoundData(Utility.response.responsedata.contactData.getPointBalance()) + " PTS");

        ChildPageSettingModel childPageSettings = Utility.response.responsedata.childPageSetting;

        if (childPageSettings.isChildPageRedeemCashBack()) {
            List<ChildPageModel> childPage = new ArrayList<>();
            for (RedeemCashBackChildPageDataModel cashback : childPageSettings.redeemCashBackChildPageData) {
                childPage.add(new ChildPageModel(cashback.image, cashback.opacity, cashback.isClickable, cashback.linkType, cashback.internalLink, cashback.externalLink));
            }


            SliderView sliderView = findViewById(R.id.imageSliderCashback);

            sliderView.setVisibility(View.VISIBLE);
            CashbackImageSliderAdapter adapter = new CashbackImageSliderAdapter(this, childPage);

            sliderView.setSliderAdapter(adapter);

            sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
            sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
            sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
            sliderView.setIndicatorSelectedColor(Color.WHITE);
            sliderView.setIndicatorUnselectedColor(Color.GRAY);
            sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
            sliderView.startAutoCycle();
        }
        setFooter();
        AppColorModel color = Utility.response.responsedata.appColor;
        swipeButtonRC.setBackgroundColor(Utility.getColor(color.getPrimaryButtonColor()));


        swipeButtonRC.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                if (active) {
                    String otherAmount = etAmountRC.getText().toString().trim();
                    if(isAllowPartialCashbackRedemption)
                    {
                        if (otherAmount.isEmpty()) {
                            swipeButtonRC.toggleState();
                            Log.e("Test", "Please enter other amount");
                            Toast.makeText(CashbackActivity.this, "Please enter other amount", Toast.LENGTH_SHORT).show();
                        } else {
                            if (isRequireWholeNumberRedemption) {
                                if (otherAmount.contains(".")) {
                                    swipeButtonRC.toggleState();

                                    Log.e("Test", "Please enter valid amount");

                                    Toast.makeText(CashbackActivity.this, "Please enter valid amount", Toast.LENGTH_SHORT).show();
                                } else {
                                    makeCashback(otherAmount);
                                }
                            } else {
                                makeCashback(otherAmount);
                            }
                        }
                    }
                    else
                    {
                        if(isTap)
                        {
                            if(amount > 0)
                            {
                                makeCashback(String.valueOf(amount));
                            }
                            else {
                                swipeButtonRC.toggleState();

                                Log.e("Test", "You don't have enough amount to redeem");

                                Toast.makeText(CashbackActivity.this, "You don't have enough amount to redeem", Toast.LENGTH_SHORT).show();

                            }
                        }
                        else
                        {
                            swipeButtonRC.toggleState();

                            Log.e("Test", "Tap to redeem maximum cashback amount");

                            Toast.makeText(CashbackActivity.this, "Tap to redeem maximum cashback amount", Toast.LENGTH_SHORT).show();

                        }
                    }

                }
            }
        });

        cardTapRC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etAmountRC.setText(Utility.getRoundData(amount));
                isTap = true;
            }
        });


    }

    private void setCashbackSuggetion() {

        if(isAllowPartialCashbackRedemption)
        {
            rvCashback.setVisibility(View.VISIBLE);
            ArrayList<String> list = new ArrayList<>();
            double finalValue = amount / 5;
            double newValue = finalValue;
            for(int i = 0;i<5;i++)
            {

                list.add(Utility.getRoundData(Math.round(finalValue)));
                Log.e("TEST",String.valueOf(Math.floor(finalValue)));
                finalValue += newValue;

            }

            RedeemCashbackAdapter adapter = new RedeemCashbackAdapter(this,list);
            rvCashback.setHasFixedSize(true);


            rvCashback.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

            rvCashback.setAdapter(adapter);
        }

    }

    private void makeCashback(String otherAmount) {

       Utility.showLoader(CashbackActivity.this);

        GetAPIData service = RetrofitClientInstance.getRetrofitInstance().create(GetAPIData.class);

        ResponsedataModel responseData = Utility.response.responsedata;

        Call<JsonObject> callCashbackRedeem = service.CashbackRedeem(ApiJsonMap(responseData.appDetails.rewardProgramId,
                responseData.appDetails.webFormID,
                responseData.contactData.contactID,
                Double.parseDouble(otherAmount)
        ));


        callCashbackRedeem.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Utility.dialog.dismiss();
                if (response.isSuccessful()) {
                    swipeButtonRC.toggleState();
                    Log.e("Test", response.body().toString());
                    int statusCode = response.body().get("statusCode").getAsInt();
                    String statusMessage = response.body().get("statusMessage").getAsString();
                    JsonElement responsedata = response.body().get("responsedata");


                    if (statusCode == 1) {

                        Log.e("Test", "Response : " + response.body().toString());
                        Utility.showAlertDialog(CashbackActivity.this,"Success", statusMessage);
                        etAmountRC.setText("");
                        Utility.response.responsedata.contactData.setPointBalance(responsedata.getAsJsonObject().get("reedemablePoints").getAsDouble());
                        Utility.response.responsedata.contactData.setReedemablePoints(responsedata.getAsJsonObject().get("reedemablePoints").getAsDouble());
                        textPointCashback.setText(Utility.getRoundData(responsedata.getAsJsonObject().get("reedemablePoints").getAsDouble()) + " PTS");


                        getData();

                    } else {
                        Log.e("Test", "Response : " + response.body().toString());

                        Utility.showAlertDialog(CashbackActivity.this,"Oh no...", statusMessage);

                    }

                } else {
                    Log.e("TEST", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable test) {
                Utility.dialog.dismiss();


                Log.e("Test:::", test.getMessage().toString());
            }
        });
    }

    private void setFooter() {
        AppColorModel appColor = Utility.response.responsedata.appColor;

        HomeScreenModel homeScreenModel = Utility.response.responsedata.homeScreen;
        if (homeScreenModel.isHomePageDisplayFooter()) {
            rvFooterCashback.setVisibility(View.VISIBLE);
            rvFooterCashback.setBackgroundColor(Utility.getColor(appColor.getFooterBarColor()));

            FooterAdapter adapter = new FooterAdapter(this, homeScreenModel.footerLinks, "redeemCashback");
            rvFooterCashback.setHasFixedSize(true);


            rvFooterCashback.setLayoutManager(new GridLayoutManager(this, homeScreenModel.footerLinks.size()));

            rvFooterCashback.setAdapter(adapter);
        } else {
            rvFooterCashback.setVisibility(View.GONE);


        }


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgBackRedeemCashback) {
            super.onBackPressed();
        }

    }

    private void getData() {




        final GetAPIData service = RetrofitClientInstance.getRetrofitInstance().create(GetAPIData.class);
        Log.e("Request", "RP ID: " + Utility.response.responsedata.appDetails.rewardProgramId + ", Contact ID: " + Utility.response.responsedata.contactData.contactID);
        Call<JsonObject> call =
                service.getCashbackAmount(Utility.response.responsedata.appDetails.rewardProgramId
                        , Utility.response.responsedata.contactData.contactID);
        call.enqueue(new Callback<JsonObject>() {

            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                relLoadingCashback.setVisibility(View.GONE);


                if (response.isSuccessful()) {

                    JsonElement responseModel = response.body().get("responsedata");
                    Log.e("Response: ", "" + String.valueOf(responseModel.getAsJsonObject().get("amount")));
                    amount = responseModel.getAsJsonObject().get("amount").getAsDouble();
                    textBalanceRC.setText("$ " + String.valueOf(Utility.getRoundData(amount)));
                    isAllowPartialCashbackRedemption = responseModel.getAsJsonObject().get("isAllowPartialCashbackRedemption").getAsBoolean();
                    isRequireWholeNumberRedemption = responseModel.getAsJsonObject().get("isRequireWholeNumberRedemption").getAsBoolean();
                    setCashbackSuggetion();

                    if (!isAllowPartialCashbackRedemption) {
                        linearAmountRC.setVisibility(View.GONE);
                    }

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



                            } else {
                                Log.e("Response - getAllPoints", "Response : "+ response.message());

                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseModel> call, Throwable test) {

                            Log.e("Response - getAllPoints", "Response : "+ test.getMessage().toString());

                        }
                    });

                    Log.e("Test", "Response: " + response.body());


                } else {

                    Utility.showAlertDialog(CashbackActivity.this,"Oops...","Something went wrong");
                    Log.e("Test Error: ", "" + response.message());


                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                relLoadingCashback.setVisibility(View.GONE);

                Log.e("Test Error: ", "" + t.getMessage());
                Utility.showAlertDialog(CashbackActivity.this,"Oops...","Something went wrong");



            }
        });

    }

    private JsonObject ApiJsonMap(String rewardProgramID, String webFormID, String contactID, double cashbackAmount
    ) {

        JsonObject gsonObject = new JsonObject();
        try {
            JSONObject jsonObj_ = new JSONObject();
            jsonObj_.put("rewardProgramID", rewardProgramID);
            jsonObj_.put("webFormID", webFormID);
            jsonObj_.put("contactID", contactID);
            jsonObj_.put("cashbackAmount", cashbackAmount);


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