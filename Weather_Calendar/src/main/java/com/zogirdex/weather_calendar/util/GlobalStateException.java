/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zogirdex.weather_calendar.util;

/**
 *
 * @author tom3k
 */
public class GlobalStateException extends Exception {

    public GlobalStateException() {
        super("An error occurred while managing global state binary files.");
    }

    public GlobalStateException(String message) {
        super(message);
    }

    public GlobalStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public GlobalStateException(Throwable cause) {
        super("An error occurred while managing global state binary files.", cause);
    }
}
