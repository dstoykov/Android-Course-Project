package com.example.hotornot.ui.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotornot.db.Forecast;
import com.example.hotornot.util.AppUtils;
import com.example.hotornot.R;

import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<HourlyForecastItemViewHolder> {
    private List<Forecast> forecastList;

    public DetailsAdapter(final List<Forecast> forecastList) {
        this.forecastList = forecastList;
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
        Forecast hourForecast = forecastList.get(position);
        holder.relativeLayout.setBackgroundResource(AppUtils.getCardBackgroundColor(hourForecast.getTempAverage()));
        holder.weatherTxt.setText(hourForecast.getWeatherCondition());
        holder.avgTempTxt.setText(String.format(holder.avgTempTxt.getText().toString(), hourForecast.getTempAverage()));
        holder.detailedWeatherTxt.setText(hourForecast.getDetailedWeatherCondition());
        holder.dateTxt.setText(hourForecast.getDt());
        holder.weatherImg.setImageResource(AppUtils.getCardBackgroundImage(hourForecast.getWeatherCondition()));
    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }
}
