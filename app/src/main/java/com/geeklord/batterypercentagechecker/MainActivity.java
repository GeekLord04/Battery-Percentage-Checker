package com.geeklord.batterypercentagechecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.CircleProgress;

public class MainActivity extends AppCompatActivity {
    TextView battery;
    TextView status;
    CircleProgress circle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        battery = findViewById(R.id.battery);
        status = findViewById(R.id.status);
        circle = findViewById(R.id.circlebar);

        this.registerReceiver(this.myBatteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }
    BroadcastReceiver myBatteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int batterylevel = intent.getIntExtra("level" , 0);
            battery.setText("The battery level is: "+ batterylevel +"%");
            circle.setProgress(batterylevel);

            if(batterylevel<25){
                status.setText("Get your phone charged");
            }
            else if(batterylevel>25){
                status.setText("Sufficient Charge");
            }
        }
    };
}