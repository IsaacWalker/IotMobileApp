package com.example.iotmobileapp.config;

import com.example.iotmobileapp.workerservice.Definitions.Configuration;
import com.example.iotmobileapp.workerservice.Definitions.SettingModel;

import java.util.HashMap;

public class ConfigProvider implements IConfigProvider
{
    private static final int UNDEFINED_CONFIG_ID = Integer.MIN_VALUE;


    private static HashMap<String, Setting> m_settingMap = new HashMap<>();


    private int _currentConfigId = UNDEFINED_CONFIG_ID;


    public static void RegisterSetting(Setting setting)
    {
        if(!m_settingMap.containsKey(setting.Name()))
            m_settingMap.put(setting.Name(), setting);
    }


    @Override
    public void UpdateConfig(Configuration config)
    {
        for(SettingModel settingModel : config.settings)
        {
            if(m_settingMap.containsKey(settingModel.name))
            {
                Setting setting = m_settingMap.get(settingModel.name);

                Object newValue = parseValue(settingModel.type, settingModel.value);
                setting.SetValue(newValue);

                _currentConfigId = config.Id;
            }
        }
    }

    private Object parseValue(String type, String value)
    {
        if(type.equals("System.Integer"))
            return Integer.parseInt(value);
        if(type.equals("System.Boolean"))
            return Boolean.parseBoolean(value);
        if(type.equals("System.Double"))
            return Boolean.parseBoolean(value);
        return value;
    }

}
