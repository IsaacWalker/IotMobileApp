package com.example.iotmobileapp.workerservice.Definitions;

import com.example.iotmobileapp.config.Setting;
import com.example.iotmobileapp.workerservice.Utilities;
import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Scan
{
    @SerializedName("dateTime")
    public Date DateTime = Utilities.GetCurrentDateTime();

    @SerializedName("globalConfigurationId")
    public int GlobalConfigurationId;

    @SerializedName("configuration")
    public List<SettingModel> LocalConfiguration;

    @SerializedName("kinematics")
    public Kinematics kinematics;

    @SerializedName("wifiDevices")
    public List<WifiDevice> wifiDevices;

    @SerializedName("bluetoothDevices")
    public List<BluetoothDevice> bluetoothDevices;
}


