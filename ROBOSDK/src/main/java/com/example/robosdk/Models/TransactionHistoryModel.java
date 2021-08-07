package com.example.robosdk.Models;

import java.util.List;

public class TransactionHistoryModel {
    public String transactionDate,
            locationName,
            type,
            transactionStatus,
            points;
    public double balance;
    public List<TransactionHistoryChildMenuModel> childMenus;
    public boolean isExpanded;

    public TransactionHistoryModel(String transactionDate, String locationName, String type, String transactionStatus, String points, double balance, List<TransactionHistoryChildMenuModel> childMenus) {
        this.transactionDate = transactionDate;
        this.locationName = locationName;
        this.type = type;
        this.transactionStatus = transactionStatus;
        this.points = points;
        this.balance = balance;
        this.childMenus = childMenus;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<TransactionHistoryChildMenuModel> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<TransactionHistoryChildMenuModel> childMenus) {
        this.childMenus = childMenus;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
