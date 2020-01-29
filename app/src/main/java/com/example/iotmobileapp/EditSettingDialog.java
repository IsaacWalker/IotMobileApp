package com.example.iotmobileapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class EditSettingDialog extends DialogFragment
{
    TextView nameView;
    String name;
    public EditSettingDialog(String name)
    {
        this.name = name;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d("Creating Dialog", "Started");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("Name");

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View v =inflater.inflate(R.layout.dialog_edit_setting, null);
        nameView = v.findViewById(R.id.dialog_name);
        nameView.setText(name);
        builder.setView(v)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        return builder.create();
    }
}
