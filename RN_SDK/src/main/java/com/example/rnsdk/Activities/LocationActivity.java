package com.example.rnsdk.Activities;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rnsdk.API.GetAPIData;
import com.example.rnsdk.API.RetrofitClientInstance;
import com.example.rnsdk.Adapter.FooterAdapter;
import com.example.rnsdk.Adapter.LocationBottomsheetAdapter;
import com.example.rnsdk.Adapter.OffersAdapter;
import com.example.rnsdk.Models.AppColorModel;
import com.example.rnsdk.Models.HomeScreenModel;
import com.example.rnsdk.Models.LocationDataModel;
import com.example.rnsdk.Models.ResponseModel;
import com.example.rnsdk.Models.ResponsedataModel;
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    CardView cardLocation;
    BottomSheetBehavior bottomSheetBehavior;
    RecyclerView rvFooterLocation;
    ImageView imgBackLocation;
    TextView textPointLocation;

    LinearLayout bottomsheetLocation;
    RecyclerView rvLocationBottomsheet;
    ProgressDialog progressDialog;
    boolean isExpanded = false;
    boolean isOpen = false;
    List<LocationDataModel> originalLocations = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        init();
        getLocations();


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
        cardLocation = findViewById(R.id.cardLocation);
        bottomsheetLocation = findViewById(R.id.bottomsheetLocation);
        rvFooterLocation = findViewById(R.id.rvFooterLocation);
        imgBackLocation = findViewById(R.id.imgBackLocation);
        textPointLocation = findViewById(R.id.textPointLocation);
        rvLocationBottomsheet = findViewById(R.id.rvLocationBottomsheet);


        imgBackLocation.setOnClickListener(this);
        cardLocation.setOnClickListener(this);

        textPointLocation.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderPointDigitColor()));
        textPointLocation.setText(Utility.getRoundData(Utility.response.responsedata.contactData.getPointBalance())+ " PTS");


        setFooter();
    }

    private void setFooter() {
        AppColorModel appColor = Utility.response.responsedata.appColor;

        HomeScreenModel homeScreenModel = Utility.response.responsedata.homeScreen;
        if(homeScreenModel.isHomePageDisplayFooter())
        {
            rvFooterLocation.setVisibility(View.VISIBLE);
            rvFooterLocation.setBackgroundColor(Utility.getColor(appColor.getFooterBarColor()));

            FooterAdapter adapter = new FooterAdapter(this,homeScreenModel.footerLinks,"locations");
            rvFooterLocation.setHasFixedSize(true);


            rvFooterLocation.setLayoutManager(new GridLayoutManager(this,homeScreenModel.footerLinks.size()));

            rvFooterLocation.setAdapter(adapter);
        }
        else
        {
            rvFooterLocation.setVisibility(View.GONE);


        }


    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng sydney = new LatLng(37.78825, -122.4324);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Primary Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


    private void showBottomsheet() {
        Utility.response.responsedata.locationData.clear();
        Utility.response.responsedata.locationData.addAll(originalLocations);
        // create an alert builder
        BottomSheetDialog dialog = new BottomSheetDialog(this, R.style.BottomSheetStyle);
        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.content_location_list, null);

        dialog.setContentView(customLayout);
        dialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
        dialog.show();


        ((View) customLayout.getParent()).setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
        TextView textLocation = dialog.findViewById(R.id.textLocation);
        TextView textNoLocation = dialog.findViewById(R.id.textNoLocation);
        RecyclerView rvLocation = dialog.findViewById(R.id.rvLocationBottomsheet);

        if (Utility.response.responsedata.locationData.size() > 0) {
            rvLocation.setVisibility(View.VISIBLE);
            textNoLocation.setVisibility(View.GONE);
            LocationBottomsheetAdapter adapter = new LocationBottomsheetAdapter(this);
            rvLocation.setHasFixedSize(true);
            rvLocation.setLayoutManager(new LinearLayoutManager(this));
            rvLocation.setAdapter(adapter);

            BottomSheetDialog d = (BottomSheetDialog) dialog;

            // This is gotten directly from the source of BottomSheetDialog
            // in the wrapInBottomSheet() method
            FrameLayout bottomSheet = (FrameLayout) d.findViewById(R.id.design_bottom_sheet);

            // Right here!
            BottomSheetBehavior.from(bottomSheet).setDraggable(false);
            BottomSheetBehavior.from(bottomSheet)
                    .setState(BottomSheetBehavior.STATE_SETTLING);

            ImageView imageExpand = dialog.findViewById(R.id.imageExpand);
            imageExpand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BottomSheetDialog d = (BottomSheetDialog) dialog;

                    // This is gotten directly from the source of BottomSheetDialog
                    // in the wrapInBottomSheet() method
                    FrameLayout bottomSheet = (FrameLayout) d.findViewById(R.id.design_bottom_sheet);

                    // Right here!

                    if (isExpanded) {
                        isExpanded = false;
                        BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_COLLAPSED);
                        imageExpand.setRotation(-90);
                    } else {
                        isExpanded = true;

                        BottomSheetBehavior.from(bottomSheet)
                                .setState(BottomSheetBehavior.STATE_EXPANDED);
                        imageExpand.setRotation(90);
                    }
                }
            });

            EditText etSearchLocationBottom = dialog.findViewById(R.id.etSearchLocationBottom);
            etSearchLocationBottom.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    if (s.toString().isEmpty()) {
                        Utility.response.responsedata.locationData.clear();
                        Utility.response.responsedata.locationData.addAll(originalLocations);
                        adapter.notifyDataSetChanged();
                        rvLocation.setVisibility(View.VISIBLE);
                        textNoLocation.setVisibility(View.GONE);

                    } else {

                        List<LocationDataModel> locations = new ArrayList<>();

                        for (LocationDataModel l : originalLocations) {

                            if (l.getLocationName().toLowerCase().contains(s.toString().toLowerCase())) {
                                locations.add(l);
                            }
                        }
                        Utility.response.responsedata.locationData.clear();
                        Utility.response.responsedata.locationData.addAll(locations);
                        adapter.notifyDataSetChanged();
                        Log.e("Test", "Result : " + locations.size());
                    }
                    if(Utility.response.responsedata.locationData.size() == 0)
                    {
                        rvLocation.setVisibility(View.GONE);
                        textNoLocation.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        rvLocation.setVisibility(View.VISIBLE);
                        textNoLocation.setVisibility(View.GONE);
                    }
                }
                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        } else
        {
            rvLocation.setVisibility(View.GONE);
            textNoLocation.setVisibility(View.VISIBLE);
        }


        // add a button
        // create and show the alert dialog

    }
    private void getLocations() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        GetAPIData service = RetrofitClientInstance.getRetrofitInstance().create(GetAPIData.class);

        Log.e("Request GetLocationData", "RP ID: " + Utility.response.responsedata.appDetails.rewardProgramId);
        Call<ResponseModel> callGetLocation =
                service.getLocationData(Utility.response.responsedata.appDetails.rewardProgramId);
        callGetLocation.enqueue(new Callback<ResponseModel>() {

            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();

                    if(response.code() == 200)
                    {
                        if(response.body() != null)
                        {

                            ResponseModel responseModel = response.body();
                            ResponsedataModel responseData = Utility.response.responsedata;

                            responseData.locationData = responseModel.responsedata.getLocationData();

                            originalLocations.addAll(responseData.locationData);
                            Log.e("GetLocationData", "onResponse - Location List Size: " + responseModel.responsedata.locationData.size());

                        }

                    }
                    else
                    {
                        Log.e("GetLocationData", "Status code - Location List Size: " + response.code() );

                    }

                } else {
                    progressDialog.dismiss();

                    Log.e("Test Error: ", "" + response.message());


                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("Test Error: ", "" + t.getMessage());


            }
        });
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.imgBackLocation){
            super.onBackPressed();
        }
        else if(v.getId() == R.id.cardLocation){
            showBottomsheet();
        }
    }
}