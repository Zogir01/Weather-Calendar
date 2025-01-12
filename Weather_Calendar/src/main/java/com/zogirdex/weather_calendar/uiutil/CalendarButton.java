package com.zogirdex.weather_calendar.uiutil;

import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

public class CalendarButton extends Button {
    
    public CalendarButton(String text, String tooltipText) {
        super(text);
        this.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.setStyle("-fx-font-size: 14px; "
                + "-fx-padding: 5px;"
                + "-fx-alignment: center;");
        
        Tooltip tp = new Tooltip();
        tp.setText(tooltipText);
        tp.setShowDelay(Duration.millis(500)); 
        tp.setShowDuration(Duration.seconds(5)); 
        tp.setHideDelay(Duration.millis(200));
        this.setTooltip(tp);
    }
    
    public void setBackgroundImage(String url) {
        this.setStyle(this.getStyle()
                + String.format("-fx-background-image: url('%s');", url)
                + "-fx-background-size: cover;"
                + "-fx-background-repeat: no-repeat;"
        );
    }
    
//    private void initializeStyle() {
//        this.setFont(Font.font("Arial", FontWeight.BOLD, 14));
//        this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
//        this.setStyle("-fx-font-size: 14px; "
//                + "-fx-padding: 5px;"
//                + "-fx-alignment: center;");
//        
//        Tooltip tp = new Tooltip();
//        tp.setText(date.toString());
//        tp.setShowDelay(Duration.millis(500)); 
//        tp.setShowDuration(Duration.seconds(5)); 
//        tp.setHideDelay(Duration.millis(200));
//        this.setTooltip(tp);
//    }
    
//    public LocalDate getDate() {
//        return this.date;
//    }
//    
//    public <T> void bindToValue(ObservableValue<T> value, boolean showDayNumber) {
//        final String initialText = showDayNumber ? String.valueOf(this.date.getDayOfMonth()).concat("\n")  : "";
//        this.setText(initialText + value.getValue());
//        value.addListener((observable, oldVal, newVal) -> this.setText(initialText + newVal));
//        // można by jeszcze np. zmieniać wygląd buttona
//    }
    
//    public void bindToEvent(CalendarEvent event) { 
//        this.bindToEvent(event, BindType.byName); // by default
//    }
    
//    public void bindToEvent(CalendarEvent event, BindType bindType) {
//        
//        String text = String.valueOf(this.dayOfMonth).concat("\n");
//        
//        if(bindType == BindType.byName) {
//            
//            this.textProperty().bind(event.eventNameProperty().concat(text).concat("\n"));
//            
//        }
//        else if(bindType == BindType.byDesc) {
//            this.textProperty().bind(event.eventDescProperty().concat(text).concat("\n"));
//        }
//    }
//    
//    // Rozważ przypisanie do konkretnej zmiennej, zamiast przekazywania tu obiektu CalendarEvent
//    // - konkretna zmienna, czyli jakieś "Property" (ObservableValue)
//    public void bindToEvent(CalendarEvent event, BindType bindType) {
//        //this.textProperty().addListener(new CalendarEventChangeListener(this));
//        String initialText = String.valueOf(this.dayOfMonth).concat("\n");
//        
//        if(bindType == BindType.byName) {
//            // initial set
//            this.setText(initialText.concat(event.getEventName()));
//            // create listener
//            event.eventNameProperty().addListener((observable, oldVal, newVal) -> {
//                this.setText(initialText.concat(newVal));  
//            });
//        }
//        else if(bindType == BindType.byDesc) {
//            // initial set
//            this.setText(initialText.concat(event.getEventDesc()));
//            // create listener
//            event.eventDescProperty().addListener((observable, oldVal, newVal) -> {
//                this.setText(initialText.concat(newVal));
//            });
//         }
//    }
//    public void assignText(String text) {
//        this.setText(String.format("%s\n%s ", 
//                    this.dayOfMonth,
//                    text
//            ));
//    }
    
//    public void insertEventText(CalendarEvent event) {
//        if(event != null) {
//            this.setText(String.format("%s\n%s ", 
//                    String.valueOf(this.getText()),
//                    event.getEventName()
//            ));
//        }
//        else {
//            throw new NullPointerException("");
//        }
//    }
}
