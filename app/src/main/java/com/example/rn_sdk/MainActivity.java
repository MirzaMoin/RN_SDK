package com.example.rn_sdk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.rnsdk.Activities.CashbackActivity;
import com.example.rnsdk.Activities.ChangePasswordActivity;
import com.example.rnsdk.Activities.ContactUsActivity;
import com.example.rnsdk.Activities.HomeActivity;
import com.example.rnsdk.Activities.LeaderboardActivity;
import com.example.rnsdk.Activities.LocationActivity;
import com.example.rnsdk.Activities.NotificationActivity;
import com.example.rnsdk.Activities.OfferActivity;
import com.example.rnsdk.Activities.ProfileActivity;
import com.example.rnsdk.Activities.ReferFriendActivity;
import com.example.rnsdk.Activities.SplashActivity;
import com.example.rnsdk.Activities.TakeSurveyActivity;
import com.example.rnsdk.Activities.TransactionHistoryActivity;
import com.example.rnsdk.Activities.TransferPointActivity;
import com.example.rnsdk.Activities.UploadReceiptActivity;
import com.example.rnsdk.Activities.WaysToEarnActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this,  SplashActivity.class));
    }
}