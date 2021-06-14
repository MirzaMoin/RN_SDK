package com.example.rnsdk.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.example.rnsdk.Models.UploadReceiptChildPageDataModel;
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class UploadReceiptActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    List<SliderItem> mSliderItems = new ArrayList<>();
    ImageView imgBack;
    Button btnSubmit;
    EditText etLocation,etReceiptType;
    RelativeLayout relLocation,relReceiptType,relReceiptDate;
    CardView cardUploadImage1,cardUploadImage2,cardUploadImage3;
    TextView textReceiptDate;

    RecyclerView rvFooterUploadReceipt;

    AlertDialog dialogReceiptType,dialogLocation;

    LinearLayout linearRPGCashback,linearHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_receipt);

        init();
    }
    private void init() {
        rvFooterUploadReceipt = findViewById(R.id.rvFooterUploadReceipt);
        toolbar = findViewById(R.id.toolbarUpoadReceipt);
        imgBack = findViewById(R.id.imgBackUploadReceipt);
        relLocation = findViewById(R.id.relLocation);
        btnSubmit = findViewById(R.id.btnSubmitReceipt);
        relReceiptType = findViewById(R.id.relReceiptType);
        relReceiptDate = findViewById(R.id.relReceiptDate);
        cardUploadImage1 = findViewById(R.id.cardUploadImage1);
        textReceiptDate = findViewById(R.id.textReceiptDate);
//        linearRPGCashback = findViewById(R.id.linearRPGCashback);
//        linearHome = findViewById(R.id.linearHomeCashback);
        btnSubmit.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        relLocation.setOnClickListener(this);
        relReceiptType.setOnClickListener(this);
        relReceiptDate.setOnClickListener(this);
        cardUploadImage1.setOnClickListener(this);
//        linearRPGCashback.setOnClickListener(this);
//        linearHome.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle("");
            setSupportActionBar(toolbar);

        }
        ChildPageSettingModel childPageSettings = Utility.response.responsedata.childPageSetting;

        if(childPageSettings.isChildPageUploadReceipt()) {

            List<ChildPageModel> childPage = new ArrayList<>();
            for (UploadReceiptChildPageDataModel upload : childPageSettings.uploadReceiptChildPageData) {
                childPage.add(new ChildPageModel(upload.image, upload.opacity, upload.isClickable, upload.linkType, upload.internalLink, upload.externalLink));
            }

            SliderView sliderView = findViewById(R.id.imageSliderUploadReceipt);

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
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.relReceiptType)
        {
            showLocationDialog();

        }
        else  if(v.getId() == R.id.relLocation)
        {
            showLocationDialog();

        }
        else  if(v.getId() == R.id.relReceiptDate)
        {

            DatePickerDialog datePickerDialog = new DatePickerDialog(UploadReceiptActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            textReceiptDate.setText(""+day+"/"+(month+1)+"/"+year);

                        }
                    }, 2021, 0, 1);

            datePickerDialog.show();
        }
        else  if(v.getId() == R.id.cardUploadImage1)
        {
//           showLocationDialog();;
            showImagePicker();
        }
    }
    void showLocationDialog(){
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

    void showImagePicker(){
        // create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.content_upload_image, null);
        builder.setView(customLayout);
        // add a button
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void setFooter() {
        AppColorModel appColor = Utility.response.responsedata.appColor;

        HomeScreenModel homeScreenModel = Utility.response.responsedata.homeScreen;
        if(homeScreenModel.isHomePageDisplayFooter())
        {
            rvFooterUploadReceipt.setVisibility(View.VISIBLE);
            rvFooterUploadReceipt.setBackgroundColor(Utility.getColor(appColor.getFooterBarColor()));

            FooterAdapter adapter = new FooterAdapter(this,homeScreenModel.footerLinks,"transferPoint");
            rvFooterUploadReceipt.setHasFixedSize(true);


            rvFooterUploadReceipt.setLayoutManager(new GridLayoutManager(this,homeScreenModel.footerLinks.size()));

            rvFooterUploadReceipt.setAdapter(adapter);
        }
        else
        {
            rvFooterUploadReceipt.setVisibility(View.GONE);


        }


    }


}