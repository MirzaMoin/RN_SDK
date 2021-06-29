package com.example.rnsdk.Models;

public class LBQualificationCriteriaModel {
    public int sharesToQualify;
    public int referralToQualify;
    public int noOfWinners;


    public LBQualificationCriteriaModel(int sharesToQualify, int referralToQualify, int noOfWinners) {
        this.sharesToQualify = sharesToQualify;
        this.referralToQualify = referralToQualify;
        this.noOfWinners = noOfWinners;
    }


    public int getSharesToQualify() {
        return sharesToQualify;
    }

    public void setSharesToQualify(int sharesToQualify) {
        this.sharesToQualify = sharesToQualify;
    }

    public int getReferralToQualify() {
        return referralToQualify;
    }

    public void setReferralToQualify(int referralToQualify) {
        this.referralToQualify = referralToQualify;
    }

    public int getNoOfWinners() {
        return noOfWinners;
    }

    public void setNoOfWinners(int noOfWinners) {
        this.noOfWinners = noOfWinners;
    }
}
