package com.example.rnsdk.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rnsdk.API.GetAPIData;
import com.example.rnsdk.API.RetrofitClientInstance;
import com.example.rnsdk.Adapter.CashbackImageSliderAdapter;
import com.example.rnsdk.Adapter.FooterAdapter;
import com.example.rnsdk.Adapter.SliderItem;
import com.example.rnsdk.Adapter.TransactionHistoryAdapter;
import com.example.rnsdk.Models.AppColorModel;
import com.example.rnsdk.Models.ChildPageModel;
import com.example.rnsdk.Models.ChildPageSettingModel;
import com.example.rnsdk.Models.HomeScreenModel;
import com.example.rnsdk.Models.ResponseModelTransactionHistory;
import com.example.rnsdk.Models.TransactionHistoryChildPageDataModel;
import com.example.rnsdk.Models.TransactionHistoryModel;
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionHistoryActivity extends AppCompatActivity implements View.OnClickListener {

    List<SliderItem> mSliderItems = new ArrayList<>();
    RecyclerView rvTransactionHistory, rvFooterTransactionHistory;

    TextView textPointTransactionHistory,
            textNoData;
    ImageView imgBackTransactionHistory,
            imgPreview,
            imgPreviewClose,
            imageTH,
            imageLogoTH;


    ResponseModelTransactionHistory responseModel;
    EditText etLocationNameSearchTH;
    RelativeLayout relImagePreview,
            relLoadingTH;

    TableLayout tableLayoutTH;

    boolean isOpen = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

        init();
        getData();


    }


    private void getData() {
        relLoadingTH.setVisibility(View.VISIBLE);
        Glide.with(this).load(Utility.response.responsedata.appIntakeImages.loadingImages.get(0).imageUrl).into(imageTH);
        Glide.with(this).load(Utility.response.responsedata.appIntakeImages.companyLogo).into(imageLogoTH);


        GetAPIData service = RetrofitClientInstance.getRetrofitInstance().create(GetAPIData.class);
        Log.e("Request", "RP ID: " + Utility.response.responsedata.appDetails.rewardProgramId + ", Contact ID: " + Utility.response.responsedata.contactData.contactID);
        Call<ResponseModelTransactionHistory> call = service.getTransactionHistory(Utility.response.responsedata.appDetails.rewardProgramId
                , Utility.response.responsedata.contactData.contactID);
        call.enqueue(new Callback<ResponseModelTransactionHistory>() {

            @Override
            public void onResponse(Call<ResponseModelTransactionHistory> call, Response<ResponseModelTransactionHistory> response) {
                relLoadingTH.setVisibility(View.GONE);

                if (response.isSuccessful()) {

                    responseModel = response.body();

                    if (response.body().responsedata.size() > 0) {
                        TransactionHistoryAdapter adapter =
                                new TransactionHistoryAdapter(TransactionHistoryActivity.this,
                                        responseModel.responsedata,
                                        relImagePreview,
                                        imgPreview,
                                        imgPreviewClose);
                        rvTransactionHistory.setHasFixedSize(true);
                        rvTransactionHistory.setLayoutManager(new LinearLayoutManager(TransactionHistoryActivity.this));
                        rvTransactionHistory.setAdapter(adapter);

                        Log.e("Test","History Size: "+response.body().responsedata.size());

                    } else {
                        textNoData.setVisibility(View.VISIBLE);
                        Log.e("Test", "No Transaction Found");
                    }

                } else {
                    Utility.showAlertDialog(TransactionHistoryActivity.this,"Oops...","Something went wrong");
                    Log.e("Test Error: ", "" + response.message());

                }
            }

            @Override
            public void onFailure(Call<ResponseModelTransactionHistory> call, Throwable t) {
                relLoadingTH.setVisibility(View.GONE);
                Utility.showAlertDialog(TransactionHistoryActivity.this,"Oops...","Something went wrong");

                Log.e("Test Error: ", "" + t.getMessage());


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

        tableLayoutTH = findViewById(R.id.tableLayoutTH);
        textNoData = findViewById(R.id.textNoData);
        tableLayoutTH.setBackgroundColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderBarColor()));

        textPointTransactionHistory = findViewById(R.id.textPointTransactionHistory);
        textPointTransactionHistory.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderPointDigitColor()));
        textPointTransactionHistory.setText(Utility.getRoundData(Utility.response.responsedata.contactData.getPointBalance()) + " PTS");

        ChildPageSettingModel childPageSettings = Utility.response.responsedata.childPageSetting;

        if (childPageSettings.isChildPageTransactionHistory()) {
            List<ChildPageModel> childPage = new ArrayList<>();
            for (TransactionHistoryChildPageDataModel transaction : childPageSettings.transactionHistoryChildPageData) {
                childPage.add(new ChildPageModel(transaction.image, transaction.opacity, transaction.isClickable, transaction.linkType, transaction.internalLink, transaction.externalLink));
            }


            SliderView sliderView = findViewById(R.id.imageSliderTransactionHistory);
            rvTransactionHistory = findViewById(R.id.rvTransactionHistory);
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

        rvFooterTransactionHistory = findViewById(R.id.rvFooterTransactionHistory);
        imgBackTransactionHistory = findViewById(R.id.imgBackTransactionHistory);
        etLocationNameSearchTH = findViewById(R.id.etLocationNameSearchTH);
        relImagePreview = findViewById(R.id.relImagePreview);
        imgPreview = findViewById(R.id.imgPreview);
        imgPreviewClose = findViewById(R.id.imgPreviewClose);
        relLoadingTH = findViewById(R.id.relLoadingTH);
        imageTH = findViewById(R.id.imageTH);
        imageLogoTH = findViewById(R.id.imageLogoTH);

        imgBackTransactionHistory.setOnClickListener(this);
        setFooter();

        etLocationNameSearchTH.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                filterLocation(s);
                Log.e("Test", "onTextChanged: " + s.toString());


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void filterLocation(CharSequence s) {
        List<TransactionHistoryModel> filterData = new ArrayList<TransactionHistoryModel>();
        for (TransactionHistoryModel history : responseModel.responsedata) {
            if (history.locationName.toLowerCase().contains(s.toString().toLowerCase())) {
                Log.e("Test", "filterLocation: " + history.locationName);
                filterData.add(history);
            }
        }
        Log.e("Test", "Filter Size" + filterData.size());
        if (filterData != null) {
            TransactionHistoryAdapter adapter = new TransactionHistoryAdapter(TransactionHistoryActivity.this, filterData, relImagePreview, imgPreview, imgPreview);
            rvTransactionHistory.setHasFixedSize(true);
            rvTransactionHistory.setLayoutManager(new LinearLayoutManager(TransactionHistoryActivity.this));
            rvTransactionHistory.setAdapter(adapter);
        }
    }

    private void setFooter() {
        Log.e("Test","SetFooter Called");
        AppColorModel appColor = Utility.response.responsedata.appColor;

        HomeScreenModel homeScreenModel = Utility.response.responsedata.homeScreen;
        if (homeScreenModel.isHomePageDisplayFooter()) {
            rvFooterTransactionHistory.setVisibility(View.VISIBLE);
            rvFooterTransactionHistory.setBackgroundColor(Utility.getColor(appColor.getFooterBarColor()));

            FooterAdapter adapter = new FooterAdapter(this, homeScreenModel.footerLinks, "transactionHistory");
            rvFooterTransactionHistory.setHasFixedSize(true);


            rvFooterTransactionHistory.setLayoutManager(new GridLayoutManager(this, homeScreenModel.footerLinks.size()));

            rvFooterTransactionHistory.setAdapter(adapter);
        } else {
            rvFooterTransactionHistory.setVisibility(View.GONE);


        }


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgBackTransactionHistory) {
            super.onBackPressed();
        }
    }
}