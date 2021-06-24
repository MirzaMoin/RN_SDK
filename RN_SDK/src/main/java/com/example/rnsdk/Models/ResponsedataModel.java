package com.example.rnsdk.Models;

import java.util.List;

public class ResponsedataModel {
    public LoginScreenModel logInScreen;
    public HomeScreenModel homeScreen;
    public AppColorModel appColor;
    public MenuPermissionModel menuPermission;
    public AppIntakeImagesModel appIntakeImages;
    public AppDetailsModel appDetails;
    public ChildPageSettingModel childPageSetting;
    public ContactData contactData;
    public Object webFormData;
    public TransactionHistoryModel transactionHistoryModel;
    public List<TransactionHistoryModel> responsedata;

    public OfferRedeemSettingModel redeemSetting;
    public OfferAddressDetailsModel addressDetails;
    public OfferUserDetailsModel userDetails;
    public List<OfferListModel> offerList;

    public List<SurveysUnTakenModel> unTaken;
    public List<SurveysUnTakenModel> completed;

    public List<RPGListModel> lstRPG;


    public WaysToEarnModel totalPoints;
    public WaysToEarnModel purchasePoints;
    public WaysToEarnModel socialShare;
    public WaysToEarnModel referFriends;
    public WaysToEarnModel leaderboard;
    public WaysToEarnModel surveys;
    public WaysToEarnModel completeProfile;


    public ResponsedataModel(LoginScreenModel logInScreen, HomeScreenModel homeScreen, AppColorModel appColor, MenuPermissionModel menuPermission, AppIntakeImagesModel appIntakeImages, AppDetailsModel appDetails, ChildPageSettingModel childPageSetting, ContactData contactData, Object webFormData, TransactionHistoryModel transactionHistoryModel, List<TransactionHistoryModel> responsedata, OfferRedeemSettingModel redeemSetting, OfferAddressDetailsModel addressDetails, OfferUserDetailsModel userDetails, List<OfferListModel> offerList, List<SurveysUnTakenModel> unTaken, List<SurveysUnTakenModel> completed, List<RPGListModel> lstRPG, WaysToEarnModel totalPoints, WaysToEarnModel purchasePoints, WaysToEarnModel socialShare, WaysToEarnModel referFriends, WaysToEarnModel leaderboard, WaysToEarnModel surveys, WaysToEarnModel completeProfile) {
        this.logInScreen = logInScreen;
        this.homeScreen = homeScreen;
        this.appColor = appColor;
        this.menuPermission = menuPermission;
        this.appIntakeImages = appIntakeImages;
        this.appDetails = appDetails;
        this.childPageSetting = childPageSetting;
        this.contactData = contactData;
        this.webFormData = webFormData;
        this.transactionHistoryModel = transactionHistoryModel;
        this.responsedata = responsedata;
        this.redeemSetting = redeemSetting;
        this.addressDetails = addressDetails;
        this.userDetails = userDetails;
        this.offerList = offerList;
        this.unTaken = unTaken;
        this.completed = completed;
        this.lstRPG = lstRPG;
        this.totalPoints = totalPoints;
        this.purchasePoints = purchasePoints;
        this.socialShare = socialShare;
        this.referFriends = referFriends;
        this.leaderboard = leaderboard;
        this.surveys = surveys;
        this.completeProfile = completeProfile;
    }

    public LoginScreenModel getLogInScreen() {
        return logInScreen;
    }

    public void setLogInScreen(LoginScreenModel logInScreen) {
        this.logInScreen = logInScreen;
    }

    public HomeScreenModel getHomeScreen() {
        return homeScreen;
    }

    public void setHomeScreen(HomeScreenModel homeScreen) {
        this.homeScreen = homeScreen;
    }

    public AppColorModel getAppColor() {
        return appColor;
    }

    public void setAppColor(AppColorModel appColor) {
        this.appColor = appColor;
    }

    public MenuPermissionModel getMenuPermission() {
        return menuPermission;
    }

