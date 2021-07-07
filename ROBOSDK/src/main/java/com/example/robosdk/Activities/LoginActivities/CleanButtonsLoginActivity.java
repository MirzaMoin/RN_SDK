package com.example.robosdk.Activities.LoginActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import com.example.robosdk.R;

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