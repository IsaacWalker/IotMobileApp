package com.example.iotmobileapp.workerservice;

import java.util.Calendar;
import java.util.Date;

public class Utilities
{
    private Utilities(){}


    public static Date GetCurrentDateTime()
    {
        return Calendar.getInstance().getTime();
    }

}
