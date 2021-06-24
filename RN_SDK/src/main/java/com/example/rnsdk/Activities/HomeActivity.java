package com.example.rnsdk.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rnsdk.API.GetAPIData;
import com.example.rnsdk.API.RetrofitClientInstance;
import com.example.rnsdk.Adapter.CustomExpandableListAdapter;
import com.example.rnsdk.Adapter.FooterAdapter;
import com.example.rnsdk.Adapter.HomeMenuLinkListAdapter;
import com.example.rnsdk.Adapter.ScreenSlidePagerAdapter;
import com.example.rnsdk.ExpandableListDataPump;
import com.example.rnsdk.Models.AppColorModel;
import com.example.rnsdk.Models.FooterLinkModel;
import com.example.rnsdk.Models.HomeScreenModel;
import com.example.rnsdk.Models.HomeScreenPointsSettingsModel;
import com.example.rnsdk.Models.ResponseModel;
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import info.androidhive.fontawesome.FontDrawable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    TextView textHomePageHeaderMenuText,
            textHomePageTopTextLine1,
            textHomePageTopTextLine2,
            textHomePageTopDescriptionText,
            textHomePageRibbonText, textHomePageRibbonTextTop,
            textHomePageDisplayPointsAvailableText,
            textHomePageDisplayPointsThisMonthText,
            textHomePageDisplayPointsTotalRedeemedText,
            homePageDisplayPointsLifetimeEarnedText,
            textHomePageDisplayPointsAvailableTextTop,
            textHomePageDisplayPointsThisMonthTextTop,
            textHomePageDisplayPointsTotalRedeemedTextTop,
            homePageDisplayPointsLifetimeEarnedTextTop;

    View viewHomePageTopTextUnderLine1,
            viewHomePageTopTextUnderLine2;

    ImageView imgHomePageRibbonIcon,
            imgHomePageRibbonIconTop,
            imgBackgroundHome,
            imgTopContainer;

    DrawerLayout drawer_layout_home;
    AppColorModel appColor;

    private ViewPager mPager;
    HomeScreenModel homeScreenModel;
    RelativeLayout relativeBottomGridHome,
            relativeBottomListHome,
            relHomePageRibbon,
            relHomePageRibbonTop,
            relBottomContainer,
            relTopContainer;

    RecyclerView rvHomeList, rvFooterHome;
    DrawerLayout drawerLayout;
    private PagerAdapter pagerAdapter;
    HomeMenuLinkListAdapter homeMenuLinkListAdapter;
    ImageView imgDrawer,
            imgBottomContainer;
    ExpandableListView expandableListView;
    Button btnHomePageHeader3;
    ExpandableListAdapter expandableListAdapter;
    CircleImageView imgProfileHome;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    LinearLayout linearPointHome,
            linearPointHomeTop,
            linearHomePageDisplayPointsAvailable,
            linearHomePageDisplayPointsThisMonth,
            linearHomePageDisplayPointsTotalRedeemed,
            linearHomePageDisplayPointsLifetimeEarned,
            linearHomePageDisplayPointsAvailableTop,
            linearHomePageDisplayPointsThisMonthTop,
            linearHomePageDisplayPointsTotalRedeemedTop,
            linearHomePageDisplayPointsLifetimeEarnedTop,
            linearDrawerHome;
    TableLayout tableLayoutHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        homeScreenModel = Utility.response.responsedata.homeScreen;
        init();

        getData();


    }


    private void initNavigationDrawer() {

        imgDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_home);

                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


        expandableListView = findViewById(R.id.expandableListView);


        expandableListView.setBackgroundColor(Utility.getColor(appColor.getMenuBackgroundColor()));
        linearDrawerHome.setBackgroundColor(Utility.getColor(appColor.getMenuBackgroundColor()));
        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {


            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                Log.e("Test", expandableListTitle.get(groupPosition)
                        + " -> "
                        + expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition));
                Utility.openNewActivity(HomeActivity.this, expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition), 0, "");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        drawerLayout.close();

                    }
                },500);

                return false;
            }
        });
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousItem = 1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousItem)
                    expandableListView.collapseGroup(previousItem);
                previousItem = groupPosition;
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        setLayout();
    }

    private void init() {

        Log.e("Test", Utility.response.responsedata.contactData.emailAddress);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Utility.getColor(Utility.response.responsedata.appColor.getPhoneNotificationBar()));
        }
        if (Utility.response.responsedata.appColor.getPhoneNotificationBarTextColor().equals("Black")) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        appColor = Utility.response.responsedata.appColor;

        linearDrawerHome = findViewById(R.id.linearDrawerHome);
        tableLayoutHome = findViewById(R.id.tableLayoutHome);
        imgBottomContainer = findViewById(R.id.imgBottomContainer);
        imgTopContainer = findViewById(R.id.imgTopContainer);
        textHomePageDisplayPointsAvailableText = findViewById(R.id.textHomePageDisplayPointsAvailableText);
        textHomePageDisplayPointsThisMonthText = findViewById(R.id.textHomePageDisplayPointsThisMonthText);
        textHomePageDisplayPointsTotalRedeemedText = findViewById(R.id.textHomePageDisplayPointsTotalRedeemedText);
        homePageDisplayPointsLifetimeEarnedText = findViewById(R.id.homePageDisplayPointsLifetimeEarnedText);

        textHomePageDisplayPointsAvailableTextTop = findViewById(R.id.textHomePageDisplayPointsAvailableTextTop);
        textHomePageDisplayPointsThisMonthTextTop = findViewById(R.id.textHomePageDisplayPointsThisMonthTextTop);
        textHomePageDisplayPointsTotalRedeemedTextTop = findViewById(R.id.textHomePageDisplayPointsTotalRedeemedTextTop);
        homePageDisplayPointsLifetimeEarnedTextTop = findViewById(R.id.homePageDisplayPointsLifetimeEarnedTextTop);


        linearHomePageDisplayPointsAvailable = findViewById(R.id.linearHomePageDisplayPointsAvailable);
        linearHomePageDisplayPointsThisMonth = findViewById(R.id.linearHomePageDisplayPointsThisMonth);
        linearHomePageDisplayPointsTotalRedeemed = findViewById(R.id.linearHomePageDisplayPointsTotalRedeemed);
        linearHomePageDisplayPointsLifetimeEarned = findViewById(R.id.linearHomePageDisplayPointsLifetimeEarned);

        linearHomePageDisplayPointsAvailableTop = findViewById(R.id.linearHomePageDisplayPointsAvailableTop);
        linearHomePageDisplayPointsThisMonthTop = findViewById(R.id.linearHomePageDisplayPointsThisMonthTop);
        linearHomePageDisplayPointsTotalRedeemedTop = findViewById(R.id.linearHomePageDisplayPointsTotalRedeemedTop);
        linearHomePageDisplayPointsLifetimeEarnedTop = findViewById(R.id.linearHomePageDisplayPointsLifetimeEarnedTop);

        textHomePageHeaderMenuText = findViewById(R.id.textHomePageHeaderMenuText);
        linearPointHome = findViewById(R.id.linearPointHome);
        relTopContainer = findViewById(R.id.relTopContainer);
        imgProfileHome = findViewById(R.id.imgProfileHome);
        rvFooterHome = findViewById(R.id.rvFooterHome);
        textHomePageTopTextLine1 = findViewById(R.id.textHomePageTopTextLine1);
        textHomePageTopTextLine2 = findViewById(R.id.textHomePageTopTextLine2);
        viewHomePageTopTextUnderLine1 = findViewById(R.id.viewHomePageTopTextUnderLine1);
        viewHomePageTopTextUnderLine2 = findViewById(R.id.viewHomePageTopTextUnderLine2);
        textHomePageTopDescriptionText = findViewById(R.id.textHomePageTopDescriptionText);
        btnHomePageHeader3 = findViewById(R.id.btnHomePageHeader3);
        textHomePageRibbonText = findViewById(R.id.textHomePageRibbonText);
        textHomePageRibbonTextTop = findViewById(R.id.textHomePageRibbonTextTop);
        relativeBottomGridHome = findViewById(R.id.relativeBottomGridHome);
        relativeBottomListHome = findViewById(R.id.relativeBottomListHome);
        relHomePageRibbon = findViewById(R.id.relHomePageRibbon);
        relHomePageRibbonTop = findViewById(R.id.relHomePageRibbonTop);
        imgHomePageRibbonIcon = findViewById(R.id.imgHomePageRibbonIcon);
        imgHomePageRibbonIconTop = findViewById(R.id.imgHomePageRibbonIconTop);
        drawer_layout_home = findViewById(R.id.drawer_layout_home);
        imgBackgroundHome = findViewById(R.id.imgBackgroundHome);
        relBottomContainer = findViewById(R.id.relBottomContainer);
        linearPointHomeTop = findViewById(R.id.linearPointHomeTop);
        toolbar = findViewById(R.id.toolbarHome);
        imgDrawer = findViewById(R.id.imgDrawer);
        mPager = (ViewPager) findViewById(R.id.viewPagerHome);
        rvHomeList = (RecyclerView) findViewById(R.id.rvHomeMenuLink);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle("");
            setSupportActionBar(toolbar);


        }

        setLayout();

    }

    private void setLayout() {


        List<FooterLinkModel> footerLink = new ArrayList<>();
        footerLink.addAll(homeScreenModel.footerLinks);
        for (FooterLinkModel link : homeScreenModel.footerLinks) {
            if (!link.isActive()) {
                footerLink.remove(link);
            }
        }
        homeScreenModel.footerLinks.clear();
        homeScreenModel.footerLinks.addAll(footerLink);


        if (homeScreenModel.getHomePageBackgroundImage() != null) {
            Glide.with(this).load(homeScreenModel.getHomePageBackgroundImage()).into(imgBackgroundHome);
        } else {
            int[] colors = {Utility.getColor(homeScreenModel.getHomePageBackgroundGradientStartColor()),
                    Utility.getColor(homeScreenModel.getHomePageBackgroundGradientStopColor())};

            //create a new gradient color
            GradientDrawable gd = new GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM, colors);

            //apply the button background to newly created drawable gradient
            drawer_layout_home.setBackground(gd);
        }


        if (!homeScreenModel.isHomePageDisplayHeaderMenu()) {
            imgDrawer.setVisibility(View.INVISIBLE);
            textHomePageHeaderMenuText.setVisibility(View.INVISIBLE);
        }
        if (!homeScreenModel.isHomePageDisplayHeaderProfile()) {
            imgProfileHome.setVisibility(View.INVISIBLE);
        }

        textHomePageHeaderMenuText.setTextColor(Utility.getColor(appColor.getHeaderTextColor()));


        textHomePageHeaderMenuText.setText(homeScreenModel.getHomePageHeaderMenuText());

        if (homeScreenModel.isHomePageDisplayHeader()) {
            toolbar.setVisibility(View.VISIBLE);
            tableLayoutHome.setBackgroundColor(Utility.getColor(appColor.getHeaderBarColor()));
        } else {
            toolbar.setVisibility(View.GONE);
        }

        setTopContainer();
        setRibbon();
        setBottomContainer();
        setFooter();

        initNavigationDrawer();

    }


    private void setFooter() {

        if (homeScreenModel.isHomePageDisplayFooter()) {
            rvFooterHome.setVisibility(View.VISIBLE);
            rvFooterHome.setBackgroundColor(Utility.getColor(appColor.getFooterBarColor()));

            FooterAdapter adapter = new FooterAdapter(this, homeScreenModel.footerLinks, "homeScreen");
            rvFooterHome.setHasFixedSize(true);


            rvFooterHome.setLayoutManager(new GridLayoutManager(this, homeScreenModel.footerLinks.size()));

            rvFooterHome.setAdapter(adapter);

        } else {
            rvFooterHome.setVisibility(View.GONE);


        }


    }
    private void getData() {


      /*  progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();*/

        GetAPIData service = RetrofitClientInstance.getRetrofitInstance().create(GetAPIData.class);
        Log.e("Request", "RP ID: " + Utility.response.responsedata.appDetails.rewardProgramId +
                ", Contact ID: " + Utility.response.responsedata.contactData.contactID);

        Call<ResponseModel> call = service.getSurveyList(Utility.response.responsedata.appDetails.rewardProgramId
                , Utility.response.responsedata.contactData.contactID);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
//                progressDialog.dismiss();

                if (response.isSuccessful()) {

                    Utility.response.responsedata.unTaken = response.body().responsedata.unTaken;
                    Utility.response.responsedata.completed = response.body().responsedata.completed;

//                    createTabs();

                } else {
                    Log.e("TEST", "Error Sub: " + response.message());
//                    createTabs();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable test) {
//                progressDialog.dismiss();

                test.printStackTrace();
                Log.e("Test", "Error Main: " + test.toString());
//                createTabs();
            }
        });

    }

    private void setBottomContainer() {

        if (homeScreenModel.getHomePageBottomBackgroundImage() != null) {
            Glide.with(this).load(homeScreenModel.getHomePageBottomBackgroundImage()).into(imgBottomContainer);
        }

        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayoutHome);
        tabLayout.setupWithViewPager(mPager, true);
