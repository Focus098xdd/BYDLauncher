package com.xstudio.bydlauncher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.xstudio.bydlauncher.ui.splash;


public class main extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        permissionManager.permission_req(this);
        // if perm is granted, go to mainapp else show toast
        if (permissionManager.BYD_PERMISSION_GRANTED) {
            //Intent mIntent = new Intent(this, mainapp.class);
            Intent mIntent = new Intent(this, splash.class);
            startActivity(mIntent);
            finish();
        } else {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            finishAffinity();
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
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );
    }

}