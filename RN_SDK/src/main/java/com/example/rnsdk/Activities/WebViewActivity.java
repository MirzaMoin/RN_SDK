package com.example.rnsdk.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {

    WebView webview;
    LinearLayout linearBack,linearRefresh,linearForward;
    ImageView imgBack;
    TextView textPointWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        init();

        String url = getIntent().getStringExtra("url");
        Log.e("Test",url);
        webview.loadUrl(url);

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
        webview = findViewById(R.id.webview);
        linearBack = findViewById(R.id.linearBack);
        linearRefresh = findViewById(R.id.linearRefresh);
        linearForward = findViewById(R.id.linearForward);
        imgBack = findViewById(R.id.imgBackWebview);
        textPointWebView = findViewById(R.id.textPointWebView);
        textPointWebView.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderPointDigitColor()));



        linearBack.setOnClickListener(this);
        linearRefresh.setOnClickListener(this);
        linearForward.setOnClickListener(this);
        imgBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.linearBack){
            if(webview.canGoBack()){
                webview.goBack();
            }
        }
        else if(v.getId() == R.id.linearRefresh){
            webview.reload();
        }
        else if(v.getId() == R.id.linearForward){
            if(webview.canGoForward()){
                webview.goForward();
            }
        }
        else if(v.getId() == R.id.imgBackWebview){
            super.onBackPressed();
        }
    }
}