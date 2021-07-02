package com.example.rnsdk.Models;

public class LocationDataModel {
    public String addressId;
    public String locationName;
    public LocationStoreAddressModel storeAddress;
    public String emailAddress;
    public Object mobilePhone;
    public String websiteUrl;
    public String logoImage;
    public boolean isSelected;

    public LocationDataModel(String addressId, String locationName, LocationStoreAddressModel storeAddress, String emailAddress, Object mobilePhone, String websiteUrl, String logoImage) {
        this.addressId = addressId;
        this.locationName = locationName;
        this.storeAddress = storeAddress;
        this.emailAddress = emailAddress;
        this.mobilePhone = mobilePhone;
        this.websiteUrl = websiteUrl;
        this.logoImage = logoImage;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public LocationStoreAddressModel getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(LocationStoreAddressModel storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Object getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(Object mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(String logoImage) {
        this.logoImage = logoImage;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
