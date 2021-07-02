package com.example.rnsdk.Models;

public class URSettingsDetailsModel {
    public String urReceiptType;
    public boolean isShowURReceiptType;
    public String urSubTotalBeforeTax;
    public boolean isShowURSubTotalBeforeTax;
    public String urReceiptDate;
    public boolean isShowURReceiptDate;
    public String urPrimaryLocation;
    public boolean isShowURPrimaryLocation;
    public String urReceiptNumber;
    public boolean isShowURReceiptNumber;

    public URSettingsDetailsModel(String urReceiptType, boolean isShowURReceiptType, String urSubTotalBeforeTax, boolean isShowURSubTotalBeforeTax, String urReceiptDate, boolean isShowURReceiptDate, String urPrimaryLocation, boolean isShowURPrimaryLocation, String urReceiptNumber, boolean isShowURReceiptNumber) {
        this.urReceiptType = urReceiptType;
        this.isShowURReceiptType = isShowURReceiptType;
        this.urSubTotalBeforeTax = urSubTotalBeforeTax;
        this.isShowURSubTotalBeforeTax = isShowURSubTotalBeforeTax;
        this.urReceiptDate = urReceiptDate;
        this.isShowURReceiptDate = isShowURReceiptDate;
        this.urPrimaryLocation = urPrimaryLocation;
        this.isShowURPrimaryLocation = isShowURPrimaryLocation;
        this.urReceiptNumber = urReceiptNumber;
        this.isShowURReceiptNumber = isShowURReceiptNumber;
    }

    public String getUrReceiptType() {
        return urReceiptType;
    }

    public void setUrReceiptType(String urReceiptType) {
        this.urReceiptType = urReceiptType;
    }

    public boolean isShowURReceiptType() {
        return isShowURReceiptType;
    }

    public void setShowURReceiptType(boolean showURReceiptType) {
        isShowURReceiptType = showURReceiptType;
    }

    public String getUrSubTotalBeforeTax() {
        return urSubTotalBeforeTax;
    }

    public void setUrSubTotalBeforeTax(String urSubTotalBeforeTax) {
        this.urSubTotalBeforeTax = urSubTotalBeforeTax;
    }

    public boolean isShowURSubTotalBeforeTax() {
        return isShowURSubTotalBeforeTax;
    }

    public void setShowURSubTotalBeforeTax(boolean showURSubTotalBeforeTax) {
        isShowURSubTotalBeforeTax = showURSubTotalBeforeTax;
    }

    public String getUrReceiptDate() {
        return urReceiptDate;
    }

    public void setUrReceiptDate(String urReceiptDate) {
        this.urReceiptDate = urReceiptDate;
    }

    public boolean isShowURReceiptDate() {
        return isShowURReceiptDate;
    }

    public void setShowURReceiptDate(boolean showURReceiptDate) {
        isShowURReceiptDate = showURReceiptDate;
    }

    public String getUrPrimaryLocation() {
        return urPrimaryLocation;
    }

    public void setUrPrimaryLocation(String urPrimaryLocation) {
        this.urPrimaryLocation = urPrimaryLocation;
    }

    public boolean isShowURPrimaryLocation() {
        return isShowURPrimaryLocation;
    }

    public void setShowURPrimaryLocation(boolean showURPrimaryLocation) {
        isShowURPrimaryLocation = showURPrimaryLocation;
    }

    public String getUrReceiptNumber() {
        return urReceiptNumber;
    }

    public void setUrReceiptNumber(String urReceiptNumber) {
        this.urReceiptNumber = urReceiptNumber;
    }

    public boolean isShowURReceiptNumber() {
        return isShowURReceiptNumber;
    }

    public void setShowURReceiptNumber(boolean showURReceiptNumber) {
        isShowURReceiptNumber = showURReceiptNumber;
    }
}
