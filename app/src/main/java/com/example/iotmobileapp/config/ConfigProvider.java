package com.example.iotmobileapp.config;

import android.util.Log;

import com.example.iotmobileapp.workerservice.Definitions.Configuration;
import com.example.iotmobileapp.workerservice.Definitions.SettingModel;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class ConfigProvider implements IConfigProvider
{
    private static final int UNDEFINED_CONFIG_ID = Integer.MIN_VALUE;


    private static HashMap<String, Setting> m_settingMap = new HashMap<>();


    private int _currentConfigId = UNDEFINED_CONFIG_ID;


    public ConfigProvider()
    {
        try {
            Class.forName("com.example.iotmobileapp.config.Config");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void RegisterSetting(Setting setting)
    {
        if(!m_settingMap.containsKey(setting.Name()))
            m_settingMap.put(setting.Name(), setting);
    }


    @Override
    public void UpdateConfig(Collection<Setting> config) {
        for(Setting )
    }

    @Override
    public void UpdateConfig(Configuration config)
    {
        for(SettingModel settingModel : config.settings)
        {
            if(m_settingMap.containsKey(settingModel.name))
            {

                Setting setting = m_settingMap.get(settingModel.name);

                Object newValue = Setting.SettingType.parseValue(settingModel.type, settingModel.value);

                setting.SetValue(newValue);

                _currentConfigId = config.Id;
            }
        }
    }


    @Override
    public Collection<Setting> getConfig() {
        return m_settingMap.values();
    }
}
