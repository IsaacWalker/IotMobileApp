package com.example.iotmobileapp.gdpr;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

public class SubjectData
{
    @SerializedName("name")
    public String Name;

    @SerializedName("categories")
    public List<String> Categories;

    @SerializedName("dateOfCollection")
    public Date DateOfCollection;

    @SerializedName("dateOfDeletion")
    public Date DateOfDeletion;

    @SerializedName("data")
    public HashMap<String, String> Data;
}
