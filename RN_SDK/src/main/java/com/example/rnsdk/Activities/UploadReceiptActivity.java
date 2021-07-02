package com.example.rnsdk.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rnsdk.API.GetAPIData;
import com.example.rnsdk.API.RetrofitClientInstance;
import com.example.rnsdk.Adapter.CashbackImageSliderAdapter;
import com.example.rnsdk.Adapter.DialogListAdapter;
import com.example.rnsdk.Adapter.FooterAdapter;
import com.example.rnsdk.Adapter.SliderItem;
import com.example.rnsdk.Models.AppColorModel;
import com.example.rnsdk.Models.ChildPageModel;
import com.example.rnsdk.Models.ChildPageSettingModel;
import com.example.rnsdk.Models.HomeScreenModel;
import com.example.rnsdk.Models.LocationDataModel;
import com.example.rnsdk.Models.ResponseModel;
import com.example.rnsdk.Models.ResponsedataModel;
import com.example.rnsdk.Models.URAddressModel;
import com.example.rnsdk.Models.URCategoryModel;
import com.example.rnsdk.Models.URSettingsDetailsModel;
import com.example.rnsdk.Models.UploadReceiptChildPageDataModel;
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadReceiptActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    List<SliderItem> mSliderItems = new ArrayList<>();
    ImageView imgBack,
            imageUploadImage1,
            imageAddImage1,
            imageUploadImage2,
            imageAddImage2,
            imageUploadImage3,
            imageAddImage3;
    Button btnSubmit;
    EditText etLocation, etReceiptType;


    TextInputLayout etlUrSubTotalBeforeTax,
            etlUrReceiptNumber;
    TextInputEditText etUrSubTotalBeforeTax,
            etUrReceiptNumber;
    RelativeLayout relLocation, relReceiptType, relReceiptDate;
    CardView cardUploadImage1, cardUploadImage2, cardUploadImage3;
    boolean isImage1 = false,isImage2 = false,isImage3 = false;
    TextView
            textPointUploadReceipt,
            textUrReceiptType,
            textUrPrimaryLocation,
            textUrReceiptDate;
    ProgressDialog progressDialog;

    private static final int CAMERA_REQUEST_1 = 1777;
    private static final int CAMERA_REQUEST_2 = 1888;
    private static final int CAMERA_REQUEST_3 = 1999;
    private static final int PICK_IMAGE_1 = 777;
    private static final int PICK_IMAGE_2 = 888;
    private static final int PICK_IMAGE_3 = 999;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;


    String sLocationID = "", sReceiptTypeID = "", sReceiptDate = "";
    boolean isError = false;
    int sReceiptTypeIndex = -1;
    int selectedImageIndex=0;

    RecyclerView rvFooterUploadReceipt;

    AlertDialog dialogReceiptType, dialogLocation;

    LinearLayout linearRPGCashback, linearHome;

    @Override
    protected void onResume() {
        super.onResume();
        setFooter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_receipt);

        init();
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
        rvFooterUploadReceipt = findViewById(R.id.rvFooterUploadReceipt);
        toolbar = findViewById(R.id.toolbarUpoadReceipt);
        imgBack = findViewById(R.id.imgBackUploadReceipt);
        relLocation = findViewById(R.id.relLocation);
        btnSubmit = findViewById(R.id.btnSubmitReceipt);
        relReceiptType = findViewById(R.id.relReceiptType);
        relReceiptDate = findViewById(R.id.relReceiptDate);
        cardUploadImage1 = findViewById(R.id.cardUploadImage1);
        cardUploadImage2 = findViewById(R.id.cardUploadImage2);
        cardUploadImage3 = findViewById(R.id.cardUploadImage3);

        textPointUploadReceipt = findViewById(R.id.textPointUploadReceipt);
        etUrSubTotalBeforeTax = findViewById(R.id.etUrSubTotalBeforeTax);
        etUrReceiptNumber = findViewById(R.id.etUrReceiptNumber);
        textUrReceiptType = findViewById(R.id.textUrReceiptType);
        textUrReceiptDate = findViewById(R.id.textUrReceiptDate);
        textUrPrimaryLocation = findViewById(R.id.textUrPrimaryLocation);

        etlUrSubTotalBeforeTax = findViewById(R.id.etlUrSubTotalBeforeTax);
        etlUrReceiptNumber = findViewById(R.id.etlUrReceiptNumber);
        imageUploadImage1 = findViewById(R.id.imageUploadImage1);
        imageAddImage1 = findViewById(R.id.imageAddImage1);
        imageUploadImage2 = findViewById(R.id.imageUploadImage2);
        imageAddImage2 = findViewById(R.id.imageAddImage2);
        imageUploadImage3 = findViewById(R.id.imageUploadImage3);
        imageAddImage3 = findViewById(R.id.imageAddImage3);


        textPointUploadReceipt.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderPointDigitColor()));
        textPointUploadReceipt.setText(Utility.getRoundData(Utility.response.responsedata.contactData.getPointBalance()) + " PTS");

        btnSubmit.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        relLocation.setOnClickListener(this);
        relReceiptType.setOnClickListener(this);
        relReceiptDate.setOnClickListener(this);
        cardUploadImage1.setOnClickListener(this);
        cardUploadImage2.setOnClickListener(this);
        cardUploadImage3.setOnClickListener(this);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle("");
            setSupportActionBar(toolbar);

        }
        ChildPageSettingModel childPageSettings = Utility.response.responsedata.childPageSetting;

        if (childPageSettings.isChildPageUploadReceipt()) {

            List<ChildPageModel> childPage = new ArrayList<>();
            for (UploadReceiptChildPageDataModel upload : childPageSettings.uploadReceiptChildPageData) {
                childPage.add(new ChildPageModel(upload.image, upload.opacity, upload.isClickable, upload.linkType, upload.internalLink, upload.externalLink));
            }

            SliderView sliderView = findViewById(R.id.imageSliderUploadReceipt);
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
        AppColorModel color = Utility.response.responsedata.appColor;
        btnSubmit.setBackgroundColor(Utility.getColor(color.getPrimaryButtonColor()));

        setFooter();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.relReceiptType) {
            showLocationDialog(false);

        } else if (v.getId() == R.id.relLocation) {
            if (sReceiptTypeIndex == -1) {
                Toast.makeText(this, "Please select receipt type", Toast.LENGTH_SHORT).show();
            } else {
                showLocationDialog(true);
            }

        } else if (v.getId() == R.id.relReceiptDate) {
            Date c = Calendar.getInstance().getTime();
            System.out.println("Current time => " + c);

            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            String formattedDate = df.format(c);

            DatePickerDialog datePickerDialog = new DatePickerDialog(UploadReceiptActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                            sReceiptDate = "" + day + "/" + (month + 1) + "/" + year;
                            textUrReceiptDate.setText("" + day + "/" + (month + 1) + "/" + year);

                        }
                    },  2021, c.getMonth(), c.getDay());

            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            if(Utility.response.responsedata.dateDetails.getReceiptsCountType().equals("Day"))
            {
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(Utility.response.responsedata.dateDetails.getReceiptsCount()));
            }
            else if(Utility.response.responsedata.dateDetails.getReceiptsCountType().equals("Month"))
            {
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(Utility.response.responsedata.dateDetails.getReceiptsCount() * 30));
            }
            else if(Utility.response.responsedata.dateDetails.getReceiptsCountType().equals("Week"))
            {
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(Utility.response.responsedata.dateDetails.getReceiptsCount() * 7));
            }
            datePickerDialog.show();
        } else if (v.getId() == R.id.cardUploadImage1) {
//           showLocationDialog();;
            showImagePicker(1);
        }
        else if (v.getId() == R.id.cardUploadImage2) {
//           showLocationDialog();;
            showImagePicker(2);
        }
        else if (v.getId() == R.id.cardUploadImage3) {
//           showLocationDialog();;
            showImagePicker(3);
        }
        else if (v.getId() == R.id.imgBackUploadReceipt) {
            super.onBackPressed();
        } else if (v.getId() == R.id.btnSubmitReceipt) {
            validate();
        }
    }

    private void validate() {
        Boolean isSubTotalBeforeTaxError = false,
                isReceiptNumberError = false;

        String urSubTotalBeforeTax, urReceiptNumber;
        urSubTotalBeforeTax = etUrSubTotalBeforeTax.getText().toString().trim();
        urReceiptNumber = etUrReceiptNumber.getText().toString().trim();


        if (urSubTotalBeforeTax.isEmpty()) {
            isSubTotalBeforeTaxError = true;
            setETError("Please Enter Display Subtotal Before Tax", etlUrSubTotalBeforeTax);
        } else {
            isSubTotalBeforeTaxError = false;
            removeError(etlUrSubTotalBeforeTax);
        }
        if (urReceiptNumber.isEmpty()) {
            isReceiptNumberError = true;
            setETError("Please Enter Receipt Number", etlUrReceiptNumber);

        } else {
            isReceiptNumberError = false;
            removeError(etlUrReceiptNumber);

        }
        if (sReceiptTypeIndex == -1) {
            isReceiptNumberError = true;

            Toast.makeText(this, "Please select receipt type", Toast.LENGTH_SHORT).show();
        } else if (sLocationID.isEmpty()) {
            isReceiptNumberError = true;

            Toast.makeText(this, "Please select location", Toast.LENGTH_SHORT).show();
        } else if (sReceiptDate.isEmpty()) {
            isReceiptNumberError = true;

            Toast.makeText(this, "Please select receipt date", Toast.LENGTH_SHORT).show();
        }


        if(isImage1 || isImage2 || isImage3)
        {
            if(!isSubTotalBeforeTaxError || isReceiptNumberError)
            {
                UploadReceipt();
            }
        }
        else
        {
            Toast.makeText(this, "please select at least one image to upload receipt", Toast.LENGTH_SHORT).show();
        }



    }

    private void setETError(String error, TextInputLayout textInputLayout) {
        textInputLayout.setError(error);
        textInputLayout.setDefaultHintTextColor(ColorStateList.valueOf(Utility.getColor("#ff0000ff")));
        textInputLayout.setStartIconTintList(ColorStateList.valueOf(Utility.getColor("#ff0000ff")));
    }

    private void removeError(TextInputLayout textInputLayout) {
        textInputLayout.setDefaultHintTextColor(ColorStateList.valueOf(Utility.getColor("#8f8f8fff")));
        textInputLayout.setStartIconTintList(ColorStateList.valueOf(Utility.getColor("#8f8f8fff")));
        textInputLayout.setErrorEnabled(false);
    }

    void showLocationDialog(boolean isLocation) {
        // create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.content_location_dialog, null);
        builder.setView(customLayout);
        // add a button
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
        Button btnConfirmLocation = dialog.findViewById(R.id.btnConfirmLocation);
        RecyclerView rv = dialog.findViewById(R.id.rvLocationDialog);

        if (isLocation) {
            DialogListAdapter adapter = new DialogListAdapter(UploadReceiptActivity.this, "URLocation", sReceiptTypeIndex);
            rv.setHasFixedSize(true);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(adapter);

            btnConfirmLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (URAddressModel data : Utility.response.responsedata.categories.get(sReceiptTypeIndex).addresses) {
                        if (data.isSelected()) {
                            textUrPrimaryLocation.setText(data.getLocationName());

                            sLocationID = data.getAddressID();

                            dialog.dismiss();
                        }
                    }
                    if (dialog.isShowing()) {
                        Toast.makeText(UploadReceiptActivity.this, "Select any receipt type", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else {
            DialogListAdapter adapter = new DialogListAdapter(UploadReceiptActivity.this, "URReceipt", -1);
            rv.setHasFixedSize(true);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(adapter);

            btnConfirmLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (URCategoryModel data : Utility.response.responsedata.categories) {
                        if (data.isSelected()) {
                            textUrReceiptType.setText(data.getName());
                            sReceiptTypeID = data.getId();
                            sReceiptTypeIndex = Utility.response.responsedata.categories.indexOf(data);
                            dialog.dismiss();

                        }
                    }
                    if (dialog.isShowing()) {
                        Toast.makeText(UploadReceiptActivity.this, "Select any receipt type", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }


    }

    void showImagePicker(int imageIndex) {
        selectedImageIndex = imageIndex;
        // create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.content_upload_image, null);
        builder.setView(customLayout);
        // add a button
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
        TextView textTakePhotoUI,textChooseFromLibraryUI,textCancelUI;
        textTakePhotoUI = dialog.findViewById(R.id.textTakePhotoUI);
        textChooseFromLibraryUI = dialog.findViewById(R.id.textChooseFromLibraryUI);
        textCancelUI = dialog.findViewById(R.id.textCancelUI);
        textTakePhotoUI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                if(selectedImageIndex == 1)
                {
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_1);
                }
                else if(selectedImageIndex == 2)
                {
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_2);
                }
                else if(selectedImageIndex == 3)
                {
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_3);
                }
            }
        });
        textCancelUI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        textChooseFromLibraryUI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                if(selectedImageIndex == 1)
                {
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_1);

                }
                else if(selectedImageIndex == 2)
                {
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_2);

                }
                else if(selectedImageIndex == 3)
                {
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_3);

                }


        /*        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

                startActivityForResult(chooserIntent, PICK_IMAGE_1);*/
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                if(selectedImageIndex == 1)
                {
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_1);
                }
                else if(selectedImageIndex == 2)
                {
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_2);
                }
                else if(selectedImageIndex == 3)
                {
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_3);
                }
