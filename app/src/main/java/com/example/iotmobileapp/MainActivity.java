package com.example.iotmobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.iotmobileapp.device.Device;
import com.example.iotmobileapp.device.IDevice;
import com.example.iotmobileapp.device.IDeviceServiceClient;
import com.example.iotmobileapp.workerservice.ForegroundService;
import com.example.iotmobileapp.workerservice.serviceclient.APIClient;

public class MainActivity extends AppCompatActivity {

    private Button startServiceBtn, bindBtn;
    private IDevice m_device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_device = new Device(
                (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE),
                BluetoothAdapter.getDefaultAdapter(),
                APIClient.getClient("http://www.device.iotrelationshipfyp.com").create(IDeviceServiceClient.class)
        );

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

        m_device.TryRegister();
    }

    private View.OnClickListener StartServiceListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view) {
            startService();
            Log.d("Started Service", "started");
        }
    };

    public void startService()
    {
        Intent serviceIntent = new Intent(this, ForegroundService.class);
        serviceIntent.putExtra("Id", m_device.GetId());
        ContextCompat.startForegroundService(this, serviceIntent);
    }






}
