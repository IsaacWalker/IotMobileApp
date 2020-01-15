package com.example.iotmobileapp.workerservice.Definitions;

import com.google.gson.annotations.SerializedName;

public class SettingModel
{
    @SerializedName("name")
    public String name;


    @SerializedName("type")
    public String type;


    @SerializedName("value")
    public String value;
}
