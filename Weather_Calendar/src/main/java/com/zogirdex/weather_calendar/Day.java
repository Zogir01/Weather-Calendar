
package com.zogirdex.weather_calendar;

//import javax.annotation.Generated;

//@Generated("jsonschema2pojo")
public class Day {

public String datetime;
public double temp;
public double humidity;
public double precip;
public double precipprob;
public double snow;
public double pressure;
public double cloudcover;
public String sunrise;
public String sunset;
public String conditions;
public String description;
public String icon;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Day() {
    }

    public Day(String datetime, double temp, double humidity, double precip, double precipprob, double snow, double pressure, double cloudcover, String sunrise, String sunset, String conditions, String description, String icon) {
        super();
        this.datetime = datetime;
        this.temp = temp;
        this.humidity = humidity;
        this.precip = precip;
        this.precipprob = precipprob;
        this.snow = snow;
        this.pressure = pressure;
        this.cloudcover = cloudcover;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.conditions = conditions;
        this.description = description;
        this.icon = icon;
    }

public String getDatetime() {
return datetime;
}

public void setDatetime(String datetime) {
this.datetime = datetime;
}

public double getTemp() {
return temp;
}

public void setTemp(double temp) {
this.temp = temp;
}

public double getHumidity() {
return humidity;
}

public void setHumidity(double humidity) {
this.humidity = humidity;
}

public double getPrecip() {
return precip;
}

public void setPrecip(double precip) {
this.precip = precip;
}

public double getPrecipprob() {
return precipprob;
}

public void setPrecipprob(double precipprob) {
this.precipprob = precipprob;
}

public double getSnow() {
return snow;
}

public void setSnow(double snow) {
this.snow = snow;
}

public double getPressure() {
return pressure;
}

public void setPressure(double pressure) {
this.pressure = pressure;
}

public double getCloudcover() {
return cloudcover;
}

public void setCloudcover(double cloudcover) {
this.cloudcover = cloudcover;
}

public String getSunrise() {
return sunrise;
}

public void setSunrise(String sunrise) {
this.sunrise = sunrise;
}

public String getSunset() {
return sunset;
}

public void setSunset(String sunset) {
this.sunset = sunset;
}

public String getConditions() {
return conditions;
}

public void setConditions(String conditions) {
this.conditions = conditions;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public String getIcon() {
return icon;
}

public void setIcon(String icon) {
this.icon = icon;
}

}
