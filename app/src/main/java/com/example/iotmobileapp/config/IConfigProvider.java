package com.example.iotmobileapp.config;

import com.example.iotmobileapp.workerservice.Definitions.Configuration;
import java.util.Collection;

public interface IConfigProvider
{
    void UpdateConfig(Configuration config);


    void updateSetting(ISetting setting, Object value);


    Collection<Setting> getConfig();


    int getGlobalConfigId(int localId);
}
