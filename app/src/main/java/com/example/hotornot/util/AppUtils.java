package com.example.hotornot.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

import com.example.hotornot.R;
import com.example.hotornot.db.Forecast;
import com.example.hotornot.model.HourlyForecast;
import com.example.hotornot.model.TodayForecast;
import com.example.hotornot.model.TomorrowForecast;
import com.example.hotornot.model.helper.DailyForecast;
import com.example.hotornot.model.helper.SpecificHourForecast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AppUtils {

    private static final int TEMP_22 = 22;
    private static final int TEMP_15 = 15;
    private static final long MAX_INTERVAL_BEFORE_UPDATE = 3L;

    private AppUtils() {}

    public static int getCardBackgroundColor(Integer temperature) {
        if (temperature >= TEMP_22) {
            return R.color.yellow;
        } else if (temperature >= TEMP_15) {
            return R.color.green;
        } else {
            return R.color.blue;
        }
    }

    public static int getCardBackgroundImage(String temperatureCondition) {
        switch (temperatureCondition) {
            case WeatherType.THUNDERSTORM:
                return R.drawable.ic_wi_lightning;
            case WeatherType.DRIZZLE:
                return R.drawable.ic_wi_sleet;
            case WeatherType.RAIN:
                return R.drawable.ic_wi_rain;
            case WeatherType.SNOW:
                return R.drawable.ic_wi_snow;
            case WeatherType.FOG:
                return R.drawable.ic_wi_fog;
            case WeatherType.CLOUDS:
                return R.drawable.ic_wi_cloudy;
            case WeatherType.VARIOUS:
                return R.drawable.ic_wi_cloudy;
            case WeatherType.EXTREME:
                return R.drawable.ic_wi_meteor;
            case WeatherType.CLEAR:
                return R.drawable.ic_wi_day_sunny;
            default:
                return R.drawable.ic_wi_cloudy;
        }
    }

    public static boolean are3HoursPassed(long creationDt) {
        long currentDt = Calendar.getInstance().getTimeInMillis();
        long diff = currentDt - creationDt;
        long hours = TimeUnit.MILLISECONDS.toHours(diff);

        return hours >= MAX_INTERVAL_BEFORE_UPDATE;
    }

    public static boolean isInternetAvailable(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
