package com.example.hotornot.util;

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

public class ModelMapper {
    private static final Integer FIRST_ELEMENT_INDEX = 0;
    private static final SimpleDateFormat DAILY_FORECAST_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private static final SimpleDateFormat HOURLY_FORECAST_DATE_FORMAT = new SimpleDateFormat("dd.MM HH:mm");
    private static final long DATE_MILLIS_MULTIPLIER = 1000L;
    private static final long ONE_DAY_DURATION = 1L;

    private ModelMapper() {}

    public static Forecast getDbEntityFromTodayForecast(TodayForecast todayForecast) {
        Forecast forecast = new Forecast();
        forecast.setDateAdded(Calendar.getInstance().getTimeInMillis());
        forecast.setType(ForecastType.FORECAST_TYPE_TODAY);
        forecast.setDt(DAILY_FORECAST_DATE_FORMAT.format(new Date(todayForecast.getDt() * DATE_MILLIS_MULTIPLIER)));
        forecast.setTown(todayForecast.getName());
        forecast.setCloudPercentage(todayForecast.getClouds().getAll());
        forecast.setWindSpeed(todayForecast.getWind().getSpeed().intValue());
        forecast.setHumidity(todayForecast.getMain().getHumidity());
        forecast.setWeatherCondition(todayForecast.getWeather().get(FIRST_ELEMENT_INDEX).getMain());
        forecast.setTempAverage((int) Math.round(todayForecast.getMain().getTemp()));
        forecast.setTempMin((int) Math.round(todayForecast.getMain().getTemp_min()));
        forecast.setTempMax((int) Math.round(todayForecast.getMain().getTemp_max()));
        forecast.setDetailedWeatherCondition(todayForecast.getWeather().get(FIRST_ELEMENT_INDEX).getDescription());

        return forecast;
    }

    public static Forecast getDbEntityFromTomorrowForecast(TomorrowForecast tomorrowForecast) {
        DailyForecast dailyForecast = tomorrowForecast.getList().get(FIRST_ELEMENT_INDEX);
        Date tomorrowDate = new Date((dailyForecast.getDt() * DATE_MILLIS_MULTIPLIER) + TimeUnit.DAYS.toMillis(ONE_DAY_DURATION));

        Forecast forecast = new Forecast();
        forecast.setDateAdded(Calendar.getInstance().getTimeInMillis());
        forecast.setType(ForecastType.FORECAST_TYPE_TOMORROW);
        forecast.setDt(DAILY_FORECAST_DATE_FORMAT.format(tomorrowDate));
        forecast.setCloudPercentage(dailyForecast.getClouds());
        forecast.setWindSpeed(dailyForecast.getSpeed().intValue());
        forecast.setHumidity(dailyForecast.getHumidity());
        forecast.setWeatherCondition(dailyForecast.getWeather().get(FIRST_ELEMENT_INDEX).getMain());
        forecast.setTempAverage((int) Math.round(dailyForecast.getTemp().getDay()));
        forecast.setTempMin((int) Math.round(dailyForecast.getTemp().getMin()));
        forecast.setTempMax((int) Math.round(dailyForecast.getTemp().getMax()));
        forecast.setDetailedWeatherCondition(dailyForecast.getWeather().get(FIRST_ELEMENT_INDEX).getDescription());

        return forecast;
    }

    public static List<Forecast> getListOfDbEntitiesFromHourlyForecast(HourlyForecast hourlyForecast) {
        List<Forecast> result = new ArrayList<>();
        fillList(hourlyForecast, result);

        return result;
    }

    private static void fillList(HourlyForecast hourlyForecast, List<Forecast> result) {
        for (SpecificHourForecast hourForecast : hourlyForecast.getList()) {
            Forecast forecast = new Forecast();
            forecast.setDateAdded(Calendar.getInstance().getTimeInMillis());
            forecast.setType(ForecastType.FORECAST_TYPE_HOURLY);
            forecast.setDt(HOURLY_FORECAST_DATE_FORMAT.format(new Date(hourForecast.getDt() * DATE_MILLIS_MULTIPLIER)));
            forecast.setCloudPercentage(hourForecast.getClouds().getAll());
            forecast.setWindSpeed(hourForecast.getWind().getSpeed().intValue());
            forecast.setHumidity(hourForecast.getMain().getHumidity());
            forecast.setWeatherCondition(hourForecast.getWeather().get(FIRST_ELEMENT_INDEX).getMain());
            forecast.setTempAverage((int) Math.round(hourForecast.getMain().getTemp()));
            forecast.setTempMin((int) Math.round(hourForecast.getMain().getTemp_min()));
            forecast.setTempMax((int) Math.round(hourForecast.getMain().getTemp_max()));
            forecast.setDetailedWeatherCondition(hourForecast.getWeather().get(FIRST_ELEMENT_INDEX).getDescription());

            result.add(forecast);
        }
    }
}
