package com.example.hotornot;

import android.location.Location;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hotornot.databinding.FragmentDetailsBinding;
import com.example.hotornot.gps.GpsLocation;
import com.example.hotornot.model.HourlyForecast;
import com.example.hotornot.recyclerview.DetailsAdapter;
import com.example.hotornot.retrofit.RetrofitInstance;
import com.example.hotornot.retrofit.WeatherService;
import com.example.hotornot.util.AppUtils;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsFragment extends Fragment {
    private static final String INTERNET_SNACKBAR_MSG = "No Internet connection";

    private GpsLocation gpsLocation;
    private RetrofitInstance retrofit;
    private FragmentDetailsBinding binding;
    private Location location;

    public DetailsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gpsLocation = GpsLocation.getInstance(getActivity());
        retrofit = RetrofitInstance.getInstance();
        getForecast();
    }

    private void getForecast() {
        Call<HourlyForecast> hourlyForecast;
        location = gpsLocation.getLocation();
        if (location == null) {
            hourlyForecast = retrofit.getWeatherService().hourlyForecastByCoordinates(
                    AppUtils.DEFAULT_LATITUDE,
                    AppUtils.DEFAULT_LONGITUDE,
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
        processRequest(hourlyForecast);
    }

    private void processRequest(Call<HourlyForecast> hourlyForecast) {
        hourlyForecast.enqueue(new Callback<HourlyForecast>() {
            @Override
            public void onResponse(Call<HourlyForecast> call, Response<HourlyForecast> response) {
                setupRecyclerView(response.body());
            }

            @Override
            public void onFailure(Call<HourlyForecast> call, Throwable t) {
                makeNoInternetSnackbar();
            }
        });
    }

    private void setupRecyclerView(HourlyForecast body) {
        DetailsAdapter adapter = new DetailsAdapter(body.getList());
        binding.recViewDetails.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recViewDetails.setAdapter(adapter);
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
