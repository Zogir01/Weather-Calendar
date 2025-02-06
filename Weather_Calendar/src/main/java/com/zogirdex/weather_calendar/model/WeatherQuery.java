package com.zogirdex.weather_calendar.model;
import java.time.LocalDate;

import java.util.List;

public class WeatherQuery {

private int queryCost;
private double latitude;
private double longitude;
private String resolvedAddress;
private String address;
private String timezone;
private double tzoffset;
private String description;
private List<WeatherDay> days;

    public WeatherQuery() {}

    public WeatherQuery(int queryCost, double latitude, double longitude, String resolvedAddress, String address, String timezone, double tzoffset, String description, List<WeatherDay> days) {
        super();
        this.queryCost = queryCost;
        this.latitude = latitude;
        this.longitude = longitude;
        this.resolvedAddress = resolvedAddress;
        this.address = address;
        this.timezone = timezone;
        this.tzoffset = tzoffset;
        this.description = description;
        this.days = days;
    }

public int getQueryCost() {
return queryCost;
}

public void setQueryCost(int queryCost) {
this.queryCost = queryCost;
}

public double getLatitude() {
return latitude;
}

public void setLatitude(double latitude) {
this.latitude = latitude;
}

public double getLongitude() {
return longitude;
}

public void setLongitude(double longitude) {
this.longitude = longitude;
}

public String getResolvedAddress() {
return resolvedAddress;
}

public void setResolvedAddress(String resolvedAddress) {
this.resolvedAddress = resolvedAddress;
}

public String getAddress() {
return address;
}

public void setAddress(String address) {
this.address = address;
}

public String getTimezone() {
return timezone;
}

public void setTimezone(String timezone) {
this.timezone = timezone;
}

public double getTzoffset() {
return tzoffset;
}

public void setTzoffset(double tzoffset) {
this.tzoffset = tzoffset;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public List<WeatherDay> getDays() {
return days;
}

public void setDays(List<WeatherDay> days) {
this.days = days;
}

@Override
public String toString() {
    return "WeatherQuery{" +
            "queryCost=" + queryCost +
            ", latitude=" + latitude +
            ", longitude=" + longitude +
            ", resolvedAddress='" + resolvedAddress + '\'' +
            ", address='" + address + '\'' +
            ", timezone='" + timezone + '\'' +
            ", tzoffset=" + tzoffset +
            ", description='" + description + '\'' +
            '}';
}

}
