package com.example.rnsdk.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.rnsdk.Adapter.HomeMenuLinkListAdapter;
import com.example.rnsdk.Adapter.WaysToEarnAdapter;
import com.example.rnsdk.R;

public class WaysToEarnActivity extends AppCompatActivity {

    RecyclerView rvList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ways_to_earn);


        init();

        WaysToEarnAdapter adapter = new WaysToEarnAdapter();
        rvList.setHasFixedSize(true);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(adapter);


    }

    private void init() {
        rvList = findViewById(R.id.rvWaysToEarn);

    }
}