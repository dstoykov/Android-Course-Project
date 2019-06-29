package com.example.hotornot.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Forecast.class}, version = 1)
public abstract class AppDb extends RoomDatabase {
    public abstract ForecastDao forecastDao();
}