//                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_1 && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            if(photo ==null)
            {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
            else
            {
                isImage1 = true;
                Glide.with(this).load(photo).into(imageUploadImage1);

                imageUploadImage1.setVisibility(View.VISIBLE);
                imageAddImage1.setVisibility(View.GONE);
            }

        }
        else   if (requestCode == CAMERA_REQUEST_2 && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            if(photo ==null)
            {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
            else
            {
                isImage2 = true;
                Glide.with(this).load(photo).into(imageUploadImage2);

                imageUploadImage2.setVisibility(View.VISIBLE);
                imageAddImage2.setVisibility(View.GONE);
            }

        }
        else   if (requestCode == CAMERA_REQUEST_3 && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            if(photo ==null)
            {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
            else
            {
                isImage3 = true;
                Glide.with(this).load(photo).into(imageUploadImage3);

                imageUploadImage3.setVisibility(View.VISIBLE);
                imageAddImage3.setVisibility(View.GONE);
            }

        }
        else   if (requestCode == PICK_IMAGE_1 && resultCode == Activity.RESULT_OK) {
            Uri selectedImageURI = data.getData();

            if(selectedImageURI != null)
            {
                isImage1 = true;
                Glide.with(this).load(selectedImageURI).into(imageUploadImage1);

                imageUploadImage1.setVisibility(View.VISIBLE);
                imageAddImage1.setVisibility(View.GONE);
            }

        }
        else   if (requestCode == PICK_IMAGE_2 && resultCode == Activity.RESULT_OK) {
            Uri selectedImageURI = data.getData();
            if(selectedImageURI != null) {
                isImage2 = true;

                Glide.with(this).load(selectedImageURI).into(imageUploadImage2);

                imageUploadImage2.setVisibility(View.VISIBLE);
                imageAddImage2.setVisibility(View.GONE);
            }

        }
        else   if (requestCode == PICK_IMAGE_3 && resultCode == Activity.RESULT_OK) {
            Uri selectedImageURI = data.getData();
            if(selectedImageURI != null) {
                isImage3 = true;
                Glide.with(this).load(selectedImageURI).into(imageUploadImage3);

                imageUploadImage3.setVisibility(View.VISIBLE);
                imageAddImage3.setVisibility(View.GONE);
            }


        }
    }
    private void UploadReceipt () {

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        GetAPIData service = RetrofitClientInstance.getRetrofitInstance().create(GetAPIData.class);

        ResponsedataModel responseData = Utility.response.responsedata;
        Call<JsonObject> callUploadReceipt = service.UploadReceipts(ApiJsonMap(responseData.contactData.contactID,
                responseData.appDetails.rewardProgramId,
                sLocationID,
                sReceiptTypeID,
                Double.parseDouble(etUrSubTotalBeforeTax.getText().toString().trim()),
                sReceiptDate,
                etUrReceiptNumber.getText().toString().trim()
        ));


        callUploadReceipt.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    if (response.body().get("statusCode").getAsInt() == 1) {

                        Log.e("Test", "Response : " + response.body().get("statusMessage").getAsString());
                        Log.e("Test", "Response : " + response.body().get("statusMessage").getAsString());

                        if(response.body().get("statusCode").getAsInt() == 1 && response.body() != null)
                        {
                            JsonElement responsedata = response.body().get("responsedata").getAsJsonObject();

                        }
                        else
                        {

                            // duplicate entry
                            Log.e("Test", "Response : " + response.body().get("statusMessage").getAsString());

                        }

                    } else {
                        progressDialog.dismiss();

//                        showAlertDialog(response.body().get("statusMessage").getAsString(), -1, "", "");
                        Log.e("Test", "Response : " + response.body().get("statusMessage").getAsString());
                    }


                } else {
                    Log.e("TEST", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable test) {
                progressDialog.dismiss();

                Log.e("Test:::", test.getMessage().toString());
            }
        });
    }
    private JsonObject ApiJsonMap(String contactID, String rewardProgramID, String addressID,String receiptCategoryID,double amount,
                                  String receiptDate, String receiptNumber) {

        JsonObject gsonObject = new JsonObject();
        try {
            JSONObject jsonObj_ = new JSONObject();
            jsonObj_.put("contactID", contactID);
            jsonObj_.put("rewardProgramID", rewardProgramID);
            jsonObj_.put("addressID", addressID);
            jsonObj_.put("receiptCategoryID", receiptCategoryID);
            jsonObj_.put("amount", amount);
            jsonObj_.put("receiptDate", receiptDate);
            jsonObj_.put("receiptNumber", receiptNumber);
            jsonObj_.put("checkStatusCode5", true);

            JsonParser jsonParser = new JsonParser();
            gsonObject = (JsonObject) jsonParser.parse(jsonObj_.toString());

            //print parameter
            Log.e("Request Body:  ", "" + gsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return gsonObject;
    }

    private void setFooter() {
        AppColorModel appColor = Utility.response.responsedata.appColor;

        HomeScreenModel homeScreenModel = Utility.response.responsedata.homeScreen;
        if (homeScreenModel.isHomePageDisplayFooter()) {
            rvFooterUploadReceipt.setVisibility(View.VISIBLE);
            rvFooterUploadReceipt.setBackgroundColor(Utility.getColor(appColor.getFooterBarColor()));

            FooterAdapter adapter = new FooterAdapter(this, homeScreenModel.footerLinks, "transferPoint");
            rvFooterUploadReceipt.setHasFixedSize(true);


            rvFooterUploadReceipt.setLayoutManager(new GridLayoutManager(this, homeScreenModel.footerLinks.size()));

            rvFooterUploadReceipt.setAdapter(adapter);
        } else {
            rvFooterUploadReceipt.setVisibility(View.GONE);


        }


    }

    private void getData() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        GetAPIData service = RetrofitClientInstance.getRetrofitInstance().create(GetAPIData.class);
        Log.e("Request", "RP ID: " + Utility.response.responsedata.appDetails.rewardProgramId + ", Contact ID: " + Utility.response.responsedata.contactData.contactID);
        Call<ResponseModel> call =
                service.getUploadReceiptsScreenData(Utility.response.responsedata.appDetails.rewardProgramId
                        , Utility.response.responsedata.appDetails.webFormID);
        call.enqueue(new Callback<ResponseModel>() {

            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful()) {

                    if (response.code() == 200) {
                        progressDialog.dismiss();
                        if (response.body() != null) {
                            ResponseModel responseModel = response.body();
                            ResponsedataModel responseData = Utility.response.responsedata;

                            Utility.response.responsedata.dateDetails = responseModel.responsedata.getDateDetails();
                            Utility.response.responsedata.categories = responseModel.responsedata.getCategories();
                            Utility.response.responsedata.settingsDetails = responseModel.responsedata.getSettingsDetails();

                            if (Utility.response.responsedata.settingsDetails != null) {
                                setLayout();
                            } else {
                                Log.e("Setting Details", " Setting Data is null");
                            }
                            Log.e("Test", "onResponse: " + responseModel.getStatusCode());

                        }
                    }
                } else {
                    progressDialog.dismiss();

                    Log.e("URScreenData Error: ", "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("Test Error: ", "" + t.getMessage());


            }
        });
    }

    private void setLayout() {
        URSettingsDetailsModel setting = Utility.response.responsedata.settingsDetails;

        if (!setting.isShowURSubTotalBeforeTax()) {
            etlUrSubTotalBeforeTax.setVisibility(View.GONE);
        } else {
            etlUrSubTotalBeforeTax.setHint(setting.getUrSubTotalBeforeTax());
        }
        if (!setting.isShowURReceiptType()) {
            relReceiptType.setVisibility(View.GONE);
        } else {
            textUrReceiptType.setText(setting.getUrReceiptType());
        }
        if (!setting.isShowURPrimaryLocation()) {
            relLocation.setVisibility(View.GONE);
        } else {
            textUrPrimaryLocation.setText(setting.getUrPrimaryLocation());
        }
        if (!setting.isShowURReceiptDate()) {
            relReceiptDate.setVisibility(View.GONE);
        } else {
            textUrReceiptDate.setText(setting.getUrReceiptDate());
        }
        if (!setting.isShowURReceiptNumber()) {
            etlUrReceiptNumber.setVisibility(View.GONE);
        } else {
            etlUrReceiptNumber.setHint(setting.getUrReceiptNumber());
        }


    }


}