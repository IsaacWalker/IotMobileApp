package com.example.iotmobileapp.config;

public class Config
{
    private Config(){}

    public static final ISetting<Integer> ScannerSleepTime = new Setting<Integer>("ScannerSleepTime", 2500);
    public static final ISetting<Integer> PusherSleepTime = new Setting<Integer>("PusherSleepTime", 10000);
    public static final ISetting<Integer> PusherBatchSize = new Setting<Integer>("PusherBatchSize", 5);

}
