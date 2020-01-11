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

    @SerializedName("operator_friendly_name")
    public String operatorFriendlyName;

    @SerializedName("venue_name")
    public String venueName;
}