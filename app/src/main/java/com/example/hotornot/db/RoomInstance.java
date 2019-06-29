package com.example.hotornot.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import com.example.hotornot.util.AppUtils;

import java.util.List;

public class RoomInstance {
    private static RoomInstance instance;

    private final AppDb db;

    private RoomInstance(Context context) {
        db = Room.databaseBuilder(context, AppDb.class, "app.db").build();
    }

    public static RoomInstance getInstance(Context context) {
        if (instance == null) {
            instance = new RoomInstance(context);
        }
        return instance;
    }

    public void insertSingleAsync(Forecast forecast) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(final Void... voids) {
                db.forecastDao().insert(forecast);
                return null;
            }
        }.execute();
    }

    public void insertAllAsync(List<Forecast> forecasts) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(final Void... voids) {
                db.forecastDao().insertAll(forecasts);
                return null;
            }
        }.execute();
    }

    public void insertAllAsync(Forecast... forecasts) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(final Void... voids) {
                db.forecastDao().insertAll(forecasts);
                return null;
            }
        }.execute();
    }

    public void getLastDateAdded(DatabaseListener<Long> callback) {
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(final Void... voids) {
                Long dateAdded = db.forecastDao().getLastInsertedDateTime();
                return dateAdded;
            }

            @Override
            protected void onPostExecute(final Long dateAdded) {
                super.onPostExecute(dateAdded);
                callback.onDataReceived(dateAdded);
            }
        }.execute();
    }

    public void getTodayForecast(DatabaseListener<Forecast> callback) {
        new AsyncTask<Void, Void, Forecast>() {
            @Override
            protected Forecast doInBackground(final Void... voids) {
                Forecast forecast = db.forecastDao().getForecast(AppUtils.FORECAST_TYPE_TODAY);
                return forecast;
            }

            @Override
            protected void onPostExecute(final Forecast forecast) {
                super.onPostExecute(forecast);
                callback.onDataReceived(forecast);
            }
        }.execute();
    }

    public void getTomorrowForecast(DatabaseListener<Forecast> callback) {
        new AsyncTask<Void, Void, Forecast>() {
            @Override
            protected Forecast doInBackground(final Void... voids) {
                Forecast forecast = db.forecastDao().getForecast(AppUtils.FORECAST_TYPE_TOMORROW);
                return forecast;
            }

            @Override
            protected void onPostExecute(final Forecast forecast) {
                super.onPostExecute(forecast);
                callback.onDataReceived(forecast);
            }
        }.execute();
    }

    public void getHourlyForecast(DatabaseListener<List<Forecast>> callback) {
        new AsyncTask<Void, Void, List<Forecast>>() {
            @Override
            protected List<Forecast> doInBackground(final Void... voids) {
                List<Forecast> stories = db.forecastDao().getHourlyForecast(AppUtils.FORECAST_TYPE_HOURLY);
                return stories;
            }

            @Override
            protected void onPostExecute(final List<Forecast> stories) {
                super.onPostExecute(stories);
                callback.onDataReceived(stories);
            }
        }.execute();
    }

    public void getAll(DatabaseListener<List<Forecast>> callback) {
        new AsyncTask<Void, Void, List<Forecast>>() {
            @Override
            protected List<Forecast> doInBackground(final Void... voids) {
                List<Forecast> stories = db.forecastDao().getAll();
                return stories;
            }

            @Override
            protected void onPostExecute(final List<Forecast> stories) {
                super.onPostExecute(stories);
                callback.onDataReceived(stories);
            }
        }.execute();
    }

    public void deleteAll() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.forecastDao().deleteAll();
                return null;
            }
        };
    }

    public interface DatabaseListener<T> {
        void onDataReceived(T data);
    }
}