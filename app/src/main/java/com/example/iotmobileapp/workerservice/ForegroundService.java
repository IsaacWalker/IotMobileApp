package com.example.iotmobileapp.workerservice;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import com.example.iotmobileapp.MainActivity;
import com.example.iotmobileapp.config.Config;
import com.example.iotmobileapp.config.ConfigProvider;
import com.example.iotmobileapp.config.Setting;
import com.example.iotmobileapp.workerservice.Database.ISharedDatabase;
import com.example.iotmobileapp.workerservice.Database.SharedDatabase;
import com.example.iotmobileapp.workerservice.Definitions.Configuration;
import com.example.iotmobileapp.workerservice.Definitions.Scan;
import com.example.iotmobileapp.workerservice.identity.UserIdentity;
import com.example.iotmobileapp.workerservice.serviceclient.APIClient;
import com.example.iotmobileapp.workerservice.serviceclient.IScanServiceClient;
import com.example.iotmobileapp.workerservice.serviceclient.ISettingServiceClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.Collection;

public class ForegroundService extends Service {


    public static final String CHANNEL_ID = "ForegroundServiceChannel";


    private final IBinder binder = new LocalBinder();


    private final ConfigProvider m_configProvider = new ConfigProvider();


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int id = intent.getIntExtra("Id",-1);
        createNotificationChannel();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Foreground Service")
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);
        //do heavy work on a background thread
        //stopSelf();

        Log.d("Id from service", ""+id);
        startWorkers();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return binder;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void startWorkers() {

        WifiManager wifiManagerManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        BluetoothManager bluetoothManager = (BluetoothManager) getApplicationContext().getSystemService(Context.BLUETOOTH_SERVICE);
        FusedLocationProviderClient locationProviderClient = LocationServices.getFusedLocationProviderClient(getApplicationContext());
        ISharedDatabase<Scan> scanDatabase = new SharedDatabase<Scan>();
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        ScannerWorker scannerWorker = new ScannerWorker(scanDatabase, wifiManagerManager, bluetoothManager, locationProviderClient, sensorManager);
        getApplicationContext().registerReceiver(scannerWorker.wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        getApplicationContext().registerReceiver(scannerWorker.bluetoothReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));

        PusherWorker pusherWorker = new PusherWorker(scanDatabase, APIClient.getClient("http://www.scan.iotrelationshipfyp.com")
                .create(IScanServiceClient.class),new UserIdentity(0));

     //   new Thread(pusherWorker).start();
       // new Thread(scannerWorker).start();
        new Thread(new SettingWorker(APIClient.getClient("http://www.setting.iotrelationshipfyp.com")
               .create(ISettingServiceClient.class), m_configProvider)).start();
    }


    public class LocalBinder extends Binder
    {
        public ForegroundService getService() {
            return ForegroundService.this;
        }
    }


    public Collection<Setting> getConfig()
    {
        return m_configProvider.getConfig();
    }







}
