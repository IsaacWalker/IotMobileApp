package com.example.iotmobileapp.workerservice;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utilities
{
    private Utilities(){}

    public static final String FORMAT_STRING = "yyyy-MM-dd'T'HH:mm:ss";
  //  private static final SimpleDateFormat s_format
    //        = new SimpleDateFormat(FORMAT_STRING);

    public static Date GetCurrentDateTime()
    {
        Date d = Calendar.getInstance().getTime();
        return d;
    }

   /* public static SimpleDateFormat GetDateFormat()
    {
        return s_format;
    }
*/
}
