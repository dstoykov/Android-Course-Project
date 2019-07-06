package com.example.hotornot.model;

import com.example.hotornot.model.helper.City;
import com.example.hotornot.model.helper.DailyForecast;

import java.util.List;

public class TomorrowForecast {
    private City city;
    private List<DailyForecast> list;
    private Integer cod;

    public TomorrowForecast() {
    }

    public TomorrowForecast(final City city, final List<DailyForecast> list, final Integer cod) {
        this.city = city;
        this.list = list;
        this.cod = cod;
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

    public Integer getCod() {
        return this.cod;
    }

    public void setCod(final Integer cod) {
        this.cod = cod;
    }
}
