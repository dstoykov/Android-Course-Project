package com.example.hotornot.util;

import android.app.Activity;

import com.example.hotornot.R;
import com.example.hotornot.databinding.FragmentOverallBinding;
import com.example.hotornot.db.Forecast;

public class CardFiller {
    private CardFiller() {}

    public static void fillTodayCardView(Forecast today, FragmentOverallBinding binding, Activity activity) {
        binding.relViewTodayOverall.setBackgroundResource(AppUtils.getCardBackgroundColor(today.getTempAverage()));
        binding.cloudPercentageTodayTxt.setText(String.format(activity.getResources().getString(R.string.percentage_placeholder), today.getCloudPercentage()));
        binding.windSpeedTodayTxt.setText(String.format(activity.getResources().getString(R.string.m_per_s_placeholder), today.getWindSpeed()));
        binding.airHumidityTodayTxt.setText(String.format(activity.getResources().getString(R.string.percentage_placeholder), today.getHumidity()));
        binding.weatherConditionTodayTxt.setText(today.getWeatherCondition());
        binding.tempAverageTodayTxt.setText(String.format(activity.getResources().getString(R.string.temperature_holder), today.getTempAverage()));
        binding.tempAmplitudeTodayTxt.setText(String.format(activity.getResources().getString(R.string.temp_amplitude_holder), today.getTempMin(), today.getTempMax()));
        binding.weatherDetailedConditionTodayTxt.setText(today.getDetailedWeatherCondition());
        binding.dateTodayTxt.setText(today.getDt());
        binding.weatherConditionTodayImg.setImageResource(AppUtils.getCardBackgroundImage(today.getWeatherCondition()));
        Configurations.setToolbarTitle(today.getTown(), activity);
    }

    public static void fillTomorrowCardView(Forecast tomorrow, FragmentOverallBinding binding, Activity activity) {
        binding.relViewTomorrowOverall.setBackgroundResource(AppUtils.getCardBackgroundColor(tomorrow.getTempAverage()));
        binding.cloudPercentageTomorrowTxt.setText(String.format(activity.getResources().getString(R.string.percentage_placeholder), tomorrow.getCloudPercentage()));
        binding.windSpeedTomorrowTxt.setText(String.format(activity.getResources().getString(R.string.m_per_s_placeholder), tomorrow.getWindSpeed()));
        binding.airHumidityTomorrowTxt.setText(String.format(activity.getResources().getString(R.string.percentage_placeholder), tomorrow.getHumidity()));
        binding.weatherConditionTomorrowTxt.setText(tomorrow.getWeatherCondition());
        binding.tempAverageTomorrowTxt.setText(String.format(activity.getResources().getString(R.string.temperature_holder), tomorrow.getTempAverage()));
        binding.tempAmplitudeTomorrowTxt.setText(String.format(activity.getResources().getString(R.string.temp_amplitude_holder), tomorrow.getTempMin(), tomorrow.getTempMax()));
        binding.weatherDetailedConditionTomorrowTxt.setText(tomorrow.getDetailedWeatherCondition());
        binding.dateTomorrowTxt.setText(tomorrow.getDt());
        binding.weatherConditionTomorrowImg.setImageResource(AppUtils.getCardBackgroundImage(tomorrow.getWeatherCondition()));
    }
}
