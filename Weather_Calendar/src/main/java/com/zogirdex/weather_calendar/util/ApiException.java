package com.zogirdex.weather_calendar.util;

public class ApiException extends Exception {

    public ApiException() {
        super("An error occurred while interacting with the external API.");
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super("An error occurred while interacting with the external API.", cause);
    }
}
