package com.example.iotmobileapp.workerservice.Definitions;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class BluetoothDevice
{
    @SerializedName("dateTime")
    public Date DateTime;

    @SerializedName("name")
    public String Name;

    @SerializedName("type")
    public String Type;

    @SerializedName("address")
    public String Address;

    @SerializedName("rssi")
    public int Rssi;

    @SerializedName("powerLevel")
    public int PowerLevel;
}
