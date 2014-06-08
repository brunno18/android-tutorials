package com.caphal.android.batteryinfo;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;

public class BatteryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);

        // Get the sticky battery changed intent
        IntentFilter intentFilter = new IntentFilter(
                Intent.ACTION_BATTERY_CHANGED);
        Intent statusIntent = registerReceiver(null, intentFilter);

        // Battery charger type
        int chargePlug = statusIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED,
                -1);
        String chargerType = "-";
        switch (chargePlug) {
        case BatteryManager.BATTERY_PLUGGED_AC:
            chargerType = "AC";
            break;
        case BatteryManager.BATTERY_PLUGGED_USB:
            chargerType = "USB";
            break;
        case BatteryManager.BATTERY_PLUGGED_WIRELESS:
            chargerType = "Wireless";
            break;
        }
        ((TextView)findViewById(R.id.chargerType)).setText(chargerType);
        
        // Battery status
        int statusType = statusIntent.getIntExtra(BatteryManager.EXTRA_STATUS,
                BatteryManager.BATTERY_STATUS_UNKNOWN);

        String batteryStatus = null;
        switch (statusType) {
        case BatteryManager.BATTERY_STATUS_CHARGING:
            batteryStatus = "Charging";
            break;
        case BatteryManager.BATTERY_STATUS_DISCHARGING:
            batteryStatus = "Discharging";
            break;
        case BatteryManager.BATTERY_STATUS_FULL:
            batteryStatus = "Full";
            break;
        case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
            batteryStatus = "Not charging";
            break;
        case BatteryManager.BATTERY_STATUS_UNKNOWN:
            batteryStatus = "Unknown";
            break;
        }
        ((TextView)findViewById(R.id.batteryStatus)).setText(batteryStatus);
        
        // Battery level
        int level = statusIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = statusIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        String batteryLevel = Integer
                .toString((int) (((float) level / (float) scale) * 100));
        ((TextView)findViewById(R.id.batteryLevel)).setText(batteryLevel);
        
        // Battery health
        int healthType = statusIntent.getIntExtra(BatteryManager.EXTRA_HEALTH,
                BatteryManager.BATTERY_HEALTH_UNKNOWN);
        String batteryHealth = null;
        switch (healthType) {
        case BatteryManager.BATTERY_HEALTH_COLD:
            batteryHealth = "Cold";
            break;
        case BatteryManager.BATTERY_HEALTH_DEAD:
            batteryHealth = "Dead";
            break;
        case BatteryManager.BATTERY_HEALTH_GOOD:
            batteryHealth = "Good";
            break;
        case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
            batteryHealth = "Over voltage";
            break;
        case BatteryManager.BATTERY_HEALTH_OVERHEAT:
            batteryHealth = "Overheat";
            break;
        case BatteryManager.BATTERY_HEALTH_UNKNOWN:
            batteryHealth = "Unknown";
            break;
        case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
            batteryHealth = "Unspecified failure";
            break;
        }
        ((TextView)findViewById(R.id.batteryHealth)).setText(batteryHealth);

        // Battery technology
        String batteryTech = statusIntent
                .getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);
        ((TextView)findViewById(R.id.batteryTech)).setText(batteryTech);

        // Battery temperature
        String batteryTemp = null;
        int temperatureInt = statusIntent.getIntExtra(
                BatteryManager.EXTRA_TEMPERATURE, Integer.MIN_VALUE);
        if (temperatureInt != Integer.MIN_VALUE) {
            batteryTemp = Float.toString(((float) temperatureInt) / 10) + " °C";
        }
        ((TextView)findViewById(R.id.batteryTemp)).setText(batteryTemp);

        // Battery voltage
        String batteryVoltage = null;
        int voltageInt = statusIntent.getIntExtra(BatteryManager.EXTRA_VOLTAGE,
                Integer.MIN_VALUE);
        if (voltageInt != Integer.MIN_VALUE) {
            batteryVoltage = Float.toString(((float) voltageInt) / 1000) + " V";
        }
        ((TextView)findViewById(R.id.batteryVoltage)).setText(batteryVoltage);
    }

}
