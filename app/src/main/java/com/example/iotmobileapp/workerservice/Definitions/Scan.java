package com.example.iotmobileapp.workerservice.Definitions;

import com.example.iotmobileapp.config.Setting;
import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Scan
{
    @SerializedName("datetime")
    public Date DateTime = Calendar.getInstance().getTime();

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


