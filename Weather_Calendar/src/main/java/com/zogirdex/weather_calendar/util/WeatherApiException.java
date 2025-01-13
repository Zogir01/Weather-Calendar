package com.zogirdex.weather_calendar.util;

/**
 *
 * @author tom3k
 */
public class WeatherApiException extends Exception {

    public WeatherApiException() {
        super("An error occurred while interacting with the weather API.");
    }

    public WeatherApiException(String message) {
        super(message);
    }

    public WeatherApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public WeatherApiException(Throwable cause) {
        super("An error occurred while interacting with the weather API.", cause);
    }
}
