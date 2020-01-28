package com.example.iotmobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iotmobileapp.config.Setting;
import com.example.iotmobileapp.workerservice.Definitions.Configuration;
import com.example.iotmobileapp.workerservice.ForegroundService;

import java.util.Collection;
import java.util.List;

public class ConfigurationActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ConfigurationAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private IForegroundServiceConnection m_serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        m_serviceConnection = new ForegroundServiceConnection();
        bindService(new Intent(ConfigurationActivity.this,
                ForegroundService.class), m_serviceConnection, Context.BIND_AUTO_CREATE);

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.configuration_swipe);
        recyclerView = (RecyclerView) findViewById(R.id.configuration_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ConfigurationAdapter();
        recyclerView.setAdapter(mAdapter);
        swipeRefreshLayout.setOnRefreshListener(refreshListener);
    }


    private class ConfigurationAdapter extends RecyclerView.Adapter<ConfigurationAdapter.SettingViewHolder>
    {
        public Setting[] settings;


        public ConfigurationAdapter()
        {
            settings = new Setting[1];
            settings[0] = new Setting("Setting",34);
        }

        @NonNull
        @Override
        public SettingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.configuration_list_row, parent, false);
            return new SettingViewHolder(v);
        }

        @Override
        public void onBindViewHolder(SettingViewHolder holder, int position) {
            holder.textView.setText(settings[position].Name());
        }

        @Override
        public int getItemCount() {
            return settings.length;
        }

        public class SettingViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView textView;
            public SettingViewHolder(View v) {
                super(v);
                textView = v.findViewById(R.id.setting_name);
            }
        }
    }

    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            if(m_serviceConnection.isConnected())
            {
                Setting[] settings = m_serviceConnection.getCurrentConfiguration().toArray(new Setting[0]);

                mAdapter.settings = settings;
                if(settings.length > 0)
                    mAdapter.notifyItemRangeChanged(0, settings.length-1);
                Log.d("Settings Length", " " + settings.length);
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    };
}
