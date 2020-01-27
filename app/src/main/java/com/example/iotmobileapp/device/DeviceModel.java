package com.example.iotmobileapp.device;

import com.google.gson.annotations.SerializedName;

public class DeviceModel
{
    @SerializedName("model")
    public String Model;

    @SerializedName("manufacturer")
    public String Manufacturer;

    @SerializedName("macAddress")
    public String MacAddress;

    @SerializedName("bluetoothName")
    public String BluetoothName;
}
