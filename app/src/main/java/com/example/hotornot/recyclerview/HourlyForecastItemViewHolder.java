package com.example.hotornot.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotornot.R;

public class HourlyForecastItemViewHolder extends RecyclerView.ViewHolder {
    CardView cardView;
    RelativeLayout relativeLayout;
    TextView dateTxt;
    TextView weatherTxt;
    TextView avgTempTxt;
    TextView detailedWeatherTxt;
    ImageView weatherImg;

    public HourlyForecastItemViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews();
    }

    private void initViews() {
        cardView = itemView.findViewById(R.id.card_details);
        relativeLayout = itemView.findViewById(R.id.rel_view_details);
        dateTxt = itemView.findViewById(R.id.date_time_txt_details);
        weatherTxt = itemView.findViewById(R.id.weather_condition_txt_details_fragment);
        avgTempTxt = itemView.findViewById(R.id.temperature_txt_details);
        detailedWeatherTxt = itemView.findViewById(R.id.weather_detailed_condition_txt_details);
        weatherImg = itemView.findViewById(R.id.weather_condition_img_details_fragment);
    }
}
