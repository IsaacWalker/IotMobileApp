package com.example.iotmobileapp.workerservice.Definitions;

public class Kinematics
{
    // GPS position
    public double altitude;
    public double latitude;
    public double longitude;
    public boolean new_location;

    // Orientation
    public double azimuth;
    public double pitch;
    public double roll;

    // Linear Acceleration
    public double acceleration_x;
    public double acceleration_y;
    public double acceleration_z;
}