//homeScreenModel.isHomePageGridMenuLayout()
        if (homeScreenModel.isHomePageGridMenuLayout()) {
            relativeBottomGridHome.setVisibility(View.VISIBLE);
            relativeBottomListHome.setVisibility(View.GONE);
        } else {
            relativeBottomGridHome.setVisibility(View.GONE);
            relativeBottomListHome.setVisibility(View.VISIBLE);

            HomeMenuLinkListAdapter adapter = new HomeMenuLinkListAdapter(this, homeScreenModel.menuLinks, true, true);
            rvHomeList.setItemViewCacheSize(homeScreenModel.menuLinks.size());
            rvHomeList.setLayoutManager(new LinearLayoutManager(this));
            rvHomeList.setAdapter(adapter);

        }

        int[] colors = {Utility.getColor(homeScreenModel.getHomePageBottomBackgroundGradientStartColor()),
                Utility.getColor(homeScreenModel.getHomePageBottomBackgroundGradientStopColor())};

        //create a new gradient color
        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM, colors);

        //apply the button background to newly created drawable gradient
        relBottomContainer.setBackground(gd);


    }

    private void setRibbon() {

        //Ribbon layout set
        if (homeScreenModel.isHomePageDisplayRibbon()) {
            if (homeScreenModel.isHomePageRibbonTextMarquee()) {
                textHomePageRibbonText.setSelected(true);
                textHomePageRibbonTextTop.setSelected(true);
            }
            if (homeScreenModel.isHomePageRibbonDisplayIcon()) {
                imgHomePageRibbonIcon.setVisibility(View.VISIBLE);

                imgHomePageRibbonIconTop.setVisibility(View.VISIBLE);
                Drawable drawable = new FontDrawable(this, Utility.getIcon(homeScreenModel.getHomePageRibbonIcon()), true, false);
                imgHomePageRibbonIcon.setImageDrawable(drawable);
                imgHomePageRibbonIcon.setImageDrawable(drawable);


                if (homeScreenModel.getHomePageRibbonIconPosition().equals("Left")) {
                    RelativeLayout.LayoutParams params =
                            new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                                    RelativeLayout.LayoutParams.WRAP_CONTENT);
                    params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                    imgHomePageRibbonIcon.setLayoutParams(params);
                    imgHomePageRibbonIconTop.setLayoutParams(params);

                }
            }
            textHomePageRibbonText.setText(homeScreenModel.getHomePageRibbonText());
            textHomePageRibbonTextTop.setText(homeScreenModel.getHomePageRibbonText());

            textHomePageRibbonText.setTextColor(Utility.getColor(homeScreenModel.getHomePageRibbonTextColor()));
            textHomePageRibbonTextTop.setTextColor(Utility.getColor(homeScreenModel.getHomePageRibbonTextColor()));

            relHomePageRibbon.setBackgroundColor(Utility.getColor(homeScreenModel.getHomePageRibbonBackgroundColor()));
            relHomePageRibbonTop.setBackgroundColor(Utility.getColor(homeScreenModel.getHomePageRibbonBackgroundColor()));

            imgHomePageRibbonIcon.setBackgroundColor(Utility.getColor(homeScreenModel.getHomePageRibbonBackgroundColor()));
            imgHomePageRibbonIconTop.setBackgroundColor(Utility.getColor(homeScreenModel.getHomePageRibbonBackgroundColor()));

            if (homeScreenModel.getHomePageRibbonPosition().equals("Middle")) {
                relHomePageRibbon.setVisibility(View.VISIBLE);
            } else {
                relHomePageRibbonTop.setVisibility(View.VISIBLE);

            }

            relHomePageRibbon.setOnClickListener(this);
            relHomePageRibbonTop.setOnClickListener(this);
        }

    }

    private void setTopContainer() {


        if (homeScreenModel.getHomePageTopBackgroundImage() != null) {
            Glide.with(this).load(homeScreenModel.getHomePageTopBackgroundImage()).into(imgTopContainer);
        }

        int[] colorsTop = {Utility.getColor(homeScreenModel.getHomePageTopBackgroundGradientStartColor()),
                Utility.getColor(homeScreenModel.getHomePageTopBackgroundGradientStopColor())};

        //create a new gradient color
        GradientDrawable gdTop = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM, colorsTop);

        //apply the button background to newly created drawable gradient
        relTopContainer.setBackground(gdTop);

        textHomePageTopTextLine1.setText(homeScreenModel.getHomePageTopTextLine1());
        textHomePageTopTextLine1.setTextColor(Utility.getColor(homeScreenModel.getHomePageTopTextLine1Color()));
        textHomePageTopTextLine2.setText(homeScreenModel.getHomePageTopTextLine2());
        textHomePageTopTextLine2.setTextColor(Utility.getColor(homeScreenModel.getHomePageTopTextLine2Color()));

        if (homeScreenModel.isHomePageTopHeader3()) {
            if (homeScreenModel.getHomePageTopHeader3Type().equals("Text")) {
                textHomePageTopDescriptionText.setVisibility(View.VISIBLE);
                textHomePageTopDescriptionText.setText(homeScreenModel.getHomePageTopDescriptionText());
                textHomePageTopDescriptionText.setTextColor(Utility.getColor(homeScreenModel.getHomePageTopTextLine3Color()));

            } else {
                btnHomePageHeader3.setVisibility(View.VISIBLE);
                btnHomePageHeader3.setText(homeScreenModel.getHomePageTopButtonText());
                btnHomePageHeader3.setTextColor(Utility.getColor(homeScreenModel.getHomePageTopButtonTextColor()));

                int[] colors = {Utility.getColor(homeScreenModel.getHomePageTopButtonGradientStartColor()),
                        Utility.getColor(homeScreenModel.getHomePageTopButtonGradientStopColor())};

                //create a new gradient color
                GradientDrawable gd = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM, colors);

                //apply the button background to newly created drawable gradient
                btnHomePageHeader3.setBackground(gd);
                btnHomePageHeader3.setOnClickListener(this);


            }

        }

        if (homeScreenModel.isHomePageTopTextUnderLine1()) {
            viewHomePageTopTextUnderLine1.setVisibility(View.VISIBLE);
            viewHomePageTopTextUnderLine1.setBackgroundColor(Utility.getColor(homeScreenModel.getHomePageTopTextUnderLine1Color()));

        }
        if (homeScreenModel.isHomePageTopTextUnderLine2()) {
            viewHomePageTopTextUnderLine2.setVisibility(View.VISIBLE);
            viewHomePageTopTextUnderLine2.setBackgroundColor(Utility.getColor(homeScreenModel.getHomePageTopTextUnderLine2Color()));

        }

        HomeScreenPointsSettingsModel homeScreenSetting = homeScreenModel.homeScreenPointsSettings;


        if (homeScreenSetting.isHomePageDisplayPoints()) {

            if (homeScreenSetting.getHomePageDisplayPointsPosition().equals("Middle")) {
                linearPointHome.setVisibility(View.VISIBLE);
                linearPointHomeTop.setVisibility(View.GONE);
            } else {
                linearPointHome.setVisibility(View.GONE);
                linearPointHomeTop.setVisibility(View.VISIBLE);
            }

            linearPointHome.setBackgroundColor(Utility.getColor(homeScreenSetting.getHomePageDisplayPointsBackgroundColor()));
            linearPointHomeTop.setBackgroundColor(Utility.getColor(homeScreenSetting.getHomePageDisplayPointsBackgroundColor()));

            if (homeScreenSetting.isHomePageDisplayPointsAvailable()) {
                linearHomePageDisplayPointsAvailable.setVisibility(View.VISIBLE);
                textHomePageDisplayPointsAvailableText.setText(homeScreenSetting.getHomePageDisplayPointsAvailableText());
                textHomePageDisplayPointsAvailableText.setTextColor(Utility.getColor(homeScreenSetting.getHomePageDisplayPointsTextColor()));

                linearHomePageDisplayPointsAvailableTop.setVisibility(View.VISIBLE);
                textHomePageDisplayPointsAvailableTextTop.setText(homeScreenSetting.getHomePageDisplayPointsAvailableText());
                textHomePageDisplayPointsAvailableTextTop.setTextColor(Utility.getColor(homeScreenSetting.getHomePageDisplayPointsTextColor()));


            } else {
                linearHomePageDisplayPointsAvailable.setVisibility(View.GONE);
                linearHomePageDisplayPointsAvailableTop.setVisibility(View.GONE);

            }
            if (homeScreenSetting.isHomePageDisplayPointsThisMonth()) {
                linearHomePageDisplayPointsThisMonth.setVisibility(View.VISIBLE);
                textHomePageDisplayPointsThisMonthText.setText(homeScreenSetting.getHomePageDisplayPointsThisMonthText());
                textHomePageDisplayPointsThisMonthText.setTextColor(Utility.getColor(homeScreenSetting.getHomePageDisplayPointsTextColor()));

                linearHomePageDisplayPointsThisMonthTop.setVisibility(View.VISIBLE);
                textHomePageDisplayPointsThisMonthTextTop.setText(homeScreenSetting.getHomePageDisplayPointsThisMonthText());
                textHomePageDisplayPointsThisMonthTextTop.setTextColor(Utility.getColor(homeScreenSetting.getHomePageDisplayPointsTextColor()));

            } else {
                linearHomePageDisplayPointsThisMonth.setVisibility(View.GONE);
                linearHomePageDisplayPointsThisMonthTop.setVisibility(View.GONE);

            }
            if (homeScreenSetting.isHomePageDisplayPointsTotalRedeemed()) {
                linearHomePageDisplayPointsTotalRedeemed.setVisibility(View.VISIBLE);
                textHomePageDisplayPointsTotalRedeemedText.setText(homeScreenSetting.getHomePageDisplayPointsTotalRedeemedText());
                textHomePageDisplayPointsTotalRedeemedText.setTextColor(Utility.getColor(homeScreenSetting.getHomePageDisplayPointsTextColor()));

                linearHomePageDisplayPointsTotalRedeemedTop.setVisibility(View.VISIBLE);
                textHomePageDisplayPointsTotalRedeemedTextTop.setText(homeScreenSetting.getHomePageDisplayPointsTotalRedeemedText());
                textHomePageDisplayPointsTotalRedeemedTextTop.setTextColor(Utility.getColor(homeScreenSetting.getHomePageDisplayPointsTextColor()));


            } else {
                linearHomePageDisplayPointsTotalRedeemed.setVisibility(View.GONE);
                linearHomePageDisplayPointsTotalRedeemedTop.setVisibility(View.GONE);

            }
            if (homeScreenSetting.isHomePageDisplayPointsLifetimeEarned()) {
                linearHomePageDisplayPointsLifetimeEarned.setVisibility(View.VISIBLE);
                homePageDisplayPointsLifetimeEarnedText.setText(homeScreenSetting.getHomePageDisplayPointsLifetimeEarnedText());
                homePageDisplayPointsLifetimeEarnedText.setTextColor(Utility.getColor(homeScreenSetting.getHomePageDisplayPointsTextColor()));

                linearHomePageDisplayPointsLifetimeEarnedTop.setVisibility(View.VISIBLE);
                homePageDisplayPointsLifetimeEarnedTextTop.setText(homeScreenSetting.getHomePageDisplayPointsLifetimeEarnedText());
                homePageDisplayPointsLifetimeEarnedTextTop.setTextColor(Utility.getColor(homeScreenSetting.getHomePageDisplayPointsTextColor()));


            } else {
                linearHomePageDisplayPointsLifetimeEarned.setVisibility(View.GONE);
                linearHomePageDisplayPointsLifetimeEarnedTop.setVisibility(View.GONE);

            }


        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.relHomePageRibbon || id == R.id.relHomePageRibbonTop) {
            if (homeScreenModel.homePageRibbonLinkType.equals("internal")) {
                Utility.openNewActivity(this, homeScreenModel.getHomePageRibbonLinkInternal(), 0, "");
            } else {
                Intent i = new Intent(this, WebViewActivity.class);
                i.putExtra("url", homeScreenModel.getHomePageRibbonLinkExternal());
                startActivity(i);
            }
        } else if (id == R.id.btnHomePageHeader3) {
            if (homeScreenModel.getHomePageTopButtonLinkType().equals("internal")) {
                Utility.openNewActivity(this, homeScreenModel.getHomePageTopButtonLinkInternal(), 0, "");
            } else {
                Intent i = new Intent(this, WebViewActivity.class);
                i.putExtra("url", homeScreenModel.getHomePageTopButtonLinkExternal());
                startActivity(i);
            }

        }
    }


}
