package com.caphal.android.spinner;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.caphal.android.spinner.R;

public class SpinnerActivity extends Activity implements OnItemSelectedListener {

	private Spinner customListSpinner;
	private Spinner selectionSpinner;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spinner);

		initCustomListSpinner();
		initSelectionSpinner();
	}
	
	private void initCustomListSpinner() {
		customListSpinner = (Spinner) findViewById(R.id.custom_list_spinner);

		// Custom choices
		List<CharSequence> choices = new ArrayList<CharSequence>();
		choices.add("Earth");
		choices.add("Mars");
		choices.add("Venus");
		choices.add("Jupiter");
		choices.add("Mercury");
		choices.add("Uranus");
		
		// Create an ArrayAdapter with custom choices
		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, choices);

		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// Set the adapter to th spinner
		customListSpinner.setAdapter(adapter);
	}
	
	private void initSelectionSpinner() {
		selectionSpinner = (Spinner) findViewById(R.id.selection_spinner);
		
		// Set SpinnerActivity as the item selected listener
		selectionSpinner.setOnItemSelectedListener(this);
	}

	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		Toast.makeText(this, selectionSpinner.getSelectedItem() + " selected", Toast.LENGTH_SHORT).show();
	}

	public void onNothingSelected(AdapterView<?> parent) {
	}
}
