package com.example.hotornot.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.hotornot.R;
import com.example.hotornot.db.DBController;
import com.example.hotornot.util.AppUtils;

public class SplashActivity extends AppCompatActivity {
    private static final Long DELAY_MILLIS = 2000L;

    private DBController dbController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        dbController = DBController.getInstance(this, this);
        loadForecast();
        startMainScreen();
    }

    private void loadForecast() {
        dbController.loadForecast();
    }

    private void startMainScreen() {
        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }, DELAY_MILLIS);
    }
}
