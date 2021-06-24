package com.example.rnsdk.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rnsdk.Adapter.CashbackImageSliderAdapter;
import com.example.rnsdk.Adapter.DialogListAdapter;
import com.example.rnsdk.Adapter.FooterAdapter;
import com.example.rnsdk.Adapter.SliderItem;
import com.example.rnsdk.Models.AppColorModel;
import com.example.rnsdk.Models.ChildPageModel;
import com.example.rnsdk.Models.ChildPageSettingModel;
import com.example.rnsdk.Models.HomeScreenModel;
import com.example.rnsdk.Models.ProfileEditChildPageDataModel;
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    List<SliderItem> mSliderItems = new ArrayList<>();
    RelativeLayout relLocation, relGender, relAllowEmail, relAllowSMS, relPreferMedia, relAnnProfile, relBirthdate;
    TextView textGender, textAllowEmail, textAllowSMS, textPreferMedia, textBirthdate, textAnn,textPointProfile;
    ImageView imgBackProfile;

    Button btnSaveProfile;
    RecyclerView rvFooterProfile;

    @Override
    protected void onResume() {
        super.onResume();
        setFooter();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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
        SliderView sliderView = findViewById(R.id.imageSliderProfile);
        rvFooterProfile = findViewById(R.id.rvFooterProfile);
        btnSaveProfile = findViewById(R.id.btnSaveProfile);
        textPointProfile = findViewById(R.id.textPointProfile);

        textPointProfile.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderPointDigitColor()));
        textPointProfile.setText(String.valueOf(Utility.response.responsedata.contactData.getPointBalance())+ " PTS");

        ChildPageSettingModel childPageSettings = Utility.response.responsedata.childPageSetting;
        if (childPageSettings.isChildPageProfileEdit()) {
            sliderView.setVisibility(View.VISIBLE);


            List<ChildPageModel> childPage = new ArrayList<>();
            for (ProfileEditChildPageDataModel profile : childPageSettings.profileEditChildPageData) {
                childPage.add(new ChildPageModel(profile.image, profile.opacity, profile.isClickable, profile.linkType, profile.internalLink, profile.externalLink));
            }


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

        imgBackProfile = findViewById(R.id.imgBackProfile);
        relLocation = findViewById(R.id.relLocationProfile);
        relGender = findViewById(R.id.relGenderProfile);
        relAllowEmail = findViewById(R.id.relAllowEmail);
        relAllowSMS = findViewById(R.id.relAllowSMS);
        relPreferMedia = findViewById(R.id.relPreferMedia);
        textGender = findViewById(R.id.textGender);
        textAllowEmail = findViewById(R.id.textAllowEmail);
        textAllowSMS = findViewById(R.id.textAllowSMS);
        textPreferMedia = findViewById(R.id.textPreferMedia);
        relAnnProfile = findViewById(R.id.relAnnProfile);
        relBirthdate = findViewById(R.id.relBirthdate);
        textBirthdate = findViewById(R.id.textBirthdate);
        textAnn = findViewById(R.id.textAnn);

        relLocation.setOnClickListener(this);
        relGender.setOnClickListener(this);
        relAllowSMS.setOnClickListener(this);
        relAllowEmail.setOnClickListener(this);
        relPreferMedia.setOnClickListener(this);
        relAnnProfile.setOnClickListener(this);
        relBirthdate.setOnClickListener(this);
        textAnn.setOnClickListener(this);
        imgBackProfile.setOnClickListener(this);

        AppColorModel color = Utility.response.responsedata.appColor;
        btnSaveProfile.setBackgroundColor(Utility.getColor(color.getPrimaryButtonColor()));

        setFooter();
    }

    private void setFooter() {
        AppColorModel appColor = Utility.response.responsedata.appColor;

        HomeScreenModel homeScreenModel = Utility.response.responsedata.homeScreen;
        if (homeScreenModel.isHomePageDisplayFooter()) {
            rvFooterProfile.setVisibility(View.VISIBLE);
            rvFooterProfile.setBackgroundColor(Utility.getColor(appColor.getFooterBarColor()));

            FooterAdapter adapter = new FooterAdapter(this, homeScreenModel.footerLinks, "profileScreen");
            rvFooterProfile.setHasFixedSize(true);


            rvFooterProfile.setLayoutManager(new GridLayoutManager(this, homeScreenModel.footerLinks.size()));

            rvFooterProfile.setAdapter(adapter);
        } else {
            rvFooterProfile.setVisibility(View.GONE);


        }


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.relLocationProfile) {
            showLocationDialog();
        } else if (v.getId() == R.id.relAllowEmail) {
            PopupMenu popup = new PopupMenu(ProfileActivity.this, relAllowEmail);
            popup.getMenuInflater().inflate(R.menu.popup_menu_yes_no, popup.getMenu());

            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    textAllowEmail.setText(item.getTitle());

                    return true;
                }
            });

            popup.show();//showing popup menu
        } else if (v.getId() == R.id.relAllowSMS) {
            //Creating the instance of PopupMenu
            PopupMenu popup = new PopupMenu(ProfileActivity.this, relAllowSMS);
            //Inflating the Popup using xml file
            popup.getMenuInflater().inflate(R.menu.popup_menu_yes_no, popup.getMenu());

            //registering popup with OnMenuItemClickListener
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    textAllowSMS.setText(item.getTitle());

                    return true;
                }
            });

            popup.show();//showing popup menu
        } else if (v.getId() == R.id.relPreferMedia) {
            //Creating the instance of PopupMenu
            PopupMenu popup = new PopupMenu(ProfileActivity.this, relPreferMedia);
            //Inflating the Popup using xml file
            popup.getMenuInflater().inflate(R.menu.popup_menu_prefer_media, popup.getMenu());

            //registering popup with OnMenuItemClickListener
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    textPreferMedia.setText(item.getTitle());
                    return true;
                }
            });

            popup.show();//showing popup menu
        } else if (v.getId() == R.id.relGenderProfile) {
            //Creating the instance of PopupMenu
            PopupMenu popup = new PopupMenu(ProfileActivity.this, relGender);
            //Inflating the Popup using xml file
            popup.getMenuInflater().inflate(R.menu.popup_menu_gender, popup.getMenu());

            //registering popup with OnMenuItemClickListener
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    textGender.setText(item.getTitle());

                    return true;
                }
            });

            popup.show();//showing popup menu
        } else if (v.getId() == R.id.relAnnProfile) {


            DatePickerDialog datePickerDialog = new DatePickerDialog(ProfileActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            textAnn.setText("" + day + "/" + (month + 1) + "/" + year);

                        }
                    }, 2021, 0, 1);

            datePickerDialog.show();
        } else if (v.getId() == R.id.relBirthdate) {


            DatePickerDialog datePickerDialog = new DatePickerDialog(ProfileActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            textBirthdate.setText("" + day + "/" + (month + 1) + "/" + year);

                        }
                    }, 2021, 0, 1);

            datePickerDialog.show();
        } else if (v.getId() == R.id.imgBackProfile) {
            super.onBackPressed();
        }
    }

    void showLocationDialog() {
        // create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.content_location_dialog, null);
        builder.setView(customLayout);

        // add a button
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
        RecyclerView rv = dialog.findViewById(R.id.rvLocationDialog);
        DialogListAdapter adapter = new DialogListAdapter();
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }


}