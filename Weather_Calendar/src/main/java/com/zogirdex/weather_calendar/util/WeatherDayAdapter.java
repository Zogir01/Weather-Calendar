package com.zogirdex.weather_calendar.util;

/**
 *
 * @author tom3k
 */
import com.zogirdex.weather_calendar.model.WeatherDay;
import com.google.gson.*;
import java.lang.reflect.Type;

class WeatherDayAdapter implements JsonDeserializer<WeatherDay> {
    @Override
    public WeatherDay deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        WeatherDay weatherDay = new WeatherDay();
        
        // Przypisanie wartości do właściwości (Property)
        weatherDay.setDatetime(obj.get("datetime").getAsString());
        weatherDay.setTemp(obj.get("temp").getAsDouble());
        weatherDay.setHumidity(obj.get("humidity").getAsDouble());
        weatherDay.setPrecip(obj.get("precip").getAsDouble());
        weatherDay.setPrecipprob(obj.get("precipprob").getAsDouble());
        weatherDay.setSnow(obj.get("snow").getAsDouble());
        weatherDay.setPressure(obj.get("pressure").getAsDouble());
        weatherDay.setCloudcover(obj.get("cloudcover").getAsDouble());
        weatherDay.setSunrise(obj.get("sunrise").getAsString());
        weatherDay.setSunset(obj.get("sunset").getAsString());
        weatherDay.setConditions(obj.get("conditions").getAsString());
        weatherDay.setDescription(obj.get("description").getAsString());
        weatherDay.setIcon(obj.get("icon").getAsString());

        return weatherDay;
    }
}
