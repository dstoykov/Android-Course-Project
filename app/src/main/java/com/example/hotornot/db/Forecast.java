package com.example.hotornot.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Forecast {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = "date_added")
    private Long dateAdded;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "dt")
    private String dt;

    @ColumnInfo(name = "town")
    private String town;

    @ColumnInfo(name = "cloud_percentage")
    private Integer cloudPercentage;

    @ColumnInfo(name = "wind_speed")
    private Integer windSpeed;

    @ColumnInfo(name = "humidity")
    private Integer humidity;

    @ColumnInfo(name = "weather_condition")
    private String weatherCondition;

    @ColumnInfo(name = "temp_average")
    private Integer tempAverage;

    @ColumnInfo(name = "temp_min")
    private Integer tempMin;

    @ColumnInfo(name = "temp_max")
    private Integer tempMax;

    @ColumnInfo(name = "detailed_weather_condition")
    private String detailedWeatherCondition;

    public Forecast() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Long getDateAdded() {
        return this.dateAdded;
    }

    public void setDateAdded(final Long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getDt() {
        return this.dt;
    }

    public void setDt(final String dt) {
        this.dt = dt;
    }

    public String getTown() {
        return this.town;
    }

    public void setTown(final String town) {
        this.town = town;
    }

    public Integer getCloudPercentage() {
        return this.cloudPercentage;
    }

    public void setCloudPercentage(final Integer cloudPercentage) {
        this.cloudPercentage = cloudPercentage;
    }

    public Integer getWindSpeed() {
        return this.windSpeed;
    }

    public void setWindSpeed(final Integer windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Integer getHumidity() {
        return this.humidity;
    }

    public void setHumidity(final Integer humidity) {
        this.humidity = humidity;
    }

    public String getWeatherCondition() {
        return this.weatherCondition;
    }

    public void setWeatherCondition(final String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public Integer getTempAverage() {
        return this.tempAverage;
    }

    public void setTempAverage(final Integer tempAverage) {
        this.tempAverage = tempAverage;
    }

    public Integer getTempMin() {
        return this.tempMin;
    }

    public void setTempMin(final Integer tempMin) {
        this.tempMin = tempMin;
    }

    public Integer getTempMax() {
        return this.tempMax;
    }

    public void setTempMax(final Integer tempMax) {
        this.tempMax = tempMax;
    }

    public String getDetailedWeatherCondition() {
        return this.detailedWeatherCondition;
    }

    public void setDetailedWeatherCondition(final String detailedWeatherCondition) {
        this.detailedWeatherCondition = detailedWeatherCondition;
    }
}
