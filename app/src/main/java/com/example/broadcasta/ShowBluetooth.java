package com.example.broadcasta;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ShowBluetooth extends BroadcastReceiver
{
    private Context context= null;
    private TextView txtBluetooth = null;
    private ImageView  img_blue;

    public ShowBluetooth(Context context, TextView txtBluetooth, ImageView img_blue) {
        this.context = context;
        this.txtBluetooth = txtBluetooth;
        this.img_blue = img_blue;
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        String action=intent.getAction();

        if(action.equals(BluetoothAdapter.ACTION_STATE_CHANGED))
        {
            int state=intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,BluetoothAdapter.ERROR);

            switch(state)
            {
                case BluetoothAdapter.STATE_OFF:
                    Toast.makeText(this.context, "STATE OFF!!", Toast.LENGTH_SHORT).show();
                    txtBluetooth.setText("STATE OFF!!");
                    img_blue.setImageResource(R.drawable.bluet_off);
                    break;
                case BluetoothAdapter.STATE_TURNING_OFF:
                    Toast.makeText(this.context, "TURNING_OFF!!", Toast.LENGTH_SHORT).show();
                    txtBluetooth.setText("TURNING_OFF");
                    img_blue.setImageResource(R.drawable.bluet_off);
                    break;
                case BluetoothAdapter.STATE_ON:
                    Toast.makeText(this.context, "STATE ON!!", Toast.LENGTH_SHORT).show();
                    txtBluetooth.setText("STATE ON!!");
                    img_blue.setImageResource(R.drawable.bluet);
                    break;
                case BluetoothAdapter.STATE_TURNING_ON:
                    Toast.makeText(this.context, "STATE TURNING_ON!!", Toast.LENGTH_SHORT).show();
                    txtBluetooth.setText("TURNING_ON!!");
                    img_blue.setImageResource(R.drawable.bluet);
                    break;
            }
        }

    }
}
