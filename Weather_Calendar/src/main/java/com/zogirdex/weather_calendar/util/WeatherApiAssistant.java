/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zogirdex.weather_calendar.util;

import com.google.gson.Gson;
import com.zogirdex.weather_calendar.config.AppConstants;
import com.zogirdex.weather_calendar.model.WeatherQuery;
import com.zogirdex.weather_calendar.manager.WeatherManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.Map;
import java.lang.Throwable;

/**
 *
 * @author tom3k
 */
public class WeatherApiAssistant {
    
    // zaaktualizuje tylko jedną date, dla jednej lokalizacji
    public static void makeQuery(String location, LocalDate date) throws WeatherApiException {
       if(AppConstants.DEBUG_MODE) {
           System.out.println(String.format("Performing query to weather API: location = %s, date = %s", location, date.toString()));
       }
        makeQuery(location, date, true);
    }
    
    // zaaktualizuje 14 dat w przód dla jednej lokalizacji
   public static void makeQuery(String location) throws WeatherApiException {
       if(AppConstants.DEBUG_MODE) {
           System.out.println(String.format("Performing query to weather API: location = %s", location));
       }
        makeQuery(location, null, false);
    }
    
    private static void makeQuery(String location, LocalDate date, boolean readOneDate) throws WeatherApiException {
        String finalUrl = AppConstants.WEATHER_API_BASE_URL + location + createParamString();
        WeatherManager weatherManager = WeatherManager.getInstance();

        try {
            URL endpoint = new URL(finalUrl);
            HttpURLConnection conn = (HttpURLConnection) endpoint.openConnection();
            conn.setRequestProperty("Accept", "application/json");
            
            if (conn.getResponseCode() == 200) {  
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        conn.getInputStream(), 
                        Charset.forName("UTF-8"))
                );
                
                Gson gson = new Gson();
                WeatherQuery query = gson.fromJson(br, WeatherQuery.class);
                
                //format daty z api: np. 2025-01-07 (yyyy-mm-dd)
                
                if(!readOneDate) { // dodaje wszystkie daty
                    query.getDays().forEach(elem ->{ 
                        weatherManager.addWeatherDay(LocalDate.parse(elem.getDatetime()), location, elem);
                    });
                }
                else { // dodaje jedną date
                    query.getDays().forEach(elem ->{ 
                    LocalDate dateFromQuery = LocalDate.parse(elem.getDatetime());
                    
                    if(dateFromQuery.equals(date)) {
                        System.out.println("znaleziono date we wczytanych danych");
                        weatherManager.addWeatherDay(LocalDate.parse(elem.getDatetime()), location, elem);
                    }
                    else {
                        System.out.println("nie znaleziono daty we wczytanych danych");
                    }
 
                    });
                }
                conn.disconnect();
            }
            else {
                throw new WeatherApiException("Error while creating weather API query, get response status code = " 
                        + conn.getResponseCode());
            }
        } catch (IOException ex) {
            throw new WeatherApiException("Error while creating weather API query.", ex);
        }
    }
    
    private static String createParamString () {
        StringBuilder builder = new StringBuilder();    
        builder.append("?");
        for (Map.Entry<String, String> entry : AppConstants.QUERY_PARAMS.entrySet()) {
            builder.append(entry.getKey())
                           .append("=")
                           .append(entry.getValue())
                           .append("&");
            }
        return builder.toString();
    }
}
