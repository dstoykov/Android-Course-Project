package com.example.hotornot.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import com.example.hotornot.util.ForecastType;

import java.util.List;

public class RoomInstance {
    private static final String DB_NAME = "hot_or_not.db";
    private static RoomInstance instance;

    private final AppDb db;

    private RoomInstance(Context context) {
        db = Room.databaseBuilder(context, AppDb.class, DB_NAME).build();
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

            @Override
            protected void onPostExecute(final Void aVoid) {
                super.onPostExecute(aVoid);
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

            @Override
            protected void onPostExecute(final Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }.execute();
    }

    public void getLastDateAdded(DatabaseListener<Long> callback) {
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(final Void... voids) {
                Long dateAdded = db.forecastDao().getFirstInsertedDateTime();
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
                Forecast forecast = db.forecastDao().getForecast(ForecastType.FORECAST_TYPE_TODAY);
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
                Forecast forecast = db.forecastDao().getForecast(ForecastType.FORECAST_TYPE_TOMORROW);
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
                List<Forecast> stories = db.forecastDao().getHourlyForecast(ForecastType.FORECAST_TYPE_HOURLY);
                return stories;
            }

            @Override
            protected void onPostExecute(final List<Forecast> forecasts) {
                super.onPostExecute(forecasts);
                callback.onDataReceived(forecasts);
            }
        }.execute();
    }

    public void deleteAll() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                List<Forecast> all = db.forecastDao().getAll();
                db.forecastDao().deleteAll(all);
                return null;
            }

            @Override
            protected void onPostExecute(final Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }.execute();
    }

    public void deleteTodayAndTomorrow() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                List<Forecast> all = db.forecastDao().getAll();
                Forecast today = db.forecastDao().getForecast(ForecastType.FORECAST_TYPE_TODAY);
                Forecast tomorrow = db.forecastDao().getForecast(ForecastType.FORECAST_TYPE_TOMORROW);
                db.forecastDao().delete(today);
                db.forecastDao().delete(tomorrow);
                return null;
            }

            @Override
            protected void onPostExecute(final Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }.execute();
    }

    public void deleteHourly() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                List<Forecast> all = db.forecastDao().getHourlyForecast(ForecastType.FORECAST_TYPE_HOURLY);
                db.forecastDao().deleteAll(all);
                return null;
            }

            @Override
            protected void onPostExecute(final Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }.execute();
    }

    public interface DatabaseListener<T> {
        void onDataReceived(T data);
    }
}
