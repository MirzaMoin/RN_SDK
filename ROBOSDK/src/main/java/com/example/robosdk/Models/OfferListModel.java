package com.example.robosdk.Models;

public class OfferListModel {
    public int offerID;
    public String offerSendID,
            offerTitle,
            titleColor,
            offerDescription,
            descColor,
            offerType,
            offerImage,
            offerImagelabel,
            offerBarcode,
            offerExpire;
    public boolean displayPrintButton,
            displayBarcode,
            allowContactRedeemOffer;

    public OfferListModel(int offerID, String offerSendID, String offerTitle, String titleColor, String offerDescription, String descColor, String offerType, String offerImage, String offerImagelabel, String offerBarcode, String offerExpire, boolean displayPrintButton, boolean displayBarcode, boolean allowContactRedeemOffer) {
        this.offerID = offerID;
        this.offerSendID = offerSendID;
        this.offerTitle = offerTitle;
        this.titleColor = titleColor;
        this.offerDescription = offerDescription;
        this.descColor = descColor;
        this.offerType = offerType;
        this.offerImage = offerImage;
        this.offerImagelabel = offerImagelabel;
        this.offerBarcode = offerBarcode;
        this.offerExpire = offerExpire;
        this.displayPrintButton = displayPrintButton;
        this.displayBarcode = displayBarcode;
        this.allowContactRedeemOffer = allowContactRedeemOffer;
    }

    public int getOfferID() {
        return offerID;
    }

    public void setOfferID(int offerID) {
        this.offerID = offerID;
    }

    public String getOfferSendID() {
        return offerSendID;
    }

    public void setOfferSendID(String offerSendID) {
        this.offerSendID = offerSendID;
    }

    public String getOfferTitle() {
        return offerTitle;
    }

    public void setOfferTitle(String offerTitle) {
        this.offerTitle = offerTitle;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    public String getOfferDescription() {
        return offerDescription;
    }

    public void setOfferDescription(String offerDescription) {
        this.offerDescription = offerDescription;
    }

    public String getDescColor() {
        return descColor;
    }

    public void setDescColor(String descColor) {
        this.descColor = descColor;
    }

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public String getOfferImage() {
        return offerImage;
    }

    public void setOfferImage(String offerImage) {
        this.offerImage = offerImage;
    }

    public String getOfferImagelabel() {
        return offerImagelabel;
    }

    public void setOfferImagelabel(String offerImagelabel) {
        this.offerImagelabel = offerImagelabel;
    }

    public String getOfferBarcode() {
        return offerBarcode;
    }

    public void setOfferBarcode(String offerBarcode) {
        this.offerBarcode = offerBarcode;
    }

    public String getOfferExpire() {
        return offerExpire;
    }

    public void setOfferExpire(String offerExpire) {
        this.offerExpire = offerExpire;
    }

    public boolean isDisplayPrintButton() {
        return displayPrintButton;
    }

    public void setDisplayPrintButton(boolean displayPrintButton) {
        this.displayPrintButton = displayPrintButton;
    }

    public boolean isDisplayBarcode() {
        return displayBarcode;
    }

    public void setDisplayBarcode(boolean displayBarcode) {
        this.displayBarcode = displayBarcode;
    }

    public boolean isAllowContactRedeemOffer() {
        return allowContactRedeemOffer;
    }

    public void setAllowContactRedeemOffer(boolean allowContactRedeemOffer) {
        this.allowContactRedeemOffer = allowContactRedeemOffer;
    }
}
