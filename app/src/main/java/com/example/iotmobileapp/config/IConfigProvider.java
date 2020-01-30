package com.example.iotmobileapp.config;

import com.example.iotmobileapp.workerservice.Definitions.Configuration;
import java.util.Collection;

public interface IConfigProvider
{
    void UpdateConfig(Collection<Setting> config);

    void UpdateConfig(Configuration config);

    Collection<Setting> getConfig();


}
