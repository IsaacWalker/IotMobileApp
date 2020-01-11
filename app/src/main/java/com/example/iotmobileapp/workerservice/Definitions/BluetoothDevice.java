package com.example.iotmobileapp.workerservice.Definitions;

import com.google.gson.annotations.SerializedName;

public class BluetoothDevice
{
    @SerializedName("timestamp")
    public long Timestamp;

    @SerializedName("name")
    public String Name;

    @SerializedName("type")
    public int Type;

    @SerializedName("address")
    public String Address;
}
