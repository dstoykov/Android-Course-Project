package com.example.hotornot.model.helper;

public class AtmosphereConditions {
    private Double temp;
    private Integer humidity;
    private Double temp_min;
    private Double temp_max;

    public AtmosphereConditions() {
    }

    public AtmosphereConditions(final Double temp, final Integer humidity, final Double temp_min, final Double temp_max) {
        this.temp = temp;
        this.humidity = humidity;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
    }

    public Double getTemp() {
        return this.temp;
    }

    public void setTemp(final Double temp) {
        this.temp = temp;
    }

    public Integer getHumidity() {
        return this.humidity;
    }

    public void setHumidity(final Integer humidity) {
        this.humidity = humidity;
    }

    public Double getTemp_min() {
        return this.temp_min;
    }

    public void setTemp_min(final Double temp_min) {
        this.temp_min = temp_min;
    }

    public Double getTemp_max() {
        return this.temp_max;
    }

    public void setTemp_max(final Double temp_max) {
        this.temp_max = temp_max;
    }
}
