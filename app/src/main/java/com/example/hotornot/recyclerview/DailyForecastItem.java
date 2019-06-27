package com.example.hotornot.recyclerview;

public class DailyForecastItem {
    private String date;
    private String humidity;
    private String windSpeed;
    private String overcast;
    private String weather;
    private String currentTemp;
    private String minTemp;
    private String maxTemp;
    private String detailedWeather;

    public DailyForecastItem(final String date, final String humidity, final String windSpeed, final String overcast, final String weather, final String currentTemp, final String minTemp, final String maxTemp, final String detailedWeather) {
        this.date = date;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.overcast = overcast;
        this.weather = weather;
        this.currentTemp = currentTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.detailedWeather = detailedWeather;
    }

    public String getDate() {
        return this.date;
    }

    public String getHumidity() {
        return this.humidity;
    }

    public String getWindSpeed() {
        return this.windSpeed;
    }

    public String getOvercast() {
        return this.overcast;
    }

    public String getWeather() {
        return this.weather;
    }

    public String getCurrentTemp() {
        return this.currentTemp;
    }

    public String getMinTemp() {
        return this.minTemp;
    }

    public String getMaxTemp() {
        return this.maxTemp;
    }

    public String getDetailedWeather() {
        return this.detailedWeather;
    }
}
