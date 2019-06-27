package com.example.hotornot;

public class AppUtils {
    public static final Integer LOCATION_REQUEST_CODE = 7;
    public static final Double DEFAULT_LATITUDE = 42.69751;
    public static final Double DEFAULT_LONGITUDE = 23.32415;

    private AppUtils() {
    }

    public static int getCardBackgroundColor(Double temperature) {
        if (temperature >= 22) {
            return R.color.yellow;
        } else if (temperature >= 15) {
            return R.color.green;
        } else {
            return R.color.blue;
        }
    }

    public static int getCardBackgroundImage(String temperatureCondition) {
        switch (temperatureCondition) {
            case WeatherType.THUNDERSTORM:
                return R.drawable.ic_wi_lightning;
            case WeatherType.DRIZZLE:
                return R.drawable.ic_wi_sleet;
            case WeatherType.RAIN:
                return R.drawable.ic_wi_rain;
            case WeatherType.SNOW:
                return R.drawable.ic_wi_snow;
            case WeatherType.FOG:
                return R.drawable.ic_wi_fog;
            case WeatherType.CLOUDS:
                return R.drawable.ic_wi_cloudy;
            case WeatherType.VARIOUS:
                return R.drawable.ic_wi_cloudy;
            case WeatherType.EXTREME:
                return R.drawable.ic_wi_meteor;
            case WeatherType.CLEAR:
                return R.drawable.ic_wi_day_sunny;
            default:
                return R.drawable.ic_wi_cloudy;
        }
    }
}
