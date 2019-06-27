package com.example.hotornot.recyclerview;

public class HourlyForecastItem {
    private String date;
    private String weather;
    private String currentTemp;
    private String detailedWeather;

    public HourlyForecastItem(final String date, final String weather, final String currentTemp, final String detailedWeather) {
        this.date = date;
        this.weather = weather;
        this.currentTemp = currentTemp;
        this.detailedWeather = detailedWeather;
    }

    public String getDate() {
        return this.date;
    }

    public String getWeather() {
        return this.weather;
    }

    public String getCurrentTemp() {
        return this.currentTemp;
    }

    public String getDetailedWeather() {
        return this.detailedWeather;
    }
}
