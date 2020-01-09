package com.example.iotmobileapp.workerservice.serviceclient;


import com.example.iotmobileapp.workerservice.Definitions.Scan;
import com.example.iotmobileapp.workerservice.Definitions.ScanBatch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IScanServiceClient
{
    @POST("/api/scan")
    Call<Void> InsertScans(@Body ScanBatch Scans);
}
