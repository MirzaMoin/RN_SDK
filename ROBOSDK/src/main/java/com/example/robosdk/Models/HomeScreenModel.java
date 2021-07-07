package com.example.robosdk.Models;


import java.util.List;

public class HomeScreenModel {
    public String ribbonIcon,
            homePageRibbonPosition,
            homePageRibbonIcon,
            homePageRibbonText,
            homePageRibbonIconPosition,
            homePageRibbonTextColor,
            homePageRibbonBackgroundColor,
            homePageRibbonLinkType,
            homePageRibbonLinkInternal,
            homePageRibbonLinkExternal,
            homePageTopBackgroundImage,
            homePageTopBackgroundGradientStartColor,
            homePageTopBackgroundGradientStopColor,
            homePageTopTextLine1,
            homePageTopTextLine1Color,
            homePageTopTextUnderLine1Color,
            homePageTopTextLine2,
            homePageTopTextLine2Color,
            homePageTopTextUnderLine2Color,
            homePageDisplayTopButton,
            homePageTopButtonText,
            homePageTopButtonTextColor,
            homePageTopButtonGradientStartColor,
            homePageTopButtonGradientStopColor,
            homePageTopButtonLinkType,
            homePageTopButtonLinkInternal,
            homePageTopButtonLinkExternal,
            homePageBottomBackgroundImage,
            homePageBottomBackgroundGradientStartColor,
            homePageBottomBackgroundGradientStopColor,
            homePageBottomTextAlign,
            homePageBottomArrowColor,
            homePageBottomIconBackgroundColor,
            homePageBottomIconColor,
            homePageBottomIconShape,
            homePageBackgroundImage,
            homePageBackgroundGradientStartColor,
            homePageBackgroundGradientStopColor,
            homePageTopHeader3Type,
            homePageTopDescriptionText,
            homePageTopTextLine3Color,
            homePageHeaderMenuText;


    public boolean homePageBottomDisplayIcon,
            homePageBottomDisplayArrowIcon,
            homePageDisplayFooter,
            homePageGridMenuLayout,
            homePageTopHeader3,
            homePageDisplayRibbon,
            homePageRibbonDisplayIcon,
            homePageRibbonTextMarquee,
            headerTokensCapital1,
            homePageTopTextUnderLine1,
            headerTokensCapital2,
            homePageTopTextUnderLine2,
            homePageDisplayHeader,
            homePageDisplayHeaderMenu,
            homePageDisplayHeaderProfile;

    public List<MenuLinkModel> menuLinks;
    public List<FooterLinkModel> footerLinks;
    public HomeScreenPointsSettingsModel homeScreenPointsSettings;

