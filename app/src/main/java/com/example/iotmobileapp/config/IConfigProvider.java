package com.example.iotmobileapp.config;

import com.example.iotmobileapp.workerservice.Definitions.Configuration;

public interface IConfigProvider
{
    void UpdateConfig(Configuration config);
}
