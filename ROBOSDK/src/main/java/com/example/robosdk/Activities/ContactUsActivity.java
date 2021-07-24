package com.example.robosdk.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;


import com.example.robosdk.API.GetAPIData;
import com.example.robosdk.API.RetrofitClientInstance;
import com.example.robosdk.Adapter.FooterAdapter;
import com.example.robosdk.Models.AppColorModel;
import com.example.robosdk.Models.HomeScreenModel;
import com.example.robosdk.Models.ResponseModel;
import com.example.robosdk.Models.ResponsedataModel;
import com.example.robosdk.R;
import com.example.robosdk.Utility.Utility;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUsActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

    Toolbar toolbar;
    private GoogleMap mMap;
    RecyclerView rvFooterContactUs;
    ImageView imgBackContactUs;
    Button btnSendMessage;
    TextView textPointContactUs;
    boolean isFirstNameError = false,
            isLastNameError = false,
            isEmailError = false,
            isMobileError = false,
            isMessageError = false;

    TextInputEditText etFNameCU,
            etLNameCU,
            etEmailCU,
            etMobileCU,
            etMessageCU;

    TextInputLayout etLFNameCU,
            etLLNameCU,
            etLEmailCU,
            etLMobileCU,
            etLMessageCU;

    TableLayout tableLayoutContactUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.myLibTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapContactUs);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        init();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng sydney = new LatLng(37.78825, -122.4324);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Primary Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

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
        toolbar = findViewById(R.id.toolbarContactUs);
        rvFooterContactUs = findViewById(R.id.rvFooterContactUs);
        imgBackContactUs = findViewById(R.id.imgBackContactUs);

        etFNameCU = findViewById(R.id.etFNameCU);
        etLNameCU = findViewById(R.id.etLNameCU);
        etEmailCU = findViewById(R.id.etEmailCU);
        etMobileCU = findViewById(R.id.etMobileCU);
        etMessageCU = findViewById(R.id.etMessageCU);

        etLFNameCU = findViewById(R.id.etLFNameCU);
        etLLNameCU = findViewById(R.id.etLLNameCU);
        etLEmailCU = findViewById(R.id.etLEmailCU);
        etLMobileCU = findViewById(R.id.etLMobileCU);
        etLMessageCU = findViewById(R.id.etLMessageCU);
        tableLayoutContactUs = findViewById(R.id.tableLayoutContactUs);

        tableLayoutContactUs.setBackgroundColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderBarColor()));

        btnSendMessage = findViewById(R.id.btnSendMessage);
        textPointContactUs = findViewById(R.id.textPointContactUs);

        textPointContactUs.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderPointDigitColor()));

        textPointContactUs.setText(Utility.getRoundData(Utility.response.responsedata.contactData.getPointBalance()) + " PTS");

        imgBackContactUs.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle("");
            setSupportActionBar(toolbar);
        }
        AppColorModel color = Utility.response.responsedata.appColor;
        btnSendMessage.setBackgroundColor(Utility.getColor(color.getPrimaryButtonColor()));
        Utility.setFooter(ContactUsActivity.this,rvFooterContactUs,"contactUs");

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

        etFNameCU.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() <= 0) {
                    isFirstNameError = true;
                    setETError("Please Enter First name", etLFNameCU);
                } else {
                    isFirstNameError = false;
                    removeError(etLFNameCU);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etLNameCU.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() <= 0) {
                    isLastNameError = true;
                    setETError("Please Enter Last name", etLLNameCU);
                } else {
                    isLastNameError = false;
                    removeError(etLLNameCU);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etEmailCU.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() <= 0) {
                    isEmailError = true;
                    setETError("Please Enter Email", etLEmailCU);
                } else {
                    if (isValidEmailId(s.toString())) {
                        isEmailError = false;
                        removeError(etLEmailCU);
                    } else {
                        isEmailError = true;
                        setETError("Please Enter Valid Email", etLEmailCU);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etMessageCU.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() <= 0) {
                    isMessageError = true;
                    setETError("Please Enter Message", etLMessageCU);
                } else {
                    isMessageError = false;
                    removeError(etLMessageCU);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etMobileCU.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() <= 0) {
                    isMobileError = true;
                    setETError("Please Enter Mobile No", etLMobileCU);
                } else {
                    if (s.length() == 10) {
                        isMobileError = false;
                        removeError(etLMobileCU);

                    } else {
                        isMobileError = true;
                        setETError("Please Enter Valid Mobile No", etLMobileCU);

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void validate() {
        String fName, lName, email, mobile, message;
        fName = etFNameCU.getText().toString().trim();
        lName = etLNameCU.getText().toString().trim();
        email = etEmailCU.getText().toString().trim();
        message = etMessageCU.getText().toString().trim();
        mobile = etMobileCU.getText().toString().trim();

        if (fName.isEmpty()) {
            isFirstNameError = true;
            setETError("Please Enter First name", etLFNameCU);
        } else {
            isFirstNameError = false;
            removeError(etLFNameCU);
        }
        if (lName.isEmpty()) {
            isLastNameError = true;
            setETError("Please Enter Last name", etLLNameCU);
        } else {
            isLastNameError = false;
            removeError(etLLNameCU);
        }
        if (email.isEmpty()) {
            isEmailError = true;
            setETError("Please Enter Email", etLEmailCU);
        } else {
            if (isValidEmailId(email)) {
                isEmailError = false;
                removeError(etLEmailCU);
            } else {
                isEmailError = true;
                removeError(etLEmailCU);
            }
        }
        if (mobile.isEmpty()) {
            isMobileError = true;
            setETError("Please Enter Mobile No", etLMobileCU);
        } else {
            if (mobile.length() == 10) {
                isMobileError = false;
                removeError(etLMobileCU);
            } else {
                isMobileError = true;
                removeError(etLMobileCU);
            }
        }
        if (message.isEmpty()) {
            isMessageError = true;
            setETError("Please Enter Message", etLMessageCU);
        } else {
            isMessageError = false;
            removeError(etLMessageCU);
        }

        if (!isMessageError && !isMobileError && !isEmailError && !isLastNameError && !isFirstNameError) {
            UploadData(fName,
                    lName,
                    email,
                    message,
                    mobile);
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

    private void UploadData(String fName, String lName, String email, String message, String mobile) {
     Utility.showLoader(ContactUsActivity.this);

        GetAPIData service = RetrofitClientInstance.getRetrofitInstance().create(GetAPIData.class);

        ResponsedataModel responseData = Utility.response.responsedata;

        Call<ResponseModel> callContactUs = service.ContactUs(ApiJsonMap(responseData.appDetails.rewardProgramId,
                responseData.appDetails.webFormID,
                responseData.contactData.contactID,
                fName,
                lName,
                email,
                mobile,
                message
        ));

        callContactUs.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
             Utility.dialog.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getStatusCode() == 1) {

                        Log.e("Test", "Response : " + response.body().getStatusMessage());
                        etFNameCU.setText("");
                        etLNameCU.setText("");
                        etEmailCU.setText("");
                        etMobileCU.setText("");
                        etMessageCU.setText("");

                        removeError(etLFNameCU);
                        removeError(etLLNameCU);
                        removeError(etLEmailCU);
                        removeError(etLMobileCU);
                        removeError(etLMessageCU);

                        if(etMessageCU.isFocused())
                        {
                            etMessageCU.clearFocus();
                        }
                        Utility.showAlertDialog(ContactUsActivity.this,"Success",response.body().getStatusMessage());

                    } else {
                        Utility.showAlertDialog(ContactUsActivity.this,"Oops...",response.message());
                        Log.e("Test", "Response : " + response.body().getStatusMessage());
                    }

                } else {
                    Log.e("TEST", "Error: " + response.message());
                    Utility.showAlertDialog(ContactUsActivity.this,"Oops...","Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable test) {
               Utility.dialog.dismiss();
                Utility.showAlertDialog(ContactUsActivity.this,"Oops...","Something went wrong");
                Log.e("Test:::", test.getMessage().toString());
            }
        });
    }

    private boolean isValidEmailId(String email) {
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgBackContactUs) {
            super.onBackPressed();
        }
    }

    private JsonObject ApiJsonMap(String rewardProgramID, String webFormID, String contactID, String firstName
            , String lastName, String emailId, String mobileNo, String message) {

        JsonObject gsonObject = new JsonObject();
        try {
            JSONObject jsonObj_ = new JSONObject();
            jsonObj_.put("rewardProgramID", rewardProgramID);
            jsonObj_.put("webFormID", webFormID);
            jsonObj_.put("contactID", contactID);
            jsonObj_.put("firstName", firstName);
            jsonObj_.put("lastName", lastName);
            jsonObj_.put("emailId", emailId);
            jsonObj_.put("mobileNo", mobileNo);
            jsonObj_.put("message", message);

            JsonParser jsonParser = new JsonParser();
            gsonObject = (JsonObject) jsonParser.parse(jsonObj_.toString());

            //print parameter
            Log.e("Request Body:  ", "" + gsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return gsonObject;
    }
}