    public HomeScreenModel(boolean homePageDisplayRibbon, boolean homePageRibbonDisplayIcon, String ribbonIcon, String homePageRibbonPosition, String homePageRibbonIcon, String homePageRibbonText, String homePageRibbonIconPosition, boolean homePageRibbonTextMarquee, String homePageRibbonTextColor, String homePageRibbonBackgroundColor, String homePageRibbonLinkType, String homePageRibbonLinkInternal, String homePageRibbonLinkExternal, String homePageTopBackgroundImage, String homePageTopBackgroundGradientStartColor, String homePageTopBackgroundGradientStopColor, String homePageTopTextLine1, boolean headerTokensCapital1, String homePageTopTextLine1Color, boolean homePageTopTextUnderLine1, String homePageTopTextUnderLine1Color, String homePageTopTextLine2, boolean headerTokensCapital2, String homePageTopTextLine2Color, boolean homePageTopTextUnderLine2, String homePageTopTextUnderLine2Color, String homePageDisplayTopButton, String homePageTopButtonText, String homePageTopButtonTextColor, String homePageTopButtonGradientStartColor, String homePageTopButtonGradientStopColor, String homePageTopButtonLinkType, String homePageTopButtonLinkInternal, String homePageTopButtonLinkExternal, String homePageBottomBackgroundImage, String homePageBottomBackgroundGradientStartColor, String homePageBottomBackgroundGradientStopColor, boolean homePageBottomDisplayIcon, boolean homePageBottomDisplayArrowIcon, String homePageBottomArrowColor, String homePageBottomTextAlign, boolean homePageDisplayFooter, String homePageBottomIconShape, String homePageBottomIconColor, String homePageBottomIconBackgroundColor, boolean homePageGridMenuLayout, String homePageBackgroundImage, String homePageBackgroundGradientStartColor, String homePageBackgroundGradientStopColor, boolean homePageTopHeader3, String homePageTopHeader3Type, String homePageTopDescriptionText, String homePageTopTextLine3Color, List<MenuLinkModel> menuLinks, List<FooterLinkModel> footerLinks, HomeScreenPointsSettingsModel homeScreenPointsSettings, boolean homePageDisplayHeader, boolean homePageDisplayHeaderMenu, String homePageHeaderMenuText, boolean homePageDisplayHeaderProfile) {
        this.homePageDisplayRibbon = homePageDisplayRibbon;
        this.homePageRibbonDisplayIcon = homePageRibbonDisplayIcon;
        this.ribbonIcon = ribbonIcon;
        this.homePageRibbonPosition = homePageRibbonPosition;
        this.homePageRibbonIcon = homePageRibbonIcon;
        this.homePageRibbonText = homePageRibbonText;
        this.homePageRibbonIconPosition = homePageRibbonIconPosition;
        this.homePageRibbonTextMarquee = homePageRibbonTextMarquee;
        this.homePageRibbonTextColor = homePageRibbonTextColor;
        this.homePageRibbonBackgroundColor = homePageRibbonBackgroundColor;
        this.homePageRibbonLinkType = homePageRibbonLinkType;
        this.homePageRibbonLinkInternal = homePageRibbonLinkInternal;
        this.homePageRibbonLinkExternal = homePageRibbonLinkExternal;
        this.homePageTopBackgroundImage = homePageTopBackgroundImage;
        this.homePageTopBackgroundGradientStartColor = homePageTopBackgroundGradientStartColor;
        this.homePageTopBackgroundGradientStopColor = homePageTopBackgroundGradientStopColor;
        this.homePageTopTextLine1 = homePageTopTextLine1;
        this.headerTokensCapital1 = headerTokensCapital1;
        this.homePageTopTextLine1Color = homePageTopTextLine1Color;
        this.homePageTopTextUnderLine1 = homePageTopTextUnderLine1;
        this.homePageTopTextUnderLine1Color = homePageTopTextUnderLine1Color;
        this.homePageTopTextLine2 = homePageTopTextLine2;
        this.headerTokensCapital2 = headerTokensCapital2;
        this.homePageTopTextLine2Color = homePageTopTextLine2Color;
        this.homePageTopTextUnderLine2 = homePageTopTextUnderLine2;
        this.homePageTopTextUnderLine2Color = homePageTopTextUnderLine2Color;
        this.homePageDisplayTopButton = homePageDisplayTopButton;
        this.homePageTopButtonText = homePageTopButtonText;
        this.homePageTopButtonTextColor = homePageTopButtonTextColor;
        this.homePageTopButtonGradientStartColor = homePageTopButtonGradientStartColor;
        this.homePageTopButtonGradientStopColor = homePageTopButtonGradientStopColor;
        this.homePageTopButtonLinkType = homePageTopButtonLinkType;
        this.homePageTopButtonLinkInternal = homePageTopButtonLinkInternal;
        this.homePageTopButtonLinkExternal = homePageTopButtonLinkExternal;
        this.homePageBottomBackgroundImage = homePageBottomBackgroundImage;
        this.homePageBottomBackgroundGradientStartColor = homePageBottomBackgroundGradientStartColor;
        this.homePageBottomBackgroundGradientStopColor = homePageBottomBackgroundGradientStopColor;
        this.homePageBottomDisplayIcon = homePageBottomDisplayIcon;
        this.homePageBottomDisplayArrowIcon = homePageBottomDisplayArrowIcon;
        this.homePageBottomArrowColor = homePageBottomArrowColor;
        this.homePageBottomTextAlign = homePageBottomTextAlign;
        this.homePageDisplayFooter = homePageDisplayFooter;
        this.homePageBottomIconShape = homePageBottomIconShape;
        this.homePageBottomIconColor = homePageBottomIconColor;
        this.homePageBottomIconBackgroundColor = homePageBottomIconBackgroundColor;
        this.homePageGridMenuLayout = homePageGridMenuLayout;
        this.homePageBackgroundImage = homePageBackgroundImage;
        this.homePageBackgroundGradientStartColor = homePageBackgroundGradientStartColor;
        this.homePageBackgroundGradientStopColor = homePageBackgroundGradientStopColor;
        this.homePageTopHeader3 = homePageTopHeader3;
        this.homePageTopHeader3Type = homePageTopHeader3Type;
        this.homePageTopDescriptionText = homePageTopDescriptionText;
        this.homePageTopTextLine3Color = homePageTopTextLine3Color;
        this.menuLinks = menuLinks;
        this.footerLinks = footerLinks;
        this.homeScreenPointsSettings = homeScreenPointsSettings;
        this.homePageDisplayHeader = homePageDisplayHeader;
        this.homePageDisplayHeaderMenu = homePageDisplayHeaderMenu;
        this.homePageHeaderMenuText = homePageHeaderMenuText;
        this.homePageDisplayHeaderProfile = homePageDisplayHeaderProfile;
    }

