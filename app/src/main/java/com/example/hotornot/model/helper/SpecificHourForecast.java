package com.example.hotornot.model.helper;

import java.util.List;

public class SpecificHourForecast {
    private Long dt;
    private List<Weather> weather;
    private AtmosphereConditions main;
    private Wind wind;
    private Clouds clouds;

    public SpecificHourForecast() {
    }

    public SpecificHourForecast(final Long dt, final List<Weather> weather, final AtmosphereConditions main, final Wind wind, final Clouds clouds) {
        this.dt = dt;
        this.weather = weather;
        this.main = main;
        this.wind = wind;
        this.clouds = clouds;
    }

    public Long getDt() {
        return this.dt;
    }

    public void setDt(final Long dt) {
        this.dt = dt;
    }

    public List<Weather> getWeather() {
        return this.weather;
    }

    public void setWeather(final List<Weather> weather) {
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
}
