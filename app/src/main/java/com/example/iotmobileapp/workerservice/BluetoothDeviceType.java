package com.example.iotmobileapp.workerservice;

import android.bluetooth.BluetoothClass;

import java.util.HashMap;

public class BluetoothDeviceType
{
    private BluetoothDeviceType(){}

    public static String getString(int type)
    {
        if(s_typeMap.containsKey(type))
        {
            return s_typeMap.get(type);
        }

        return UNCATEGORISED;
    };


    private static final String UNCATEGORISED = "uncategorised";


    private static final HashMap<Integer, String> s_typeMap = new HashMap<Integer, String>()
    {{
        put(BluetoothClass.Device.AUDIO_VIDEO_CAMCORDER, "Camcorder");
        put( BluetoothClass.Device.AUDIO_VIDEO_CAR_AUDIO,
             "Car Audio");
        put( BluetoothClass.Device.AUDIO_VIDEO_HANDSFREE,
             "Handsfree");
        put( BluetoothClass.Device.AUDIO_VIDEO_HEADPHONES,
             "Headphones");
        put( BluetoothClass.Device.AUDIO_VIDEO_HIFI_AUDIO,
             "HIFI Audio");
        put( BluetoothClass.Device.AUDIO_VIDEO_LOUDSPEAKER,
             "Loudspeaker");
        put( BluetoothClass.Device.AUDIO_VIDEO_MICROPHONE,
             "Microphone");
        put( BluetoothClass.Device.AUDIO_VIDEO_PORTABLE_AUDIO,
             "Portable Audio");
        put( BluetoothClass.Device.AUDIO_VIDEO_SET_TOP_BOX,
             "Set Top Box");
        put( BluetoothClass.Device.AUDIO_VIDEO_UNCATEGORIZED,
             "Audio Uncategorized");
        put( BluetoothClass.Device.AUDIO_VIDEO_VCR,
             "VCR");
        put( BluetoothClass.Device.AUDIO_VIDEO_VIDEO_CAMERA,
             "Camera");
        put( BluetoothClass.Device.AUDIO_VIDEO_VIDEO_CONFERENCING,
             "Conferencing");
        put( BluetoothClass.Device.AUDIO_VIDEO_VIDEO_DISPLAY_AND_LOUDSPEAKER,
             "Display and Loudspeaker");
        put( BluetoothClass.Device.AUDIO_VIDEO_VIDEO_GAMING_TOY,
             "Video Gaming Toy");
        put( BluetoothClass.Device.AUDIO_VIDEO_VIDEO_MONITOR,
             "Video Monitor");
        put( BluetoothClass.Device.AUDIO_VIDEO_WEARABLE_HEADSET,
             "Wearble Headset");
        put( BluetoothClass.Device.COMPUTER_DESKTOP,
             "Desktop");
        put( BluetoothClass.Device.COMPUTER_HANDHELD_PC_PDA,
             "Handheld PC");
        put( BluetoothClass.Device.COMPUTER_LAPTOP,
             "Laptop");
        put( BluetoothClass.Device.COMPUTER_PALM_SIZE_PC_PDA,
             "Palm Size Marketing");
        put( BluetoothClass.Device.COMPUTER_SERVER,
             "Server");
        put( BluetoothClass.Device.COMPUTER_UNCATEGORIZED,
             "Computer Uncategorized");
        put( BluetoothClass.Device.COMPUTER_WEARABLE,
             "Wearable Computer");
        put( BluetoothClass.Device.HEALTH_BLOOD_PRESSURE,
             "Blood Pressure Device");
        put( BluetoothClass.Device.HEALTH_DATA_DISPLAY,
             "Health Data Display");
        put( BluetoothClass.Device.HEALTH_GLUCOSE,
             "Glucose Device");
        put( BluetoothClass.Device.HEALTH_PULSE_OXIMETER,
             "Pulse Oximeter");
        put( BluetoothClass.Device.HEALTH_PULSE_RATE,
             "Pulse Rate");
        put( BluetoothClass.Device.HEALTH_THERMOMETER,
             "Thermometer");
        put( BluetoothClass.Device.HEALTH_UNCATEGORIZED,
             "Health Uncategorized");
        put( BluetoothClass.Device.HEALTH_WEIGHING,
             "Weighing Device");
        put( BluetoothClass.Device.PHONE_CELLULAR,
             "Cell Phone");
        put( BluetoothClass.Device.PHONE_CORDLESS,
             "Cordless Phone");
        put( BluetoothClass.Device.PHONE_ISDN,
             "ISDN Phone");
        put( BluetoothClass.Device.PHONE_MODEM_OR_GATEWAY,
             "Modem");
        put( BluetoothClass.Device.PHONE_SMART,
             "Smartphone");
        put( BluetoothClass.Device.PHONE_UNCATEGORIZED,
             "Uncategorized Phone");
        put( BluetoothClass.Device.TOY_CONTROLLER,
             "Toy Controller");
        put( BluetoothClass.Device.TOY_DOLL_ACTION_FIGURE,
             "Action Figure");
        put( BluetoothClass.Device.TOY_GAME,
             "Game Toy");
        put( BluetoothClass.Device.TOY_ROBOT,
             "Robot");
        put( BluetoothClass.Device.TOY_UNCATEGORIZED,
             "Uncategorized Toy");
        put( BluetoothClass.Device.TOY_VEHICLE,
             "Toy Vehicle");
        put( BluetoothClass.Device.WEARABLE_GLASSES,
             "Wearable Glasses");
        put( BluetoothClass.Device.WEARABLE_HELMET,
             "Wearable helmet");
        put( BluetoothClass.Device.WEARABLE_JACKET,
             "Wearable Jacket");
        put( BluetoothClass.Device.WEARABLE_PAGER,
             "Wearable Pager");
        put( BluetoothClass.Device.WEARABLE_UNCATEGORIZED,
             "Wearable Uncategorized");
        put( BluetoothClass.Device.WEARABLE_WRIST_WATCH,
             "Wearable Wrist Watch");
    }};

}
