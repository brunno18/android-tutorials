package net.korri.tutorial.android.trafficstats;

import android.app.Activity;
import android.net.TrafficStats;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TrafficStatsActivity extends Activity {

	private TextView mobileRxBytes;
	private TextView mobileRxPackets;

	private TextView totalRxBytes;
	private TextView totalRxPackets;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_traffic_stats);

		mobileRxBytes = (TextView) findViewById(R.id.mobile_rx_bytes);
		mobileRxPackets = (TextView) findViewById(R.id.mobile_rx_packets);

		totalRxBytes = (TextView) findViewById(R.id.total_rx_bytes);
		totalRxPackets = (TextView) findViewById(R.id.total_rx_packets);

		refreshTrafficStats();
	}

	/**
	 * Update traffic stats
	 */
	private void refreshTrafficStats() {

		mobileRxBytes.setText(TrafficStats.getMobileRxBytes() + "");
		mobileRxPackets.setText(TrafficStats.getMobileRxPackets() + "");

		totalRxBytes.setText(TrafficStats.getTotalRxBytes() + "");
		totalRxPackets.setText(TrafficStats.getTotalRxPackets() + "");
	}

	public void onRefreshClick(View view) {
		refreshTrafficStats();
	}
}
