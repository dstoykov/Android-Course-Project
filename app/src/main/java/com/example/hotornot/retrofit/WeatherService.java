package com.example.hotornot.retrofit;

import com.example.hotornot.HourlyForecast;
import com.example.hotornot.model.TodayForecast;
import com.example.hotornot.model.TomorrowForecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    String APP_ID = "37426f016190340c55b693d9a76e5015";
    String UNITS_METRIC = "metric";
    Integer TOMORROW_FORECAST_CNT = 1;
    Integer HOURLY_FORECAST_CNT = 10;

    @GET("weather")
    Call<TodayForecast> todayForecastByCoordinates(@Query("lat") Double lat,
                                                   @Query("lon") Double lon,
                                                   @Query("appid") String apiKey,
                                                   @Query("units") String units);

    @GET("weather")
    Call<TodayForecast> todayForecastByTownName(@Query("q") String town,
                                          @Query("appid") String apiKey,
                                          @Query("units") String units);

    @GET("forecast/daily")
    Call<TomorrowForecast> tomorrowForecastByCoordinates(@Query("lat") Double lat,
                                                   @Query("lon") Double lon,
                                                   @Query("appid") String apiKey,
                                                   @Query("units") String units,
                                                   @Query("cnt") Integer daysCount);

    @GET("forecast/daily")
    Call<TomorrowForecast> tomorrowForecastByTown(@Query("q") String town,
                                            @Query("appid") String apiKey,
                                            @Query("units") String units,
                                            @Query("cnt") Integer daysCount);

    @GET("forecast")
    Call<HourlyForecast> hourlyForecastByCoordinates(@Query("lat") Double lat,
                                               @Query("lon") Double lon,
                                               @Query("appid") String apiKey,
                                               @Query("cnt") Integer daysCount,
                                               @Query("units") String units);

    @GET("forecast")
    Call<HourlyForecast> hourlyForecastByTownName(@Query("q") String town,
                                            @Query("appid") String apiKey,
                                            @Query("cnt") Integer daysCount,
                                            @Query("units") String units);
}
