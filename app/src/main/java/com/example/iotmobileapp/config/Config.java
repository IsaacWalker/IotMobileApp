package com.example.iotmobileapp.config;

public class Config
{
    private Config(){}


    public static final ISetting<Integer> ScannerSleepTime;


    public static final ISetting<Integer> PusherSleepTime;


    public static final ISetting<Integer> PusherBatchSize;


    public static final ISetting<Integer> ScanningTime;

    static
    {
        ScannerSleepTime = new Setting<Integer>("ScannerSleepTime", 1000);
        PusherSleepTime = new Setting<Integer>("PusherSleepTimeSetting", 16000);
        PusherBatchSize = new Setting<Integer>("PusherBatchSizeSetting", 5);
        ScanningTime = new Setting<>("ScanningTimeSetting", 3000);
    }


}
