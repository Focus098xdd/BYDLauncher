package com.xstudio.bydlauncher.ui;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xstudio.bydlauncher.R;

public class mainapp extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // Register Listener
    @Override
    protected void onResume() {
        super.onResume();
    }

    // Unregister Listener
    @Override
    protected void onPause() {
        super.onPause();
    }
}
