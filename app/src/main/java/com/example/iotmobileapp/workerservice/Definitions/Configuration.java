package com.example.iotmobileapp.workerservice.Definitions;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Configuration
{
    @SerializedName("id")
    public int Id;


    @SerializedName("settings")
    public List<SettingModel> settings;
}
