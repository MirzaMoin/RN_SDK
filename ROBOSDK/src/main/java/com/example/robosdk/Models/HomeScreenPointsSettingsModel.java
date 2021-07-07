package com.example.robosdk.Models;

public class HomeScreenPointsSettingsModel {
    public boolean homePageDisplayPoints;
    public boolean homePageDisplayPointsAvailable;
    public String homePageDisplayPointsAvailableText;
    public boolean homePageDisplayPointsThisMonth;
    public String homePageDisplayPointsThisMonthText;
    public boolean homePageDisplayPointsTotalRedeemed;
    public String homePageDisplayPointsTotalRedeemedText;
    public boolean homePageDisplayPointsLifetimeEarned;
    public String homePageDisplayPointsLifetimeEarnedText;
    public String homePageDisplayPointsBackgroundColor;
    public String homePageDisplayPointsTextColor;
    public String homePageDisplayPointsPosition;

    public HomeScreenPointsSettingsModel(boolean homePageDisplayPoints, boolean homePageDisplayPointsAvailable, String homePageDisplayPointsAvailableText, boolean homePageDisplayPointsThisMonth, String homePageDisplayPointsThisMonthText, boolean homePageDisplayPointsTotalRedeemed, String homePageDisplayPointsTotalRedeemedText, boolean homePageDisplayPointsLifetimeEarned, String homePageDisplayPointsLifetimeEarnedText, String homePageDisplayPointsBackgroundColor, String homePageDisplayPointsTextColor, String homePageDisplayPointsPosition) {
        this.homePageDisplayPoints = homePageDisplayPoints;
        this.homePageDisplayPointsAvailable = homePageDisplayPointsAvailable;
        this.homePageDisplayPointsAvailableText = homePageDisplayPointsAvailableText;
        this.homePageDisplayPointsThisMonth = homePageDisplayPointsThisMonth;
        this.homePageDisplayPointsThisMonthText = homePageDisplayPointsThisMonthText;
        this.homePageDisplayPointsTotalRedeemed = homePageDisplayPointsTotalRedeemed;
        this.homePageDisplayPointsTotalRedeemedText = homePageDisplayPointsTotalRedeemedText;
        this.homePageDisplayPointsLifetimeEarned = homePageDisplayPointsLifetimeEarned;
        this.homePageDisplayPointsLifetimeEarnedText = homePageDisplayPointsLifetimeEarnedText;
        this.homePageDisplayPointsBackgroundColor = homePageDisplayPointsBackgroundColor;
        this.homePageDisplayPointsTextColor = homePageDisplayPointsTextColor;
        this.homePageDisplayPointsPosition = homePageDisplayPointsPosition;
    }

    public boolean isHomePageDisplayPoints() {
        return homePageDisplayPoints;
    }

    public void setHomePageDisplayPoints(boolean homePageDisplayPoints) {
        this.homePageDisplayPoints = homePageDisplayPoints;
    }

    public boolean isHomePageDisplayPointsAvailable() {
        return homePageDisplayPointsAvailable;
    }

    public void setHomePageDisplayPointsAvailable(boolean homePageDisplayPointsAvailable) {
        this.homePageDisplayPointsAvailable = homePageDisplayPointsAvailable;
    }

    public String getHomePageDisplayPointsAvailableText() {
        return homePageDisplayPointsAvailableText;
    }

    public void setHomePageDisplayPointsAvailableText(String homePageDisplayPointsAvailableText) {
        this.homePageDisplayPointsAvailableText = homePageDisplayPointsAvailableText;
    }

    public boolean isHomePageDisplayPointsThisMonth() {
        return homePageDisplayPointsThisMonth;
    }

    public void setHomePageDisplayPointsThisMonth(boolean homePageDisplayPointsThisMonth) {
        this.homePageDisplayPointsThisMonth = homePageDisplayPointsThisMonth;
    }

    public String getHomePageDisplayPointsThisMonthText() {
        return homePageDisplayPointsThisMonthText;
    }

    public void setHomePageDisplayPointsThisMonthText(String homePageDisplayPointsThisMonthText) {
        this.homePageDisplayPointsThisMonthText = homePageDisplayPointsThisMonthText;
    }

    public boolean isHomePageDisplayPointsTotalRedeemed() {
        return homePageDisplayPointsTotalRedeemed;
    }

    public void setHomePageDisplayPointsTotalRedeemed(boolean homePageDisplayPointsTotalRedeemed) {
        this.homePageDisplayPointsTotalRedeemed = homePageDisplayPointsTotalRedeemed;
    }

    public String getHomePageDisplayPointsTotalRedeemedText() {
        return homePageDisplayPointsTotalRedeemedText;
    }

    public void setHomePageDisplayPointsTotalRedeemedText(String homePageDisplayPointsTotalRedeemedText) {
        this.homePageDisplayPointsTotalRedeemedText = homePageDisplayPointsTotalRedeemedText;
    }

    public boolean isHomePageDisplayPointsLifetimeEarned() {
        return homePageDisplayPointsLifetimeEarned;
    }

    public void setHomePageDisplayPointsLifetimeEarned(boolean homePageDisplayPointsLifetimeEarned) {
        this.homePageDisplayPointsLifetimeEarned = homePageDisplayPointsLifetimeEarned;
    }

    public String getHomePageDisplayPointsLifetimeEarnedText() {
        return homePageDisplayPointsLifetimeEarnedText;
    }

    public void setHomePageDisplayPointsLifetimeEarnedText(String homePageDisplayPointsLifetimeEarnedText) {
        this.homePageDisplayPointsLifetimeEarnedText = homePageDisplayPointsLifetimeEarnedText;
    }

    public String getHomePageDisplayPointsBackgroundColor() {
        return homePageDisplayPointsBackgroundColor;
    }

    public void setHomePageDisplayPointsBackgroundColor(String homePageDisplayPointsBackgroundColor) {
        this.homePageDisplayPointsBackgroundColor = homePageDisplayPointsBackgroundColor;
    }

    public String getHomePageDisplayPointsTextColor() {
        return homePageDisplayPointsTextColor;
    }

    public void setHomePageDisplayPointsTextColor(String homePageDisplayPointsTextColor) {
        this.homePageDisplayPointsTextColor = homePageDisplayPointsTextColor;
    }

    public String getHomePageDisplayPointsPosition() {
        return homePageDisplayPointsPosition;
    }

    public void setHomePageDisplayPointsPosition(String homePageDisplayPointsPosition) {
        this.homePageDisplayPointsPosition = homePageDisplayPointsPosition;
    }
}
