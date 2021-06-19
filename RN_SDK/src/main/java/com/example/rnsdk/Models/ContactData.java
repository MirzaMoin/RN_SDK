package com.example.rnsdk.Models;

public class ContactData{
    public String contactID;
    public int pointBalance;
    public int reedemablePoints;
    public String firstName;
    public String lastName;
    public String mobilePhone;
    public String memberCardID;
    public String emailAddress;
    public Boolean isBeaconAppLogin;
    public String profilePitcure;
    public Boolean isRequiredPasswordChanged;
    public String contactListVisibleName;
    public String contactListName;


    public ContactData(String contactID, int pointBalance, int reedemablePoints, String firstName, String lastName, String mobilePhone, String memberCardID, String emailAddress, Boolean isBeaconAppLogin, String profilePitcure, Boolean isRequiredPasswordChanged, String contactListVisibleName, String contactListName) {
        this.contactID = contactID;
        this.pointBalance = pointBalance;
        this.reedemablePoints = reedemablePoints;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobilePhone = mobilePhone;
        this.memberCardID = memberCardID;
        this.emailAddress = emailAddress;
        this.isBeaconAppLogin = isBeaconAppLogin;
        this.profilePitcure = profilePitcure;
        this.isRequiredPasswordChanged = isRequiredPasswordChanged;
        this.contactListVisibleName = contactListVisibleName;
        this.contactListName = contactListName;
    }

    public String getContactID() {
        return contactID;
    }

    public void setContactID(String contactID) {
        this.contactID = contactID;
    }

    public int getPointBalance() {
        return pointBalance;
    }

    public void setPointBalance(int pointBalance) {
        this.pointBalance = pointBalance;
    }

    public int getReedemablePoints() {
        return reedemablePoints;
    }

    public void setReedemablePoints(int reedemablePoints) {
        this.reedemablePoints = reedemablePoints;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getMemberCardID() {
        return memberCardID;
    }

    public void setMemberCardID(String memberCardID) {
        this.memberCardID = memberCardID;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Boolean isBeaconAppLogin() {
        return isBeaconAppLogin;
    }

    public void setBeaconAppLogin(Boolean beaconAppLogin) {
        isBeaconAppLogin = beaconAppLogin;
    }

    public String getProfilePitcure() {
        return profilePitcure;
    }

    public void setProfilePitcure(String profilePitcure) {
        this.profilePitcure = profilePitcure;
    }

    public Boolean isRequiredPasswordChanged() {
        return isRequiredPasswordChanged;
    }

    public void setRequiredPasswordChanged(Boolean requiredPasswordChanged) {
        isRequiredPasswordChanged = requiredPasswordChanged;
    }

    public String getContactListVisibleName() {
        return contactListVisibleName;
    }

    public void setContactListVisibleName(String contactListVisibleName) {
        this.contactListVisibleName = contactListVisibleName;
    }

    public String getContactListName() {
        return contactListName;
    }

    public void setContactListName(String contactListName) {
        this.contactListName = contactListName;
    }
}