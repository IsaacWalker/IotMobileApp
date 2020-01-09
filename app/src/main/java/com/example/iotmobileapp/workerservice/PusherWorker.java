package com.example.iotmobileapp.workerservice;

import android.content.Context;
import android.util.Log;

import com.example.iotmobileapp.config.Config;
import com.example.iotmobileapp.workerservice.Database.ISharedDatabase;
import com.example.iotmobileapp.workerservice.Definitions.Scan;
import com.example.iotmobileapp.workerservice.Definitions.ScanBatch;
import com.example.iotmobileapp.workerservice.serviceclient.IScanServiceClient;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okio.BufferedSink;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PusherWorker implements Runnable
{
    private final ISharedDatabase<Scan> m_scanDatabase;

    private final IScanServiceClient m_scanServiceClient;

    public PusherWorker(ISharedDatabase<Scan> scanDatabase, IScanServiceClient scanServiceClient)
    {
        m_scanDatabase = scanDatabase;
        m_scanServiceClient = scanServiceClient;
    }

    @Override
    public void run()
    {
        while(true)
        {
            List<Scan> scans = m_scanDatabase.take(Config.PusherBatchSize.Value());

            if(scans != null)
            {
                ScanBatch batch = new ScanBatch(scans);
                Call<Void> call = m_scanServiceClient.InsertScans(batch);
                call.enqueue(scanCallback);
            }

            try
            {
                Thread.sleep(Config.ScannerSleepTime.Value());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private final Callback<Void> scanCallback = new Callback<Void>()
    {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            if(response.code() != 400)
            {

            }
        }

        @Override
        public void onFailure(Call call, Throwable t) {

        }
    };

}
