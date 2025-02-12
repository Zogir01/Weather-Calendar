package com.zogirdex.weather_calendar.uiutil;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class AlertException extends Alert {
    public AlertException(Exception ex) {
        super(AlertType.ERROR);
        setTitle("Błąd aplikacji");
        setHeaderText(ex.getMessage());

        StringBuilder result = new StringBuilder();
        for (StackTraceElement element : ex.getStackTrace()) {
            result.append(element.toString()).append("\n");
        }
        
        TextArea textArea = new TextArea(result.toString());
        textArea.setEditable(false);
        textArea.setWrapText(true);

        VBox dialogPaneContent = new VBox(textArea);
        dialogPaneContent.setSpacing(10);

        getDialogPane().setExpandableContent(dialogPaneContent);
    }
}
