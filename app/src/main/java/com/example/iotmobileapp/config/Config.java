package com.example.iotmobileapp.config;

public class Config
{
    private Config(){}


    public static final ISetting<Integer> ScannerSleepTime;


    public static final ISetting<Integer> PusherSleepTime;


    public static final ISetting<Integer> PusherBatchSize;


    public static final ISetting<Integer> ScanningTime;


    public static final ISetting<Integer> SettingSleepTime;


    public static final ISetting<Integer> MeetingFrequency;

    static
    {
        ScannerSleepTime = new Setting<Integer>("ScannerSleepTime", 4000);
        PusherSleepTime = new Setting<Integer>("PusherSleepTime",  1000);
        PusherBatchSize = new Setting<Integer>("PusherBatchSize", 5);
        ScanningTime = new Setting<>("ScanningTime", 3000);
        SettingSleepTime = new Setting<>("SettingSleepTime", 20000);
        MeetingFrequency = new Setting<>("MeetingFrequency", 3);
    }


}
