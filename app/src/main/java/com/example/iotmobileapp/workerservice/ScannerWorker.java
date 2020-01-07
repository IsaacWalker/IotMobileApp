package com.example.iotmobileapp.workerservice;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.iotmobileapp.config.Config;
import com.example.iotmobileapp.workerservice.Database.ISharedDatabase;
import com.example.iotmobileapp.workerservice.Definitions.BluetoothScan;
import com.example.iotmobileapp.workerservice.Definitions.Scan;
import com.example.iotmobileapp.workerservice.Definitions.WifiScan;

import java.util.ArrayList;
import java.util.List;

public class ScannerWorker implements Runnable
{
    private final ISharedDatabase<Scan> m_database;

    private final WifiManager m_wifiManager;

    private final BluetoothAdapter m_bluetoothAdapter;

    private volatile Scan _scans;
    private volatile boolean _isWifiComplete = false;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public ScannerWorker(ISharedDatabase<Scan> _database, WifiManager wifiManager, BluetoothManager bluetoothManager)
    {
        m_database = _database;
        m_wifiManager = wifiManager;
        m_bluetoothAdapter = bluetoothManager.getAdapter();
    }

    @Override
    public void run()
    {
        m_wifiManager.setWifiEnabled(true);

        while(true)
        {
            _scans = new Scan();
            _scans.bluetoothScans = new ArrayList<BluetoothScan>();
            _isWifiComplete = false;

            boolean wifi_scan_success = m_wifiManager.startScan();
            m_bluetoothAdapter.startDiscovery();

            Log.d("Scan Success", "Success: "+wifi_scan_success);

            while(!_isWifiComplete)
            {
                try
                {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            m_bluetoothAdapter.cancelDiscovery();

            m_database.insert(_scans);
            Log.d("Scan Numbers:", ""+ _scans.wifiScans.size() + " : " + _scans.bluetoothScans.size());

            try
            {
                Thread.sleep(Config.PusherSleepTime.Value());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onReceive(Context context, Intent intent) {
            List<ScanResult> scanResults = m_wifiManager.getScanResults();
            Log.d("Scan",""+ scanResults.size());

            ArrayList<WifiScan> wifiScans = new ArrayList<>();
            for (ScanResult scanResult : scanResults)
            {
                WifiScan wifiScan = new WifiScan();
                wifiScan.SSID = scanResult.SSID;
                wifiScan.BSSID = scanResult.BSSID;
                wifiScan.capabilities = scanResult.capabilities;
                wifiScan.level = scanResult.level;
                wifiScan.venueName = scanResult.venueName.toString();
                wifiScan.operatorFriendlyName = scanResult.operatorFriendlyName.toString();
                wifiScans.add(wifiScan);
            }

            _scans.wifiScans = wifiScans;
            _isWifiComplete = true;
        }
    };

    BroadcastReceiver bluetoothReceiver = new BroadcastReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                BluetoothScan bluetoothScan = new BluetoothScan();
                bluetoothScan.Name = device.getName();
                bluetoothScan.Type = device.getType();
                bluetoothScan.Address = device.getAddress();

                _scans.bluetoothScans.add(bluetoothScan);
            }
        }
    };
}
