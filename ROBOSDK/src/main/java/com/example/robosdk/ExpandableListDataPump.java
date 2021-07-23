package com.example.robosdk;

import com.example.robosdk.Models.MenuPermissionModel;
import com.example.robosdk.Utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        LinkedHashMap<String, List<String>> expandableListDetail = new LinkedHashMap<>();
        MenuPermissionModel menu = Utility.response.responsedata.menuPermission;

        List<String> HOME = new ArrayList<String>();
        expandableListDetail.put("HOME", HOME);


        List<String> USE_POINTS = new ArrayList<String>();
        USE_POINTS.add("Offers");
        if(menu.isVisibleMenuRPGoal())
            USE_POINTS.add("Reward Entry Goal");
        if(menu.isVisibleMenuCashbackRedemption())
            USE_POINTS.add("Redeem Cashback");
        if(menu.isVisibleMenuTransferPoints())
            USE_POINTS.add("Transfer Points");

        expandableListDetail.put("USE POINTS", USE_POINTS);


        List<String> EARN_POINTS = new ArrayList<String>();
        if(menu.isVisibleMenuWaysToEarnPoints())
            EARN_POINTS.add("Ways To Earn");
        if(menu.isVisibleMenuTakeSurvey())
            EARN_POINTS.add("Take Survey");
       /* if(menu.isVisibleMenuReferFriends())
            EARN_POINTS.add("Refer Friend");*/
        if(menu.isVisibleMenuUploadReciepts())
            EARN_POINTS.add("Upload Receipt");
        expandableListDetail.put("EARN POINTS", EARN_POINTS);


        List<String> ACTIVITY = new ArrayList<String>();
        if(menu.isVisibleMenuTxHistory())
            ACTIVITY.add("Transaction History");
        ACTIVITY.add("Leaderboard");
        expandableListDetail.put("ACTIVITY", ACTIVITY);

/*
        List<String> PROFILE = new ArrayList<String>();
        if(menu.isVisibleUpdatePassword())
            PROFILE.add("Update Profile");
        *//*if(menu.isVisibleChangePassword())
            PROFILE.add("Change Password");*//*
        expandableListDetail.put("PROFILE", PROFILE);*/


        List<String> CONTACT = new ArrayList<String>();
        if(menu.isVisibleMenuContactUs)
            CONTACT.add("Contact Us");

        if(menu.isVisibleMenuLocation())
            CONTACT.add("Location");
     /*   if(menu.isVisibleMenuAppointment())
            CONTACT.add("Appointment");*/
        expandableListDetail.put("CONTACT", CONTACT);



        return expandableListDetail;
    }
}
