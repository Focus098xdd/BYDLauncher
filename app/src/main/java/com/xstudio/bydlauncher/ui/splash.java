package com.xstudio.bydlauncher.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.xstudio.bydlauncher.R;

public class splash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        /* new Handler().postDelayed(() -> {
            Intent mIntent = new Intent(splash.this, mainapp.class);
            startActivity(mIntent);
            overridePendingTransition(0, R.anim.fade_out);
            finish();
        }, 3000); */
    }
}