    public boolean isHomePageDisplayRibbon() {
        return homePageDisplayRibbon;
    }

    public void setHomePageDisplayRibbon(boolean homePageDisplayRibbon) {
        this.homePageDisplayRibbon = homePageDisplayRibbon;
    }

    public boolean isHomePageRibbonDisplayIcon() {
        return homePageRibbonDisplayIcon;
    }

    public void setHomePageRibbonDisplayIcon(boolean homePageRibbonDisplayIcon) {
        this.homePageRibbonDisplayIcon = homePageRibbonDisplayIcon;
    }

    public String getRibbonIcon() {
        return ribbonIcon;
    }

    public void setRibbonIcon(String ribbonIcon) {
        this.ribbonIcon = ribbonIcon;
    }

    public String getHomePageRibbonPosition() {
        return homePageRibbonPosition;
    }

    public void setHomePageRibbonPosition(String homePageRibbonPosition) {
        this.homePageRibbonPosition = homePageRibbonPosition;
    }

    public String getHomePageRibbonIcon() {
        return homePageRibbonIcon;
    }

    public void setHomePageRibbonIcon(String homePageRibbonIcon) {
        this.homePageRibbonIcon = homePageRibbonIcon;
    }

    public String getHomePageRibbonText() {
        return homePageRibbonText;
    }

    public void setHomePageRibbonText(String homePageRibbonText) {
        this.homePageRibbonText = homePageRibbonText;
    }

    public String getHomePageRibbonIconPosition() {
        return homePageRibbonIconPosition;
    }

    public void setHomePageRibbonIconPosition(String homePageRibbonIconPosition) {
        this.homePageRibbonIconPosition = homePageRibbonIconPosition;
    }

    public boolean isHomePageRibbonTextMarquee() {
        return homePageRibbonTextMarquee;
    }

    public void setHomePageRibbonTextMarquee(boolean homePageRibbonTextMarquee) {
        this.homePageRibbonTextMarquee = homePageRibbonTextMarquee;
    }

    public String getHomePageRibbonTextColor() {
        return homePageRibbonTextColor;
    }

    public void setHomePageRibbonTextColor(String homePageRibbonTextColor) {
        this.homePageRibbonTextColor = homePageRibbonTextColor;
    }

    public String getHomePageRibbonBackgroundColor() {
        return homePageRibbonBackgroundColor;
    }

    public void setHomePageRibbonBackgroundColor(String homePageRibbonBackgroundColor) {
        this.homePageRibbonBackgroundColor = homePageRibbonBackgroundColor;
    }

    public String getHomePageRibbonLinkType() {
        return homePageRibbonLinkType;
    }

    public void setHomePageRibbonLinkType(String homePageRibbonLinkType) {
        this.homePageRibbonLinkType = homePageRibbonLinkType;
    }

    public String getHomePageRibbonLinkInternal() {
        return homePageRibbonLinkInternal;
    }

    public void setHomePageRibbonLinkInternal(String homePageRibbonLinkInternal) {
        this.homePageRibbonLinkInternal = homePageRibbonLinkInternal;
    }

    public String getHomePageRibbonLinkExternal() {
        return homePageRibbonLinkExternal;
    }

    public void setHomePageRibbonLinkExternal(String homePageRibbonLinkExternal) {
        this.homePageRibbonLinkExternal = homePageRibbonLinkExternal;
    }

    public String getHomePageTopBackgroundImage() {
        return homePageTopBackgroundImage;
    }

