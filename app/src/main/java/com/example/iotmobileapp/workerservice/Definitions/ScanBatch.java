package com.example.iotmobileapp.workerservice.Definitions;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScanBatch
{
    @SerializedName("scans")
    public List<Scan> scans;

    public ScanBatch(List<Scan> scans)
    {
        this.scans = scans;
    }

}
