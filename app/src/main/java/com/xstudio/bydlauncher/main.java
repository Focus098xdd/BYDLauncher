package com.xstudio.bydlauncher;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.xstudio.bydlauncher.ui.splash;

public class main extends AppCompatActivity {

    private static final String TAG = "MAIN CLASS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        proceedToSplash();

        // note : uncheck this when port to real device
        //checkAndRequestPermissions();
    }

    private void checkAndRequestPermissions() {
        if (permissionManager.hasPermissions(this)) {
            Log.d(TAG, "All permissions are already granted.");
            permissionManager.BYD_PERMISSION_GRANTED = true; // Ensure
            proceedToSplash();
        } else {
            Log.d(TAG, "Permissions not granted. Requesting...");
            // Call the static requestPermissions method from your utility class
            permissionManager.requestPermissions(this);
        }
    }

    private void proceedToSplash() {
        Log.d(TAG, "Permissions granted! Starting Splash Activity.");
        Intent mIntent = new Intent(this, splash.class);
        startActivity(mIntent);
        finish(); // Finish this activity so user can't navigate back to permission check
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == permissionManager.PERMISSION_REQUEST_CODE) {
            boolean allGranted = true;
            if (grantResults.length > 0) {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        allGranted = false;
                        Log.w(TAG, "Permission denied: " + permissions[i]);
                        break; // No need to check further if one is denied
                    } else {
                        Log.d(TAG, "Permission granted: " + permissions[i]);
                    }
                }
            } else {
                // grantResults is empty. This might happen if the request was interrupted.
                allGranted = false;
                Log.w(TAG, "Permission request resulted in an empty grantResult array.");
            }

            permissionManager.BYD_PERMISSION_GRANTED = allGranted; // Update the static flag

            if (allGranted) {
                Log.d(TAG, "All BYD permissions granted via callback.");
                proceedToSplash();
            } else {
                Log.w(TAG, "Some BYD permissions were denied.");
                Toast.makeText(this, "Required permissions were denied. The application cannot start.", Toast.LENGTH_LONG).show();

                finish(); //Exit if permissions are critical and denied.
            }
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
        );
    }
}