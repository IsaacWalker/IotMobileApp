package com.example.iotmobileapp.workerservice.Definitions;

import com.google.gson.annotations.SerializedName;

public class Kinematics
{
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
    @SerializedName("acceleration_x")
    public double acceleration_x;

    @SerializedName("acceleration_y")
    public double acceleration_y;

    @SerializedName("acceleration_z")
    public double acceleration_z;
}
