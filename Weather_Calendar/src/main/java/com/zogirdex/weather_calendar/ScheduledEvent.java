package com.zogirdex.weather_calendar;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectStreamException;


/**
 *
 * @author tom3k
 */
public class ScheduledEvent implements Serializable {
    private transient StringProperty eventName;
    private transient StringProperty eventDesc;
    private transient StringProperty dayOfMonth;
    
    public ScheduledEvent() {
        this.eventName = new SimpleStringProperty("");
        this.eventDesc = new SimpleStringProperty("");
        this.dayOfMonth = new SimpleStringProperty("");
    }
    
    public ScheduledEvent(String eventName, String eventDesc, String dayOfMonth) {
        this.eventName = new SimpleStringProperty(eventName);
        this.eventDesc = new SimpleStringProperty(eventDesc);
        this.dayOfMonth = new SimpleStringProperty(dayOfMonth);
    }
    
    public StringProperty eventNameProperty() { return this.eventName; }
    public String getEventName() { return this.eventName.get(); }
    public void setEventName(String eventName) { this.eventName.set(eventName); }
    
    public StringProperty eventDescProperty() { return this.eventDesc; }
    public String getEventDesc() { return this.eventDesc.get(); }
    public void setEventDesc(String eventDesc) { this.eventDesc.set(eventDesc); }
    
    public StringProperty dayOfMonthProperty() { return this.dayOfMonth; }
    public String getDayOfMonth() { return this.dayOfMonth.get(); }
    public void setDayOfMonth(String dayOfMonth) { this.dayOfMonth.set(dayOfMonth); }
    
    public StringProperty calendarTextProperty() {
        StringProperty fullText = new SimpleStringProperty();
        fullText.bind(dayOfMonth.concat("\n").concat(eventName));
        return fullText;
    }
    private void writeObject(ObjectOutputStream out) throws IOException{
        out.defaultWriteObject();
        out.writeUTF(eventName.get());
        out.writeUTF(eventDesc.get());
        out.writeUTF(dayOfMonth.get());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        this.eventName = new SimpleStringProperty(in.readUTF());
        this.eventDesc = new SimpleStringProperty(in.readUTF());
        this.dayOfMonth = new SimpleStringProperty(in.readUTF());
    }

    private void readObjectNoData() throws ObjectStreamException{
        this.eventName = new SimpleStringProperty();
        this.eventDesc = new SimpleStringProperty();
        this.dayOfMonth = new SimpleStringProperty();
    }
}
