package com.example.hotornot;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

public class GpsLocation {
    private static final String SNACKBAR_MSG = "You have to enable Location information";

    private static GpsLocation instance;

    private Location location;
    private Activity contextActivity;
    private LocationManager locationManager;

    private GpsLocation(final Activity activity) {
        this.contextActivity = activity;
        locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
    }

    public static GpsLocation getInstance(Activity activity) {
        if (instance == null) {
            instance = new GpsLocation(activity);
        }

        return instance;
    }

    public Location getLocation() {
        updateLocation();
        return getValidLocation();
    }

    private void updateLocation() {
        if (ActivityCompat.checkSelfPermission(contextActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(contextActivity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    AppUtils.LOCATION_REQUEST_CODE);
        } else {
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                showSnackbar();
            } else {
                location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
            }
        }
    }

    private void showSnackbar() {
        Snackbar snackbar =
                Snackbar.make(contextActivity.findViewById(R.id.content_view_pager),
                        SNACKBAR_MSG, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("SETTINGS", l -> openSettingsScreen())
                .setActionTextColor(ContextCompat
                        .getColor(
                                contextActivity.getApplicationContext(),
                                R.color.snackbarActionTextColor
                        )
                );
        snackbar.show();
    }

    private void openSettingsScreen() {
        Intent gpsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        contextActivity.startActivity(gpsIntent);
    }

    private Location getValidLocation() {
        if (location == null) {
            return null;
        }
        return this.location;
    }
}
