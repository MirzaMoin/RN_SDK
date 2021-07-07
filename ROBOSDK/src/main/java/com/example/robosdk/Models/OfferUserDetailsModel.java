package com.example.robosdk.Models;

public class OfferUserDetailsModel {
    public String mobilePhone;
    public String memberCardID;
    public String addressID;

    public OfferUserDetailsModel(String mobilePhone, String memberCardID, String addressID) {
        this.mobilePhone = mobilePhone;
        this.memberCardID = memberCardID;
        this.addressID = addressID;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getMemberCardID() {
        return memberCardID;
    }

    public void setMemberCardID(String memberCardID) {
        this.memberCardID = memberCardID;
    }

    public String getAddressID() {
        return addressID;
    }

    public void setAddressID(String addressID) {
        this.addressID = addressID;
    }
}
