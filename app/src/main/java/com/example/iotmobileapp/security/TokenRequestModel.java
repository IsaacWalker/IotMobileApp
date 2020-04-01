package com.example.iotmobileapp.security;

import com.example.iotmobileapp.device.DeviceModel;
import com.google.gson.annotations.SerializedName;

public class TokenRequestModel
{
    @SerializedName("nonce")
    public String Nonce;


    @SerializedName("deviceModel")
    public DeviceModel DeviceModel;


    @SerializedName("attestationCypher")
    public String AttestationCypher;
}
