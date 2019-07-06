package com.example.hotornot.model;

import com.example.hotornot.model.helper.City;
import com.example.hotornot.model.helper.SpecificHourForecast;

import java.util.List;

public class HourlyForecast {
    private List<SpecificHourForecast> list;
    private City city;
    private Integer cod;

    public HourlyForecast() {}

    public HourlyForecast(final List<SpecificHourForecast> list, final City city, final Integer cod) {
        this.list = list;
        this.city = city;
        this.cod = cod;
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

    public Integer getCod() {
        return this.cod;
    }

    public void setCod(final Integer cod) {
        this.cod = cod;
    }
}
