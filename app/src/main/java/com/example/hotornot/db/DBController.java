package com.example.hotornot.db;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.widget.Toast;

import com.example.hotornot.gps.GpsLocation;
import com.example.hotornot.model.HourlyForecast;
import com.example.hotornot.model.TodayForecast;
import com.example.hotornot.model.TomorrowForecast;
import com.example.hotornot.retrofit.RetrofitInstance;
import com.example.hotornot.retrofit.WeatherService;
import com.example.hotornot.util.AppUtils;
import com.example.hotornot.util.ModelMapper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DBController {
    private static final String NO_INTERNET_MSG = "No Internet connection";
    private static final String NO_CITY_FOUND = "City Not Found";
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
        } else {
            showNoInternetToast();
        }
    }

    public void updateDbByTown(String town) {
        if (AppUtils.isInternetAvailable(context)) {
            checkIfTownIsValid(town);
        } else {
            showNoInternetToast();
        }
    }

    public void updateTodayAndTomorrowForecast() {
        if (AppUtils.isInternetAvailable(context)) {
            removeTodayAndTomorrowEntities();
            getTodayForecastFromApi();
            getTomorrowForecastFromApi();
        } else {
            showNoInternetToast();
        }
    }

    public void updateHourlyForecast() {
        if (AppUtils.isInternetAvailable(context)) {
            removeHourlyEntities();
            getHourlyForecastFromApi();
        } else {
            showNoInternetToast();
        }
    }

    private void loadForecastFromApi() {
        if (AppUtils.isInternetAvailable(context)) {
            getTodayForecastFromApi();
            getTomorrowForecastFromApi();
            getHourlyForecastFromApi();
        } else {
            showNoInternetToast();
        }
    }

    private void loadForecastByTownFromApi(String town) {
        getTodayForecastByTownFromApi(town);
        getTomorrowForecastByTownFromApi(town);
        getHourlyForecastByTownFromApi(town);
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

    private void getTodayForecastByTownFromApi(String town) {
        Call<TodayForecast> todayForecast = retrofit.getWeatherService().todayForecastByTownName(
                town,
                WeatherService.APP_ID,
                WeatherService.UNITS_METRIC
        );
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
                showNoInternetToast();
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

    private void getTomorrowForecastByTownFromApi(String town) {
        Call<TomorrowForecast> tomorrowForecast = retrofit.getWeatherService().tomorrowForecastByTown(
                town,
                WeatherService.APP_ID,
                WeatherService.UNITS_METRIC,
                WeatherService.TOMORROW_FORECAST_CNT
        );
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
                showNoInternetToast();
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

    private void getHourlyForecastByTownFromApi(String town) {
        Call<HourlyForecast> hourlyForecast;
        hourlyForecast = retrofit.getWeatherService().hourlyForecastByTownName(
                town,
                WeatherService.APP_ID,
                WeatherService.HOURLY_FORECAST_CNT,
                WeatherService.UNITS_METRIC);
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
                showNoInternetToast();
            }
        });
    }

    private void clearDb() {
        room.deleteAll();
    }

    private void removeTodayAndTomorrowEntities() {
        room.deleteTodayAndTomorrow();
    }

    private void removeHourlyEntities() {
        room.deleteHourly();
    }

    private void showNoInternetToast() {
        Toast.makeText(activity, NO_INTERNET_MSG, Toast.LENGTH_LONG).show();
    }

    private void showNoCityFoundToast() {
        Toast.makeText(activity, NO_CITY_FOUND, Toast.LENGTH_LONG).show();
    }

    private void checkIfTownIsValid(String town) {
        Call<TodayForecast> todayForecast = retrofit.getWeatherService().todayForecastByTownName(
                town,
                WeatherService.APP_ID,
                WeatherService.UNITS_METRIC
        );
        todayForecast.enqueue(new Callback<TodayForecast>() {
            @Override
            public void onResponse(Call<TodayForecast> call, Response<TodayForecast> response) {
                if (response.body() == null) {
                    showNoCityFoundToast();
                } else {
                    clearDb();
                    loadForecastByTownFromApi(town);
                }
            }

            @Override
            public void onFailure(Call<TodayForecast> call, Throwable t) {}
        });
    }
}