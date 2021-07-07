package com.example.robosdk.Models;

public class FooterLinkModel{
    public int id;
    public String rewardProgramId;
    public String footerText;
    public String footerLinkType;
    public String footerInternalLinkUrl;
    public String footerExternalLinkUrl;
    public String footerIcon;
    public boolean isActive;


    public FooterLinkModel(int id, String rewardProgramId, String footerText, String footerLinkType, String footerInternalLinkUrl, String footerExternalLinkUrl, String footerIcon, boolean isActive) {
        this.id = id;
        this.rewardProgramId = rewardProgramId;
        this.footerText = footerText;
        this.footerLinkType = footerLinkType;
        this.footerInternalLinkUrl = footerInternalLinkUrl;
        this.footerExternalLinkUrl = footerExternalLinkUrl;
        this.footerIcon = footerIcon;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRewardProgramId() {
        return rewardProgramId;
    }

    public void setRewardProgramId(String rewardProgramId) {
        this.rewardProgramId = rewardProgramId;
    }

    public String getFooterText() {
        return footerText;
    }

    public void setFooterText(String footerText) {
        this.footerText = footerText;
    }

    public String getFooterLinkType() {
        return footerLinkType;
    }

    public void setFooterLinkType(String footerLinkType) {
        this.footerLinkType = footerLinkType;
    }

    public String getFooterInternalLinkUrl() {
        return footerInternalLinkUrl;
    }

    public void setFooterInternalLinkUrl(String footerInternalLinkUrl) {
        this.footerInternalLinkUrl = footerInternalLinkUrl;
    }

    public String getFooterExternalLinkUrl() {
        return footerExternalLinkUrl;
    }

    public void setFooterExternalLinkUrl(String footerExternalLinkUrl) {
        this.footerExternalLinkUrl = footerExternalLinkUrl;
    }

    public String getFooterIcon() {
        return footerIcon;
    }

    public void setFooterIcon(String footerIcon) {
        this.footerIcon = footerIcon;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

