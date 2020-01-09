package com.example.iotmobileapp.workerservice.Definitions;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Scan
{
    @SerializedName("kinematics")
    public Kinematics kinematics;

    @SerializedName("wifi_scans")
    public List<WifiScan> wifiScans;

    @SerializedName("bluetooth_scans")
    public List<BluetoothScan> bluetoothScans;
}


