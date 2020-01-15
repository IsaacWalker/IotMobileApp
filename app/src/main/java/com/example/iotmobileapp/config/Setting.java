package com.example.iotmobileapp.config;

public class Setting<T> implements ISetting<T> {

    private String _name;

    private T _value;

    public Setting(String name, T initVal)
    {
        _name = name;
        _value = initVal;
        ConfigProvider.RegisterSetting(this);
    }


    @Override
    public T Value() {
        return _value;
    }


    @Override
    public String Name()
    {
        return _name;
    }


    public void SetValue(T value)
    {
        _value = value;
    }


}
