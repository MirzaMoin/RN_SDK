package com.example.robosdk.Models;

import java.util.List;

public class ResponseModelTransactionHistory {
    public int statusCode;
    public String statusMessage;
    public List<TransactionHistoryModel> responsedata;


    public ResponseModelTransactionHistory(int statusCode, String statusMessage, List<TransactionHistoryModel> responsedata) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.responsedata = responsedata;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public List<TransactionHistoryModel> getResponsedata() {
        return responsedata;
    }

    public void setResponsedata(List<TransactionHistoryModel> responsedata) {
        this.responsedata = responsedata;
    }
}
