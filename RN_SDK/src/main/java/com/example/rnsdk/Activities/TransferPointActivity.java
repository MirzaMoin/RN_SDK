package com.example.rnsdk.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.example.rnsdk.API.GetAPIData;
import com.example.rnsdk.API.RetrofitClientInstance;
import com.example.rnsdk.Adapter.CashbackImageSliderAdapter;
import com.example.rnsdk.Adapter.FooterAdapter;
import com.example.rnsdk.Adapter.SliderItem;
import com.example.rnsdk.Models.AppColorModel;
import com.example.rnsdk.Models.ChildPageModel;
import com.example.rnsdk.Models.ChildPageSettingModel;
import com.example.rnsdk.Models.HomeScreenModel;
import com.example.rnsdk.Models.ResponseModel;
import com.example.rnsdk.Models.TransaferPointChildPageDataModel;
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransferPointActivity extends AppCompatActivity implements View.OnClickListener {

    List<SliderItem> mSliderItems = new ArrayList<>();
    ImageView imgBackTransferPoints;
    RecyclerView rvFooterTransferPoint;
    SwipeButton swipeBtnTransferPoint;
    TextView textPointTransferPoints;
    ProgressDialog progressDialog;


    boolean isOpen = false;

    EditText etPointAmountTP, etUserDetailsTP;
    boolean isError = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                        progressDialog = new ProgressDialog(TransferPointActivity.this);
                        progressDialog.setTitle("Loading...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                        GetAPIData service = RetrofitClientInstance.getRetrofitInstance().create(GetAPIData.class);
                        Log.e("Request", "RP ID: " + Utility.response.responsedata.appDetails.rewardProgramId +
                                ", Contact ID: " + Utility.response.responsedata.contactData.contactID);

                        Call<ResponseModel> callLogin = service.TransferPoints(ApiJsonMap(Utility.response.responsedata.appDetails.rewardProgramId,
                                Utility.response.responsedata.contactData.contactID,
                                Double.parseDouble(etPointAmountTP.getText().toString()),
                                etUserDetailsTP.getText().toString()
                        ));


                        callLogin.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                                if (response.isSuccessful()) {
                                    if (response.body().getStatusCode() == 1) {
                                        progressDialog.dismiss();


                                        etPointAmountTP.setText("");
                                        etUserDetailsTP.setText("");
                                        swipeBtnTransferPoint.toggleState();

                                        showAlertDialog("Success",response.body().getStatusMessage());
                                        Log.e("Test",response.body().getStatusMessage());


                                        Call<ResponseModel> callGetContactData = service.getContactData(Utility.response.responsedata.appDetails.rewardProgramId,
                                                Utility.response.responsedata.contactData.getContactID()
                                        );


                                        callGetContactData.enqueue(new Callback<ResponseModel>() {
                                            @Override
                                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                                progressDialog.dismiss();

                                                if (response.isSuccessful()) {


                                                    Log.e("Test:", "Response : Got Contact Data");
                                                    Utility.response.responsedata.contactData = response.body().responsedata.contactData;


                                                    etPointAmountTP.setHint(Utility.getRoundData(Utility.response.responsedata.contactData.getPointBalance()) + " PTS");
                                                    textPointTransferPoints.setText(Utility.getRoundData(Utility.response.responsedata.contactData.getPointBalance()) + " PTS");


                                                } else {
                                                    Log.e("TEST", "Error: " + response.message());
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<ResponseModel> call, Throwable test) {
                                                progressDialog.dismiss();
                                                swipeBtnTransferPoint.toggleState();


                                                Log.e("Test:::", test.getMessage().toString());
                                            }
                                        });


                                    } else {
                                        swipeBtnTransferPoint.toggleState();

                                        progressDialog.dismiss();

                                        Log.e("Test", response.body().getStatusMessage());
                                        showAlertDialog("Opps...",response.body().getStatusMessage());


                                    }


                                } else {
                                    swipeBtnTransferPoint.toggleState();

                                    progressDialog.dismiss();

                                    Log.e("TEST", "Error: " + response.message());
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable test) {
                                progressDialog.dismiss();
                                swipeBtnTransferPoint.toggleState();


                                Log.e("Test:::", test.getMessage().toString());
                            }
                        });
                    }

                } else {
                    isError = false;
                }
            }
        });
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
        rvFooterTransferPoint = findViewById(R.id.rvFooterTransferPoint);
        imgBackTransferPoints = findViewById(R.id.imgBackTransferPoints);
        swipeBtnTransferPoint = findViewById(R.id.swipeBtnTransferPoint);
        textPointTransferPoints = findViewById(R.id.textPointTransferPoints);
        etPointAmountTP = findViewById(R.id.etPointAmountTP);
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

        setFooter();
    }

    private void setFooter() {
        AppColorModel appColor = Utility.response.responsedata.appColor;

        HomeScreenModel homeScreenModel = Utility.response.responsedata.homeScreen;
        if (homeScreenModel.isHomePageDisplayFooter()) {
            rvFooterTransferPoint.setVisibility(View.VISIBLE);
            rvFooterTransferPoint.setBackgroundColor(Utility.getColor(appColor.getFooterBarColor()));

            FooterAdapter adapter = new FooterAdapter(this, homeScreenModel.footerLinks, "transferPoint");
            rvFooterTransferPoint.setHasFixedSize(true);


            rvFooterTransferPoint.setLayoutManager(new GridLayoutManager(this, homeScreenModel.footerLinks.size()));

            rvFooterTransferPoint.setAdapter(adapter);
        } else {
            rvFooterTransferPoint.setVisibility(View.GONE);


        }


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


    void showAlertDialog(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final View customLayout = getLayoutInflater().inflate(R.layout.content_alert_dialog, null);
        builder.setView(customLayout);

        AlertDialog dialog = builder.create();
        dialog.show();

        TextView textMessage, textOk, textTitle;
        textMessage = dialog.findViewById(R.id.textMessageAlert);
        textTitle = dialog.findViewById(R.id.textTitleAlert);
        textOk = dialog.findViewById(R.id.textOKAlert);
        textMessage.setText(message);
        textTitle.setText(title);
        textOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }

}