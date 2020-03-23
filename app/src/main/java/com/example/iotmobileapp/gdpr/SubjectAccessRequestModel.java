package com.example.iotmobileapp.gdpr;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class SubjectAccessRequestModel
{
    @SerializedName("deviceSubjectData")
    public SubjectData DeviceSubjectData;

    @SerializedName("scanSubjectData")
    public  SubjectData ScanSubjectData;

    @SerializedName("dateTimeOfRequest")
    public Date DateTimeOfRequest;
}
