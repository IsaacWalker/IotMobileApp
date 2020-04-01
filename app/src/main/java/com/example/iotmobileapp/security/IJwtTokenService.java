package com.example.iotmobileapp.security;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface IJwtTokenService
{
    @GET("/secret/nonce")
    Call<String> getNonce();

    @GET("/secret/token")
    Call<String> getToken(@Body TokenRequestModel tokenRequestModel);
}
