package com.example.iotmobileapp.workerservice.serviceclient;

import com.example.iotmobileapp.workerservice.Definitions.Configuration;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ISettingServiceClient
{
    @GET("/api/setting/current")
    Call<Configuration> GetCurrentConfig();
}
