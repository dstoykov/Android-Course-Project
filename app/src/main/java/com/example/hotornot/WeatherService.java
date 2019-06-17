package com.example.hotornot;

import com.example.hotornot.model.DailyForecast;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherService {

    @GET("weather?lat={latitude}&lon={longitude}&apikey={apiKey}&units=metric")
    DailyForecast todayForecastByCoordinates(@Path(value = "latitude", encoded = true) String latitude,
                                             @Path(value = "longitude", encoded = true) String longitude,
                                             @Path(value = "apiKey", encoded = true) String apiKey);

    @GET("weather?q={town}&apikey={apiKey}&units=metric")
    DailyForecast todayForecastByTownName(@Path(value = "town", encoded = true) String town,
                                          @Path(value = "apiKey", encoded = true) String apiKey);

    @GET("forecast?lat={latitude}&lon={longitude}&apikey={apiKey}&cnt=10&units=metric")
    HourlyForecast hourlyForecastByCoordinates(@Path(value = "latitude", encoded = true) String latitude,
                                               @Path(value = "longitude", encoded = true) String longitude,
                                               @Path(value = "apiKey", encoded = true) String apiKey);

    @GET("forecast?q={town}&apikey={apiKey}&cnt=10&units=metric")
    HourlyForecast hourlyForecastByTownName(@Path(value = "town", encoded = true) String town,
                                            @Path(value = "apiKey", encoded = true) String apiKey);
}
