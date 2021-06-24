package com.example.rnsdk.Activities;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rnsdk.Adapter.FooterAdapter;
import com.example.rnsdk.Models.AppColorModel;
import com.example.rnsdk.Models.HomeScreenModel;
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

public class LocationActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    CardView cardLocation;
    BottomSheetBehavior bottomSheetBehavior;
    RecyclerView rvFooterLocation;
    ImageView imgBackLocation;
    TextView textPointLocation;

    LinearLayout bottomsheetLocation;

    @Override
    protected void onResume() {
        super.onResume();
        setFooter();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        init();


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


        imgBackLocation.setOnClickListener(this);
        cardLocation.setOnClickListener(this);

        textPointLocation.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderPointDigitColor()));
        textPointLocation.setText(String.valueOf(Utility.response.responsedata.contactData.getPointBalance())+ " PTS");


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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    private void showBottomsheet() {

        // create an alert builder
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.content_location_list, null);
        dialog.setContentView(customLayout);

        // add a button
        // create and show the alert dialog
        dialog.show();

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