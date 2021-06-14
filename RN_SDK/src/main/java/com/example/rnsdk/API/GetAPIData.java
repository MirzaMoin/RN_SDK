package com.example.rnsdk.API;

import com.example.rnsdk.Models.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetAPIData {
    @GET("/api/UserAccountProfile/AppIntakeLayoutData")
    Call<ResponseModel> getAllData(
            @Query("RPToken")String RPToken
    );

    @FormUrlEncoded
    @POST("/api/Tablet/LogIn")
    Call<ResponseModel> Login(
            @Field("username") String username,
            @Field("password") String password
    );
}