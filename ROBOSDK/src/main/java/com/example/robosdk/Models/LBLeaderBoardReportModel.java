package com.example.robosdk.Models;

public class LBLeaderBoardReportModel {
    public int rank;
    public int totalPoints;
    public String contactID;
    public String profilePitcure;
    public String fullName;

    public LBLeaderBoardReportModel(int rank, int totalPoints, String contactID, String profilePitcure, String fullName) {
        this.rank = rank;
        this.totalPoints = totalPoints;
        this.contactID = contactID;
        this.profilePitcure = profilePitcure;
        this.fullName = fullName;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getContactID() {
        return contactID;
    }

    public void setContactID(String contactID) {
        this.contactID = contactID;
    }

    public String getProfilePitcure() {
        return profilePitcure;
    }

    public void setProfilePitcure(String profilePitcure) {
        this.profilePitcure = profilePitcure;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
