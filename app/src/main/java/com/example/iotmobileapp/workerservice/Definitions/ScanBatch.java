package com.example.iotmobileapp.workerservice.Definitions;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScanBatch
{
    @SerializedName("device_id")
    public long DeviceId;

    @SerializedName("scans")
    public List<Scan> scans;

    public ScanBatch(long deviceId, List<Scan> scans)
    {
        this.DeviceId = deviceId;
        this.scans = scans;
    }

}
