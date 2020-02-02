package com.example.iotmobileapp.workerservice.Definitions;

import com.example.iotmobileapp.config.Setting;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Scan
{
    @SerializedName("timestamp")
    public long Timestamp = System.currentTimeMillis();

    @SerializedName("configuration")
    public List<SettingModel> Configuration;

    @SerializedName("kinematics")
    public Kinematics kinematics;

    @SerializedName("wifiDevices")
    public List<WifiDevice> wifiDevices;

    @SerializedName("bluetoothDevices")
    public List<BluetoothDevice> bluetoothDevices;
}


