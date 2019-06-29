package com.example.hotornot.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotornot.util.AppUtils;
import com.example.hotornot.R;
import com.example.hotornot.model.helper.SpecificHourForecast;

import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<HourlyForecastItemViewHolder> {
    private List<SpecificHourForecast> hourForecastList;

    public DetailsAdapter(final List<SpecificHourForecast> hourForecastList) {
        this.hourForecastList = hourForecastList;
    }

    @NonNull
    @Override
    public HourlyForecastItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup
                .getContext())
                .inflate(R.layout.item_details_weather, viewGroup, false);
        HourlyForecastItemViewHolder holder = new HourlyForecastItemViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HourlyForecastItemViewHolder holder, int position) {
        SpecificHourForecast hourForecast = hourForecastList.get(position);
        holder.relativeLayout.setBackgroundResource(AppUtils.getCardBackgroundColor(hourForecast.getMain().getTemp()));
        holder.weatherTxt.setText(hourForecast.getWeather().get(0).getMain());
        holder.avgTempTxt.setText(String.format(holder.avgTempTxt.getText().toString(), hourForecast.getMain().getTemp().intValue()));
        holder.detailedWeatherTxt.setText(hourForecast.getWeather().get(0).getDescription());
        holder.weatherImg.setImageResource(AppUtils.getCardBackgroundImage(hourForecast.getWeather().get(0).getMain()));
    }

    @Override
    public int getItemCount() {
        return hourForecastList.size();
    }
}
