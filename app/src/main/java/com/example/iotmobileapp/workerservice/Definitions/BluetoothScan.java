package com.example.iotmobileapp.workerservice.Definitions;

import com.google.gson.annotations.SerializedName;

public class BluetoothScan
{
    @SerializedName("name")
    public String Name;

    @SerializedName("type")
    public int Type;

    @SerializedName("address")
    public String Address;
}
