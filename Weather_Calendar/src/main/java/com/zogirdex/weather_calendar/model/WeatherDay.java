package com.zogirdex.weather_calendar.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.DoubleProperty;
import java.time.LocalDate;

public class WeatherDay {

    private String datetime;
    private double temp; 
    private double humidity; 
    private double precip; 
    private double precipprob; 
    private double snow; 
    private double pressure; 
    private double cloudcover; 
    private String sunrise; 
    private String sunset; 
    private String conditions; 
    private String description; 
    private String icon;

    public WeatherDay() { }

    public WeatherDay(String datetime, double temp, double humidity, double precip, double precipprob, double snow, double pressure, double cloudcover, String sunrise, String sunset, String conditions, String description, String icon) {
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

    public StringProperty datetimeProperty() { return new SimpleStringProperty(datetime); }
    public String getDatetime() { return datetime; } 
    public void setDatetime(String datetime) { this.datetime = datetime; }
//    public LocalDate getDatetimeAsLocalDate() {
//        return LocalDate.parse(this.getDatetime());
//    }

    public DoubleProperty tempProperty() { return new SimpleDoubleProperty(temp); }
    public double getTemp() { return temp; } 
    public void setTemp(double temp) { this.temp = temp; }

    public DoubleProperty humidityProperty() { return new SimpleDoubleProperty(humidity); }
    public double getHumidity() { return humidity; } 
    public void setHumidity(double humidity) { this.humidity = humidity; }

    public DoubleProperty precipProperty() { return new SimpleDoubleProperty(precip); }
    public double getPrecip() { return precip; } 
    public void setPrecip(double precip) { this.precip = precip; }

    public DoubleProperty precipprobProperty() { return new SimpleDoubleProperty(precipprob); }
    public double getPrecipprob() { return precipprob; } 
    public void setPrecipprob(double precipprob) { this.precipprob = precipprob; }

    public DoubleProperty snowProperty() { return new SimpleDoubleProperty(snow); }
    public double getSnow() { return snow; } 
    public void setSnow(double snow) { this.snow = snow; }

    public DoubleProperty pressureProperty() { return new SimpleDoubleProperty(pressure); }
    public double getPressure() { return pressure; } 
    public void setPressure(double pressure) { this.pressure = pressure; }

    public DoubleProperty cloudcoverProperty() { return new SimpleDoubleProperty(cloudcover); }
    public double getCloudcover() { return cloudcover; } 
    public void setCloudcover(double cloudcover) { this.cloudcover = cloudcover; }

    public StringProperty sunriseProperty() { return new SimpleStringProperty(sunrise); }
    public String getSunrise() { return sunrise; } 
    public void setSunrise(String sunrise) { this.sunrise = sunrise; }

    public StringProperty sunsetProperty() { return new SimpleStringProperty(sunset); }
    public String getSunset() { return sunset; } 
    public void setSunset(String sunset) { this.sunset = sunset; }

    public StringProperty conditionsProperty() { return new SimpleStringProperty(conditions); }
    public String getConditions() { return conditions; } 
    public void setConditions(String conditions) { this.conditions = conditions; }

    public StringProperty descriptionProperty() { return new SimpleStringProperty(description); }
    public String getDescription() { return description; } 
    public void setDescription(String description) { this.description = description; }

    public StringProperty iconProperty() { return new SimpleStringProperty(icon); }
    public String getIcon() { return icon; } 
    public void setIcon(String icon) { this.icon = icon; }
}
