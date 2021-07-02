package com.example.rnsdk.Models;

import java.util.List;

public class URCategoryModel {
    public String id;
    public String name;
    boolean isSelected;
    public List<URAddressModel> addresses;

    public URCategoryModel(String id, String name, List<URAddressModel> addresses) {
        this.id = id;
        this.name = name;
        this.addresses = addresses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<URAddressModel> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<URAddressModel> addresses) {
        this.addresses = addresses;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
