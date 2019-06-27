package com.example.hotornot.model;

import java.util.List;

public class DailyForecast {
    private Temperature temp;
    private Double pressure;
    private Integer humidity;
    private List<Weather> weather;
    private Double speed;
    private Integer clouds;
    private Integer rain;

    public DailyForecast() {
    }

    public DailyForecast(final Temperature temp, final Double pressure, final Integer humidity, final List<Weather> weather, final Double speed, final Integer clouds, final Integer rain) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.weather = weather;
        this.speed = speed;
        this.clouds = clouds;
        this.rain = rain;
    }

    public Temperature getTemp() {
        return this.temp;
    }

    public void setTemp(final Temperature temp) {
        this.temp = temp;
    }

    public Double getPressure() {
        return this.pressure;
    }

    public void setPressure(final Double pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return this.humidity;
    }

    public void setHumidity(final Integer humidity) {
        this.humidity = humidity;
    }

    public List<Weather> getWeather() {
        return this.weather;
    }

    public void setWeather(final List<Weather> weather) {
        this.weather = weather;
    }

    public Double getSpeed() {
        return this.speed;
    }

    public void setSpeed(final Double speed) {
        this.speed = speed;
    }

    public Integer getClouds() {
        return this.clouds;
    }

    public void setClouds(final Integer clouds) {
        this.clouds = clouds;
    }

    public Integer getRain() {
        return this.rain;
    }

    public void setRain(final Integer rain) {
        this.rain = rain;
    }
}
