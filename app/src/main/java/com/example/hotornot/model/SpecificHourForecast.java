package com.example.hotornot.model;

public class SpecificHourForecast {
    private WeatherList weather;
    private AtmosphereConditions main;
    private Wind wind;
    private Clouds clouds;
    private String name;
    private String dt_txt;

    public SpecificHourForecast() {
    }

    public SpecificHourForecast(final WeatherList weather, final AtmosphereConditions main, final Wind wind, final Clouds clouds, final String name, final String dt_txt) {
        this.weather = weather;
        this.main = main;
        this.wind = wind;
        this.clouds = clouds;
        this.name = name;
        this.dt_txt = dt_txt;
    }

    public WeatherList getWeather() {
        return this.weather;
    }

    public void setWeather(final WeatherList weather) {
        this.weather = weather;
    }

    public AtmosphereConditions getMain() {
        return this.main;
    }

    public void setMain(final AtmosphereConditions main) {
        this.main = main;
    }

    public Wind getWind() {
        return this.wind;
    }

    public void setWind(final Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return this.clouds;
    }

    public void setClouds(final Clouds clouds) {
        this.clouds = clouds;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDt_txt() {
        return this.dt_txt;
    }

    public void setDt_txt(final String dt_txt) {
        this.dt_txt = dt_txt;
    }
}
