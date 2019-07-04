package com.example.hotornot.util;

import android.app.Activity;

import com.example.hotornot.databinding.FragmentOverallBinding;
import com.example.hotornot.db.Forecast;

public class CardFiller {
    private CardFiller() {}

    public static void fillTodayCardView(Forecast today, FragmentOverallBinding binding, Activity activity) {
        binding.relViewTodayOverall.setBackgroundResource(AppUtils.getCardBackgroundColor(today.getTempAverage()));
        binding.cloudPercentageTodayTxt.setText(String.format(binding.cloudPercentageTodayTxt.getText().toString(), today.getCloudPercentage()));
        binding.windSpeedTodayTxt.setText(String.format(binding.windSpeedTodayTxt.getText().toString(), today.getWindSpeed()));
        binding.airHumidityTodayTxt.setText(String.format(binding.airHumidityTodayTxt.getText().toString(), today.getHumidity()));
        binding.weatherConditionTodayTxt.setText(today.getWeatherCondition());
        binding.tempAverageTodayTxt.setText(String.format(binding.tempAverageTodayTxt.getText().toString(), today.getTempAverage()));
        binding.tempAmplitudeTodayTxt.setText(String.format(binding.tempAmplitudeTodayTxt.getText().toString(), today.getTempMin(), today.getTempMax()));
        binding.weatherDetailedConditionTodayTxt.setText(today.getDetailedWeatherCondition());
        binding.dateTodayTxt.setText(today.getDt());
        binding.weatherConditionTodayImg.setImageResource(AppUtils.getCardBackgroundImage(today.getWeatherCondition()));
        Configurations.setToolbarTitle(today.getTown(), activity);
    }

    public static void fillTomorrowCardView(Forecast tomorrow, FragmentOverallBinding binding) {
        binding.relViewTomorrowOverall.setBackgroundResource(AppUtils.getCardBackgroundColor(tomorrow.getTempAverage()));
        binding.cloudPercentageTomorrowTxt.setText(String.format(binding.cloudPercentageTomorrowTxt.getText().toString(), tomorrow.getCloudPercentage()));
        binding.windSpeedTomorrowTxt.setText(String.format(binding.windSpeedTomorrowTxt.getText().toString(), tomorrow.getWindSpeed()));
        binding.airHumidityTomorrowTxt.setText(String.format(binding.airHumidityTomorrowTxt.getText().toString(), tomorrow.getHumidity()));
        binding.weatherConditionTomorrowTxt.setText(tomorrow.getWeatherCondition());
        binding.tempAverageTomorrowTxt.setText(String.format(binding.tempAverageTomorrowTxt.getText().toString(), tomorrow.getTempAverage()));
        binding.tempAmplitudeTomorrowTxt.setText(String.format(binding.tempAmplitudeTomorrowTxt.getText().toString(), tomorrow.getTempMin(), tomorrow.getTempMax()));
        binding.weatherDetailedConditionTomorrowTxt.setText(tomorrow.getDetailedWeatherCondition());
        binding.dateTomorrowTxt.setText(tomorrow.getDt());
        binding.weatherConditionTomorrowImg.setImageResource(AppUtils.getCardBackgroundImage(tomorrow.getWeatherCondition()));
    }
}
