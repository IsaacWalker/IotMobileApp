package com.example.iotmobileapp.workerservice;

import android.util.Log;

import com.example.iotmobileapp.config.Config;
import com.example.iotmobileapp.workerservice.Database.ISharedDatabase;
import com.example.iotmobileapp.workerservice.Definitions.Scan;

import java.util.List;

public class PusherWorker implements Runnable
{
    private final ISharedDatabase<Scan> m_scanDatabase;

    public PusherWorker(ISharedDatabase<Scan> scanDatabase)
    {
        m_scanDatabase = scanDatabase;
    }

    @Override
    public void run()
    {

        while(true)
        {
            List<Scan> s = m_scanDatabase.take(Config.PusherBatchSize.Value());

            if(s != null)
                Log.d("Removed", "Removed" + s.size());

            try {

                Thread.sleep(Config.ScannerSleepTime.Value());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
