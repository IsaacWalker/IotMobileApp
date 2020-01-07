package com.example.iotmobileapp.config;

public class Setting<T> implements ISetting<T> {

    private String _id;

    private T _value;

    public Setting(String id, T initVal)
    {
        _id = id;
        _value = initVal;
    }


    @Override
    public T Value() {
        return _value;
    }

    @Override
    public String Id()
    {
        return _id;
    }

}
