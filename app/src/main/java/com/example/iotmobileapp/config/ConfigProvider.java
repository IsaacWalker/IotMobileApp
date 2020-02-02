package com.example.iotmobileapp.config;

import android.util.Log;

import com.example.iotmobileapp.workerservice.Definitions.Configuration;
import com.example.iotmobileapp.workerservice.Definitions.SettingModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class ConfigProvider implements IConfigProvider
{
    private static HashMap<String, Setting> m_settingMap = new HashMap<>();


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


    public static List<SettingModel> GetSettingModels()
    {
        Setting[] settings = m_settingMap.values().toArray(new Setting[0]);

        ArrayList<SettingModel> settingModels = new ArrayList<>();

        for(Setting s : settings)
        {
            SettingModel model = new SettingModel();
            model.name = s.Name();
            model.type = s.Type();
            model.value = s.Value().toString();

            settingModels.add(model);
        }

        return settingModels;
     }


    // Updating a global configuration
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
            }
        }
    }


    @Override
    public void updateSetting(ISetting setting, Object value) {
        Setting settingObj = m_settingMap.get(setting.Name());
        settingObj.SetValue(value);
    }


    @Override
    public Collection<Setting> getConfig() {
        return m_settingMap.values();
    }


    @Override
    public int getGlobalConfigId(int localId) {
        return 0;
    }
}
