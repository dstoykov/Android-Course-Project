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

public class AppUtils {
    public static final Integer LOCATION_REQUEST_CODE = 7;
    public static final Integer FIRST_ELEMENT_INDEX = 0;
    public static final Double DEFAULT_LATITUDE = 42.69751;
    public static final Double DEFAULT_LONGITUDE = 23.32415;
    public static final String FORECAST_TYPE_TODAY = "TODAY";
    public static final String FORECAST_TYPE_TOMORROW = "TOMORROW";
    public static final String FORECAST_TYPE_HOURLY = "HOURLY";

    private static final SimpleDateFormat DAILY_FORECAST_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private static final SimpleDateFormat HOURLY_FORECAST_DATE_FORMAT = new SimpleDateFormat("dd.MM HH:mm");

    private AppUtils() {
    }

    public static int getCardBackgroundColor(Double temperature) {
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

    public static Forecast getDbEntityFromTodayForecast(TodayForecast todayForecast) {
        Forecast forecast = new Forecast();
        forecast.setDateAdded(Calendar.getInstance().getTimeInMillis());
        forecast.setType(FORECAST_TYPE_TODAY);
        forecast.setDt(DAILY_FORECAST_DATE_FORMAT.format(new Date(todayForecast.getDt() * 1000)));
        forecast.setCloudPercentage(todayForecast.getClouds().getAll());
        forecast.setWindSpeed(todayForecast.getWind().getSpeed().intValue());
        forecast.setHumidity(todayForecast.getMain().getHumidity());
        forecast.setWeatherCondition(todayForecast.getWeather().get(FIRST_ELEMENT_INDEX).getMain());
        forecast.setTempAverage(todayForecast.getMain().getTemp().intValue());
        forecast.setTempMin(todayForecast.getMain().getTemp_min().intValue());
        forecast.setTempMax(todayForecast.getMain().getTemp_max().intValue());
        forecast.setDetailedWeatherCondition(todayForecast.getWeather().get(FIRST_ELEMENT_INDEX).getDescription());

        return forecast;
    }

    public static Forecast getDbEntityFromTomorrowForecast(TomorrowForecast tomorrowForecast) {
        DailyForecast dailyForecast = tomorrowForecast.getList().get(FIRST_ELEMENT_INDEX);

        Forecast forecast = new Forecast();
        forecast.setDateAdded(Calendar.getInstance().getTimeInMillis());
        forecast.setType(FORECAST_TYPE_TOMORROW);
        forecast.setDt(DAILY_FORECAST_DATE_FORMAT.format(new Date(dailyForecast.getDt() * 1000)));
        forecast.setCloudPercentage(dailyForecast.getClouds());
        forecast.setWindSpeed(dailyForecast.getSpeed().intValue());
        forecast.setHumidity(dailyForecast.getHumidity());
        forecast.setWeatherCondition(dailyForecast.getWeather().get(FIRST_ELEMENT_INDEX).getMain());
        forecast.setTempAverage(dailyForecast.getTemp().getDay().intValue());
        forecast.setTempMin(dailyForecast.getTemp().getMin().intValue());
        forecast.setTempMax(dailyForecast.getTemp().getMax().intValue());
        forecast.setDetailedWeatherCondition(dailyForecast.getWeather().get(FIRST_ELEMENT_INDEX).getDescription());

        return forecast;
    }

    public static List<Forecast> getListOfDbEntitiesFromHourlyForecast(HourlyForecast hourlyForecast) {
        List<Forecast> result = new ArrayList<>();
        fillList(hourlyForecast, result);

        return result;
    }

    private static void fillList(HourlyForecast hourlyForecast, List<Forecast> result) {
        Forecast forecast = new Forecast();
        for (SpecificHourForecast hourForecast : hourlyForecast.getList()) {
            forecast.setDateAdded(Calendar.getInstance().getTimeInMillis());
            forecast.setType(FORECAST_TYPE_HOURLY);
            forecast.setDt(HOURLY_FORECAST_DATE_FORMAT.format(new Date(hourForecast.getDt() * 1000)));
            forecast.setCloudPercentage(hourForecast.getClouds().getAll());
            forecast.setWindSpeed(hourForecast.getWind().getSpeed().intValue());
            forecast.setHumidity(hourForecast.getMain().getHumidity());
            forecast.setWeatherCondition(hourForecast.getWeather().get(FIRST_ELEMENT_INDEX).getMain());
            forecast.setTempAverage(hourForecast.getMain().getTemp().intValue());
            forecast.setTempMin(hourForecast.getMain().getTemp_min().intValue());
            forecast.setTempMax(hourForecast.getMain().getTemp_max().intValue());
            forecast.setDetailedWeatherCondition(hourForecast.getWeather().get(FIRST_ELEMENT_INDEX).getDescription());

            result.add(forecast);
        }
    }
}
