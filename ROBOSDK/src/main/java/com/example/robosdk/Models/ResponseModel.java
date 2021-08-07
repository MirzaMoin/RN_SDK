package com.example.robosdk.Models;

public class ResponseModel {
    public int statusCode;
    public String statusMessage;
    public ResponsedataModel responsedata;

  /*  public ResponseModel(int statusCode, String statusMessage, ResponsedataModel responsedata) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.responsedata = responsedata;
    }*/

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

    public ResponsedataModel getResponsedata() {
        return responsedata;
    }

    public void setResponsedata(ResponsedataModel responsedata) {
        this.responsedata = responsedata;
    }
}

