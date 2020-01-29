package com.example.iotmobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }


    private class ConfigurationAdapter extends RecyclerView.Adapter<ConfigurationAdapter.SettingViewHolder>
    {
        public Setting[] settings;


        public ConfigurationAdapter()
        {
            settings=new Setting[0];
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

            final String name = settings[position].Name();
            final String value = settings[position].Value().toString();

            holder.nameView.setText(name);
            holder.valueView.setText(value);
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) {
                    DialogFragment newFragment = new EditSettingDialog(name);

                    newFragment.show(getSupportFragmentManager(), "missiles");
                }
            });
        }

        @Override
        public int getItemCount() {
            return settings.length;
        }

        public class SettingViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView nameView;
            public TextView valueView;
            public SettingViewHolder(View v) {
                super(v);
                nameView = v.findViewById(R.id.setting_name);
                valueView = v.findViewById(R.id.setting_value);
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
                {
                    mAdapter.notifyDataSetChanged();
                }

                swipeRefreshLayout.setRefreshing(false);
            }
        }
    };



}
