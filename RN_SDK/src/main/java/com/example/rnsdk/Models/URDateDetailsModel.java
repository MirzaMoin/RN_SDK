package com.example.rnsdk.Models;

public class URDateDetailsModel {
    public int receiptsCount;
    public String receiptsCountType;

    public URDateDetailsModel(int receiptsCount, String receiptsCountType) {
        this.receiptsCount = receiptsCount;
        this.receiptsCountType = receiptsCountType;
    }

    public int getReceiptsCount() {
        return receiptsCount;
    }

    public void setReceiptsCount(int receiptsCount) {
        this.receiptsCount = receiptsCount;
    }

    public String getReceiptsCountType() {
        return receiptsCountType;
    }

    public void setReceiptsCountType(String receiptsCountType) {
        this.receiptsCountType = receiptsCountType;
    }
}
