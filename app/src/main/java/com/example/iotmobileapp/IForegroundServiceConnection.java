package com.example.iotmobileapp;

import android.content.ServiceConnection;

import com.example.iotmobileapp.config.Setting;
import com.example.iotmobileapp.workerservice.Definitions.Configuration;

import java.util.Collection;

public interface IForegroundServiceConnection extends ServiceConnection
{
    void updateConfiguration(Collection<Setting> config);

    Collection<Setting> getCurrentConfiguration();

    boolean isConnected();
}
