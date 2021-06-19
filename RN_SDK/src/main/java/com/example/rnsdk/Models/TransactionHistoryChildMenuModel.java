package com.example.rnsdk.Models;

import android.util.Log;

import java.util.List;

public class TransactionHistoryChildMenuModel {
    public String name;
    public Object value;
    public String stringValue;
    public List<String> images;

    public TransactionHistoryChildMenuModel(String name, Object value, String stringValue, List<String> images) {
        this.name = name;
        this.value = value;
        this.stringValue = stringValue;
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
