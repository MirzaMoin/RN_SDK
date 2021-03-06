package com.example.robosdk.Models;

public class SocialShareChildPageDataModel {
    public double opacity;
    public boolean isClickable;
    public String image,
            linkType,
            internalLink,
            externalLink;

    public SocialShareChildPageDataModel(String image, double opacity, boolean isClickable, String linkType, String internalLink, String externalLink) {
        this.image = image;
        this.opacity = opacity;
        this.isClickable = isClickable;
        this.linkType = linkType;
        this.internalLink = internalLink;
        this.externalLink = externalLink;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getOpacity() {
        return opacity;
    }

    public void setOpacity(double opacity) {
        this.opacity = opacity;
    }

    public boolean isClickable() {
        return isClickable;
    }

    public void setClickable(boolean clickable) {
        isClickable = clickable;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public String getInternalLink() {
        return internalLink;
    }

    public void setInternalLink(String internalLink) {
        this.internalLink = internalLink;
    }

    public String getExternalLink() {
        return externalLink;
    }

    public void setExternalLink(String externalLink) {
        this.externalLink = externalLink;
    }
}