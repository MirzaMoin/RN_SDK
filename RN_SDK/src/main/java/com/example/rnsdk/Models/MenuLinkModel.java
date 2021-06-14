package com.example.rnsdk.Models;

public class MenuLinkModel{
    public int id;
    public String rewardProgramId;
    public String menuText;
    public String menuTextColor;
    public String menuLinkType;
    public String menuInternalLinkUrl;
    public String menuExternalLinkUrl;
    public String icon;
    public String menuTopColor;
    public String menuBottomColor;
    public String menuBackgroudImage;

    public MenuLinkModel(int id, String rewardProgramId, String menuText, String menuTextColor, String menuLinkType, String menuInternalLinkUrl, String menuExternalLinkUrl, String icon, String menuTopColor, String menuBottomColor, String menuBackgroudImage) {
        this.id = id;
        this.rewardProgramId = rewardProgramId;
        this.menuText = menuText;
        this.menuTextColor = menuTextColor;
        this.menuLinkType = menuLinkType;
        this.menuInternalLinkUrl = menuInternalLinkUrl;
        this.menuExternalLinkUrl = menuExternalLinkUrl;
        this.icon = icon;
        this.menuTopColor = menuTopColor;
        this.menuBottomColor = menuBottomColor;
        this.menuBackgroudImage = menuBackgroudImage;
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

    public String getMenuText() {
        return menuText;
    }

    public void setMenuText(String menuText) {
        this.menuText = menuText;
    }

    public String getMenuTextColor() {
        return menuTextColor;
    }

    public void setMenuTextColor(String menuTextColor) {
        this.menuTextColor = menuTextColor;
    }

    public String getMenuLinkType() {
        return menuLinkType;
    }

    public void setMenuLinkType(String menuLinkType) {
        this.menuLinkType = menuLinkType;
    }

    public String getMenuInternalLinkUrl() {
        return menuInternalLinkUrl;
    }

    public void setMenuInternalLinkUrl(String menuInternalLinkUrl) {
        this.menuInternalLinkUrl = menuInternalLinkUrl;
    }

    public String getMenuExternalLinkUrl() {
        return menuExternalLinkUrl;
    }

    public void setMenuExternalLinkUrl(String menuExternalLinkUrl) {
        this.menuExternalLinkUrl = menuExternalLinkUrl;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMenuTopColor() {
        return menuTopColor;
    }

    public void setMenuTopColor(String menuTopColor) {
        this.menuTopColor = menuTopColor;
    }

    public String getMenuBottomColor() {
        return menuBottomColor;
    }

    public void setMenuBottomColor(String menuBottomColor) {
        this.menuBottomColor = menuBottomColor;
    }

    public String getMenuBackgroudImage() {
        return menuBackgroudImage;
    }

    public void setMenuBackgroudImage(String menuBackgroudImage) {
        this.menuBackgroudImage = menuBackgroudImage;
    }
}