    public void setHomePageTopBackgroundImage(String homePageTopBackgroundImage) {
        this.homePageTopBackgroundImage = homePageTopBackgroundImage;
    }

    public String getHomePageTopBackgroundGradientStartColor() {
        return homePageTopBackgroundGradientStartColor;
    }

    public void setHomePageTopBackgroundGradientStartColor(String homePageTopBackgroundGradientStartColor) {
        this.homePageTopBackgroundGradientStartColor = homePageTopBackgroundGradientStartColor;
    }

    public String getHomePageTopBackgroundGradientStopColor() {
        return homePageTopBackgroundGradientStopColor;
    }

    public void setHomePageTopBackgroundGradientStopColor(String homePageTopBackgroundGradientStopColor) {
        this.homePageTopBackgroundGradientStopColor = homePageTopBackgroundGradientStopColor;
    }

    public String getHomePageTopTextLine1() {
        return homePageTopTextLine1;
    }

    public void setHomePageTopTextLine1(String homePageTopTextLine1) {
        this.homePageTopTextLine1 = homePageTopTextLine1;
    }

    public boolean isHeaderTokensCapital1() {
        return headerTokensCapital1;
    }

    public void setHeaderTokensCapital1(boolean headerTokensCapital1) {
        this.headerTokensCapital1 = headerTokensCapital1;
    }

    public String getHomePageTopTextLine1Color() {
        return homePageTopTextLine1Color;
    }

    public void setHomePageTopTextLine1Color(String homePageTopTextLine1Color) {
        this.homePageTopTextLine1Color = homePageTopTextLine1Color;
    }

    public boolean isHomePageTopTextUnderLine1() {
        return homePageTopTextUnderLine1;
    }

    public void setHomePageTopTextUnderLine1(boolean homePageTopTextUnderLine1) {
        this.homePageTopTextUnderLine1 = homePageTopTextUnderLine1;
    }

    public String getHomePageTopTextUnderLine1Color() {
        return homePageTopTextUnderLine1Color;
    }

    public void setHomePageTopTextUnderLine1Color(String homePageTopTextUnderLine1Color) {
        this.homePageTopTextUnderLine1Color = homePageTopTextUnderLine1Color;
    }

    public String getHomePageTopTextLine2() {
        return homePageTopTextLine2;
    }

    public void setHomePageTopTextLine2(String homePageTopTextLine2) {
        this.homePageTopTextLine2 = homePageTopTextLine2;
    }

    public boolean isHeaderTokensCapital2() {
        return headerTokensCapital2;
    }

    public void setHeaderTokensCapital2(boolean headerTokensCapital2) {
        this.headerTokensCapital2 = headerTokensCapital2;
    }

    public String getHomePageTopTextLine2Color() {
        return homePageTopTextLine2Color;
    }

    public void setHomePageTopTextLine2Color(String homePageTopTextLine2Color) {
        this.homePageTopTextLine2Color = homePageTopTextLine2Color;
    }

    public boolean isHomePageTopTextUnderLine2() {
        return homePageTopTextUnderLine2;
    }

    public void setHomePageTopTextUnderLine2(boolean homePageTopTextUnderLine2) {
        this.homePageTopTextUnderLine2 = homePageTopTextUnderLine2;
    }

    public String getHomePageTopTextUnderLine2Color() {
        return homePageTopTextUnderLine2Color;
    }

    public void setHomePageTopTextUnderLine2Color(String homePageTopTextUnderLine2Color) {
        this.homePageTopTextUnderLine2Color = homePageTopTextUnderLine2Color;
    }

    public String getHomePageDisplayTopButton() {
        return homePageDisplayTopButton;
    }

    public void setHomePageDisplayTopButton(String homePageDisplayTopButton) {
        this.homePageDisplayTopButton = homePageDisplayTopButton;
    }

    public String getHomePageTopButtonText() {
        return homePageTopButtonText;
    }

    public void setHomePageTopButtonText(String homePageTopButtonText) {
        this.homePageTopButtonText = homePageTopButtonText;
    }

    public String getHomePageTopButtonTextColor() {
        return homePageTopButtonTextColor;
    }

    public void setHomePageTopButtonTextColor(String homePageTopButtonTextColor) {
        this.homePageTopButtonTextColor = homePageTopButtonTextColor;
    }

