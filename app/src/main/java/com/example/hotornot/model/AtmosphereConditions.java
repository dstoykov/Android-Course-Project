package com.example.hotornot.model;

public class AtmosphereConditions {
    private Integer temp;
    private Integer humidity;
    private Integer temp_min;
    private Integer temp_max;

    public AtmosphereConditions() {
    }

    public AtmosphereConditions(final Integer temp, final Integer humidity, final Integer temp_min, final Integer temp_max) {
        this.temp = temp;
        this.humidity = humidity;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
    }

    public Integer getTemp() {
        return this.temp;
    }

    public void setTemp(final Integer temp) {
        this.temp = temp;
    }

    public Integer getHumidity() {
        return this.humidity;
    }

    public void setHumidity(final Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getTemp_min() {
        return this.temp_min;
    }

    public void setTemp_min(final Integer temp_min) {
        this.temp_min = temp_min;
    }

    public Integer getTemp_max() {
        return this.temp_max;
    }

    public void setTemp_max(final Integer temp_max) {
        this.temp_max = temp_max;
    }
}
