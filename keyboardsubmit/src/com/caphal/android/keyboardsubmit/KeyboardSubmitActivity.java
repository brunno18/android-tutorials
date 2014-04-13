package com.caphal.android.keyboardsubmit;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class KeyboardSubmitActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText pwField = (EditText) findViewById(R.id.main_password);

        pwField.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                    KeyEvent event) {

                // Check if done button was pressed
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    handleLogin();
                }

                return false;
            }
        });
    }

    /**
     * Handle login when done button has been pressed
     */
    private void handleLogin() {
        Toast.makeText(this, "Handling login!", Toast.LENGTH_LONG).show();
    }
}
