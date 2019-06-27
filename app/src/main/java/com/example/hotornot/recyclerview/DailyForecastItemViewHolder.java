package com.example.hotornot.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotornot.R;

public class DailyForecastItemViewHolder extends RecyclerView.ViewHolder {
    RelativeLayout relativeLayout;
    TextView dayTxt;
    TextView dateTxt;
    TextView overcastTxt;
    TextView windSpeedTxt;
    TextView humidityTxt;
    TextView weatherTxt;
    TextView avgTempTxt;
    TextView tempAmplitudeTxt;
    TextView detailedWeatherTxt;
    ImageView weatherImg;

    public DailyForecastItemViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews();
    }

    private void initViews() {
        relativeLayout = itemView.findViewById(R.id.rel_view_overall);
        dayTxt = itemView.findViewById(R.id.day_today_txt);
        dateTxt = itemView.findViewById(R.id.date_today_txt);
        overcastTxt = itemView.findViewById(R.id.cloud_percentage_today_txt);
        windSpeedTxt = itemView.findViewById(R.id.wind_speed_today_txt);
        humidityTxt = itemView.findViewById(R.id.air_humidity_today_txt);
        weatherTxt = itemView.findViewById(R.id.weather_condition_txt);
        avgTempTxt = itemView.findViewById(R.id.temp_average_txt);
        tempAmplitudeTxt = itemView.findViewById(R.id.temp_amplitude_txt);
        detailedWeatherTxt = itemView.findViewById(R.id.weather_detailed_condition_txt);
        weatherImg = itemView.findViewById(R.id.weather_condition_img);
    }
}
