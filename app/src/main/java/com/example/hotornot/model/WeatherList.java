package com.example.hotornot.model;

import java.util.List;

public class WeatherList {
    private List<Weather> weather;

    public WeatherList() {
    }

    public WeatherList(final List<Weather> weather) {
        this.weather = weather;
    }

    public List<Weather> getWeather() {
        return this.weather;
    }

    public void setWeather(final List<Weather> weather) {
        this.weather = weather;
    }
}
