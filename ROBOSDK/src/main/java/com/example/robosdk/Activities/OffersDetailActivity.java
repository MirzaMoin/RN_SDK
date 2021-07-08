package com.example.robosdk.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.robosdk.API.GetAPIData;
import com.example.robosdk.API.RetrofitClientInstance;
import com.example.robosdk.Adapter.DialogListAdapter;
import com.example.robosdk.Constants;
import com.example.robosdk.Models.AppColorModel;
import com.example.robosdk.Models.LocationDataModel;
import com.example.robosdk.Models.OfferAddressDetailsModel;
import com.example.robosdk.Models.OfferListModel;
import com.example.robosdk.Models.OfferUserDetailsModel;
import com.example.robosdk.Models.ResponsedataModel;
import com.example.robosdk.R;
import com.example.robosdk.Utility.ScreenshotUtils;
import com.example.robosdk.Utility.Utility;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OffersDetailActivity extends AppCompatActivity implements View.OnClickListener {
    String barcodeData;
    ImageView imgback,
            imageOfferImageDetail,
            imageBarcode;
    TextView textPointOfferDetail,
            textOfferTitleDetail,
            textOfferDescriptionDetail,
            textOfferTypeDetail,
            textOfferExpireDetail,
            textRedeemOfferDetail,
            textPrintOfferDetail,
            textInternalDetail,
            textMobilePhoneDetail,
            textMemberCardID,
            textOfferIDDetail,
            textNameDetail,
            textAddressDetail,
            textCityStateZip,
            textBusinessPhone,
            textWebsiteURL;
    DialogListAdapter adapter;
    View viewOfferDetail;

    ConstraintLayout rootLayout;

    TableLayout tableLayoutOfferDetails;


    OfferListModel currentOffer = null;
    List<LocationDataModel> originalLocations = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.myLibTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers_detail);

        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
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
        imgback = findViewById(R.id.imgBackOfferDetail);
        rootLayout = findViewById(R.id.rootConLayout);
        textPointOfferDetail = findViewById(R.id.textPointOfferDetail);
        textOfferTitleDetail = findViewById(R.id.textOfferTitleDetail);
        textOfferDescriptionDetail = findViewById(R.id.textOfferDescriptionDetail);
        textOfferTypeDetail = findViewById(R.id.textOfferTypeDetail);
        textOfferExpireDetail = findViewById(R.id.textOfferExpireDetail);
        textRedeemOfferDetail = findViewById(R.id.textRedeemOfferDetail);
        textPrintOfferDetail = findViewById(R.id.textPrintOfferDetail);
        imageOfferImageDetail = findViewById(R.id.imageOfferImageDetail);
        textInternalDetail = findViewById(R.id.textInternalDetail);
        textMobilePhoneDetail = findViewById(R.id.textMobilePhoneDetail);
        textMemberCardID = findViewById(R.id.textMemberCardID);
        textOfferIDDetail = findViewById(R.id.textOfferIDDetail);
        viewOfferDetail = findViewById(R.id.viewOfferDetail);
        textNameDetail = findViewById(R.id.textNameDetail);
        textAddressDetail = findViewById(R.id.textAddressDetail);
        textCityStateZip = findViewById(R.id.textCityStateZip);
        textBusinessPhone = findViewById(R.id.textBusinessPhone);
        textWebsiteURL = findViewById(R.id.textWebsiteURL);
        imageBarcode = findViewById(R.id.imageBarcode);
        tableLayoutOfferDetails = findViewById(R.id.tableLayoutOfferDetails);
        tableLayoutOfferDetails.setBackgroundColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderBarColor()));


        imgback.setOnClickListener(this);
        textPointOfferDetail.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderPointDigitColor()));
        textPointOfferDetail.setText(Utility.getRoundData(Utility.response.responsedata.contactData.getPointBalance()) + " PTS");

        setLayout();

    }

    public void onWindowFocusChanged(boolean hasFocus) {
        // TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            barCode(barcodeData);
        }

    }

    private void setLayout() {
        Intent i = getIntent();
        String offerSendID = i.getStringExtra("offerSendID");


        Log.e("Test", "Offer Send ID: " + offerSendID);


        for (OfferListModel offer : Utility.response.responsedata.offerList) {
            if (offer.getOfferSendID().equals(offerSendID)) {
                currentOffer = offer;

            }
        }
        AppColorModel color = Utility.response.responsedata.appColor;
        final OfferUserDetailsModel userDetails = Utility.response.responsedata.getUserDetails();


        Glide.with(this).load(currentOffer.getOfferImage()).into(imageOfferImageDetail);
        textOfferTitleDetail.setText(currentOffer.getOfferTitle());
        textOfferTitleDetail.setTextColor(Utility.getColor(color.getTitleTextColor()));
        textOfferDescriptionDetail.setTextColor(Utility.getColor(color.getTitleTextColor()));
        textOfferDescriptionDetail.setText(currentOffer.getOfferDescription());
        textOfferTypeDetail.setText(currentOffer.getOfferType());
        textOfferExpireDetail.setText(currentOffer.getOfferExpire());
        textOfferExpireDetail.setTextColor(Utility.getColor(color.getTitleTextColor()));
        textInternalDetail.setText("Internal Use Only: " + currentOffer.getOfferBarcode());
        textInternalDetail.setTextColor(Utility.getColor(color.getTitleTextColor()));

        textMobilePhoneDetail.setText("Mobile: " + userDetails.getMobilePhone());
        textMobilePhoneDetail.setTextColor(Utility.getColor(color.getTitleTextColor()));
        textMemberCardID.setText("CardID: " + userDetails.getMemberCardID());
        textMemberCardID.setTextColor(Utility.getColor(color.getTitleTextColor()));
        textOfferIDDetail.setText("Offer ID: " + String.valueOf(currentOffer.getOfferID()));
        textOfferIDDetail.setTextColor(Utility.getColor(color.getTitleTextColor()));


        textNameDetail.setTextColor(Utility.getColor(color.getTitleTextColor()));
        textAddressDetail.setTextColor(Utility.getColor(color.getTitleTextColor()));
        textCityStateZip.setTextColor(Utility.getColor(color.getTitleTextColor()));
        textBusinessPhone.setTextColor(Utility.getColor(color.getLocationsLinkColor()));

        textWebsiteURL.setTextColor(Utility.getColor(color.getLocationsLinkColor()));


        final OfferAddressDetailsModel addressDetails = Utility.response.responsedata.getAddressDetails();

        textNameDetail.setText(addressDetails.getName());
        textAddressDetail.setText(addressDetails.getAddress());
        textCityStateZip.setText(addressDetails.getCity() + " " + addressDetails.getState() + " " + addressDetails.getZipCode());
        textBusinessPhone.setText(addressDetails.getBusinessPhone());
        textWebsiteURL.setText(addressDetails.getWebsiteURL());


        if (currentOffer.isDisplayPrintButton()) {
            textPrintOfferDetail.setVisibility(View.VISIBLE);
        }
        if (currentOffer.isAllowContactRedeemOffer()) {
            textRedeemOfferDetail.setVisibility(View.VISIBLE);
        }
        if (currentOffer.isDisplayPrintButton() && currentOffer.isAllowContactRedeemOffer()) {
            viewOfferDetail.setVisibility(View.VISIBLE);
        }
        barcodeData = currentOffer.getOfferBarcode();

        textBusinessPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + addressDetails.getBusinessPhone()));
                startActivity(intent);
            }
        });

        textWebsiteURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = addressDetails.getWebsiteURL();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        textPrintOfferDetail.setOnClickListener(this);

        textRedeemOfferDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog(Utility.response.responsedata.redeemSetting.getRedeemOfferInstruction(), currentOffer.getOfferID(), currentOffer.getOfferSendID(), userDetails.getAddressID());
            }
        });

    }

    public void barCode(String data) {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        Log.e("Test", "Height: " + imageBarcode.getHeight() + ", Width: " + imageBarcode.getWidth());
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(data, BarcodeFormat.CODE_128, imageBarcode.getWidth(), imageBarcode.getHeight());
            Bitmap bitmap = Bitmap.createBitmap(imageBarcode.getWidth(), imageBarcode.getHeight(), Bitmap.Config.RGB_565);
            for (int i = 0; i < imageBarcode.getWidth(); i++) {
                for (int j = 0; j < imageBarcode.getHeight(); j++) {
                    bitmap.setPixel(i, j, bitMatrix.get(i, j) ? Color.BLACK : Color.WHITE);
                }
            }
            imageBarcode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgBackOfferDetail) {
            super.onBackPressed();
        } else if (v.getId() == R.id.textPrintOfferDetail) {
            Log.e("Test", "Print Clicked");
            takeScreenshot();
//                screenshot(getWindow().getDecorView().getRootView(), "result");
        }
    }

    protected File screenshot(View view, String filename) {
        Date date = new Date();
        Log.e("Test", "reach 0");

        // Here we are initialising the format of our image name
        CharSequence format = android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", date);
        try {
            Log.e("Test", "reach 1");

            // Initialising the directory of storage
            String dirpath = /*Environment.getExternalStorageDirectory().getParent()*/ "/storage/emulated/0/DCIM/" + "";
            File file = new File(dirpath);
            if (!file.exists()) {
                boolean mkdir = file.mkdir();
            }
            Log.e("Test", "reach 2");

            // File name
            String path = dirpath + "/" + filename + "-" + format + ".jpeg";
            view.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);
            File imageurl = new File(path);
            FileOutputStream outputStream = new FileOutputStream(imageurl);
            Log.e("Test", "reach 3");

            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
            Log.e("Test", "reach 4");

            outputStream.flush();
            outputStream.close();
            Log.e("Test", "reach 5");

            Toast.makeText(this, "Offer save to gallery", Toast.LENGTH_SHORT).show();
            return imageurl;

        } catch (FileNotFoundException io) {
            io.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*  Method which will take screenshot on Basis of Screenshot Type ENUM  */
    private void takeScreenshot() {
        Bitmap b = null;

        b = ScreenshotUtils.getScreenShot(rootLayout);

        //If bitmap is not null
        if (b != null) {
            showScreenShotImage(b);//show bitmap over imageview

            File saveFile = ScreenshotUtils.getMainDirectoryName(this);//get the path to save screenshot
            Date d = new Date();
            CharSequence s = DateFormat.format("MM_dd_yy_hh_mm_ss", d.getTime());
            File file = ScreenshotUtils.store(b, "RoboRewards_" + s + ".jpg", saveFile);//save the screenshot to selected path
            if (file.exists()) {
                moveImage(saveFile.getPath() + "/", file.getName(), Environment.getExternalStoragePublicDirectory("DCIM").getPath() + "/");
                Toast.makeText(this, "Image saved to gallery", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        } else
            //If bitmap is null show toast message
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
    }

    /*  Show screenshot Bitmap */
    private void showScreenShotImage(Bitmap b) {
//        imageView.setImageBitmap(b);
    }

    void showAlertDialog(String message, final int offerID, final String offerSendID, final String addressID) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final View customLayout = getLayoutInflater().inflate(R.layout.content_alert_dialog, null);
        builder.setView(customLayout);

        final AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();

        TextView textMessage, textOk, textCancel, textTitleAlert;
        textTitleAlert = dialog.findViewById(R.id.textTitleAlert);
        textMessage = dialog.findViewById(R.id.textMessageAlert);
        textOk = dialog.findViewById(R.id.textOKAlert);
        textCancel = dialog.findViewById(R.id.textCancelAlert);

        if (offerID == -1) {
            textTitleAlert.setText("Opps...");
        }
        if (!offerSendID.isEmpty()) {
            textTitleAlert.setText("Redeem Offer");
            textOk.setText("REDEEM");
            textCancel.setText("CANCEL");
            textCancel.setVisibility(View.VISIBLE);
        } else {
            textOk.setText("OKAY");

        }

        textMessage.setText(message);

        textCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        textOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                if (!offerSendID.isEmpty()) {
                    if (Utility.response.responsedata.redeemSetting.isAskWhereAreYou()) {
                        originalLocations.addAll(Utility.response.responsedata.locationData);

                        showLocationDialog(offerID, offerSendID);
                    } else {
                        RedeemOffer(offerID, offerSendID, addressID);
                    }
                } else if (offerID == -1) {
                    dialog.dismiss();
                } else {
                    Constants.isOfferRedeem = true;
                    OffersDetailActivity.super.onBackPressed();
                }

            }
        });


    }

    private void showLocationDialog(final int offerID, final String offerSendID) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final View customLayout = getLayoutInflater().inflate(R.layout.content_location_dialog, null);
        builder.setView(customLayout);

        final AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();


        EditText etSearchLocation = dialog.findViewById(R.id.etSearchLocation);
        RecyclerView rvLocationDialog = dialog.findViewById(R.id.rvLocationDialog);
        Button btnConfirmLocation = dialog.findViewById(R.id.btnConfirmLocation);


        etSearchLocation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().isEmpty()) {
                    Utility.response.responsedata.locationData.clear();
                    Utility.response.responsedata.locationData.addAll(originalLocations);
                    adapter.notifyDataSetChanged();

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


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        etSearchLocation.setHint("Search Location");

        adapter = new DialogListAdapter(this, "Location", -1);
        rvLocationDialog.setLayoutManager(new LinearLayoutManager(this));
        rvLocationDialog.setAdapter(adapter);

        btnConfirmLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (LocationDataModel data : Utility.response.responsedata.locationData) {
                    if (data.isSelected()) {
                        dialog.dismiss();
                        RedeemOffer(offerID, offerSendID, data.addressId);
                    }
                }
                if (dialog.isShowing()) {
                    Toast.makeText(OffersDetailActivity.this, "Select any location", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void RedeemOffer(int offerID, String offerSendID, String addressID) {
        Utility.showLoader(OffersDetailActivity.this);

        GetAPIData service = RetrofitClientInstance.getRetrofitInstance().create(GetAPIData.class);

        ResponsedataModel responseData = Utility.response.responsedata;
        Call<JsonObject> callRedeemOffer = service.RedeemOffer(ApiJsonMap(responseData.appDetails.rewardProgramId,
                responseData.appDetails.webFormID,
                responseData.contactData.contactID,
                offerID,
                offerSendID,
                addressID

        ));


        callRedeemOffer.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                Utility.dialog.dismiss();
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    if (response.body().get("statusCode").getAsInt() == 1) {

                        Log.e("Test", "Response : " + response.body().get("statusMessage").getAsString());
                        JsonElement responsedata = response.body().get("responsedata").getAsJsonObject();
//
                        Utility.response.responsedata.contactData.setPointBalance(responsedata.getAsJsonObject().get("reedemablePoints").getAsDouble());
                        Utility.response.responsedata.contactData.setReedemablePoints(responsedata.getAsJsonObject().get("reedemablePoints").getAsDouble());
                        textPointOfferDetail.setText(Utility.getRoundData(Utility.response.responsedata.contactData.getPointBalance()) + " PTS");

                        showAlertDialog(response.body().get("statusMessage").getAsString(), 0, "", "");

                    } else {
                        showAlertDialog(response.body().get("statusMessage").getAsString(), -1, "", "");
                        Log.e("Test", "Response : " + response.body().get("statusMessage").getAsString());

                    }


                } else {
                    Log.e("TEST", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable test) {
                Utility.dialog.dismiss();
                Log.e("Test:::", test.getMessage().toString());
            }
        });
    }

    private JsonObject ApiJsonMap(String rewardProgramID, String webFormID, String contactID, int offerID, String offerSendID, String addressID) {

        JsonObject gsonObject = new JsonObject();
        try {
            JSONObject jsonObj_ = new JSONObject();
            jsonObj_.put("offerID", offerID);
            jsonObj_.put("offerSendID", offerSendID);
            jsonObj_.put("rewardProgramID", rewardProgramID);
            jsonObj_.put("contactID", contactID);
            jsonObj_.put("addressID", addressID);
            jsonObj_.put("webFormID", webFormID);

            JsonParser jsonParser = new JsonParser();
            gsonObject = (JsonObject) jsonParser.parse(jsonObj_.toString());

            //print parameter
            Log.e("Request Body:  ", "" + gsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return gsonObject;
    }

/*
    // verifying if storage permission is given or not
    public static void verifystoragepermissions(Activity activity) {

        int permissions = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        // If storage permission is not given then request for External Storage Permission
        if (permissions != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, permissionstorage, REQUEST_EXTERNAL_STORAGE);
        }
    }*/
    private void moveImage(String inputPath, String inputFile, String outputPath) {

        Log.e("Test", "Input Path: " + inputPath + ", File Name: " + inputFile + ", Output Path: " + outputPath);

        InputStream in = null;
        OutputStream out = null;
        try {

            //create output directory if it doesn't exist
            File dir = new File(outputPath);
            if (!dir.exists()) {
                if (!dir.mkdirs()) ;
                {
                    Log.e("Test", "Dir not created");
                }
            }


            in = new FileInputStream(inputPath + inputFile);
            out = new FileOutputStream(outputPath + inputFile);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;

            // write the output file
            out.flush();
            out.close();
            out = null;

            // delete the original file
            new File(inputPath + inputFile).delete();

        } catch (FileNotFoundException fnfe1) {
            Log.e("tag", fnfe1.getMessage());
        } catch (Exception e) {
            Log.e("tag", e.getMessage());
        }
    }
}