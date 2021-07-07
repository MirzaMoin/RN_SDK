package com.example.robosdk.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
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
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.robosdk.API.GetAPIData;
import com.example.robosdk.API.RetrofitClientInstance;
import com.example.robosdk.Adapter.CashbackImageSliderAdapter;
import com.example.robosdk.Adapter.DialogListAdapter;
import com.example.robosdk.Adapter.FooterAdapter;
import com.example.robosdk.Adapter.SliderItem;
import com.example.robosdk.Models.AppColorModel;
import com.example.robosdk.Models.ChildPageModel;
import com.example.robosdk.Models.ChildPageSettingModel;
import com.example.robosdk.Models.HomeScreenModel;
import com.example.robosdk.Models.ResponseModel;
import com.example.robosdk.Models.ResponsedataModel;
import com.example.robosdk.Models.URAddressModel;
import com.example.robosdk.Models.URCategoryModel;
import com.example.robosdk.Models.URSettingsDetailsModel;
import com.example.robosdk.Models.UploadReceiptChildPageDataModel;
import com.example.robosdk.R;
import com.example.robosdk.Utility.PermissionUtils;
import com.example.robosdk.Utility.ScreenshotUtils;
import com.example.robosdk.Utility.Utility;
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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
            imageAddImage3,
            imageUR,
            imageLogoUR;

    TableLayout tableLayoutTP;

    String image1 = "", image2 = "", image3 = "";
    Button btnSubmit;

    TextInputLayout etlUrSubTotalBeforeTax,
            etlUrReceiptNumber;
    TextInputEditText etUrSubTotalBeforeTax,
            etUrReceiptNumber;
    RelativeLayout relLocation, relReceiptType, relReceiptDate, relLoadingUR;
    CardView cardUploadImage1, cardUploadImage2, cardUploadImage3;
    boolean isImage1 = false, isImage2 = false, isImage3 = false;
    TextView
            textPointUploadReceipt,
            textUrReceiptType,
            textUrPrimaryLocation,
            textUrReceiptDate;
    private static final int MY_PERMISSIONS_REQUEST_CODE = 123;

    boolean isOpen = false;
    private static final int CAMERA_REQUEST_1 = 1777;
    private static final int CAMERA_REQUEST_2 = 1888;
    private static final int CAMERA_REQUEST_3 = 1999;
    private static final int PICK_IMAGE_1 = 777;
    private static final int PICK_IMAGE_2 = 888;
    private static final int PICK_IMAGE_3 = 999;
    private ImageView imageView;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;
    private static final int STORAGE_PERMISSION_REQUEST_CODE = 101;

    AlertDialog imagePickerDialog;
    String sLocationID = "", sReceiptTypeID = "", sReceiptDate = "";
    boolean isError = false;
    int sReceiptTypeIndex = -1;
    int selectedImageIndex = 0;

    RecyclerView rvFooterUploadReceipt;

    AlertDialog dialogReceiptType, dialogLocation;

    LinearLayout linearRPGCashback, linearHome;


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
        relLoadingUR = findViewById(R.id.relLoadingUR);
        imageUR = findViewById(R.id.imageUR);
        imageLogoUR = findViewById(R.id.imageLogoUR);
        tableLayoutTP = findViewById(R.id.tableLayoutTP);
        tableLayoutTP.setBackgroundColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderBarColor()));

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

                            sReceiptDate = "" + year + "-" + (month + 1) + "-" + day;
                            String sDay, sMonth;
                            if ((month + 1) < 10) {
                                sMonth = "0" + month;
                            } else {
                                sMonth = "" + month;
                            }
                            if (day < 10) {
                                sDay = "0" + day;
                            } else {
                                sDay = "" + day;
                            }
                            textUrReceiptDate.setText("" + year + "-" + sMonth + "-" + sDay);

                        }
                    }, 2021, c.getMonth(), c.getDay());

            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            if (Utility.response.responsedata.dateDetails.getReceiptsCountType().equals("Day")) {
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(Utility.response.responsedata.dateDetails.getReceiptsCount()));
            } else if (Utility.response.responsedata.dateDetails.getReceiptsCountType().equals("Month")) {
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(Utility.response.responsedata.dateDetails.getReceiptsCount() * 30));
            } else if (Utility.response.responsedata.dateDetails.getReceiptsCountType().equals("Week")) {
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(Utility.response.responsedata.dateDetails.getReceiptsCount() * 7));
            }
            datePickerDialog.show();
        } else if (v.getId() == R.id.cardUploadImage1) {
//           showLocationDialog();;

            showImagePicker(1);
        } else if (v.getId() == R.id.cardUploadImage2) {
//           showLocationDialog();;
            showImagePicker(2);
        } else if (v.getId() == R.id.cardUploadImage3) {
//           showLocationDialog();;
            showImagePicker(3);
        } else if (v.getId() == R.id.imgBackUploadReceipt) {
            super.onBackPressed();
        } else if (v.getId() == R.id.btnSubmitReceipt) {
            validate();
        }
    }

    protected void checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager
                .PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (PermissionUtils.neverAskAgainSelected(this, Manifest.permission.CAMERA)) {
                    displayNeverAskAgainDialog(true);
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                            CAMERA_PERMISSION_REQUEST_CODE);
                }
            }
        }
        else
        {
            imagePickerDialog.dismiss();
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            if (selectedImageIndex == 1) {
                startActivityForResult(cameraIntent, CAMERA_REQUEST_1);
            } else if (selectedImageIndex == 2) {
                startActivityForResult(cameraIntent, CAMERA_REQUEST_2);
            } else if (selectedImageIndex == 3) {
                startActivityForResult(cameraIntent, CAMERA_REQUEST_3);
            }

        }
    }

    protected void checkStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager
                .PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (PermissionUtils.neverAskAgainSelected(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    displayNeverAskAgainDialog(false);
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            STORAGE_PERMISSION_REQUEST_CODE);
                }
            }
        }
        else
        {
            imagePickerDialog.dismiss();
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);

            if (selectedImageIndex == 1) {
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_1);

            } else if (selectedImageIndex == 2) {
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_2);

            } else if (selectedImageIndex == 3) {
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_3);

            }
        }
    }


    private void displayNeverAskAgainDialog(boolean isCamera) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(isCamera)
        {
            builder.setMessage("We need camera permission for performing necessary task. Please permit the permission through "
                    + "Settings screen.\n\nSelect Permissions -> Enable permission");
        }
        else
        {
            builder.setMessage("We need Storage permission for performing necessary task. Please permit the permission through "
                    + "Settings screen.\n\nSelect Permissions -> Enable permission");
        }

        builder.setCancelable(false);
        builder.setPositiveButton("Permit Manually", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent();
                intent.setAction(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }



    private void validate() {
        Boolean isSubTotalBeforeTaxError = false,
                isReceiptNumberError = false,
                otherErrors = false;

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
            otherErrors = true;

            Toast.makeText(this, "Please select receipt type", Toast.LENGTH_SHORT).show();
        } else if (sLocationID.isEmpty()) {
            otherErrors = true;

            Toast.makeText(this, "Please select location", Toast.LENGTH_SHORT).show();
        } else if (sReceiptDate.isEmpty()) {
            otherErrors = true;

            Toast.makeText(this, "Please select receipt date", Toast.LENGTH_SHORT).show();
        }


        if (!isSubTotalBeforeTaxError && !isReceiptNumberError && !otherErrors) {
            if (isImage1 || isImage2 || isImage3) {
                UploadReceipt();
            } else {
                Toast.makeText(this, "please select at least one image to upload receipt", Toast.LENGTH_SHORT).show();
            }
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
        EditText etSearchLocation = dialog.findViewById(R.id.etSearchLocation);

        if (isLocation) {
            DialogListAdapter adapter = new DialogListAdapter(UploadReceiptActivity.this, "URLocation", sReceiptTypeIndex);
            rv.setHasFixedSize(true);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(adapter);

            List<URAddressModel> originalLocations = new ArrayList<>();
            originalLocations.addAll(Utility.response.responsedata.categories.get(sReceiptTypeIndex).addresses);

            etSearchLocation.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    if (s.toString().isEmpty()) {
                        Utility.response.responsedata.categories.get(sReceiptTypeIndex).addresses.clear();
                        Utility.response.responsedata.categories.get(sReceiptTypeIndex).addresses.addAll(originalLocations);
                        adapter.notifyDataSetChanged();

                    } else {

                        List<URAddressModel> locations = new ArrayList<>();

                        for (URAddressModel l : originalLocations) {

                            if (l.getLocationName().toLowerCase().contains(s.toString().toLowerCase())) {
                                locations.add(l);
                            }
                        }
                        Utility.response.responsedata.categories.get(sReceiptTypeIndex).addresses.clear();
                        Utility.response.responsedata.categories.get(sReceiptTypeIndex).addresses.addAll(locations);
                        adapter.notifyDataSetChanged();
                        Log.e("Test", "Result : " + locations.size());
                    }


                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            etSearchLocation.setHint("Search location");
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
                    } else {
                        Utility.response.responsedata.categories.get(sReceiptTypeIndex).addresses.clear();
                        Utility.response.responsedata.categories.get(sReceiptTypeIndex).addresses.addAll(originalLocations);
                    }

                }
            });

        } else {
            DialogListAdapter adapter = new DialogListAdapter(UploadReceiptActivity.this, "URReceipt", -1);
            rv.setHasFixedSize(true);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(adapter);

            etSearchLocation.setHint("Search category");

            List<URCategoryModel> originalCategories = new ArrayList<>();
            originalCategories.addAll(Utility.response.responsedata.categories);

            etSearchLocation.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    if (s.toString().isEmpty()) {
                        Utility.response.responsedata.categories.clear();
                        Utility.response.responsedata.categories.addAll(originalCategories);
                        adapter.notifyDataSetChanged();

                    } else {

                        List<URCategoryModel> category = new ArrayList<>();
                        for (URCategoryModel l : originalCategories) {
                            if (l.getName().toLowerCase().contains(s.toString().toLowerCase())) {
                                category.add(l);
                            }
                        }
                        Utility.response.responsedata.categories.clear();
                        Utility.response.responsedata.categories.addAll(category);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });


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
                    } else {
                        Utility.response.responsedata.categories.clear();
                        Utility.response.responsedata.categories.addAll(originalCategories);

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
          imagePickerDialog = builder.create();
        imagePickerDialog.show();
        imagePickerDialog.setCancelable(false);
        TextView textTakePhotoUI, textChooseFromLibraryUI, textCancelUI;
        textTakePhotoUI = imagePickerDialog.findViewById(R.id.textTakePhotoUI);
        textChooseFromLibraryUI = imagePickerDialog.findViewById(R.id.textChooseFromLibraryUI);
        textCancelUI = imagePickerDialog.findViewById(R.id.textCancelUI);
        textTakePhotoUI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                     checkCameraPermission();
                }
                else
                {
                    imagePickerDialog.dismiss();
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    if (selectedImageIndex == 1) {
                        startActivityForResult(cameraIntent, CAMERA_REQUEST_1);
                    } else if (selectedImageIndex == 2) {
                        startActivityForResult(cameraIntent, CAMERA_REQUEST_2);
                    } else if (selectedImageIndex == 3) {
                        startActivityForResult(cameraIntent, CAMERA_REQUEST_3);
                    }

                }
                }
        });
        textCancelUI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 imagePickerDialog.dismiss();
            }
        });

        textChooseFromLibraryUI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    checkStoragePermission();
                }
                else
                {
                    imagePickerDialog.dismiss();
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);

                    if (selectedImageIndex == 1) {
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_1);

                    } else if (selectedImageIndex == 2) {
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_2);

                    } else if (selectedImageIndex == 3) {
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_3);

                    }

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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (CAMERA_PERMISSION_REQUEST_CODE == requestCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i("TEST", "Permission granted successfully");

                imagePickerDialog.dismiss();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                if (selectedImageIndex == 1) {
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_1);
                } else if (selectedImageIndex == 2) {
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_2);
                } else if (selectedImageIndex == 3) {
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_3);
                }

            } else {
                PermissionUtils.setShouldShowStatus(this, Manifest.permission.CAMERA);
            }
        }
        else if (STORAGE_PERMISSION_REQUEST_CODE == requestCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i("TEST", "Permission granted successfully");

                imagePickerDialog.dismiss();
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                if (selectedImageIndex == 1) {
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_1);

                } else if (selectedImageIndex == 2) {
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_2);

                } else if (selectedImageIndex == 3) {
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_3);

                }

            } else {
                PermissionUtils.setShouldShowStatus(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_1 && resultCode == Activity.RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            if (bitmap == null) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            } else {
                isImage1 = true;
                Glide.with(this).load(bitmap).into(imageUploadImage1);

                saveImage(bitmap, 1);
                imageUploadImage1.setVisibility(View.VISIBLE);
                imageAddImage1.setVisibility(View.GONE);
            }

        } else if (requestCode == CAMERA_REQUEST_2 && resultCode == Activity.RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            if (bitmap == null) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            } else {
                isImage2 = true;
                Glide.with(this).load(bitmap).into(imageUploadImage2);

                saveImage(bitmap, 2);

                imageUploadImage2.setVisibility(View.VISIBLE);
                imageAddImage2.setVisibility(View.GONE);
            }

        } else if (requestCode == CAMERA_REQUEST_3 && resultCode == Activity.RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            if (bitmap == null) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            } else {
                isImage3 = true;
                Glide.with(this).load(bitmap).into(imageUploadImage3);
                saveImage(bitmap, 3);

                imageUploadImage3.setVisibility(View.VISIBLE);
                imageAddImage3.setVisibility(View.GONE);
            }

        } else if (requestCode == PICK_IMAGE_1 && resultCode == Activity.RESULT_OK) {
            Uri selectedImageURI = data.getData();

            if (selectedImageURI != null) {
                isImage1 = true;
                Glide.with(this).load(selectedImageURI).into(imageUploadImage1);

                saveURIImage(selectedImageURI, 1);

                imageUploadImage1.setVisibility(View.VISIBLE);
                imageAddImage1.setVisibility(View.GONE);
            }

        } else if (requestCode == PICK_IMAGE_2 && resultCode == Activity.RESULT_OK) {
            Uri selectedImageURI = data.getData();
            if (selectedImageURI != null) {
                isImage2 = true;

                Glide.with(this).load(selectedImageURI).into(imageUploadImage2);

                saveURIImage(selectedImageURI, 2);

                imageUploadImage2.setVisibility(View.VISIBLE);
                imageAddImage2.setVisibility(View.GONE);
            }

        } else if (requestCode == PICK_IMAGE_3 && resultCode == Activity.RESULT_OK) {
            Uri selectedImageURI = data.getData();
            if (selectedImageURI != null) {
                isImage3 = true;
                Glide.with(this).load(selectedImageURI).into(imageUploadImage3);

                saveURIImage(selectedImageURI, 3);

                imageUploadImage3.setVisibility(View.VISIBLE);
                imageAddImage3.setVisibility(View.GONE);
            }


        }
    }

    private void saveImage(Bitmap bitmap, int image) {

        if (bitmap != null) {
            File saveFile = ScreenshotUtils.getMainDirectoryName(this);//get the path to save screenshot
            Date d = new Date();
            CharSequence s = DateFormat.format("MM_dd_yy_hh_mm_ss", d.getTime());
            File file1 = ScreenshotUtils.store(bitmap, "RoboRewards_" + s + ".jpg", saveFile);//save the screenshot to selected path
            if (file1.exists()) {
                if (image == 1) {
                    image1 = file1.getAbsolutePath();
                } else if (image == 2) {
                    image2 = file1.getAbsolutePath();
                } else {
                    image3 = file1.getAbsolutePath();
                }

            } else {
                Toast.makeText(this, "Please re upload image", Toast.LENGTH_SHORT).show();
            }
        } else
            //If bitmap is null show toast message
            Toast.makeText(this, "Please re upload image", Toast.LENGTH_SHORT).show();
    }

    private void saveURIImage(Uri selectedImageURI, int image) {
        Bitmap b = null;
        try {
            b = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageURI);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (b != null) {
            File saveFile = ScreenshotUtils.getMainDirectoryName(this);//get the path to save screenshot
            Date d = new Date();
            CharSequence s = DateFormat.format("MM_dd_yy_hh_mm_ss", d.getTime());
            File file1 = ScreenshotUtils.store(b, "RoboRewards_" + s + ".jpg", saveFile);//save the screenshot to selected path
            if (file1.exists()) {
                if (image == 1) {
                    image1 = file1.getAbsolutePath();
                } else if (image == 2) {
                    image2 = file1.getAbsolutePath();
                } else {
                    image3 = file1.getAbsolutePath();
                }

            } else {
                Toast.makeText(this, "Please re upload image", Toast.LENGTH_SHORT).show();
            }
        } else
            //If bitmap is null show toast message
            Toast.makeText(this, "Please re upload image", Toast.LENGTH_SHORT).show();
    }

    private void UploadReceipt() {

        Utility.showLoader(UploadReceiptActivity.this);

        GetAPIData service = RetrofitClientInstance.getRetrofitInstance().create(GetAPIData.class);

        ResponsedataModel responseData = Utility.response.responsedata;
        Log.e("Requested API: ", "/api/UserProfile/UploadReceipts");
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

                        if (response.body().get("statusCode").getAsInt() == 1 && response.body() != null) {
                            JsonElement responsedata = response.body().get("responsedata");

                            UploadImage(responsedata.getAsJsonObject().get("receiptUploadID").getAsString());

                        } else {

                            Utility.showAlertDialog(UploadReceiptActivity.this, "Oops...", response.body().get("statusMessage").getAsString());
                            // duplicate entry
                            Log.e("Test", "Response : " + response.body().get("statusMessage").getAsString());

                        }

                    } else {
                        Utility.dialog.dismiss();
                        Utility.showAlertDialog(UploadReceiptActivity.this, "Oops...", response.body().get("statusMessage").getAsString());

                        Log.e("Test", "Response : " + response.body().get("statusMessage").getAsString());
                    }
                } else {
                    Utility.showAlertDialog(UploadReceiptActivity.this, "Oops...", "Something went wrong");

                    Log.e("TEST", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable test) {
                Utility.dialog.dismiss();

                Utility.showAlertDialog(UploadReceiptActivity.this, "Oops...", "Something went wrong");

                Log.e("Test:::", test.getMessage().toString());
            }
        });
    }

    private void UploadImage(String receiptUploadID) {

        ArrayList<String> filePaths = new ArrayList<>();
        if (!image1.isEmpty()) {
            filePaths.add(image1);
        }
        if (!image2.isEmpty()) {
            filePaths.add(image2);
        }
        if (!image3.isEmpty()) {
            filePaths.add(image3);
        }


        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);

        builder.addFormDataPart("RewardProgramID", Utility.response.responsedata.appDetails.rewardProgramId);
        builder.addFormDataPart("ReceiptUploadID", receiptUploadID);

        // Map is used to multipart the file using okhttp3.RequestBody
        // Multiple Images
        for (int i = 0; i < filePaths.size(); i++) {
            File file = new File(filePaths.get(i));
            builder.addFormDataPart("files", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
        }
        GetAPIData service = RetrofitClientInstance.getRetrofitInstance().create(GetAPIData.class);


        MultipartBody requestBody = builder.build();
        Call<ResponseModel> call = service.uploadReceiptImage(requestBody);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                Utility.dialog.dismiss();

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getStatusCode() == 1) {
                            if (!image1.isEmpty()) {
                                File file1 = new File(image1);
                                if (file1.exists())
                                    file1.delete();
                            }
                            if (!image2.isEmpty()) {
                                File file2 = new File(image2);
                                if (file2.exists())
                                    file2.delete();
                            }

                            if (!image3.isEmpty()) {
                                File file3 = new File(image3);
                                if (file3.exists())
                                    file3.delete();
                            }
                            setLayout();

                            Utility.showAlertDialog(UploadReceiptActivity.this, "Success", response.body().getStatusMessage());
                        } else {

                            Utility.showAlertDialog(UploadReceiptActivity.this, "Oops...", response.body().getStatusMessage());

                        }
                    }

                }

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Utility.dialog.dismiss();

                Utility.showAlertDialog(UploadReceiptActivity.this, "Oops...", "Something went wrong");

                Log.d("TEST", "Error " + t.getMessage());
            }
        });

    }

    private JsonObject ApiJsonMap(String contactID, String rewardProgramID, String addressID, String receiptCategoryID, double amount,
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
        relLoadingUR.setVisibility(View.VISIBLE);
        Glide.with(this).load(Utility.response.responsedata.appIntakeImages.loadingImages.get(0).imageUrl).into(imageUR);
        Glide.with(this).load(Utility.response.responsedata.appIntakeImages.companyLogo).into(imageLogoUR);


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
                        relLoadingUR.setVisibility(View.GONE);

                        if (response.body() != null) {
                            ResponseModel responseModel = response.body();
                            ResponsedataModel responseData = Utility.response.responsedata;

                            Utility.response.responsedata.dateDetails = responseModel.responsedata.getDateDetails();
                            Utility.response.responsedata.categories = responseModel.responsedata.getCategories();
                            Utility.response.responsedata.settingsDetails = responseModel.responsedata.getSettingsDetails();

                            if (Utility.response.responsedata.settingsDetails != null) {
                                setLayout();
                            } else {
                                Utility.showAlertDialog(UploadReceiptActivity.this, "Oops...", "Something went wrong");

                                Log.e("Setting Details", " Setting Data is null");
                            }
                            Log.e("Test", "onResponse: " + responseModel.getStatusCode());

                        }
                    } else {
                        Utility.showAlertDialog(UploadReceiptActivity.this, "Oops...", "Something went wrong");
                    }
                } else {
                    relLoadingUR.setVisibility(View.GONE);

                    Utility.showAlertDialog(UploadReceiptActivity.this, "Oops...", "Something went wromg");

                    Log.e("URScreenData Error: ", "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                relLoadingUR.setVisibility(View.GONE);

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

        image1 = "";
        image2 = "";
        image3 = "";

        imageUploadImage1.setVisibility(View.GONE);
        imageAddImage1.setVisibility(View.VISIBLE);

        imageUploadImage2.setVisibility(View.GONE);
        imageAddImage2.setVisibility(View.VISIBLE);

        imageUploadImage3.setVisibility(View.GONE);
        imageAddImage3.setVisibility(View.VISIBLE);

        etUrReceiptNumber.setText("");
        etUrSubTotalBeforeTax.setText("");


    }


}