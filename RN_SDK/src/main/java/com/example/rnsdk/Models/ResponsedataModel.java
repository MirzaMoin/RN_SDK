package com.example.rnsdk.Models;

public class ResponsedataModel{
    public LoginScreenModel logInScreen;
    public HomeScreenModel homeScreen;
    public AppColorModel appColor;
    public MenuPermissionModel menuPermission;
    public AppIntakeImagesModel appIntakeImages;
    public AppDetailsModel appDetails;
    public ChildPageSettingModel childPageSetting;

    public ResponsedataModel(LoginScreenModel logInScreen, HomeScreenModel homeScreen, AppColorModel appColor, MenuPermissionModel menuPermission, AppIntakeImagesModel appIntakeImages, AppDetailsModel appDetails, ChildPageSettingModel childPageSetting) {
        this.logInScreen = logInScreen;
        this.homeScreen = homeScreen;
        this.appColor = appColor;
        this.menuPermission = menuPermission;
        this.appIntakeImages = appIntakeImages;
        this.appDetails = appDetails;
        this.childPageSetting = childPageSetting;
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
}