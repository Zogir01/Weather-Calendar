package com.zogirdex.weather_calendar.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectStreamException;

public class ScheduledEvent implements Serializable {
    private transient StringProperty eventName;
    private transient StringProperty eventDesc;
    private transient StringProperty location;
    
    public ScheduledEvent() {
        this.eventName = new SimpleStringProperty("");
        this.eventDesc = new SimpleStringProperty("");
        this.location = new SimpleStringProperty("");
    }
    
    public ScheduledEvent(String eventName, String eventDesc, String location) {
        this.eventName = new SimpleStringProperty(eventName);
        this.eventDesc = new SimpleStringProperty(eventDesc);
        this.location = new SimpleStringProperty(location);
    }
    
    public StringProperty eventNameProperty() { return this.eventName; }
    public String getEventName() { return this.eventName.get(); }
    public void setEventName(String eventName) { this.eventName.set(eventName); }
    
    public StringProperty eventDescProperty() { return this.eventDesc; }
    public String getEventDesc() { return this.eventDesc.get(); }
    public void setEventDesc(String eventDesc) { this.eventDesc.set(eventDesc); }
    
    public StringProperty locationProperty() { return this.location; }
    public String getLocation() { return this.location.get(); }
    public void setLocation(String dayOfMonth) { this.location.set(dayOfMonth); }
    
    private void writeObject(ObjectOutputStream out) throws IOException{
        out.defaultWriteObject();
        out.writeUTF(eventName.get());
        out.writeUTF(eventDesc.get());
        out.writeUTF(location.get());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        this.eventName = new SimpleStringProperty(in.readUTF());
        this.eventDesc = new SimpleStringProperty(in.readUTF());
        this.location = new SimpleStringProperty(in.readUTF());
    }

    private void readObjectNoData() throws ObjectStreamException{
        this.eventName = new SimpleStringProperty();
        this.eventDesc = new SimpleStringProperty();
        this.location = new SimpleStringProperty();
    }
}
