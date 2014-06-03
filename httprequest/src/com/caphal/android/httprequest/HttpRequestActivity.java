package com.caphal.android.httprequest;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import com.caphal.android.httprequest.R;

public class HttpRequestActivity extends Activity {

	private static final String TAG = "HttpRequestActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);  
		setContentView(R.layout.activity_http_request);
	}

	public void onStartRequestClick(View view) {
		// Start a background task which handles the page fetching
		new FetchTask().execute();
	}

	private class FetchTask extends AsyncTask<Void, Void, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			// Show progressbar
			setProgressBarIndeterminateVisibility(true);
		}
		
		@Override
		protected String doInBackground(Void... params) {

			try {
				URL url = new URL("http://caphal.com/");

				HttpURLConnection connection = (HttpURLConnection) url.openConnection();

				// Log the server response code
				int responseCode = connection.getResponseCode();
				Log.i(TAG, "Server responded with: " + responseCode);

				// And if the code was HTTP_OK then parse the contents
				if (responseCode == HttpURLConnection.HTTP_OK) {

					// Convert request content to string
					InputStream is = connection.getInputStream();
					String content = convertInputStream(is, "UTF-8");
					is.close();

					return content;
				}

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return null;
		}

		private String convertInputStream(InputStream is, String encoding) {
			Scanner scanner = new Scanner(is, encoding).useDelimiter("\\A");
			return scanner.hasNext() ? scanner.next() : "";
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			
			// Hide progressbar
			setProgressBarIndeterminateVisibility(false);

			if (result != null) {
				
				// Create a dialog
				Builder builder = new Builder(HttpRequestActivity.this);
				builder.setMessage(result.substring(0, 200) + "...");
				builder.setPositiveButton("OK", null);

				// and show it
				builder.create().show();
			}
		}

	}
}
