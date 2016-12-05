package com.andapp.futcorp.obdfut;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView2);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter.isEnabled()) {
            imageView.setImageResource(0);
            imageView.setBackgroundResource(R.drawable.ic_bluetooth3_connected_final);
        } else {
            imageView.setImageResource(0);
            imageView.setBackgroundResource(R.drawable.ic_bluetooth3_disconnected_final);
        }
    }

    public void connectToBluetooth(View view) {

        if (mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.disable();
            imageView.setImageResource(0);
            imageView.setBackgroundResource(R.drawable.ic_bluetooth3_disconnected_final);
            SingleToast.show(this, "Bluetooth kesildi", Toast.LENGTH_SHORT);
        } else {
            mBluetoothAdapter.enable();
            imageView.setImageResource(0);
            imageView.setBackgroundResource(R.drawable.ic_bluetooth3_connected_final);
            SingleToast.show(this, "Bluetooth bağlandı", Toast.LENGTH_SHORT);
        }


    }

    public void goToRealTimeInfo(View view) {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter.isEnabled()) {
            Intent intent = new Intent(MainActivity.this, RealTimeInfoTest.class);
            MainActivity.this.startActivity(intent);
        } else {
            SingleToast.show(this, "Cihazınınz bluetooth'unu açınız", Toast.LENGTH_LONG);
        }

    }

    public void goToFirstFaultCodes(View view) {
        Intent intent = new Intent(MainActivity.this, FirstFaultCodes.class);
        MainActivity.this.startActivity(intent);
    }

    public void goToSettingsPage(View view) {
        Intent intent = new Intent(MainActivity.this, SettingsPage.class);
        MainActivity.this.startActivity(intent);
    }
}
