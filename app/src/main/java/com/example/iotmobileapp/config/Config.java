package com.example.iotmobileapp.config;

public class Config
{
    private Config(){}


    public static final ISetting<Integer> ScannerSleepDuration;


    public static final ISetting<Integer> LogSleepDuration;


    public static final ISetting<Integer> MinLogBatchSize;


    public static final ISetting<Integer> ScanDuration;


    public static final ISetting<Integer> SettingSleepDuration;


    public static final ISetting<Integer> MeetingFrequency;

    static
    {
        ScannerSleepDuration = new Setting<Integer>("ScannerSleepDuration", 4000);
        LogSleepDuration = new Setting<Integer>("LogSleepDuration",  1000);
        MinLogBatchSize = new Setting<Integer>("MinLogBatchSize", 5);
        ScanDuration = new Setting<>("ScanDuration", 3000);
        SettingSleepDuration = new Setting<>("SettingSleepDuration", 20000);
        MeetingFrequency = new Setting<>("MeetingFrequency", 3);
    }


}