    public void setMenuPermission(MenuPermissionModel menuPermission) {
        this.menuPermission = menuPermission;
    }

    public AppIntakeImagesModel getAppIntakeImages() {
        return appIntakeImages;
    }

    public void setAppIntakeImages(AppIntakeImagesModel appIntakeImages) {
        this.appIntakeImages = appIntakeImages;
    }

    public AppDetailsModel getAppDetails() {
        return appDetails;
    }

    public void setAppDetails(AppDetailsModel appDetails) {
        this.appDetails = appDetails;
    }

    public ChildPageSettingModel getChildPageSetting() {
        return childPageSetting;
    }

    public void setChildPageSetting(ChildPageSettingModel childPageSetting) {
        this.childPageSetting = childPageSetting;
    }

    public ContactData getContactData() {
        return contactData;
    }

    public void setContactData(ContactData contactData) {
        this.contactData = contactData;
    }

    public Object getWebFormData() {
        return contactData;
    }

    public void setWebFormData(Object webFormData) {
        this.webFormData = webFormData;
    }

    public TransactionHistoryModel getTransactionHistoryModel() {
        return transactionHistoryModel;
    }

    public void setTransactionHistoryModel(TransactionHistoryModel transactionHistoryModel) {
        this.transactionHistoryModel = transactionHistoryModel;
    }

    public List<TransactionHistoryModel> getResponsedata() {
        return responsedata;
    }

    public void setResponsedata(List<TransactionHistoryModel> responsedata) {
        this.responsedata = responsedata;
    }

    public OfferRedeemSettingModel getRedeemSetting() {
        return redeemSetting;
    }

    public void setRedeemSetting(OfferRedeemSettingModel redeemSetting) {
        this.redeemSetting = redeemSetting;
    }

    public OfferAddressDetailsModel getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(OfferAddressDetailsModel addressDetails) {
        this.addressDetails = addressDetails;
    }

    public OfferUserDetailsModel getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(OfferUserDetailsModel userDetails) {
        this.userDetails = userDetails;
    }

    public List<OfferListModel> getOfferList() {
        return offerList;
    }

    public void setOfferList(List<OfferListModel> offerList) {
        this.offerList = offerList;
    }

    public List<SurveysUnTakenModel> getUnTaken() {
        return unTaken;
    }

    public void setUnTaken(List<SurveysUnTakenModel> unTaken) {
        this.unTaken = unTaken;
    }

    public List<SurveysUnTakenModel> getCompleted() {
        return completed;
    }

    public void setCompleted(List<SurveysUnTakenModel> completed) {
        this.completed = completed;
    }


    public List<RPGListModel> getLstRPG() {
        return lstRPG;
    }

    public void setLstRPG(List<RPGListModel> lstRPG) {
        this.lstRPG = lstRPG;
    }

    public WaysToEarnModel getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(WaysToEarnModel totalPoints) {
        this.totalPoints = totalPoints;
    }

    public WaysToEarnModel getPurchasePoints() {
        return purchasePoints;
    }

    public void setPurchasePoints(WaysToEarnModel purchasePoints) {
        this.purchasePoints = purchasePoints;
    }

    public WaysToEarnModel getSocialShare() {
        return socialShare;
    }

    public void setSocialShare(WaysToEarnModel socialShare) {
        this.socialShare = socialShare;
    }

    public WaysToEarnModel getReferFriends() {
        return referFriends;
    }

    public void setReferFriends(WaysToEarnModel referFriends) {
        this.referFriends = referFriends;
    }

    public WaysToEarnModel getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(WaysToEarnModel leaderboard) {
        this.leaderboard = leaderboard;
    }

    public WaysToEarnModel getSurveys() {
        return surveys;
    }

    public void setSurveys(WaysToEarnModel surveys) {
        this.surveys = surveys;
    }

    public WaysToEarnModel getCompleteProfile() {
        return completeProfile;
    }

    public void setCompleteProfile(WaysToEarnModel completeProfile) {
        this.completeProfile = completeProfile;
    }
}