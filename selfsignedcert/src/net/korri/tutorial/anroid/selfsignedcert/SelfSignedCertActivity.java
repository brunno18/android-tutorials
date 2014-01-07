package net.korri.tutorial.anroid.selfsignedcert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SelfSignedCertActivity extends Activity {

	private static final String TAG = "SelfSignedCertActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_self_signed_cert);
	}

	public void onOpenConnectionClick(View view) {

		new OpenConnectionTask().execute();
	}

	private class OpenConnectionTask extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			// Dummy trust manager that trusts all certificates
			TrustManager localTrustmanager = new X509TrustManager() {

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}

				@Override
				public void checkClientTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {

				}
			};

			// Create SSLContext and set the socket factory as default
			try {
				SSLContext sslc = SSLContext.getInstance("SSL");
				sslc.init(null, new TrustManager[] { localTrustmanager },
						new SecureRandom());
				HttpsURLConnection.setDefaultSSLSocketFactory(sslc.getSocketFactory());
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (KeyManagementException e) {
				e.printStackTrace();
			}
		}

		@Override
		protected Boolean doInBackground(Void... params) {

			try {

				URL url = new URL("https://www.korri.net/");

				HttpsURLConnection connection = (HttpsURLConnection) url
						.openConnection();

				// Log the server response code
				int responseCode = connection.getResponseCode();
				Log.i(TAG, "Server responded with: " + responseCode);

				// And if the code was HTTP_OK then return true
				if (responseCode == HttpURLConnection.HTTP_OK) {
					return true;
				}

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return false;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);

			// Hide progressbar
			setProgressBarIndeterminateVisibility(false);

			if (result != null) {

				// Create a dialog
				Builder builder = new Builder(SelfSignedCertActivity.this);
				if (result) {
					builder.setMessage("Connection was opened successfully");
				} else {
					builder.setMessage("Connection failed");
				}
				builder.setPositiveButton("OK", null);

				// and show it
				builder.create().show();
			}
		}
	}
}
