package com.example.activity_analysis_app;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static com.example.activity_analysis_app.CurrentActivity.REQUEST_ENABLE_BT;

public class ExpandableListDataPump {

    public static HashMap<String, List<String>> getData() {
        CurrentActivity CA = new CurrentActivity();
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        expandableListDetail.put("BLUETOOTH DEVICES", CA.devices);
        return expandableListDetail;
    }
}