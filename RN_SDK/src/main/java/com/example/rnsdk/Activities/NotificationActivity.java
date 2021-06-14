package com.example.rnsdk.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rnsdk.Adapter.CustomExpandableListAdapter;
import com.example.rnsdk.Adapter.HomeMenuLinkListAdapter;
import com.example.rnsdk.Adapter.NotificationAdapter;
import com.example.rnsdk.Adapter.OffersAdapter;
import com.example.rnsdk.ExpandableListDataPump;
import com.example.rnsdk.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {


    RecyclerView rvNotification;

    DrawerLayout drawerLayout;

    ImageView imgDrawer;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nitification);

        init();

        imgDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_notification);

                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


        initNavigationDrawer();
    }

    private void initNavigationDrawer() {
        expandableListView = findViewById(R.id.expandableListViewNotification);
        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousItem = 1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition != previousItem )
                    expandableListView.collapseGroup(previousItem );
                previousItem = groupPosition;
            }
        });

        NotificationAdapter adapter = new NotificationAdapter(this);
        rvNotification.setHasFixedSize(true);
        rvNotification.setLayoutManager(new LinearLayoutManager(this));
        rvNotification.setAdapter(adapter);
    }

    private void init() {

        rvNotification = findViewById(R.id.rvNotification);

        imgDrawer = findViewById(R.id.imgDrawerNotification);





    }


}