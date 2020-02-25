package com.example.iotmobileapp.workerservice;


import android.util.Log;
import com.example.iotmobileapp.config.Config;
import com.example.iotmobileapp.workerservice.Database.ISharedDatabase;
import com.example.iotmobileapp.workerservice.Definitions.Scan;
import com.example.iotmobileapp.workerservice.Definitions.ScanBatch;
import com.example.iotmobileapp.workerservice.identity.IIdentity;
import com.example.iotmobileapp.workerservice.serviceclient.IScanServiceClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PusherWorker implements Runnable
{
    private final ISharedDatabase<Scan> m_scanDatabase;

    private final IScanServiceClient m_scanServiceClient;

    private final IIdentity m_userIdentity;

    public PusherWorker(ISharedDatabase<Scan> scanDatabase, IScanServiceClient scanServiceClient, IIdentity userIdentity)
    {
        m_scanDatabase = scanDatabase;
        m_scanServiceClient = scanServiceClient;
        m_userIdentity = userIdentity;
    }

    @Override
    public void run()
    {
        while(true)
        {
            List<Scan> scans = m_scanDatabase.take(Config.PusherBatchSize.Value());

            if(scans != null && scans.size() > 0)
            {
                ScanBatch batch = new ScanBatch(m_userIdentity.GetDeviceId(), scans);
                Call<Void> call = m_scanServiceClient.InsertScans(batch);
                call.enqueue(scanCallback);
            }

            try
            {
                Thread.sleep(Config.PusherSleepTime.Value());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private final Callback<Void> scanCallback = new Callback<Void>()
    {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {

            Log.d("Success", "Log inserted" + response.code());
            if(response.code() != 400)
            {

            }
        }

        @Override
        public void onFailure(Call call, Throwable t) {

        }
    };

}