    public String getHomePageTopButtonGradientStartColor() {
        return homePageTopButtonGradientStartColor;
    }

    public void setHomePageTopButtonGradientStartColor(String homePageTopButtonGradientStartColor) {
        this.homePageTopButtonGradientStartColor = homePageTopButtonGradientStartColor;
    }

    public String getHomePageTopButtonGradientStopColor() {
        return homePageTopButtonGradientStopColor;
    }

    public void setHomePageTopButtonGradientStopColor(String homePageTopButtonGradientStopColor) {
        this.homePageTopButtonGradientStopColor = homePageTopButtonGradientStopColor;
    }

    public String getHomePageTopButtonLinkType() {
        return homePageTopButtonLinkType;
    }

    public void setHomePageTopButtonLinkType(String homePageTopButtonLinkType) {
        this.homePageTopButtonLinkType = homePageTopButtonLinkType;
    }

    public String getHomePageTopButtonLinkInternal() {
        return homePageTopButtonLinkInternal;
    }

    public void setHomePageTopButtonLinkInternal(String homePageTopButtonLinkInternal) {
        this.homePageTopButtonLinkInternal = homePageTopButtonLinkInternal;
    }

    public String getHomePageTopButtonLinkExternal() {
        return homePageTopButtonLinkExternal;
    }

    public void setHomePageTopButtonLinkExternal(String homePageTopButtonLinkExternal) {
        this.homePageTopButtonLinkExternal = homePageTopButtonLinkExternal;
    }

    public String getHomePageBottomBackgroundImage() {
        return homePageBottomBackgroundImage;
    }

    public void setHomePageBottomBackgroundImage(String homePageBottomBackgroundImage) {
        this.homePageBottomBackgroundImage = homePageBottomBackgroundImage;
    }

    public String getHomePageBottomBackgroundGradientStartColor() {
        return homePageBottomBackgroundGradientStartColor;
    }

    public void setHomePageBottomBackgroundGradientStartColor(String homePageBottomBackgroundGradientStartColor) {
        this.homePageBottomBackgroundGradientStartColor = homePageBottomBackgroundGradientStartColor;
    }

    public String getHomePageBottomBackgroundGradientStopColor() {
        return homePageBottomBackgroundGradientStopColor;
    }

    public void setHomePageBottomBackgroundGradientStopColor(String homePageBottomBackgroundGradientStopColor) {
        this.homePageBottomBackgroundGradientStopColor = homePageBottomBackgroundGradientStopColor;
    }

    public boolean isHomePageBottomDisplayIcon() {
        return homePageBottomDisplayIcon;
    }

    public void setHomePageBottomDisplayIcon(boolean homePageBottomDisplayIcon) {
        this.homePageBottomDisplayIcon = homePageBottomDisplayIcon;
    }

    public boolean isHomePageBottomDisplayArrowIcon() {
        return homePageBottomDisplayArrowIcon;
    }

    public void setHomePageBottomDisplayArrowIcon(boolean homePageBottomDisplayArrowIcon) {
        this.homePageBottomDisplayArrowIcon = homePageBottomDisplayArrowIcon;
    }

    public String getHomePageBottomArrowColor() {
        return homePageBottomArrowColor;
    }

    public void setHomePageBottomArrowColor(String homePageBottomArrowColor) {
        this.homePageBottomArrowColor = homePageBottomArrowColor;
    }

    public String getHomePageBottomTextAlign() {
        return homePageBottomTextAlign;
    }

    public void setHomePageBottomTextAlign(String homePageBottomTextAlign) {
        this.homePageBottomTextAlign = homePageBottomTextAlign;
    }

    public boolean isHomePageDisplayFooter() {
        return homePageDisplayFooter;
    }

    public void setHomePageDisplayFooter(boolean homePageDisplayFooter) {
        this.homePageDisplayFooter = homePageDisplayFooter;
    }

    public String getHomePageBottomIconShape() {
        return homePageBottomIconShape;
    }

    public void setHomePageBottomIconShape(String homePageBottomIconShape) {
        this.homePageBottomIconShape = homePageBottomIconShape;
    }

    public String getHomePageBottomIconColor() {
        return homePageBottomIconColor;
    }

    public void setHomePageBottomIconColor(String homePageBottomIconColor) {
        this.homePageBottomIconColor = homePageBottomIconColor;
    }

