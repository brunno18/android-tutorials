package net.korri.tutorial.android.webview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * <p>
 * WebViewActivity is an example Activity that shows how to access JavaScript
 * functions from application code and application code from JavaScript
 * functions.
 * </p>
 * <p>
 * This class contains two subclasses, CustomWebViewClient and
 * JavaScriptInterface. CustomWebViewClient is used to add hooks to WebView
 * events like url loading and loading started. JavaScriptInterface on the other
 * hand is used as the interface class between WebView's JavaScript context and
 * the application code. All public methods in JavaScriptInterface can be
 * invoked from the JavaScript code.
 * </p>
 * 
 * @author Taneli Korri <tkorri@gmail.com>
 * 
 */
public class WebViewActivity extends Activity {

	/**
	 * The visible WebView instance
	 */
	private WebView webView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);

		webView = (WebView) findViewById(R.id.webView);
		webView.getSettings().setJavaScriptEnabled(true);

		// Add the custom WebViewClient class
		webView.setWebViewClient(new CustomWebViewClient());

		// Add the javascript interface
		webView.addJavascriptInterface(new JavaScriptInterface(), "interface");

		// Load the example html file to the WebView
		webView.loadUrl("file:///android_asset/index.html");
	}

	/**
	 * Onclick callback method for Button.
	 * 
	 * @param view
	 */
	public void onButtonClick(View view) {
		webView.loadUrl("javascript:(function() { callFromApp();})()");
	}

	/**
	 * CustomWebViewclient is used to add a custom hook to the url loading.
	 * 
	 */
	private class CustomWebViewClient extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {

			// If the url to be loaded starts with the custom protocol, skip
			// loading and do something else
			if (url.startsWith("korri://")) {

				Toast.makeText(WebViewActivity.this, "Custom protocol call", Toast.LENGTH_LONG).show();

				return true;
			}

			return false;
		}
	}

	/**
	 * JavaScriptInterface is the interface class for the application code
	 * calls. All public methods in this class can be called from JavaScript.
	 * 
	 */
	private class JavaScriptInterface {

		public void callFromJS() {
			Toast.makeText(WebViewActivity.this, "JavaScript interface call", Toast.LENGTH_LONG).show();
		}
	}
}
