package com.example.hotornot.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ForecastDao {

    @Insert
    void insert(Forecast forecast);

    @Insert
    void insertAll(List<Forecast> forecasts);

    @Query("SELECT date_added FROM forecast ORDER BY date_added ASC LIMIT 1")
    Long getFirstInsertedDateTime();

    @Query("SELECT * FROM forecast WHERE type LIKE :forecastType")
    Forecast getForecast(String forecastType);

    @Query("SELECT * FROM forecast WHERE type LIKE :hourlyForecastType")
    List<Forecast> getHourlyForecast(String hourlyForecastType);

    @Query("SELECT * FROM forecast")
    List<Forecast> getAll();

    @Delete
    void delete(Forecast forecast);

    @Delete
    void deleteAll(List<Forecast> all);
}
