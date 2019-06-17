package com.example.hotornot.model;

public class HourlyForecast {
    private SpecificHourForecastList list;
    private City city;

    public HourlyForecast() {
    }

    public HourlyForecast(final SpecificHourForecastList list, final City city) {
        this.list = list;
        this.city = city;
    }

    public SpecificHourForecastList getList() {
        return this.list;
    }

    public void setList(final SpecificHourForecastList list) {
        this.list = list;
    }

    public City getCity() {
        return this.city;
    }

    public void setCity(final City city) {
        this.city = city;
    }
}
