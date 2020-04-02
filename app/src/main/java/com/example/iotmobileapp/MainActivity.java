package com.example.iotmobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.iotmobileapp.device.Device;
import com.example.iotmobileapp.device.DeviceModel;
import com.example.iotmobileapp.device.IDevice;
import com.example.iotmobileapp.device.IDeviceServiceClient;
import com.example.iotmobileapp.gdpr.PrivacyActivity;
import com.example.iotmobileapp.security.GatewayTokenService;
import com.example.iotmobileapp.security.IGatewayTokenService;
import com.example.iotmobileapp.security.IJwtTokenService;
import com.example.iotmobileapp.workerservice.ForegroundService;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetClient;

public class MainActivity extends AppCompatActivity {

    private Button startServiceBtn, bindBtn, privacyBtn;
    private IDevice m_device;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_device = new Device( BluetoothAdapter.getDefaultAdapter());

        startServiceBtn = findViewById(R.id.startServiceBtn);
        startServiceBtn.setOnClickListener(StartServiceListener);
        bindBtn = findViewById(R.id.bindBtn);

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
         int locationRequestCode = 1000;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        locationRequestCode);

        }
        bindBtn.setOnClickListener(BindServiceListener);

        privacyBtn = findViewById(R.id.privacyBtn);
        privacyBtn.setOnClickListener(PrivacyButtonListener);
    }


    private View.OnClickListener StartServiceListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view) {
            startService();
            Log.d("Started Service", "started");
        }
    };


    private View.OnClickListener BindServiceListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, ConfigurationActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener PrivacyButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DeviceModel model = new DeviceModel();
            model.Model = m_device.GetModel();
            model.Manufacturer = m_device.GetManufacturer();
            model.MacAddress = m_device.GetMacAddress();
            model.BluetoothName = m_device.GetBluetoothName();

            IGatewayTokenService gatewayTokenService = new GatewayTokenService(
                    APIClient.getClient("http://www.iotrelationshipfyp.com")
                    .create(IJwtTokenService.class),
                    SafetyNet.getClient(MainActivity.this),
                    model);

            gatewayTokenService.getCurrentToken();
          //  Intent intent = new Intent(MainActivity.this, PrivacyActivity.class);
            //intent.putExtra("Id", m_device.GetId());
            //startActivity(intent);
        }
    };

    public void startService()
    {
        Intent serviceIntent = new Intent(this, ForegroundService.class);
        serviceIntent.putExtra("Id", m_device.GetMacAddress());
        ContextCompat.startForegroundService(this, serviceIntent);
    }


}
