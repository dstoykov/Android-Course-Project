package com.example.hotornot.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static RetrofitInstance instance;

    private Retrofit retrofit;
    private WeatherService weatherService;

    private RetrofitInstance() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        retrofit = new Retrofit.Builder()
                .baseUrl(WeatherService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        weatherService = retrofit.create(WeatherService.class);
    }

    public static RetrofitInstance getInstance() {
        if (instance == null) {
            instance = new RetrofitInstance();
        }
        return instance;
    }

    public WeatherService getWeatherService() {
        return this.weatherService;
    }
}
