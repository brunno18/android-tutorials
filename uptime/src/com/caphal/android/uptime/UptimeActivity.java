package com.caphal.android.uptime;

import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.TextView;

public class UptimeActivity extends Activity {

    private TextView wholeView;
    private TextView withoutView;

    private Handler updateHandler;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uptime);

        wholeView = (TextView) findViewById(R.id.uptime_whole_count);
        withoutView = (TextView) findViewById(R.id.uptime_without_sleep_count);

        updateHandler = new Handler();

        updateUptimes();
        
    }

    private void updateUptimes() {
        
        // Get the whole uptime
        long uptimeMillis = SystemClock.elapsedRealtime();
        String wholeUptime = String.format(
                "%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(uptimeMillis),
                TimeUnit.MILLISECONDS.toMinutes(uptimeMillis)
                        - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
                                .toHours(uptimeMillis)),
                TimeUnit.MILLISECONDS.toSeconds(uptimeMillis)
                        - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                                .toMinutes(uptimeMillis)));
        wholeView.setText(wholeUptime);

        // Get the uptime without deep sleep
        long elapsedMillis = SystemClock.uptimeMillis();        
        String withouUptime = String.format(
                "%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(elapsedMillis),
                TimeUnit.MILLISECONDS.toMinutes(elapsedMillis)
                        - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
                                .toHours(elapsedMillis)),
                TimeUnit.MILLISECONDS.toSeconds(elapsedMillis)
                        - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                                .toMinutes(elapsedMillis)));
        withoutView.setText(withouUptime);
        
        // Call updateUptimes after one second
        updateHandler.postDelayed(new Runnable() {
            
            @Override
            public void run() {
                updateUptimes();
            }
        }, 1000);
    }
}
