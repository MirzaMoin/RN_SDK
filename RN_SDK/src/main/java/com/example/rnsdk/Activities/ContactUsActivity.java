package com.example.rnsdk.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;


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

public class ContactUsActivity extends AppCompatActivity implements OnMapReadyCallback {

    Toolbar toolbar;
    private GoogleMap mMap;
    RecyclerView rvFooterContactUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapContactUs);
        mapFragment.getMapAsync(this);

        init();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    private void init() {
        toolbar = findViewById(R.id.toolbarContactUs);
        rvFooterContactUs = findViewById(R.id.rvFooterContactUs);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle("");
            setSupportActionBar(toolbar);


        }

        setFooter();


    }
    private void setFooter() {
        AppColorModel appColor = Utility.response.responsedata.appColor;

        HomeScreenModel homeScreenModel = Utility.response.responsedata.homeScreen;
        if(homeScreenModel.isHomePageDisplayFooter())
        {
            rvFooterContactUs.setVisibility(View.VISIBLE);
            rvFooterContactUs.setBackgroundColor(Utility.getColor(appColor.getFooterBarColor()));

            FooterAdapter adapter = new FooterAdapter(this,homeScreenModel.footerLinks,"contactUs");
            rvFooterContactUs.setHasFixedSize(true);


            rvFooterContactUs.setLayoutManager(new GridLayoutManager(this,homeScreenModel.footerLinks.size()));

            rvFooterContactUs.setAdapter(adapter);
        }
        else
        {
            rvFooterContactUs.setVisibility(View.GONE);


        }


    }
}