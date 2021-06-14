package com.example.rnsdk.Activities.LoginActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.example.rnsdk.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CleanButtonsLoginActivity extends AppCompatActivity {
    ConstraintLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clean_buttons_login);

        init();


    }

    private void init() {
        mainLayout = findViewById(R.id.layoutLoginCleanButtons);


    }

}