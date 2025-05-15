package com.example.broadcasta;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowBattery  extends BroadcastReceiver
{
    private TextView txtLevel = null;
    private ImageView img_b;

    public ShowBattery(TextView txtLevel,ImageView img_b)
    {
        this.txtLevel = txtLevel;
        this.img_b=img_b;
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        int battery_level = intent.getIntExtra("level",0);
        txtLevel.setText("Battery Level:" + String.valueOf(battery_level) + "%");
        if(battery_level<20)
            img_b.setImageResource(R.drawable.low_battery);
        else if(battery_level<50)
            img_b.setImageResource(R.drawable.half_battery);
        else
            img_b.setImageResource(R.drawable.full_battery);
    }
}
