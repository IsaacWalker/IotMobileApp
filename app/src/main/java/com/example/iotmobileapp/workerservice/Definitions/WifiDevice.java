package com.example.iotmobileapp.workerservice.Definitions;

import com.google.gson.annotations.SerializedName;

public class WifiDevice
{

    @SerializedName("timestamp")
    public long Timestamp;

    @SerializedName("bssid")
    public String BSSID;

    @SerializedName("ssid")
    public String SSID;

    @SerializedName("capabilities")
    public String capabilities;

    @SerializedName("level")
    public int level;

    @SerializedName("operatorFriendlyName")
    public String operatorFriendlyName;

    @SerializedName("venueName")
    public String venueName;
}
