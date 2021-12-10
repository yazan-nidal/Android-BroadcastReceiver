package com.example.project4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class BReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving





           if (intent.getAction ().equals ( TelephonyManager.ACTION_PHONE_STATE_CHANGED )) {

            /*  if (intent.getAction ().equals ( Intent.ACTION_NEW_OUTGOING_CALL )){

                  Toast.makeText ( context, "The phone number : '"
                          +intent.getStringExtra ( Intent.EXTRA_PHONE_NUMBER )
                          +"' is now being called '"
                         , Toast.LENGTH_LONG ).show ();

                  return;
              }*/

               // for  beginning outgoing calls , for answer incoming calls
               if(intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_OFFHOOK))
               {
                   String s =  intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

                   if(s!=null) {
                       Toast.makeText(context, "The phone number : '"
                               + s
                               + "' is now being call ", Toast.LENGTH_LONG)
                               .show();
                   }
                   return;
               }else
               if(intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_OFFHOOK))


               if(intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE))
               {
                   String s =  intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

                   if(s!=null) {
                       Toast.makeText(context, "The call has ended with the phone number : '"
                               + s
                               + "'", Toast.LENGTH_LONG)
                               .show();
                   }
                   return;
               }



            String outState = "";
            TelephonyManager tel = (TelephonyManager)context.getSystemService(context.TELEPHONY_SERVICE);
            int state = tel.getCallState();

            switch (state)
            {
                case TelephonyManager.CALL_STATE_IDLE:{
                    outState = "IDLE";
                    break;
                }

                case TelephonyManager.CALL_STATE_OFFHOOK:{
                    outState = "OFFHOOK";
                    break;
                }

                case TelephonyManager.CALL_STATE_RINGING:{
                    String s =  intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

                    if(s!=null) {
                        outState = "The Phone : '"
                                + s
                                + "' is call you";
                    }
                    else
                    {
                        outState =null;
                    }
                }
            }

          if(outState != null) {
              Toast.makeText(context, "Phone Status : " + outState, Toast.LENGTH_LONG)
                      .show();
          }

                    return;
        }

        if (intent.getAction ().equals ( Intent.ACTION_AIRPLANE_MODE_CHANGED ) )
        {
            Toast.makeText(context , "AIRPLANE_MODE : "
                    + (intent.getBooleanExtra("state", false)?"ON":"OFF"), Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if (intent.getAction ().equals ( Intent.ACTION_BATTERY_OKAY ) )
        {
            Toast.makeText(context , "BATTERY is OKAY ",Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if (intent.getAction ().equals ( Intent.ACTION_BATTERY_LOW ) )
        {
            Toast.makeText(context , "BATTERY is LOW ,please connect charger ",Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if (intent.getAction ().equals ( Intent.ACTION_BATTERY_CHANGED) )
        {
            Intent batteryStatus = context.registerReceiver(null,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

            float batteryPct = level * 100 / (float)scale;
            Toast.makeText(context , "battery : "+batteryPct+"%",Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        if (intent.getAction ().equals ( Intent.ACTION_POWER_CONNECTED ) )
        {
            Intent batteryStatus = context.registerReceiver(null,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

            float batteryPct = level * 100 / (float)scale;
            Toast.makeText(context , "The battery is charging : "+batteryPct+"%",Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if (intent.getAction ().equals ( Intent.ACTION_POWER_DISCONNECTED ) )
        {
            Intent batteryStatus = context.registerReceiver(null,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

            float batteryPct = level * 100 / (float)scale;
            Toast.makeText(context , "charging is off : "+batteryPct+"%",Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if (intent.getAction ().equals ( WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION ) ) {

            final String action = intent.getAction();
            if (action.equals(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION)) {
                if (intent.getBooleanExtra(WifiManager.EXTRA_SUPPLICANT_CONNECTED, false)) {
                    Toast.makeText(context , "WIFI is on",Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(context , "WIFI is off",Toast.LENGTH_SHORT)
                            .show();
                }
            }

        }
/*
        if (intent.getAction ().equals ( Intent.ACTION_MEDIA_EJECT ) ) {
            Toast.makeText(context , "The SD was Ejected",Toast.LENGTH_LONG)
                    .show();
        }*/

        //Toast.makeText ( context, intent.getAction (), Toast.LENGTH_LONG ).show ();

        //throw new UnsupportedOperationException("Not yet implemented");
    }
}