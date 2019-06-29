package com.example.hotornot.model.helper;

public class Temperature {
    private Double day;
    private Double min;
    private Double max;

    public Temperature() {
    }

    public Temperature(final Double day, final Double min, final Double max) {
        this.day = day;
        this.min = min;
        this.max = max;
    }

    public Double getDay() {
        return this.day;
    }

    public void setDay(final Double day) {
        this.day = day;
    }

    public Double getMin() {
        return this.min;
    }

    public void setMin(final Double min) {
        this.min = min;
    }

    public Double getMax() {
        return this.max;
    }

    public void setMax(final Double max) {
        this.max = max;
    }
}
