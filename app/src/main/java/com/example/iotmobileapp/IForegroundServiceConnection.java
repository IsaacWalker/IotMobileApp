package com.example.iotmobileapp;

import android.content.ServiceConnection;

import com.example.iotmobileapp.workerservice.Definitions.Configuration;

public interface IForegroundServiceConnection extends ServiceConnection
{
    void updateConfiguration(Configuration config);

    Configuration getCurrentConfiguration();
}
