package com.example.hotornot.model;

import java.util.List;

public class SpecificHourForecastList {
    private List<SpecificHourForecast> list;

    public SpecificHourForecastList() {
    }

    public SpecificHourForecastList(final List<SpecificHourForecast> list) {
        this.list = list;
    }

    public List<SpecificHourForecast> getList() {
        return this.list;
    }

    public void setList(final List<SpecificHourForecast> list) {
        this.list = list;
    }
}
