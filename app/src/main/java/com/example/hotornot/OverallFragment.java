package com.example.hotornot;

import android.location.Location;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hotornot.databinding.FragmentOverallBinding;
import com.example.hotornot.model.TodayForecast;
import com.example.hotornot.model.TomorrowForecast;
import com.example.hotornot.retrofit.RetrofitInstance;
import com.example.hotornot.retrofit.WeatherService;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OverallFragment extends Fragment {
    private static final String INTERNET_SNACKBAR_MSG = "No Internet connection";

    private GpsLocation gpsLocation;
    private RetrofitInstance retrofit;
    private FragmentOverallBinding binding;
    private Location location;

    public OverallFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_overall, container, false);
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gpsLocation = GpsLocation.getInstance(getActivity());
        retrofit = RetrofitInstance.getInstance();
        getTodayForecast();
        getTomorrowForecast();
    }

    private void getTodayForecast() {
        Call<TodayForecast> todayForecast;
        location = gpsLocation.getLocation();
        if (location == null) {
            todayForecast = retrofit.getWeatherService().todayForecastByCoordinates(
                    AppUtils.DEFAULT_LATITUDE,
                    AppUtils.DEFAULT_LONGITUDE,
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
                fillTodayCardView(response.body());
            }

            @Override
            public void onFailure(Call<TodayForecast> call, Throwable t) {
                makeNoInternetSnackbar();
            }
        });
    }

    private void fillTodayCardView(TodayForecast todayForecast) {
        binding.relViewTodayOverall.setBackgroundResource(AppUtils.getCardBackgroundColor(todayForecast.getMain().getTemp()));
        binding.cloudPercentageTodayTxt.setText(String.format(binding.cloudPercentageTodayTxt.getText().toString(), todayForecast.getClouds().getAll()));
        binding.windSpeedTodayTxt.setText(String.format(binding.windSpeedTodayTxt.getText().toString(), todayForecast.getWind().getSpeed().intValue()));
        binding.airHumidityTodayTxt.setText(String.format(binding.airHumidityTodayTxt.getText().toString(), todayForecast.getMain().getHumidity()));
        binding.weatherConditionTodayTxt.setText(todayForecast.getWeather().get(0).getMain());
        binding.tempAverageTodayTxt.setText(String.format(binding.tempAverageTodayTxt.getText().toString(), todayForecast.getMain().getTemp().intValue()));
        binding.tempAmplitudeTodayTxt.setText(String.format(binding.tempAmplitudeTodayTxt.getText().toString(), todayForecast.getMain().getTemp_min().intValue(), todayForecast.getMain().getTemp_max().intValue()));
        binding.weatherDetailedConditionTodayTxt.setText(todayForecast.getWeather().get(0).getDescription());
        binding.weatherConditionTodayImg.setImageResource(AppUtils.getCardBackgroundImage(todayForecast.getWeather().get(0).getMain()));
    }

    private void getTomorrowForecast() {
        Call<TomorrowForecast> tomorrowForecast;
        location = gpsLocation.getLocation();
        if (location == null) {
            tomorrowForecast = retrofit.getWeatherService().tomorrowForecastByCoordinates(
                    AppUtils.DEFAULT_LATITUDE,
                    AppUtils.DEFAULT_LONGITUDE,
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
                fillTomorrowCardView(response.body());
            }

            @Override
            public void onFailure(Call<TomorrowForecast> call, Throwable t) {
                t.printStackTrace();
                makeNoInternetSnackbar();
            }
        });
    }

    private void fillTomorrowCardView(TomorrowForecast tomorrowForecast) {
        binding.relViewTomorrowOverall.setBackgroundResource(AppUtils.getCardBackgroundColor(tomorrowForecast.getList().get(0).getTemp().getDay()));
        binding.cloudPercentageTomorrowTxt.setText(String.format(binding.cloudPercentageTomorrowTxt.getText().toString(), tomorrowForecast.getList().get(0).getClouds()));
        binding.windSpeedTomorrowTxt.setText(String.format(binding.windSpeedTomorrowTxt.getText().toString(), tomorrowForecast.getList().get(0).getSpeed().intValue()));
        binding.airHumidityTomorrowTxt.setText(String.format(binding.airHumidityTomorrowTxt.getText().toString(), tomorrowForecast.getList().get(0).getHumidity()));
        binding.weatherConditionTomorrowTxt.setText(tomorrowForecast.getList().get(0).getWeather().get(0).getMain());
        binding.tempAverageTomorrowTxt.setText(String.format(binding.tempAverageTomorrowTxt.getText().toString(), tomorrowForecast.getList().get(0).getTemp().getDay().intValue()));
        binding.tempAmplitudeTomorrowTxt.setText(String.format(binding.tempAmplitudeTomorrowTxt.getText().toString(), tomorrowForecast.getList().get(0).getTemp().getMin().intValue(), tomorrowForecast.getList().get(0).getTemp().getMax().intValue()));
        binding.weatherDetailedConditionTomorrowTxt.setText(tomorrowForecast.getList().get(0).getWeather().get(0).getDescription());
        binding.weatherConditionTomorrowImg.setImageResource(AppUtils.getCardBackgroundImage(tomorrowForecast.getList().get(0).getWeather().get(0).getMain()));
    }

    private void makeNoInternetSnackbar() {
        Snackbar snackbar = Snackbar.make(getActivity().findViewById(R.id.content_view_pager),
                        INTERNET_SNACKBAR_MSG, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("OK", l -> snackbar.dismiss())
                .setActionTextColor(ContextCompat
                        .getColor(
                                getActivity().getApplicationContext(),
                                R.color.snackbarActionTextColor
                        )
                );
        snackbar.show();
    }
}
