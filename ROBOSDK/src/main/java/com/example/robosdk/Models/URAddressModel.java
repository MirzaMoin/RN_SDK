package com.example.robosdk.Models;

public class URAddressModel {
    public String addressID;
    public String locationName;
    public boolean isSelected;

    public URAddressModel(String addressID, String locationName) {
        this.addressID = addressID;
        this.locationName = locationName;
    }

    public String getAddressID() {
        return addressID;
    }

    public void setAddressID(String addressID) {
        this.addressID = addressID;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
