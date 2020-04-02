package com.example.iotmobileapp.security;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IJwtTokenService
{
    @GET("/secret/nonce")
    Call<NonceModel> getNonce();

    @POST("/secret/tokenAttempt")
    Call<String> getToken(@Body TokenRequestModel tokenRequestModel);
}
