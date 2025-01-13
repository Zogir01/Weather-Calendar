//package com.zogirdex.weather_calendar;
//
//import javafx.beans.value.ChangeListener;
//import javafx.collections.MapChangeListener;
//
//import javafx.beans.value.ObservableValue;
//import java.time.LocalDate;
//import java.util.function.Consumer;
//import javafx.beans.value.ObservableMapValue;
//
///**
// *
// * @author tom3k
// */
//public class EventChangeListener implements MapChangeListener<LocalDate, ScheduledEvent> {
//    private final Consumer<Change<? extends LocalDate, ? extends ScheduledEvent>> onChangeHandler;
//
//    // metoda jako parametr
//    //https://www.geeksforgeeks.org/java-8-consumer-interface-in-java-with-examples/
//    public EventChangeListener(Consumer<Change<? extends LocalDate, ? extends ScheduledEvent>> onChangeHandler) {
//        this.onChangeHandler = onChangeHandler;
//    }
//
//    @Override
//    public void onChanged(Change<? extends LocalDate, ? extends ScheduledEvent> change) {
//        onChangeHandler.accept(change);
//    }
//}
////public class EventChangeListener  implements MapChangeListener<LocalDate, ScheduledEvent> {
////    @Override
////    public void onChanged(Change<? extends LocalDate, ? extends ScheduledEvent> change) {
////        if(change.wasAdded()) {
////            
////        }
////        
////        if(change.wasRemoved()) {
////            
////        }
////    }
//    
//
//
////    public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
////        //((XYChart.Data)(seria.getData().get(row))).setYValue(t1);
////    }
//
