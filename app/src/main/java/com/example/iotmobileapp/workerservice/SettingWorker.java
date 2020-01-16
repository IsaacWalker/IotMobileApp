package com.example.iotmobileapp.workerservice;

import android.util.Log;

import com.example.iotmobileapp.config.Config;
import com.example.iotmobileapp.config.IConfigProvider;
import com.example.iotmobileapp.workerservice.Definitions.Configuration;
import com.example.iotmobileapp.workerservice.serviceclient.ISettingServiceClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingWorker implements Runnable
{
    private final ISettingServiceClient m_serviceClient;


    private final IConfigProvider m_configProvider;


    public SettingWorker(ISettingServiceClient serviceClient, IConfigProvider configProvider)
    {
        m_serviceClient = serviceClient;
        m_configProvider = configProvider;
    }


    @Override
    public void run()
    {
        while(true)
        {
            Log.d("Setting Value: ", "" +Config.PusherBatchSize.Value());
            Log.d("Setting Value: ", "" +Config.PusherSleepTime.Value());
            Log.d("Setting Value: ", "" +Config.ScannerSleepTime.Value());
            Log.d("Setting Value: ", "" +Config.ScanningTime.Value());

            Call<Configuration> call = m_serviceClient.GetCurrentConfig();
            call.enqueue(configurationCallback);

            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private final Callback<Configuration> configurationCallback = new Callback<Configuration>()
    {
        @Override
        public void onResponse(Call<Configuration> call, Response<Configuration> response) {
            if(response.code() == 200)
            {
                Configuration config = response.body();
                m_configProvider.UpdateConfig(config);
            }
        }

        @Override
        public void onFailure(Call call, Throwable t) {

        }
    };
}
