package com.example.iotmobileapp.workerservice.Definitions;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Kinematics
{
    @SerializedName("datetime")
    public Date DateTime;

    // GPS position
    @SerializedName("altitude")
    public double altitude;

    @SerializedName("latitude")
    public double latitude;

    @SerializedName("longitude")
    public double longitude;

    // Orientation
    @SerializedName("azimuth")
    public double azimuth;

    @SerializedName("pitch")
    public double pitch;

    @SerializedName("roll")
    public double roll;

    // Linear Acceleration
    @SerializedName("accelerationX")
    public double acceleration_x;

    @SerializedName("accelerationY")
    public double acceleration_y;

    @SerializedName("accelerationZ")
    public double acceleration_z;
}
