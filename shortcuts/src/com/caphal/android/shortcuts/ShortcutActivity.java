    package com.caphal.android.shortcuts;
    
    import android.app.Activity;
    import android.content.Intent;
    import android.net.Uri;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Toast;
    
    public class ShortcutActivity extends Activity {
    
        private static final String DATA = "caphal://shortcut";
    
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_shortcut);
    
            // Check if the app was started from the shortcut
            if (DATA.equals(getIntent().getDataString())) {
                Toast.makeText(this, "App started from shortcut",
                        Toast.LENGTH_SHORT).show();
            }
        }
    
        /**
         * This method creates the shortcut Intent which is fired when the user
         * presses the icon on the home screen
         * 
         * @return Shortcut Intent
         */
        private Intent createShortcutIntent() {
            Intent intent = new Intent(this, ShortcutActivity.class);
            intent.setAction(Intent.ACTION_MAIN);
            // Include data to know when the app is started from the shortcut
            intent.setData(Uri.parse(DATA));
    
            return intent;
        }
    
        /**
         * onClick handler for install button
         * 
         * @param view
         */
        public void onInstallShortcutClick(View view) {
    
            // Get the shortcut intent
            Intent sIntent = createShortcutIntent();
    
            // Create intent for installing the shortcut
            Intent addIntent = new Intent();
            addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, sIntent);
            // Set the name of the shortcut
            addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "Example shortcut");
            // Set the icon of the shortcut
            addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                    Intent.ShortcutIconResource.fromContext(this,
                            R.drawable.ic_launcher));
            addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
    
            // Send broadcast to install shortcut
            sendBroadcast(addIntent);
        }
    
        /**
         * onClickHandler for uninstall button
         * 
         * @param view
         */
        public void onUninstallShortcutClick(View view) {
    
            // Get the shortcut intent
            Intent sIntent = createShortcutIntent();
    
            Intent addIntent = new Intent();
            addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, sIntent);
            addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "Example shortcut");
            addIntent.setAction("com.android.launcher.action.UNINSTALL_SHORTCUT");
    
            // Send broadcast to uninstall shortcut
            sendBroadcast(addIntent);
        }
    }
