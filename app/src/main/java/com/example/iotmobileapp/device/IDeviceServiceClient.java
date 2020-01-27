package com.example.iotmobileapp.device;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IDeviceServiceClient
{
    @POST("/api/device")
    Call<Integer> RegisterDevice(@Body DeviceModel device);
}
