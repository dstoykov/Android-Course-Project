package com.example.hotornot.util;

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
    private AppUtils() {}

    public static int getCardBackgroundColor(Integer temperature) {
        if (temperature >= 22) {
            return R.color.yellow;
        } else if (temperature >= 15) {
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

        return hours >= 3;
    }
}
