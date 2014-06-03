package com.caphal.android.networkconnection;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import com.caphal.android.networkconnection.R;

public class NetworkConnectionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_network_connection);
	}
	
	public void onCheckNetworkClick(View view) {
		Builder builder = new Builder(this);
		
		builder.setTitle(R.string.app_name);
		builder.setMessage("Network connection available: " + isNetworkConnectionAvailable());
		
		builder.create().show();
	}

	private boolean isNetworkConnectionAvailable() {

		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo currentNetwork = cm.getActiveNetworkInfo();

		// Return the value of isConnected if the network connection is
		// available
		if (currentNetwork != null) {
			return currentNetwork.isConnected();
		}

		// Otherwise return false
		return false;
	}
}