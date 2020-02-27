package com.example.iotmobileapp.workerservice.Definitions;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class WifiDevice
{

    @SerializedName("dateTime")
    public Date DateTime;

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
