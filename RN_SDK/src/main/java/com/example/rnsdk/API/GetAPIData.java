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

    @POST("/api/UserProfile/LogIn")
    Call<ResponseModel> Login(
            @Body JsonObject jsonBody
    );
}