    public String getHomePageBottomIconBackgroundColor() {
        return homePageBottomIconBackgroundColor;
    }

    public void setHomePageBottomIconBackgroundColor(String homePageBottomIconBackgroundColor) {
        this.homePageBottomIconBackgroundColor = homePageBottomIconBackgroundColor;
    }

    public boolean isHomePageGridMenuLayout() {
        return homePageGridMenuLayout;
    }

    public void setHomePageGridMenuLayout(boolean homePageGridMenuLayout) {
        this.homePageGridMenuLayout = homePageGridMenuLayout;
    }

    public String getHomePageBackgroundImage() {
        return homePageBackgroundImage;
    }

    public void setHomePageBackgroundImage(String homePageBackgroundImage) {
        this.homePageBackgroundImage = homePageBackgroundImage;
    }

    public String getHomePageBackgroundGradientStartColor() {
        return homePageBackgroundGradientStartColor;
    }

    public void setHomePageBackgroundGradientStartColor(String homePageBackgroundGradientStartColor) {
        this.homePageBackgroundGradientStartColor = homePageBackgroundGradientStartColor;
    }

    public String getHomePageBackgroundGradientStopColor() {
        return homePageBackgroundGradientStopColor;
    }

    public void setHomePageBackgroundGradientStopColor(String homePageBackgroundGradientStopColor) {
        this.homePageBackgroundGradientStopColor = homePageBackgroundGradientStopColor;
    }

    public boolean isHomePageTopHeader3() {
        return homePageTopHeader3;
    }

    public void setHomePageTopHeader3(boolean homePageTopHeader3) {
        this.homePageTopHeader3 = homePageTopHeader3;
    }

    public String getHomePageTopHeader3Type() {
        return homePageTopHeader3Type;
    }

    public void setHomePageTopHeader3Type(String homePageTopHeader3Type) {
        this.homePageTopHeader3Type = homePageTopHeader3Type;
    }

    public String getHomePageTopDescriptionText() {
        return homePageTopDescriptionText;
    }

    public void setHomePageTopDescriptionText(String homePageTopDescriptionText) {
        this.homePageTopDescriptionText = homePageTopDescriptionText;
    }

    public String getHomePageTopTextLine3Color() {
        return homePageTopTextLine3Color;
    }

    public void setHomePageTopTextLine3Color(String homePageTopTextLine3Color) {
        this.homePageTopTextLine3Color = homePageTopTextLine3Color;
    }

    public List<MenuLinkModel> getMenuLinks() {
        return menuLinks;
    }

    public void setMenuLinks(List<MenuLinkModel> menuLinks) {
        this.menuLinks = menuLinks;
    }

    public List<FooterLinkModel> getFooterLinks() {
        return footerLinks;
    }

    public void setFooterLinks(List<FooterLinkModel> footerLinks) {
        this.footerLinks = footerLinks;
    }

    public HomeScreenPointsSettingsModel getHomeScreenPointsSettings() {
        return homeScreenPointsSettings;
    }

    public void setHomeScreenPointsSettings(HomeScreenPointsSettingsModel homeScreenPointsSettings) {
        this.homeScreenPointsSettings = homeScreenPointsSettings;
    }

    public boolean isHomePageDisplayHeader() {
        return homePageDisplayHeader;
    }

    public void setHomePageDisplayHeader(boolean homePageDisplayHeader) {
        this.homePageDisplayHeader = homePageDisplayHeader;
    }

    public boolean isHomePageDisplayHeaderMenu() {
        return homePageDisplayHeaderMenu;
    }

    public void setHomePageDisplayHeaderMenu(boolean homePageDisplayHeaderMenu) {
        this.homePageDisplayHeaderMenu = homePageDisplayHeaderMenu;
    }

    public String getHomePageHeaderMenuText() {
        return homePageHeaderMenuText;
    }

    public void setHomePageHeaderMenuText(String homePageHeaderMenuText) {
        this.homePageHeaderMenuText = homePageHeaderMenuText;
    }

    public boolean isHomePageDisplayHeaderProfile() {
        return homePageDisplayHeaderProfile;
    }

    public void setHomePageDisplayHeaderProfile(boolean homePageDisplayHeaderProfile) {
        this.homePageDisplayHeaderProfile = homePageDisplayHeaderProfile;
    }
}