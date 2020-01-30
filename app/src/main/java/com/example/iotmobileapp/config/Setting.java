package com.example.iotmobileapp.config;

import android.util.Log;

public class Setting<T> implements ISetting<T> {

    private String _name;

    private volatile T _value;

    private String _type;

    public Setting(String name, T initVal)
    {
        _name = name;
        _value = initVal;
        _type = SettingType.getTypeString(_value);
        ConfigProvider.RegisterSetting(this);
    }


    @Override
    public T Value() {
        return _value;
    }

    @Override
    public String Type() {return _type;}

    @Override
    public String Name()
    {
        return _name;
    }


    public synchronized void SetValue(T value)
    {
        _value = value;
    }

    public static class SettingType
    {
        public static final String INTEGER = "System.Integer";
        public static final String STRING = "System.String";
        public static final String DOUBLE = "System.Double";
        public static final String BOOL = "System.Boolean";


        public static String getTypeString(Object param)
        {
            if(param instanceof String)
                return SettingType.STRING;
            if(param instanceof Integer)
                return SettingType.INTEGER;
            if(param instanceof Double)
                return SettingType.DOUBLE;
            if(param instanceof String)
                return SettingType.STRING;

            Log.e("SettingType", "Getting Type String of " + param);
            return null;
        }


        public static Object parseValue(String type, String value)
        {
            if(type.equals(Setting.SettingType.INTEGER))
                return Integer.parseInt(value);
            if(type.equals(Setting.SettingType.BOOL))
                return Boolean.parseBoolean(value);
            if(type.equals(Setting.SettingType.DOUBLE))
                return Double.parseDouble(value);
            if(type.equals(SettingType.STRING))
                return value;

            Log.e("SettingType", "Parsing Value " + value + " of type " + value);
            return null;
        }
    }
}
