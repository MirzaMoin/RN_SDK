package com.example.robosdk.Models;

public class RPGListModel {
    public String title,
            details,
            image;
    public int pointValue;
    public boolean isActive;

    public RPGListModel(String title, String details, String image, int pointValue, boolean isActive) {
        this.title = title;
        this.details = details;
        this.image = image;
        this.pointValue = pointValue;
        this.isActive = isActive;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPointValue() {
        return pointValue;
    }

    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
