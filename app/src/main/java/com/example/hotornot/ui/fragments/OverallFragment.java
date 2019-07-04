package com.example.hotornot.ui.fragments;

import android.location.Location;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hotornot.R;
import com.example.hotornot.databinding.FragmentOverallBinding;
import com.example.hotornot.db.Forecast;
import com.example.hotornot.db.RoomInstance;
import com.example.hotornot.gps.GpsLocation;
import com.example.hotornot.model.TodayForecast;
import com.example.hotornot.model.TomorrowForecast;
import com.example.hotornot.retrofit.RetrofitInstance;
import com.example.hotornot.retrofit.WeatherService;
import com.example.hotornot.util.AppUtils;
import com.example.hotornot.util.CardFiller;
import com.example.hotornot.util.ModelMapper;
import com.example.hotornot.util.SnackbarMaker;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OverallFragment extends Fragment {
    private GpsLocation gpsLocation;
    private RetrofitInstance retrofit;
    private RoomInstance room;
    private FragmentOverallBinding binding;
    private Location location;

    public OverallFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_overall, container, false);
        gpsLocation = GpsLocation.getInstance(getActivity());
        retrofit = RetrofitInstance.getInstance();
        room = RoomInstance.getInstance(getContext());
        loadForecast();
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void loadForecast() {
        room.getLastDateAdded(date -> {
            if (date == null) {
                getTodayForecastFromApi();
                getTomorrowForecastFromApi();
            } else if (AppUtils.are3HoursPassed(date)) {
                clearDb();
                getTodayForecastFromApi();
                getTomorrowForecastFromApi();
            } else {
                loadTodayDataFromDb();
                loadTomorrowDataFromDb();
            }
        });
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
                System.out.println(today.getTown());
                System.out.println(today.getType());
                room.insertSingleAsync(today);
                loadTodayDataFromDb();
            }

            @Override
            public void onFailure(Call<TodayForecast> call, Throwable t) {
                SnackbarMaker.showNoInternetSnackbar(getActivity());
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
                System.out.println(tomorrow.getTown());
                System.out.println(tomorrow.getType());
                room.insertSingleAsync(tomorrow);
                loadTomorrowDataFromDb();
            }

            @Override
            public void onFailure(Call<TomorrowForecast> call, Throwable t) {
                t.printStackTrace();
                SnackbarMaker.showNoInternetSnackbar(getActivity());
            }
        });
    }

    private void loadTodayDataFromDb() {
        room.getTodayForecast(data -> CardFiller.fillTodayCardView(data, binding, getActivity()));
    }

    private void loadTomorrowDataFromDb() {
        room.getTomorrowForecast(data -> CardFiller.fillTomorrowCardView(data, binding));
    }

    private void clearDb() {
        room.deleteAll();
    }
}
