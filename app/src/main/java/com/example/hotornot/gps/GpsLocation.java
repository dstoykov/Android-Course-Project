package com.example.hotornot.gps;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;

import androidx.core.app.ActivityCompat;

import com.example.hotornot.util.AppUtils;
import com.example.hotornot.util.SnackbarMaker;

public class GpsLocation {
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
                SnackbarMaker.showLocationSnackbar(contextActivity);
            } else {
                location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
            }
        }
    }

    private Location getValidLocation() {
        if (location == null) {
            return null;
        }
        return this.location;
    }
}
