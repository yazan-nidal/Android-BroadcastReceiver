package com.example.project4;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BReceiver receiver = null;
    IntentFilter filter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkSelfPermission ( Manifest.permission.CALL_PHONE )== PackageManager.PERMISSION_DENIED){
            requestPermissions (new String[]{Manifest.permission.CALL_PHONE} ,999);
        }

        if (checkSelfPermission ( Manifest.permission.ANSWER_PHONE_CALLS )== PackageManager.PERMISSION_DENIED){
            requestPermissions (new String[]{Manifest.permission.ANSWER_PHONE_CALLS} ,999);
        }

        if (checkSelfPermission ( Manifest.permission.READ_PHONE_NUMBERS )== PackageManager.PERMISSION_DENIED){
            requestPermissions (new String[]{Manifest.permission.READ_PHONE_NUMBERS} ,999);
        }

        if (checkSelfPermission ( Manifest.permission.READ_PHONE_STATE )== PackageManager.PERMISSION_DENIED){
            requestPermissions (new String[]{Manifest.permission.READ_PHONE_STATE} ,999);
        }


        if (checkSelfPermission ( Manifest.permission.READ_CALL_LOG )== PackageManager.PERMISSION_DENIED){
            requestPermissions (new String[]{Manifest.permission.READ_CALL_LOG} ,999);
        }


        /*
        //WRITE_EXTERNAL_STORAGE

        if (checkSelfPermission ( Manifest.permission.WRITE_EXTERNAL_STORAGE )== PackageManager.PERMISSION_DENIED){
            requestPermissions (new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE} ,999);
        }
*/
        receiver = new BReceiver();
        filter = new IntentFilter();

        filter.addAction (Intent.ACTION_AIRPLANE_MODE_CHANGED);

        filter.addAction (Intent.ACTION_BATTERY_CHANGED);
        filter.addAction (Intent.ACTION_BATTERY_LOW);
        filter.addAction (Intent.ACTION_BATTERY_OKAY);
        filter.addAction (Intent.ACTION_POWER_CONNECTED);
        filter.addAction (Intent.ACTION_POWER_DISCONNECTED);

        filter.addAction (WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION);

        filter.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
        filter.addAction (Intent.ACTION_NEW_OUTGOING_CALL );


        // filter.addAction (Intent.ACTION_MEDIA_EJECT);


        //filter.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED);

        filter.setPriority(1000);

        registerReceiver(receiver,filter);




    }
}