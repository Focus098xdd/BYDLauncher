package com.xstudio.bydlauncher;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class permissionManager extends AppCompatActivity {
    public static boolean BYD_PERMISSION_GRANTED = false;
    private static final int PERMISSION_REQUEST_CODE = 100;

    private static final String[] permissions = {
            Manifest.permission.BYDAUTO_SPEED_COMMON,
            Manifest.permission.BYDAUTO_SPEED_GET,
            Manifest.permission.BYDAUTO_GEARBOX_COMMON,
            Manifest.permission.BYDAUTO_GEARBOX_GET,
            Manifest.permission.BYDAUTO_BODYWORK_COMMON,
            Manifest.permission.BYDAUTO_BODYWORK_GET
    };

    // Call this from your main activity
    public static void permission_req(Activity activity) {
        Log.d("permissionManager", "Requesting BYD Device Permission");

        if (!hasPermissions(activity)) {
            ActivityCompat.requestPermissions(activity, permissions, PERMISSION_REQUEST_CODE);
        } else {
            Log.d("permissionManager", "All permissions already granted");
            BYD_PERMISSION_GRANTED = true;
        }
    }

    private static boolean hasPermissions(Activity activity) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    // To be placed in the Activity that calls permission_req
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            boolean allGranted = true;
            for (int result : grantResults) {
                if (result != PERMISSION_GRANTED) {
                    allGranted = false;
                    break;
                }
            }

            BYD_PERMISSION_GRANTED = allGranted;

            if (allGranted) {
                Log.d("permissionManager", "All BYD permissions granted");
            } else {
                Log.w("permissionManager", "Some BYD permissions denied");
            }
        }
    }
}
