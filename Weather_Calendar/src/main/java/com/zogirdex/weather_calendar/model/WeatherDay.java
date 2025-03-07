package com.zogirdex.weather_calendar.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.DoubleProperty;

public class WeatherDay {

    private StringProperty datetime;
    private DoubleProperty temp; 
    private DoubleProperty humidity; 
    private DoubleProperty precip; 
    private DoubleProperty precipprob; 
    private DoubleProperty snow; 
    private DoubleProperty pressure; 
    private DoubleProperty cloudcover; 
    private StringProperty sunrise; 
    private StringProperty sunset; 
    private StringProperty conditions; 
    private StringProperty description; 
    private StringProperty icon; 

    public WeatherDay() { 
        this.datetime = new SimpleStringProperty();
        this.temp = new SimpleDoubleProperty(); 
        this.humidity = new SimpleDoubleProperty(); 
        this.precip = new SimpleDoubleProperty(); 
        this.precipprob = new SimpleDoubleProperty(); 
        this.snow = new SimpleDoubleProperty(); 
        this.pressure = new SimpleDoubleProperty(); 
        this.cloudcover = new SimpleDoubleProperty(); 
        this.sunrise = new SimpleStringProperty(); 
        this.sunset = new SimpleStringProperty(); 
        this.conditions = new SimpleStringProperty(); 
        this.description = new SimpleStringProperty(); 
        this.icon = new SimpleStringProperty(); 
    }

    public WeatherDay(String datetime, double temp, double humidity, double precip, double precipprob, double snow, double pressure, double cloudcover, String sunrise, String sunset, String conditions, String description, String icon) {
        this.datetime.set(datetime);
        this.temp.set(temp);
        this.humidity.set(humidity);
        this.precip.set(precip);
        this.precipprob.set(precipprob);
        this.snow.set(snow);
        this.pressure.set(pressure);
        this.cloudcover.set(cloudcover);
        this.sunrise.set(sunrise);
        this.sunset.set(sunset);
        this.conditions.set(conditions);
        this.description.set(description);
        this.icon.set(icon);
    }

    public StringProperty datetimeProperty() { return datetime; }
    public String getDatetime() { return datetime.get(); } 
    public void setDatetime(String datetime) { this.datetime.set(datetime); }

    public DoubleProperty tempProperty() { return temp; } 
    public double getTemp() { return temp.get(); }
    public void setTemp(double temp) { this.temp.set(temp); }

    public DoubleProperty humidityProperty() { return humidity; }
    public double getHumidity() { return humidity.get(); }
    public void setHumidity(double humidity) { this.humidity.set(humidity); }

    public DoubleProperty precipProperty() { return precip; }
    public double getPrecip() { return precip.get(); }
    public void setPrecip(double precip) { this.precip.set(precip); }

    public DoubleProperty precipprobProperty() { return precipprob; }
    public double getPrecipprob() { return precipprob.get(); }
    public void setPrecipprob(double precipprob) { this.precipprob.set(precipprob); }

    public DoubleProperty snowProperty() { return snow; }
    public double getSnow() { return snow.get(); }
    public void setSnow(double snow) { this.snow.set(snow); }

    public DoubleProperty pressureProperty() { return pressure; }
    public double getPressure() { return pressure.get(); }
    public void setPressure(double pressure) { this.pressure.set(pressure); }

    public DoubleProperty cloudcoverProperty() { return cloudcover; }
    public double getCloudcover() { return cloudcover.get(); }
    public void setCloudcover(double cloudcover) { this.cloudcover.set(cloudcover); }

    public StringProperty sunriseProperty() { return sunrise; }
    public String getSunrise() { return sunrise.get(); }
    public void setSunrise(String sunrise) { this.sunrise.set(sunrise); }

    public StringProperty sunsetProperty() { return sunset; }
    public String getSunset() { return sunset.get(); }
    public void setSunset(String sunset) { this.sunset.set(sunset); }

    public StringProperty conditionsProperty() { return conditions; }
    public String getConditions() { return conditions.get(); }
    public void setConditions(String conditions) { this.conditions.set(conditions); }

    public StringProperty descriptionProperty() { return description; }
    public String getDescription() { return description.get(); }
    public void setDescription(String description) { this.description.set(description); }

    public StringProperty iconProperty() { return icon; }
    public String getIcon() { return icon.get(); }
    public void setIcon(String icon) { this.icon.set(icon); }
}