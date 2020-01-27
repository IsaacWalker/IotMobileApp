package com.example.iotmobileapp.device;

import android.bluetooth.BluetoothAdapter;
import android.net.MacAddress;
import android.net.wifi.WifiManager;
import android.os.Build;
import java.net.NetworkInterface;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.util.Collections.*;

public class Device implements IDevice
{
    private String _model;
    private String _manufacturer;
    private String _macAddress;
    private String _bluetoothName;
    private int _id;
    private boolean _isRegistered;

    private final IDeviceServiceClient m_deviceServiceClient;

    public Device(WifiManager wifiManager,
                  BluetoothAdapter bluetoothAdapter,
                  IDeviceServiceClient serviceClient)
    {
        _isRegistered = false;
        _model = Build.MODEL;
        _manufacturer = Build.MANUFACTURER;
        _macAddress = getMacAddr();
        _bluetoothName = bluetoothAdapter.getName();
        m_deviceServiceClient = serviceClient;
    }

    @Override
    public String GetModel() {
        return _model;
    }

    @Override
    public String GetManufacturer() {
        return _manufacturer;
    }


    @Override
    public String GetMacAddress() {
        return _macAddress;
    }


    @Override
    public String GetBluetoothName() {
        return _bluetoothName;
    }

    @Override
    public int GetId() {
        return _id;
    }

    @Override
    public boolean IsRegistered() {
        return _isRegistered;
    }

    @Override
    public void TryRegister() {
        DeviceModel model = new DeviceModel();
        model.BluetoothName = _bluetoothName;
        model.MacAddress = _macAddress;
        model.Manufacturer = _manufacturer;
        model.Model = _model;

        Call<Integer> call =  m_deviceServiceClient.RegisterDevice(model);
        call.enqueue(registerDeviceCallback);
    }


    private String getMacAddr() {
        try {
            List<NetworkInterface> all = list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(Integer.toHexString(b & 0xFF) + ":");
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }

        return "02:00:00:00:00:00";
    }

    private final Callback<Integer> registerDeviceCallback = new Callback<Integer>()
    {

        @Override
        public void onResponse(Call<Integer> call, Response<Integer> response) {
            if(response.isSuccessful())
            {
                _id = response.body();
                _isRegistered = true;
            }
        }

        @Override
        public void onFailure(Call<Integer> call, Throwable t) {

        }
    };
}
