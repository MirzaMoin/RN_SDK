package com.example.rnsdk.Models;

public class LocationStoreAddressModel {

    public String address;
    public String address2;
    public String address3;
    public String city;
    public String zipCode;
    public String state;
    public String latitude;
    public String longitude;

    public LocationStoreAddressModel(String address, String address2, String address3, String city, String zipCode, String state, String latitude, String longitude) {
        this.address = address;
        this.address2 = address2;
        this.address3 = address3;
        this.city = city;
        this.zipCode = zipCode;
        this.state = state;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
