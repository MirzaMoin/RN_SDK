package com.example.robosdk.Models;

public class WaysToEarnModel {
    public String imageURL,
            title,
            description,
            subtitle;
    public double points;
    public boolean isVisible;

    public WaysToEarnModel(boolean isVisible, String imageURL, String title, String description, String subtitle, double points) {
        this.isVisible = isVisible;
        this.imageURL = imageURL;
        this.title = title;
        this.description = description;
        this.subtitle = subtitle;
        this.points = points;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }
}
