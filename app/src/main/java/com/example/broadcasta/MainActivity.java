package com.example.broadcasta;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    private ImageView img_b,img_a,img_blue,img_w;

    private ShowBattery showBattery = null;
    private ShowBluetooth showBluetooth = null;
    private ShowAirPlane showAirPlane = null;
    private ShowWifi showWifi= null;


    private TextView txtLevel = null,txtBluetooth=null,txtAir=null,txtWifi=null;

    private boolean isBluetoothEnabled()
    {
        // Get the BluetoothAdapter system service
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if(bluetoothAdapter != null)// Return true if Bluetooth is enabled, false otherwise
        return bluetoothAdapter.isEnabled();
        else
            return false;
    }


    private boolean isAirplaneModeOn()
    {
        // Get the current Airplane Mode status using Settings.System
        int airplaneModeSetting = Settings.System.getInt(
                getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0);

        return airplaneModeSetting == 1;
    }

    private void Start()
    {
        if (isAirplaneModeOn())
        {
            txtAir.setText("AirPlane mode is on");
            img_a.setImageResource(R.drawable.plane_on);
        }

       else
        {
            txtAir.setText("AirPlane mode is off");
            img_a.setImageResource(R.drawable.plane_off);
        }

       //========================================

        if (isBluetoothEnabled())
        {
            txtBluetooth.setText("Bluetooth is ON");
            img_blue.setImageResource(R.drawable.bluet);
        }

        else
        {
            txtBluetooth.setText("Bluetooth is Off");
            img_blue.setImageResource(R.drawable.bluet_off);
        }

    }


    @Override
    protected void onResume()
    {
        super.onResume();

        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.wifi.STATE_CHANGE");
        filter.addAction("android.net.wifi.WIFI_STATE_CHANGED");


        registerReceiver(showBattery, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        registerReceiver(showBluetooth,new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
        registerReceiver(showAirPlane, new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED));
        registerReceiver(showWifi, new IntentFilter(filter));
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        unregisterReceiver(showBattery);
        unregisterReceiver(showBluetooth);
        unregisterReceiver(showAirPlane);
    }

    private void Wifi()
    {
        txtWifi=findViewById(R.id.txtWifi);
        img_w=findViewById(R.id.img_w);
        showWifi = new ShowWifi(txtWifi,img_w);
    }

    private void Bluetooth()
    {
        txtBluetooth=findViewById(R.id.txtBluetooth);
        img_blue=findViewById(R.id.img_blue);
        showBluetooth = new ShowBluetooth(this,txtBluetooth,img_blue);
    }


    private void AirPlane()
    {
        txtAir=findViewById(R.id.txtAir);
        img_a=findViewById(R.id.img_a);
        showAirPlane = new ShowAirPlane(txtAir,img_a);
    }

    private void Battery()
    {
        txtLevel = findViewById(R.id.txtLevel);
        img_b= findViewById(R.id.img_b);
        showBattery = new ShowBattery(txtLevel,img_b);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        Battery();
        Bluetooth();
        AirPlane();
        Wifi();

        Start();


    }
}