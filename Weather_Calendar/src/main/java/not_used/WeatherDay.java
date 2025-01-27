//
//package not_used;
//
//import com.zogirdex.weather_calendar.model.*;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.property.StringProperty;
//import javafx.beans.property.SimpleDoubleProperty;
//import javafx.beans.property.DoubleProperty;
//
////import javax.annotation.Generated;
//
////@Generated("jsonschema2pojo")
//public class WeatherDay {
//
//public StringProperty datetime;
//public DoubleProperty temp; // temperatura
//public DoubleProperty humidity; // wilgotnosc
//public DoubleProperty precip; // opady
//public DoubleProperty precipprob; // szansa opadów
//public DoubleProperty snow; // śnieg
//public DoubleProperty pressure; // ciśnienie
//public DoubleProperty cloudcover; // zachmurzenie
//public StringProperty sunrise; // wschód słońca
//public StringProperty sunset; // zachód słońca
//public StringProperty conditions; // warunki
//public StringProperty description; // opis
//public StringProperty icon; // ikona
//
//public WeatherDay() {
//}
//
//public WeatherDay(String datetime, double temp, double humidity, double precip, double precipprob, double snow, double pressure, double cloudcover, String sunrise, String sunset, String conditions, String description, String icon) {
//        super();
//        this.datetime = new SimpleStringProperty(datetime);
//        this.temp = new SimpleDoubleProperty(temp);
//        this.humidity = new SimpleDoubleProperty(humidity);
//        this.precip = new SimpleDoubleProperty(precip);
//        this.precipprob = new SimpleDoubleProperty(precipprob);
//        this.snow = new SimpleDoubleProperty(snow);
//        this.pressure = new SimpleDoubleProperty(pressure);
//        this.cloudcover = new SimpleDoubleProperty(cloudcover);
//        this.sunrise = new SimpleStringProperty(sunrise);
//        this.sunset = new SimpleStringProperty(sunset);
//        this.conditions = new SimpleStringProperty(conditions);
//        this.description = new SimpleStringProperty(description);
//        this.icon = new SimpleStringProperty(icon);
//    }
//
//public StringProperty datetimeProperty() { return this.datetime; }
//public String getDatetime() { return datetime.get(); }
//public void setDatetime(String datetime) { this.datetime.set(datetime); }
//
//public DoubleProperty tempProperty() { return this.temp; }
//public double getTemp() { return temp.get(); }
//public void setTemp(double temp) { this.temp.set(temp); }
//
//public DoubleProperty humidityProperty() { return this.humidity; }
//public double getHumidity() { return humidity.get(); }
//public void setHumidity(double humidity) { this.humidity.set(humidity); }
//
//public DoubleProperty precipProperty() { return this.precip; }
//public double getPrecip() { return precip.get(); }
//public void setPrecip(double precip) { this.precip.set(precip);  }
//
//public DoubleProperty precipprobProperty() { return this.precipprob; }
//public double getPrecipprob() { return precipprob.get(); }
//public void setPrecipprob(double precipprob) { this.precipprob.set(precipprob); }
//
//public DoubleProperty snowProperty() { return this.snow; }
//public double getSnow() { return snow.get(); }
//public void setSnow(double snow) { this.snow.set(snow); }
//
//public DoubleProperty pressureProperty() { return this.pressure; }
//public double getPressure() { return pressure.get(); }
//public void setPressure(double pressure) { this.pressure.set(pressure); }
//
//public DoubleProperty cloudcoverProperty() { return this.cloudcover; }
//public double getCloudcover() { return cloudcover.get(); }
//public void setCloudcover(double cloudcover) { this.cloudcover.set(cloudcover); }
//
//public StringProperty sunriseProperty() { return this.sunrise; }
//public String getSunrise() { return sunrise.get(); }
//public void setSunrise(String sunrise) { this.sunrise.set(sunrise); }
//
//public StringProperty sunsetProperty() { return this.sunset; }
//public String getSunset() { return sunset.get(); }
//public void setSunset(String sunset) { this.sunset.set(sunset); }
//
//public StringProperty conditionsProperty() { return this.conditions; }
//public String getConditions() { return conditions.get(); }
//public void setConditions(String conditions) { this.conditions.set(conditions); }
//
//public StringProperty descriptionProperty() { return this.description; }
//public String getDescription() { return description.get(); }
//public void setDescription(String description) { this.description.set(description); }
//
//public StringProperty iconProperty() { return this.icon; }
//public String getIcon() { return icon.get(); }
//public void setIcon(String icon) { this.icon.set(icon); }
//
//}
