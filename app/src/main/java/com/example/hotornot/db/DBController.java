package com.example.hotornot.db;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.hotornot.R;
import com.example.hotornot.gps.GpsLocation;
import com.example.hotornot.model.HourlyForecast;
import com.example.hotornot.model.TodayForecast;
import com.example.hotornot.model.TomorrowForecast;
import com.example.hotornot.retrofit.RetrofitInstance;
import com.example.hotornot.retrofit.WeatherService;
import com.example.hotornot.util.AppUtils;
import com.example.hotornot.util.ModelMapper;
import com.example.hotornot.util.SnackbarMaker;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DBController {
    private static final String NO_INTERNET_MSG = "No Internet connection";
    private static DBController instance;

    private RetrofitInstance retrofit;
    private RoomInstance room;
    private GpsLocation gpsLocation;
    private Location location;
    private Activity activity;
    private Context context;

    private DBController(Context context, Activity activity) {
        retrofit = RetrofitInstance.getInstance();
        room = RoomInstance.getInstance(context);
        gpsLocation = GpsLocation.getInstance(activity);
        this.activity = activity;
        this.context = context;
    }

    public static DBController getInstance(Context context, Activity activity) {
        if (instance == null) {
            instance = new DBController(context, activity);
        }
        return instance;
    }

    public void loadForecast() {
        room.getLastDateAdded(date -> {
            if (date == null) {
                loadForecastFromApi();
            } else if (AppUtils.are3HoursPassed(date)) {
                updateDb();
            }
        });
    }

    public void updateDb() {
        if (AppUtils.isInternetAvailable(context)) {
            clearDb();
            loadForecastFromApi();
        }
        AppUtils.checkInternetConnection(context);
    }

    private void loadForecastFromApi() {
        if (AppUtils.isInternetAvailable(context)) {
            getTodayForecastFromApi();
            getTomorrowForecastFromApi();
            getHourlyForecastFromApi();
        }
        AppUtils.checkInternetConnection(context);
    }

    private void getTodayForecastFromApi() {
        Call<TodayForecast> todayForecast;
        location = gpsLocation.getLocation();
        if (location == null) {
            todayForecast = retrofit.getWeatherService().todayForecastByCoordinates(
                    GpsLocation.DEFAULT_LATITUDE,
                    GpsLocation.DEFAULT_LONGITUDE,
                    WeatherService.APP_ID,
                    WeatherService.UNITS_METRIC
            );
        } else {
            todayForecast = retrofit.getWeatherService().todayForecastByCoordinates(
                    location.getLatitude(),
                    location.getLongitude(),
                    WeatherService.APP_ID,
                    WeatherService.UNITS_METRIC
            );
        }
        processTodayForecastRequest(todayForecast);
    }

    private void processTodayForecastRequest(Call<TodayForecast> todayForecast) {
        todayForecast.enqueue(new Callback<TodayForecast>() {
            @Override
            public void onResponse(Call<TodayForecast> call, Response<TodayForecast> response) {
                Forecast today = ModelMapper.getDbEntityFromTodayForecast(response.body());
                room.insertSingleAsync(today);
            }

            @Override
            public void onFailure(Call<TodayForecast> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(activity, NO_INTERNET_MSG, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getTomorrowForecastFromApi() {
        Call<TomorrowForecast> tomorrowForecast;
        location = gpsLocation.getLocation();
        if (location == null) {
            tomorrowForecast = retrofit.getWeatherService().tomorrowForecastByCoordinates(
                    GpsLocation.DEFAULT_LATITUDE,
                    GpsLocation.DEFAULT_LONGITUDE,
                    WeatherService.APP_ID,
                    WeatherService.UNITS_METRIC,
                    WeatherService.TOMORROW_FORECAST_CNT
            );
        } else {
            tomorrowForecast = retrofit.getWeatherService().tomorrowForecastByCoordinates(
                    location.getLatitude(),
                    location.getLongitude(),
                    WeatherService.APP_ID,
                    WeatherService.UNITS_METRIC,
                    WeatherService.TOMORROW_FORECAST_CNT
            );
        }
        processTomorrowForecastRequest(tomorrowForecast);
    }

    private void processTomorrowForecastRequest(Call<TomorrowForecast> tomorrowForecast) {
        tomorrowForecast.enqueue(new Callback<TomorrowForecast>() {
            @Override
            public void onResponse(Call<TomorrowForecast> call, Response<TomorrowForecast> response) {
                Forecast tomorrow = ModelMapper.getDbEntityFromTomorrowForecast(response.body());
                room.insertSingleAsync(tomorrow);
            }

            @Override
            public void onFailure(Call<TomorrowForecast> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(activity, NO_INTERNET_MSG, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getHourlyForecastFromApi() {
        Call<HourlyForecast> hourlyForecast;
        location = gpsLocation.getLocation();
        if (location == null) {
            hourlyForecast = retrofit.getWeatherService().hourlyForecastByCoordinates(
                    GpsLocation.DEFAULT_LATITUDE,
                    GpsLocation.DEFAULT_LONGITUDE,
                    WeatherService.APP_ID,
                    WeatherService.HOURLY_FORECAST_CNT,
                    WeatherService.UNITS_METRIC);
        } else {
            hourlyForecast = retrofit.getWeatherService().hourlyForecastByCoordinates(
                    location.getLatitude(),
                    location.getLongitude(),
                    WeatherService.APP_ID,
                    WeatherService.HOURLY_FORECAST_CNT,
                    WeatherService.UNITS_METRIC);
        }
        processHourlyForecastRequest(hourlyForecast);
    }

    private void processHourlyForecastRequest(Call<HourlyForecast> hourlyForecast) {
        hourlyForecast.enqueue(new Callback<HourlyForecast>() {
            @Override
            public void onResponse(Call<HourlyForecast> call, Response<HourlyForecast> response) {
                List<Forecast> hourly = ModelMapper.getListOfDbEntitiesFromHourlyForecast(response.body());
                room.insertAllAsync(hourly);
            }

            @Override
            public void onFailure(Call<HourlyForecast> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(activity, NO_INTERNET_MSG, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void clearDb() {
        room.deleteAll();
    }
}
