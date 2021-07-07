package com.example.robosdk.API;

import com.example.robosdk.Models.ResponseModel;
import com.example.robosdk.Models.ResponseModelTransactionHistory;
import com.google.gson.JsonObject;

import okhttp3.RequestBody;
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
    Call<ResponseModelTransactionHistory> getTransactionHistory(
            @Query("RewardProgramId") String RewardProgramId,
            @Query("ContactID") String ContactID
    );


    @GET("/api/UserProfile/Getoffers")
    Call<ResponseModel> getOffers(
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


    @GET("/api/UserAccountProfile/LeaderBoardScreenData")
    Call<ResponseModel> getLeaderBoardScreenData(
            @Query("RewardProgramID") String RewardProgramID
    );

    @GET("/api/UserAccountProfile/LeaderBoardReport")
    Call<JsonObject> getLeaderBoardReport(
            @Query("RewardProgramId") String RewardProgramId,
            @Query("Month") int Month,
            @Query("Year") int Year
    );
   @GET("/api/UserAccountProfile/GetAllPoints")
    Call<ResponseModel> getAllPoints(
            @Query("RewardProgramId") String RewardProgramId,
            @Query("ContactID") String ContactID
    );

    @GET("/api/UserAccountProfile/GetLocationData")
    Call<ResponseModel> getLocationData(
            @Query("RewardProgramId") String RewardProgramId
    );

  @GET("/api/UserProfile/UploadReceiptsScreenData")
    Call<ResponseModel> getUploadReceiptsScreenData(
            @Query("RewardProgramId") String RewardProgramId,
            @Query("WebFormID") String WebFormID
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

    @POST("/api/UserProfile/RedeemOffer")
    Call<JsonObject> RedeemOffer(
            @Body JsonObject jsonBody
    );
   @POST("/api/UserProfile/UploadReceipts")
    Call<JsonObject> UploadReceipts(
            @Body JsonObject jsonBody
    );
    @POST("/api/UserProfile/UploadReceiptsImages")
    Call<ResponseModel> uploadReceiptImage(@Body RequestBody file);

}