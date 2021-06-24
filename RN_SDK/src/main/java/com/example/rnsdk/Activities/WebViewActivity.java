package com.example.rnsdk.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {

    WebView webview;
    LinearLayout linearBack, linearRefresh, linearForward;
    ImageView imgBack;
    TextView textPointWebView;
    ProgressDialog progressDialog;
    String url = "";
    boolean isSurvey = false;
    String originalUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        init();

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.show();


        url = getIntent().getStringExtra("url");
        if (getIntent().hasExtra("isSurvey")) {
            isSurvey = getIntent().getBooleanExtra("isSurvey", false);
        }

        Log.e("URL: ", url);


        webview.setWebViewClient(new WebViewClient());

        webview.loadUrl(url);
        webview.getSettings().setJavaScriptEnabled(true);


        if(isSurvey)
        {
            try {
                originalUrl = expandUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url1) {
                progressDialog.dismiss();

                if(isSurvey)
                {
                    if (view.getUrl().equals(originalUrl)) {

                        Log.e("Test", "Loaded");


                    }
                    else {

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


    /*    Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    try {
                        originalUrl = expandUrl(url);
                        Log.e("Test","Loading");

                        webview.setWebChromeClient(new WebChromeClient() {
                                                       @Override
                                                       public void onProgressChanged(WebView view, int newProgress) {
                                                           Log.e("Test","Loaded");

                                                           if (view.getUrl().equals(url)) {

                                                               Log.e("Test","Loaded");


                                                           } else {

                                                               startActivity(new Intent(WebViewActivity.this, HomeActivity.class));
                                                               finish();
                                                               // onUrlChanged(mUrl) // url changed
                                                           }
                                                           super.onProgressChanged(view, newProgress);
                                                       }



                                                   }
                        );

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                webview.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url1) {
                        progressDialog.dismiss();

                        if (view.getUrl().equals(originalUrl)) {

                            Log.e("Test","Loaded");


                        } else {

                            Log.e("TEST","Changed URL : "+view.getUrl());
                            startActivity(new Intent(WebViewActivity.this, HomeActivity.class));
                            finish();
                            // onUrlChanged(mUrl) // url changed
                        }

                    }
                    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                        Toast.makeText(WebViewActivity.this, description, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        thread.start();
*/

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
        webview = findViewById(R.id.webview);
        linearBack = findViewById(R.id.linearBack);
        linearRefresh = findViewById(R.id.linearRefresh);
        linearForward = findViewById(R.id.linearForward);
        imgBack = findViewById(R.id.imgBackWebview);
        textPointWebView = findViewById(R.id.textPointWebView);
        textPointWebView.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderPointDigitColor()));
        textPointWebView.setText(String.valueOf(Utility.response.responsedata.contactData.getPointBalance())+ " PTS");


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