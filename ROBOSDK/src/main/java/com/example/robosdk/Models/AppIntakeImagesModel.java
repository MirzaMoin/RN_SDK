package com.example.robosdk.Models;

import java.util.List;

public class AppIntakeImagesModel {
    public List<LoadingImageModel> loadingImages;
    public String appIcon, companyLogo;

    public AppIntakeImagesModel(List<LoadingImageModel> loadingImages, String appIcon, String companyLogo) {
        this.loadingImages = loadingImages;
        this.appIcon = appIcon;
        this.companyLogo = companyLogo;
    }

    public List<LoadingImageModel> getLoadingImages() {
        return loadingImages;
    }

    public void setLoadingImages(List<LoadingImageModel> loadingImages) {
        this.loadingImages = loadingImages;
    }

    public String getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }
}