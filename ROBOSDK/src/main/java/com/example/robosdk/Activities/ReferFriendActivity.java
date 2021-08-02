package com.example.robosdk.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.robosdk.Adapter.CashbackImageSliderAdapter;
import com.example.robosdk.Adapter.FooterAdapter;
import com.example.robosdk.Models.AppColorModel;
import com.example.robosdk.Models.ChildPageModel;
import com.example.robosdk.Models.ChildPageSettingModel;
import com.example.robosdk.Models.HomeScreenModel;
import com.example.robosdk.Models.ReferFriendChildPageDataModel;
import com.example.robosdk.R;
import com.example.robosdk.Utility.Utility;
import com.facebook.CallbackManager;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class ReferFriendActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    ImageView imgBackReferFriend,
            imageFacebook,
            imageWhatsapp,
            imageEmail,
            imageTwitter,
            imageSMS;

    TextView textPointReferFriend;
    Button btnGetInviteLink;

    RecyclerView rvFooterReferFriend;
    TableLayout tableLayoutReferFriends;
    CallbackManager callbackManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.myLibTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_friend);

        init();
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
        toolbar = findViewById(R.id.toolbarReferFriend);
        rvFooterReferFriend = findViewById(R.id.rvFooterReferFriend);
        imgBackReferFriend = findViewById(R.id.imgBackReferFriend);
        btnGetInviteLink = findViewById(R.id.btnGetInviteLink);
        textPointReferFriend = findViewById(R.id.textPointReferFriend);
        tableLayoutReferFriends = findViewById(R.id.tableLayoutReferFriends);

        imageFacebook = findViewById(R.id.imageFacebook);
        imageWhatsapp = findViewById(R.id.imageWhatsapp);
        imageEmail = findViewById(R.id.imageEmail);
        imageTwitter = findViewById(R.id.imageTwitter);
        imageSMS = findViewById(R.id.imageSMS);


        imageFacebook.setOnClickListener(this);
        imageWhatsapp.setOnClickListener(this);
        imageEmail.setOnClickListener(this);
        imageTwitter.setOnClickListener(this);
        imageSMS.setOnClickListener(this);

        textPointReferFriend.setTextColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderPointDigitColor()));
        textPointReferFriend.setText(String.valueOf(Utility.response.responsedata.contactData.getPointBalance())+ " PTS");

        imgBackReferFriend.setOnClickListener(this);

        tableLayoutReferFriends.setBackgroundColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderBarColor()));


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle("");
            setSupportActionBar(toolbar);

        }

        ChildPageSettingModel childPageSettings = Utility.response.responsedata.childPageSetting;

        if(childPageSettings.isChildPageReferFriend()) {
            List<ChildPageModel> childPage = new ArrayList<>();
            for (ReferFriendChildPageDataModel refer : childPageSettings.referFriendChildPageData) {
                childPage.add(new ChildPageModel(refer.image, refer.opacity, refer.isClickable, refer.linkType, refer.internalLink, refer.externalLink));
            }


            SliderView sliderView = findViewById(R.id.imageSliderReferFriend);
            sliderView.setVisibility(View.VISIBLE);

            CashbackImageSliderAdapter adapter = new CashbackImageSliderAdapter(this, childPage);


            sliderView.setSliderAdapter(adapter);

            sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
            sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
            sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
            sliderView.setIndicatorSelectedColor(Color.WHITE);
            sliderView.setIndicatorUnselectedColor(Color.GRAY);
            sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
            sliderView.startAutoCycle();
        }

        btnGetInviteLink.setOnClickListener(this);
        AppColorModel color = Utility.response.responsedata.appColor;
        btnGetInviteLink.setBackgroundColor(Utility.getColor(color.getPrimaryButtonColor()));
        setFooter();
    }
    private void setFooter() {
        AppColorModel appColor = Utility.response.responsedata.appColor;

        HomeScreenModel homeScreenModel = Utility.response.responsedata.homeScreen;
        if(homeScreenModel.isHomePageDisplayFooter())
        {
            rvFooterReferFriend.setVisibility(View.VISIBLE);
            rvFooterReferFriend.setBackgroundColor(Utility.getColor(appColor.getFooterBarColor()));

            FooterAdapter adapter = new FooterAdapter(this,homeScreenModel.footerLinks,"profileScreen");
            rvFooterReferFriend.setHasFixedSize(true);


            rvFooterReferFriend.setLayoutManager(new GridLayoutManager(this,homeScreenModel.footerLinks.size()));

            rvFooterReferFriend.setAdapter(adapter);
        }
        else
        {
            rvFooterReferFriend.setVisibility(View.GONE);


        }


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.imgBackReferFriend){
            super.onBackPressed();
        }
        else if(id == R.id.btnGetInviteLink)
        {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_TEXT,"www.google.com");
            startActivity(Intent.createChooser(i,"Share via"));
        }
        else if(id == R.id.imageFacebook){
            callbackManager = CallbackManager.Factory.create();
            ShareDialog shareDialog = new ShareDialog(this);

            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setQuote("This is Link")
                    .setContentUrl(Uri.parse("https://www.google.com")).build();

            if(ShareDialog.canShow(ShareLinkContent.class))
            {
                shareDialog.show(linkContent);
            }

        }
        else if(id == R.id.imageWhatsapp)
        {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_TEXT,"www.google.com");
            i.setPackage("com.whatsapp");
            startActivity(i);
        }
        else if(id == R.id.imageEmail){
            Intent i = new Intent(Intent.ACTION_SENDTO);
            i.setData(Uri.parse("mailto:"));
            i.putExtra(Intent.EXTRA_EMAIL,new String[]{""});
            i.putExtra(Intent.EXTRA_SUBJECT,"");
            i.putExtra(Intent.EXTRA_TEXT,"www.google.com");
            startActivity(i);

        }
        else if(id == R.id.imageTwitter)
        {
            Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse("https://twitter.com/intent/tweet?text=Welcome to the app click the link to install app&url=www.google.com"));
            startActivity(i);
        }
        else if(id == R.id.imageSMS)
        {
            Uri uri = Uri.parse("smsto:");
            Intent i = new Intent(Intent.ACTION_SENDTO,uri);
            i.putExtra("sms_body","www.google.com");
            startActivity(i);
        }

    }
}