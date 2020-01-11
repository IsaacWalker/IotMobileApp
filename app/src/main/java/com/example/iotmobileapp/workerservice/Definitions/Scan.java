package com.example.iotmobileapp.workerservice.Definitions;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Scan
{
    @SerializedName("timestamp")
    public long Timestamp = System.currentTimeMillis();

    @SerializedName("kinematics")
    public Kinematics kinematics;

    @SerializedName("wifi_devices")
    public List<WifiDevice> wifiDevices;

    @SerializedName("bluetooth_devices")
    public List<BluetoothDevice> bluetoothDevices;
}


