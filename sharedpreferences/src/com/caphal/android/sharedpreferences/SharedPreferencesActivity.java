package com.caphal.android.sharedpreferences;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.caphal.android.sharedpreferences.R;

public class SharedPreferencesActivity extends Activity {

	private static final String KEY = "key";

	private SharedPreferences preferences;

	private EditText textField;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shared_preferences);

		preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
		textField = (EditText) findViewById(R.id.editText);
	}

	@SuppressLint("NewApi")
	public void onWriteClick(View view) {

		Editor editor = preferences.edit();
		editor.putString(KEY, textField.getText().toString());

		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.GINGERBREAD) {
			editor.apply();
		} else {
			// This call is synchronous and should be done in a background
			// thread
			editor.commit();
		}
	}
	
	
	public void onReadClick(View view) {

		String text = "Saved value is " + preferences.getString(KEY, null);
		
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}

}
