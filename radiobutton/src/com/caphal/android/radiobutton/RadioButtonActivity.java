package com.caphal.android.radiobutton;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.caphal.android.radiobutton.R;

public class RadioButtonActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_radiobutton);
	}

	public void onRadioButtonClicked(View view) {

		String selection = "";
		
		switch (view.getId()) {
		case R.id.option_africa:
			selection = "Africa";
			break;
		case R.id.option_america:
			selection = "America";
			break;
		case R.id.option_asia:
			selection = "Asia";
			break;
		case R.id.option_australia:
			selection = "Australia";
			break;
		case R.id.option_europe:
			selection = "Europe";
			break;
		}
		
		Toast.makeText(this, selection, Toast.LENGTH_SHORT).show();
	}
}
