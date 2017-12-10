package com.example.sanourtomal.finalproject.activities;


import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.sanourtomal.finalproject.R;

public class ConnectivityCheck extends Activity {

     Button Check,On,Off;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connectivity);

        initialize();


        Check.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                isInternetOn();
            }
        });
        On.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                turnWifiOn();
            }
        });

        Off.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                turnWifiOff();
            }
        });
    }



    //initialize buttons
    private void initialize() {
         Check = (Button) findViewById(R.id.btn_check);
         On=(Button)findViewById(R.id.btn_on);
         Off=(Button)findViewById(R.id.btn_off);
    }

    public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

            // if connected with internet

            Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {

            Toast.makeText(this, " Not Connected ", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }

    //turn on wifi with wifimanager
    private void turnWifiOn() {

        WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        wifi.setWifiEnabled(true);
    }

    //turn off wifi with wifimanager
    private void turnWifiOff() {

        WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        wifi.setWifiEnabled(false);
    }

    //cheaking screen off or on
    @Override
    protected void onResume() {
        super.onResume();

        PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {


            pm.isInteractive();
            turnWifiOff();
        } else {

            pm.isScreenOn();
            turnWifiOn();
        }

    }
}