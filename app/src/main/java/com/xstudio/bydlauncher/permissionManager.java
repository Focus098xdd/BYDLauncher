package com.xstudio.bydlauncher;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import android.Manifest;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class permissionManager {

    public static boolean BYD_PERMISSION_GRANTED = false;
    public static final int PERMISSION_REQUEST_CODE = 100;

    // permissions list
    private static final String[] permissions = {
            Manifest.permission.BYDAUTO_SPEED_COMMON,
            Manifest.permission.BYDAUTO_GEARBOX_COMMON,
            Manifest.permission.BYDAUTO_BODYWORK_COMMON
            // Add any other standard Android permissions you might need here
    };

    // check all permissions in list
    public static boolean hasPermissions(Activity activity) {
        if (activity == null) {
            Log.e("permissionManager", "Activity context is null in hasPermissions. Cannot check permissions.");
            return false;
        }
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PERMISSION_GRANTED) {
                Log.d("permissionManager", "Permission not granted: " + permission);
                return false; // If any permission is not granted, return false immediately.
            }
        }
        Log.d("permissionManager", "All listed permissions are already granted.");
        return true; // All permissions in the list are granted
    }

    // request necessary permissions from list
    public static void requestPermissions(Activity activity) {
        if (activity == null) {
            Log.e("permissionManager", "Activity context is null in requestPermissions. Cannot request permissions.");
            return;
        }
        Log.d("permissionManager", "Requesting necessary permissions.");
        ActivityCompat.requestPermissions(activity, permissions, PERMISSION_REQUEST_CODE);
    }

    // copy all perm list from this class to use anywhere
    public static String[] getManagedPermissions() {
        return permissions.clone();
    }
}