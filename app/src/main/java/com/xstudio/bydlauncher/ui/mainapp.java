package com.xstudio.bydlauncher.ui;

import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.speed.AbsBYDAutoSpeedListener;
import android.hardware.bydauto.speed.BYDAutoSpeedDevice;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xstudio.bydlauncher.R;

public class mainapp extends AppCompatActivity {
    // Create a BYDDevice instance
    private BYDAutoSpeedDevice mBYDAutoSpeedDevice = null;
    private AbsBYDAutoSpeedListener mAbsBYDAutoSpeedListener;

    private BYDAutoGearboxDevice mBYDAutoGearboxDevice = null;
    private AbsBYDAutoGearboxListener mAbsBYDAutoGearboxListener;

    private BYDAutoBodyworkDevice mBYDAutoBodyworkDevice = null;
    private AbsBYDAutoBodyworkListener mAbsBYDAutoBodyworkListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView mSpeedo = (TextView) findViewById(R.id.speedo);
        TextView mGear = (TextView) findViewById(R.id.gear);
        TextView mRange = (TextView) findViewById(R.id.range);
        TextView mBattery = (TextView) findViewById(R.id.battery_percent);
        ProgressBar mBatteryBar = (ProgressBar) findViewById(R.id.battery_bar);

        // Create & Check a BYDDevice instance
        if (mBYDAutoSpeedDevice == null) {
            mBYDAutoSpeedDevice =  BYDAutoSpeedDevice.getInstance(getApplicationContext());
        }
        if (mBYDAutoGearboxDevice == null) {
            mBYDAutoGearboxDevice =  BYDAutoGearboxDevice.getInstance(getApplicationContext());
        }
        if (mBYDAutoBodyworkDevice == null) {
            mBYDAutoBodyworkDevice =  BYDAutoBodyworkDevice.getInstance(getApplicationContext());
        }

        // Functions
        mAbsBYDAutoSpeedListener = new AbsBYDAutoSpeedListener() {
            @Override
            public void onSpeedChanged(double speed) {
                super.onSpeedChanged(speed);
                mSpeedo.setText((int) speed);
            }
        };

        mAbsBYDAutoGearboxListener = new AbsBYDAutoGearboxListener() {
            @Override
            public void onGearboxAutoModeTypeChanged(int level) {
                super.onGearboxAutoModeTypeChanged(level);
                if (level == BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_P) {
                    mGear.setText("P");
                } else if (level == BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_R) {
                    mGear.setText("R");
                } else if (level == BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_N) {
                    mGear.setText("N");
                } else if (level == BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_D) {
                    mGear.setText("D");
                }
            }
        };

        mAbsBYDAutoBodyworkListener = new AbsBYDAutoBodyworkListener() {
            @Override
            public void onBatteryVoltageLevelChanged(int level) {
                super.onBatteryVoltageLevelChanged(level);
                mBattery.setText(level + "%");
                mBatteryBar.setProgress(level);
            }
        };
    }

    // Register Listener
    @Override
    protected void onResume() {
        super.onResume();
        mBYDAutoSpeedDevice.registerListener(mAbsBYDAutoSpeedListener);
        mBYDAutoGearboxDevice.registerListener(mAbsBYDAutoGearboxListener);
        mBYDAutoBodyworkDevice.registerListener(mAbsBYDAutoBodyworkListener);
    }

    // Unregister Listener
    @Override
    protected void onPause() {
        super.onPause();
        mBYDAutoSpeedDevice.unregisterListener(mAbsBYDAutoSpeedListener);
        mBYDAutoGearboxDevice.unregisterListener(mAbsBYDAutoGearboxListener);
        mBYDAutoBodyworkDevice.unregisterListener(mAbsBYDAutoBodyworkListener);
    }
}
