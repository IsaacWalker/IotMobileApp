package com.example.iotmobileapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.iotmobileapp.config.Setting;

public class EditSettingDialog extends DialogFragment
{
    TextView nameView, typeView;
    EditText valueView;
    Setting setting;
    public EditSettingDialog(Setting setting)
    {
        this.setting = setting;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d("Creating Dialog", "Started");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("Name");

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_edit_setting, null);

        nameView = v.findViewById(R.id.dialog_name);
        typeView = v.findViewById(R.id.dialog_type);
        valueView = v.findViewById(R.id.dialog_value);

        nameView.setText(setting.Name());
        typeView.setText(setting.Type());
        valueView.setText(setting.Value().toString());

        builder.setView(v)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String newValueString = valueView.getText()
                                .toString();
                        Object newValue = Setting.SettingType.parseValue(setting.Type(),newValueString);
                        setting.SetValue(newValue);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        return builder.create();
    }
}
