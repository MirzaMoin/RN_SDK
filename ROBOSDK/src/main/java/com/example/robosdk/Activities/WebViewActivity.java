package com.example.robosdk.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.robosdk.Models.ResponsedataModel;
import com.example.robosdk.R;
import com.example.robosdk.Utility.Utility;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;

import static com.example.robosdk.Utility.Utility.getRoundData;
import static com.example.robosdk.Utility.Utility.response;

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {

    WebView webview;
    LinearLayout linearBack, linearRefresh, linearForward;
    ImageView imgBack;
    TextView textPointWebView,
            textTitleWeb;
    String url = "";
    boolean isSurvey = false;
    String title = "";
    String originalUrl = "";

    TableLayout tableLayoutWeb;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        init();

        Utility.showLoader(this);


        url = getIntent().getStringExtra("url");
        if (getIntent().hasExtra("isSurvey")) {
            isSurvey = getIntent().getBooleanExtra("isSurvey", false);
        }
        if (getIntent().hasExtra("title")) {
            title = getIntent().getStringExtra("title");
            textTitleWeb.setText(title);

        }

        Log.e("URL: ", url);


        webview.setWebViewClient(new WebViewClient());

        webview.loadUrl(url);
        webview.getSettings().setJavaScriptEnabled(true);


        if (isSurvey) {
            try {
                originalUrl = expandUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url1) {
                Utility.dialog.dismiss();
                if (isSurvey) {
                    if (view.getUrl().equals(originalUrl)) {

                        Log.e("Test", "Loaded");


                    } else {

                        Log.e("TEST", "Changed URL : " + view.getUrl());
                        startActivity(new Intent(WebViewActivity.this, HomeActivity.class));
                        finish();
                        // onUrlChanged(mUrl) // url changed
                    }
                }


            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(WebViewActivity.this, description, Toast.LENGTH_SHORT).show();
            }
        });


        Log.e("TEST URL", "onCreate: " + originalUrl);


    }


    public static String expandUrl(String shortenedUrl) throws IOException {
        URL url = new URL(shortenedUrl);
        // open connection
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);

        // stop following browser redirect
        httpURLConnection.setInstanceFollowRedirects(false);

        // extract location header containing the actual destination URL
        String expandedURL = httpURLConnection.getHeaderField("Location");
        httpURLConnection.disconnect();

        return expandedURL;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    private void init() {
        ResponsedataModel responseData = response.responsedata;

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Utility.getColor(responseData.appColor.getPhoneNotificationBar()));
        }
        if (responseData.appColor.getPhoneNotificationBarTextColor().equals("Black")) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        tableLayoutWeb = findViewById(R.id.tableLayoutWeb);
        tableLayoutWeb.setBackgroundColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderBarColor()));

        webview = findViewById(R.id.webview);
        linearBack = findViewById(R.id.linearBack);
        linearRefresh = findViewById(R.id.linearRefresh);
        linearForward = findViewById(R.id.linearForward);
        imgBack = findViewById(R.id.imgBackWebview);
        textPointWebView = findViewById(R.id.textPointWebView);
        textTitleWeb = findViewById(R.id.textTitleWeb);
        textPointWebView.setTextColor(Utility.getColor(responseData.appColor.getHeaderPointDigitColor()));
        textPointWebView.setText(getRoundData(responseData.contactData.getPointBalance()) + " PTS");


        linearBack.setOnClickListener(this);
        linearRefresh.setOnClickListener(this);
        linearForward.setOnClickListener(this);
        imgBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.linearBack) {
            if (webview.canGoBack()) {
                webview.goBack();
            }
        } else if (v.getId() == R.id.linearRefresh) {
            webview.reload();
        } else if (v.getId() == R.id.linearForward) {
            if (webview.canGoForward()) {
                webview.goForward();
            }
        } else if (v.getId() == R.id.imgBackWebview) {
            super.onBackPressed();
        }
    }
}