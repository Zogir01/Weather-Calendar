/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zogirdex.weather_calendar;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.time.LocalDate;

/**
 *
 * @author tom3k
 */
public class WeatherApiService {
    private final EventManager eventManager;
    private Map<String, String> queryParams = new HashMap<>();
    private String baseUrl;

    //String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/%s?unitGroup=metric&elements=datetime%2Cname%2Ctemp%2Chumidity%2Cprecip%2Cprecipprob%2Csnow%2Cpressure%2Ccloudcover%2Csunrise%2Csunset%2Cconditions%2Cdescription%2Cicon&include=days%2Cfcst&key=VHEMMB29AXXDT86HR399VV4RT&contentType=json";
    
    public WeatherApiService() {
        this.eventManager = EventManager.getInstance();
        this.baseUrl = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
        queryParams.put("unitGroup", "metric");
        queryParams.put("elements", "datetime%2Cname%2Ctemp%2Chumidity%2Cprecip%2Cprecipprob%2Csnow%2Cpressure%2Ccloudcover%2Csunrise%2Csunset%2Cconditions%2Cdescription%2Cicon");
        queryParams.put("include", "days%2Cfcst");
        queryParams.put("key", "VHEMMB29AXXDT86HR399VV4RT");
        queryParams.put("contentType","json");  
    }
    
    private String createParamString () {
        StringBuilder builder = new StringBuilder();    
        builder.append("?");
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            builder.append(entry.getKey())
                           .append("=")
                           .append(entry.getValue())
                           .append("&");
            }
        return builder.toString();
    }
    
    // zwraca czy operacja zakończyła się sukcesem.
    public boolean makeQuery(String location) throws IOException{
        String finalUrl = this.baseUrl + location + this.createParamString();

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
                Query query = gson.fromJson(br, Query.class);
                query.getDays().forEach(elem -> {
                    this.eventManager.addWeatherDay(LocalDate.parse(elem.getDatetime()), elem);
                //format elem.getDatetime(): 2025-01-07 yyyy-mm-dd
                });
                conn.disconnect();
                return true;
                
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }
}