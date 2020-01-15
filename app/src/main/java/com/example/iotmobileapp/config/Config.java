package com.example.iotmobileapp.config;

public class Config
{
    private Config(){}


    public static final ISetting<Integer> ScannerSleepTime = new Setting<Integer>("ScannerSleepTime", 1000);


    public static final ISetting<Integer> PusherSleepTime = new Setting<Integer>("PusherSleepTime", 16000);


    public static final ISetting<Integer> PusherBatchSize = new Setting<Integer>("PusherBatchSize", 5);


    public static final ISetting<Integer> ScanningTime = new Setting<>("ScanningTime", 3000);

}
