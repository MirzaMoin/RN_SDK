package com.example.robosdk.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.example.robosdk.API.GetAPIData;
import com.example.robosdk.API.RetrofitClientInstance;
import com.example.robosdk.Adapter.CashbackImageSliderAdapter;
import com.example.robosdk.Adapter.FooterAdapter;
import com.example.robosdk.Adapter.SliderItem;
import com.example.robosdk.Models.AppColorModel;
import com.example.robosdk.Models.ChildPageModel;
import com.example.robosdk.Models.ChildPageSettingModel;
import com.example.robosdk.Models.HomeScreenModel;
import com.example.robosdk.Models.ResponseModel;
import com.example.robosdk.Models.TransaferPointChildPageDataModel;
import com.example.robosdk.R;
import com.example.robosdk.Utility.Utility;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransferPointActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imgBackTransferPoints;
    RecyclerView rvFooterTransferPoint;
    SwipeButton swipeBtnTransferPoint;
    TextView textPointTransferPoints;
    EditText etPointAmountTP, etUserDetailsTP;
    boolean isError = false;
    TableLayout tableLayoutTP;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.myLibTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_point);
        init();



        swipeBtnTransferPoint.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                if (active) {
                    if (etPointAmountTP.getText().toString().isEmpty()) {
                        isError = true;
                        if (swipeBtnTransferPoint.isActive()) {
                            swipeBtnTransferPoint.toggleState();
                        }
                        etPointAmountTP.setBackgroundDrawable(ContextCompat.getDrawable(TransferPointActivity.this, R.drawable.edit_text_border_error));
                    } else {
                        isError = false;
                        etPointAmountTP.setBackgroundDrawable(ContextCompat.getDrawable(TransferPointActivity.this, R.drawable.edit_text_border));
                    }
                    if (etUserDetailsTP.getText().toString().isEmpty()) {
                        isError = true;
                        if (swipeBtnTransferPoint.isActive()) {
                            swipeBtnTransferPoint.toggleState();
                        }
                        etUserDetailsTP.setBackgroundDrawable(ContextCompat.getDrawable(TransferPointActivity.this, R.drawable.edit_text_border_error));

                    } else {
                        isError = false;
                        etUserDetailsTP.setBackgroundDrawable(ContextCompat.getDrawable(TransferPointActivity.this, R.drawable.edit_text_border));

                    }

                    if (!isError) {
                        Utility.showLoader(TransferPointActivity.this);

                        final GetAPIData service = RetrofitClientInstance.getRetrofitInstance().create(GetAPIData.class);
                        Log.e("Request", "RP ID: " + Utility.response.responsedata.appDetails.rewardProgramId +
                                ", Contact ID: " + Utility.response.responsedata.contactData.contactID);

                        Call<ResponseModel> callTP = service.TransferPoints(ApiJsonMap(Utility.response.responsedata.appDetails.rewardProgramId,
                                Utility.response.responsedata.contactData.contactID,
                                Double.parseDouble(etPointAmountTP.getText().toString()),
                                etUserDetailsTP.getText().toString()
                        ));

                        callTP.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(@NotNull Call<ResponseModel> call, @NotNull Response<ResponseModel> response) {

                                if (response.isSuccessful() && response.body() != null) {
                                    if (response.body().getStatusCode() == 1) {
                                        Utility.dialog.dismiss();

                                        etPointAmountTP.setText("");
                                        etUserDetailsTP.setText("");
                                        swipeBtnTransferPoint.toggleState();

                                        Utility.showAlertDialog(TransferPointActivity.this,"Success",response.body().getStatusMessage());
                                        Log.e("Transfer Point",response.body().getStatusMessage());

                                        Call<ResponseModel> callGetContactData = service.getContactData(Utility.response.responsedata.appDetails.rewardProgramId,
                                                Utility.response.responsedata.contactData.getContactID()
                                        );

                                        callGetContactData.enqueue(new Callback<ResponseModel>() {
                                            @SuppressLint("SetTextI18n")
                                            @Override
                                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                                Utility.dialog.dismiss();


                                                if (response.isSuccessful() && response.body() != null) {


                                                    Log.e("Transfer Point", "Response : Got Contact Data");
                                                    Utility.response.responsedata.contactData = response.body().responsedata.contactData;

                                                    etPointAmountTP.setHint(Utility.getRoundData(Utility.response.responsedata.contactData.getPointBalance()) + " PTS");
                                                    if(Utility.response.responsedata.contactData.getPointBalance() > 0)
                                                    {
                                                        textPointTransferPoints.setVisibility(View.VISIBLE);
                                                        textPointTransferPoints.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderPointDigitColor()));

                                                        textPointTransferPoints.setText(Utility.getRoundData(Utility.response.responsedata.contactData.getPointBalance()) + " PTS");

                                                    }

                                                    Call<ResponseModel> callGetContactData = service.getAllPoints(Utility.response.responsedata.appDetails.rewardProgramId,
                                                            Utility.response.responsedata.contactData.getContactID()
                                                    );
                                                    callGetContactData.enqueue(new Callback<ResponseModel>() {
                                                        @Override
                                                        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                                            if (response.isSuccessful() && response.body() != null) {

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
                                                } else {
                                                    Utility.showAlertDialog(TransferPointActivity.this,"Oops...","Something went wrong");
                                                    Log.e("Transfer Point", "Error: " + response.message());
                                                }
                                            }
                                            @Override
                                            public void onFailure(Call<ResponseModel> call, Throwable test) {
                                                Utility.dialog.dismiss();
                                                swipeBtnTransferPoint.toggleState();
                                                Utility.showAlertDialog(TransferPointActivity.this,"Oops...","Something went wrong");
                                                Log.e("Transfer Point", test.getMessage());
                                            }
                                        });
                                    } else {
                                        swipeBtnTransferPoint.toggleState();
                                        Utility.dialog.dismiss();
                                        Log.e("Transfer Point", response.body().getStatusMessage());
                                        Utility.showAlertDialog(TransferPointActivity.this,"Opps...",response.body().getStatusMessage());
                                    }
                                } else {
                                    swipeBtnTransferPoint.toggleState();
                                    Utility.dialog.dismiss();
                                    Utility.showAlertDialog(TransferPointActivity.this,"Oops...","Something went wrong");
                                    Log.e("Transfer Point", "Error: " + response.message());
                                }
                            }
                            @Override
                            public void onFailure(@NotNull Call<ResponseModel> call, @NotNull Throwable test) {
                                Utility.dialog.dismiss();
                                swipeBtnTransferPoint.toggleState();
                                Utility.showAlertDialog(TransferPointActivity.this,"Oops...","Something went wrong");
                                Log.e("Transfer Point", test.getMessage());
                            }
                        });
                    }
                } else {
                    isError = false;
                }
            }
        });
    }
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.M)
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
        rvFooterTransferPoint = findViewById(R.id.rvFooterTransferPoint);
        imgBackTransferPoints = findViewById(R.id.imgBackTransferPoints);
        swipeBtnTransferPoint = findViewById(R.id.swipeBtnTransferPoint);
        textPointTransferPoints = findViewById(R.id.textPointTransferPoints);
        etPointAmountTP = findViewById(R.id.etPointAmountTP);
        tableLayoutTP = findViewById(R.id.tableLayoutTP);
        tableLayoutTP.setBackgroundColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderBarColor()));
        etUserDetailsTP = findViewById(R.id.etUserDetailsTP);
        textPointTransferPoints.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderPointDigitColor()));
        double balance = Utility.response.responsedata.contactData.getPointBalance();
        textPointTransferPoints.setText(Utility.getRoundData(balance) + " PTS");
        etPointAmountTP.setHint(Utility.getRoundData(balance) + " PTS");
        AppColorModel color = Utility.response.responsedata.appColor;
        swipeBtnTransferPoint.setBackgroundColor(Utility.getColor(color.getPrimaryButtonColor()));
        ChildPageSettingModel childPageSettings = Utility.response.responsedata.childPageSetting;
        if (childPageSettings.isChildPageTransaferPoint()) {
            List<ChildPageModel> childPage = new ArrayList<>();
            for (TransaferPointChildPageDataModel point : childPageSettings.transaferPointChildPageData) {
                childPage.add(new ChildPageModel(point.image, point.opacity, point.isClickable, point.linkType, point.internalLink, point.externalLink));
            }

            SliderView sliderView = findViewById(R.id.imageSliderTransferPoints);
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
        imgBackTransferPoints.setOnClickListener(this);
        Utility.setFooter(TransferPointActivity.this,rvFooterTransferPoint,"transferPoint");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgBackTransferPoints) {
            super.onBackPressed();
        }
    }
    private JsonObject ApiJsonMap(String rewardProgramID, String contactID, double transferPoints, String transferTo) {

        JsonObject gsonObject = new JsonObject();
        try {
            JSONObject jsonObj_ = new JSONObject();
            jsonObj_.put("rewardProgramID", rewardProgramID);
            jsonObj_.put("contactID", contactID);
            jsonObj_.put("transferPoints", transferPoints);
            jsonObj_.put("transferTo", transferTo);
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