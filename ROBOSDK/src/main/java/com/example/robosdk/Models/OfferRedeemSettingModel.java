package com.example.robosdk.Models;

public class OfferRedeemSettingModel {
    public String redeemOfferInstruction;
    public boolean askWhereAreYou;

    public OfferRedeemSettingModel(String redeemOfferInstruction, boolean askWhereAreYou) {
        this.redeemOfferInstruction = redeemOfferInstruction;
        this.askWhereAreYou = askWhereAreYou;
    }

    public String getRedeemOfferInstruction() {
        return redeemOfferInstruction;
    }

    public void setRedeemOfferInstruction(String redeemOfferInstruction) {
        this.redeemOfferInstruction = redeemOfferInstruction;
    }

    public boolean isAskWhereAreYou() {
        return askWhereAreYou;
    }

    public void setAskWhereAreYou(boolean askWhereAreYou) {
        this.askWhereAreYou = askWhereAreYou;
    }
}
