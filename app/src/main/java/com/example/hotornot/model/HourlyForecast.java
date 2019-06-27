package com.example.hotornot.model;

import java.util.List;

public class HourlyForecast {
    private List<SpecificHourForecast> list;
    private City city;

    public HourlyForecast() {
    }

    public HourlyForecast(final List<SpecificHourForecast> list, final City city) {
        this.list = list;
        this.city = city;
    }

    public List<SpecificHourForecast> getList() {
        return this.list;
    }

    public void setList(final List<SpecificHourForecast> list) {
        this.list = list;
    }

    public City getCity() {
        return this.city;
    }

    public void setCity(final City city) {
        this.city = city;
    }
}
