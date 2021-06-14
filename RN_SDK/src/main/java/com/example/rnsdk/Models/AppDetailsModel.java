package com.example.rnsdk.Models;

public class AppDetailsModel{
    public String rewardProgramId;
    public String webFormID;
    public String tosLink;
    public String privacyPolicyLink;

    public AppDetailsModel(String rewardProgramId, String webFormID, String tosLink, String privacyPolicyLink) {
        this.rewardProgramId = rewardProgramId;
        this.webFormID = webFormID;
        this.tosLink = tosLink;
        this.privacyPolicyLink = privacyPolicyLink;
    }

    public String getRewardProgramId() {
        return rewardProgramId;
    }

    public void setRewardProgramId(String rewardProgramId) {
        this.rewardProgramId = rewardProgramId;
    }

    public String getWebFormID() {
        return webFormID;
    }

    public void setWebFormID(String webFormID) {
        this.webFormID = webFormID;
    }

    public String getTosLink() {
        return tosLink;
    }

    public void setTosLink(String tosLink) {
        this.tosLink = tosLink;
    }

    public String getPrivacyPolicyLink() {
        return privacyPolicyLink;
    }

    public void setPrivacyPolicyLink(String privacyPolicyLink) {
        this.privacyPolicyLink = privacyPolicyLink;
    }
}
