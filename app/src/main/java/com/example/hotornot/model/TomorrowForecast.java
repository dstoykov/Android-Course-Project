package com.example.hotornot.model;

import com.example.hotornot.model.helper.City;
import com.example.hotornot.model.helper.DailyForecast;

import java.util.List;

public class TomorrowForecast {
    private City city;
    private List<DailyForecast> list;

    public TomorrowForecast() {
    }

    public TomorrowForecast(final City city, final List<DailyForecast> list) {
        this.city = city;
        this.list = list;
    }

    public City getCity() {
        return this.city;
    }

    public void setCity(final City city) {
        this.city = city;
    }

    public List<DailyForecast> getList() {
        return this.list;
    }

    public void setList(final List<DailyForecast> list) {
        this.list = list;
    }
}
