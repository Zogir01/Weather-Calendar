package com.zogirdex.weather_calendar.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zogirdex.weather_calendar.config.AppConstants;
import com.zogirdex.weather_calendar.model.WeatherDay;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

/**
 *
 * @author tom3k
 */
public class QueryAssistant {
    
    public static String buildUrl(String baseUrl, Map<String, String> params) {
        StringBuilder builder = new StringBuilder().append("?");    
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.append(entry.getKey())
                           .append("=")
                           .append(entry.getValue())
                           .append("&");
        }
        return baseUrl + builder.toString();
    }
    
    public static <T> T makeQuery(String url, Class<T> classOfT) throws ApiException {
        try {
            URL endpoint = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) endpoint.openConnection();
            
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() == 200) {  
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        conn.getInputStream(), 
                        Charset.forName("UTF-8"))
                );
//                Gson gson = new Gson();
//                T object = gson.fromJson(br, classOfT);

                Gson gson = new GsonBuilder()
                    .registerTypeAdapter(WeatherDay.class, new WeatherDayAdapter())
                    .create();
                T object = gson.fromJson(br, classOfT);
                conn.disconnect();
                return object;
            }
            else {
                // można by obsłużyć jeszcze więcej http status codes.
                throw new ApiException("Error while creating API query, get response status code = " 
                        + conn.getResponseCode());
            }
        } catch (IOException ex) {
            throw new ApiException("Error while creating API query.", ex);
        }
    }
}
