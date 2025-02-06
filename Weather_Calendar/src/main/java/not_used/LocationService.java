///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.zogirdex.weather_calendar.service;
//import com.zogirdex.weather_calendar.model.WeatherQuery;
//import com.zogirdex.weather_calendar.util.QueryAssistant;
//import java.util.Set;
//import java.lang.IllegalArgumentException;
//import java.util.Map;
//
///**
// *
// * @author tom3k
// */
//public class LocationService {
//    private final String apiBaseUrl;
//    private final Map<String, String> apiQueryParams;
//    private Set<String> locations;
//    
//    public LocationService() {
//         this.apiBaseUrl = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
//         this.apiQueryParams = Map.of(
//            "tucosmozebedzie1", "tucosmozebedzie1",
//            "tucosmozebedzie2", "tucosmozebedzie2"
//        );
//         LocationQuery result = this.makeQuery();
//         this.locations = result.getLocations();
//         
//    }
//    
//    public updateLocations() {
//        LocationQuery result = this.makeQuery();
//        this.locations = result.getLocations();
//    }
//    
//    public void validateLocation(String location) {
//        if(!this.locations.contains(location)) {
//            throw new IllegalArgumentException("Podano niepoprawną lokalizację");
//        }
//    }
//    
//    private LocationQuery makeQuery() {
//        String url = QueryAssistant.buildUrl(this.apiBaseUrl, this.apiQueryParams);
//        return QueryAssistant.makeQuery(url, WeatherQuery.class);
//    }
//}
