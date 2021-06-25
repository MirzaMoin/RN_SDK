package com.example.rnsdk.API;

import com.example.rnsdk.Models.ResponseModel;
import com.example.rnsdk.Models.ResponseModelTransactionHistory;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetAPIData {
    @GET("/api/UserAccountProfile/AppIntakeLayoutData")
    Call<ResponseModel> getAllData(
            @Query("RPToken") String RPToken
    );

    @GET("/api/UserAccountProfile/TransactionHistory")
    Call<ResponseModel> getTransactionHistory(
            @Query("RewardProgramId") String RewardProgramId,
            @Query("ContactID") String ContactID
    );


    @GET("/api/UserProfile/Getoffers")
    Call<ResponseModelTransactionHistory> getOffers(
            @Query("RewardProgramID") String RewardProgramId,
            @Query("ContactID") String ContactID
    );


    @GET("/api/UserAccountProfile/GetSurveyList")
    Call<ResponseModel> getSurveyList(
            @Query("RewardProgramId") String RewardProgramId,
            @Query("ContactID") String ContactID
    );

    @GET("/api/UserAccountProfile/RPGScreenData")
    Call<ResponseModel> getRPGList(
            @Query("RPToken") String RPToken,
            @Query("ContactID") String ContactID
    );

    @GET("/api/UserProfile/GetContactData")
    Call<ResponseModel> getContactData(
            @Query("RewardProgramID") String RewardProgramID,
            @Query("ContactID") String ContactID
    );

    @GET("/api/UserAccountProfile/WayToEarnScreenData")
    Call<ResponseModel> getWaysToEarn(
            @Query("RPToken") String RPToken,
            @Query("WebFormID") String WebFormID,
            @Query("ContactID") String ContactID
    );

    @GET("/api/UserProfile/CashbackAmount")
    Call<JsonObject> getCashbackAmount(
            @Query("RewardProgramID") String RewardProgramID,
            @Query("ContactID") String ContactID
    );


    @POST("/api/UserProfile/TransferPoints")
    Call<ResponseModel> TransferPoints(
            @Body JsonObject jsonBody
    );

    @POST("/api/UserProfile/LogIn")
    Call<ResponseModel> Login(
            @Body JsonObject jsonBody
    );

    @POST("/api/UserProfile/ContactUs")
    Call<ResponseModel> ContactUs(
            @Body JsonObject jsonBody
    );
   @POST("/api/UserProfile/CashbackRedeem")
    Call<JsonObject> CashbackRedeem(
            @Body JsonObject jsonBody
    );


}