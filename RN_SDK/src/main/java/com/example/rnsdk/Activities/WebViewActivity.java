package com.example.rnsdk.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.rnsdk.R;

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {

    WebView webview;
    LinearLayout linearBack,linearRefresh,linearForward;
    ImageView imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webview = findViewById(R.id.webview);
        linearBack = findViewById(R.id.linearBack);
        linearRefresh = findViewById(R.id.linearRefresh);
        linearForward = findViewById(R.id.linearForward);
        imgBack = findViewById(R.id.imgBackWebview);

        String url = getIntent().getStringExtra("url");
        Log.e("Test",url);
        webview.loadUrl(url);

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