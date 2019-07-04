package com.example.hotornot.ui.fragments;

import android.location.Location;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hotornot.R;
import com.example.hotornot.databinding.FragmentDetailsBinding;
import com.example.hotornot.db.Forecast;
import com.example.hotornot.db.RoomInstance;
import com.example.hotornot.gps.GpsLocation;
import com.example.hotornot.model.HourlyForecast;
import com.example.hotornot.ui.recyclerview.DetailsAdapter;
import com.example.hotornot.retrofit.RetrofitInstance;
import com.example.hotornot.retrofit.WeatherService;
import com.example.hotornot.util.ModelMapper;
import com.example.hotornot.util.SnackbarMaker;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsFragment extends Fragment {
    private GpsLocation gpsLocation;
    private RetrofitInstance retrofit;
    private RoomInstance room;
    private FragmentDetailsBinding binding;
    private Location location;

    public DetailsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);
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
        room.getAll(data -> {
            if (data.size() <= 2) {
                getForecastFromApi();
            }
            loadDataFromDb();
        });
    }

    private void getForecastFromApi() {
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
        processRequest(hourlyForecast);
    }

    private void processRequest(Call<HourlyForecast> hourlyForecast) {
        hourlyForecast.enqueue(new Callback<HourlyForecast>() {
            @Override
            public void onResponse(Call<HourlyForecast> call, Response<HourlyForecast> response) {
                List<Forecast> hourly = ModelMapper.getListOfDbEntitiesFromHourlyForecast(response.body());
                room.insertAllAsync(hourly);
                loadDataFromDb();
            }

            @Override
            public void onFailure(Call<HourlyForecast> call, Throwable t) {
                SnackbarMaker.showNoInternetSnackbar(getActivity());
            }
        });
    }

    private void loadDataFromDb() {
        room.getHourlyForecast(data -> setupRecyclerView(data));
    }

    private void setupRecyclerView(List<Forecast> forecasts) {
        DetailsAdapter adapter = new DetailsAdapter(forecasts);
        binding.recViewDetails.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recViewDetails.setAdapter(adapter);
    }
}
