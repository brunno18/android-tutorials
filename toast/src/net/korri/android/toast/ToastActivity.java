package net.korri.android.toast;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class ToastActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_toast);
	}

	/**
	 * Shows a toast for a short time period
	 * 
	 * @param view
	 */
	public void onShortClick(View view) {
		Toast.makeText(this, "Short toast", Toast.LENGTH_SHORT).show();
	}

	/**
	 * Shows a toast for a longer time period
	 * 
	 * @param view
	 */
	public void onLongClick(View view) {
		Toast.makeText(this, "Long toast", Toast.LENGTH_LONG).show();
	}

	/**
	 * Shows a toast with a custom gravity
	 * 
	 * @param view
	 */
	public void onGravityClick(View view) {
		Toast toast = Toast.makeText(this, R.string.gravity_toast_text, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM | Gravity.RIGHT, 0, 0);
		toast.show();
	}

	/**
	 * Shows toast with a custom view as a contents
	 * 
	 * @param view
	 */
	public void onCustomClick(View view) {

		// Inflate custom toast view
		LayoutInflater inflater = getLayoutInflater();
		View toastLayout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.toast_layout_root));

		Toast toast = new Toast(getApplicationContext());
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(toastLayout);
		toast.show();
	}
}
