package com.example.iotmobileapp.config;

public interface ISetting<T>
{
    T Value();

    String Name();

    String Type();
}
