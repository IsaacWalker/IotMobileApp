package com.example.iotmobileapp.config;

import com.example.iotmobileapp.workerservice.Definitions.Configuration;
import java.util.Collection;

public interface IConfigProvider
{
    void UpdateConfig(Configuration config);


    Collection<Setting> getConfig();
}
