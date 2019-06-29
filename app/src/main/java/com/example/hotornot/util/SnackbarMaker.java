package com.example.hotornot.util;

import android.app.Activity;
import android.content.Intent;
import android.provider.Settings;

import androidx.core.content.ContextCompat;

import com.example.hotornot.R;
import com.google.android.material.snackbar.Snackbar;

public class SnackbarMaker {
    private static final String INTERNET_SNACKBAR_MSG = "No Internet connection";
    private static final String LOCATION_SNACKBAR_MSG = "You have to enable Location information";
    private static final String SNACKBAR_OK_BTN = "OK";
    private static final String SNACKBAR_SETTINGS_BTN = "SETTINGS";

    private SnackbarMaker() {
    }

    public static void showNoInternetSnackbar(Activity activity) {
        Snackbar snackbar = Snackbar.make(activity.findViewById(R.id.content_view_pager),
                INTERNET_SNACKBAR_MSG, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(SNACKBAR_OK_BTN, l -> snackbar.dismiss())
                .setActionTextColor(ContextCompat
                        .getColor(
                                activity.getApplicationContext(),
                                R.color.snackbarActionTextColor
                        )
                );
        snackbar.show();
    }

    public static void showLocationSnackbar(Activity activity) {
        Snackbar snackbar =
                Snackbar.make(activity.findViewById(R.id.content_view_pager),
                        LOCATION_SNACKBAR_MSG, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(SNACKBAR_SETTINGS_BTN, l -> openSettingsScreen(activity))
                .setActionTextColor(ContextCompat
                        .getColor(
                                activity.getApplicationContext(),
                                R.color.snackbarActionTextColor
                        )
                );
        snackbar.show();
    }

    private static void openSettingsScreen(Activity activity) {
        Intent gpsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        activity.startActivity(gpsIntent);
    }
}
