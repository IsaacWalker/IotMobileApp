package com.example.iotmobileapp;

import android.content.ComponentName;
import android.os.IBinder;
import android.util.Log;

import com.example.iotmobileapp.config.Setting;
import com.example.iotmobileapp.workerservice.Definitions.Configuration;
import com.example.iotmobileapp.workerservice.ForegroundService;

import java.util.Collection;

public class ForegroundServiceConnection implements IForegroundServiceConnection
{
    private ForegroundService m_service = null;


    @Override
    public void updateConfiguration(Collection<Setting> config) {

    }


    @Override
    public Collection<Setting> getCurrentConfiguration() {
        return m_service.getConfig();
    }

    @Override
    public boolean isConnected() {
        return m_service != null;
    }


    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        m_service = ((ForegroundService.LocalBinder) iBinder).getService();
        Log.d("ServiceConnected", "Connected to foreground service" + (m_service == null));
    }


    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        m_service = null;
    }
}
