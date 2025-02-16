package com.zogirdex.weather_calendar.uiutil;

import com.zogirdex.weather_calendar.config.AppConstants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.stage.Modality;
import javafx.scene.Node;
import javafx.event.ActionEvent;

/**
 *
 * @author tom3k
 * 
 */
public class StageAssistant {
    private static StageAssistant instance;
    private StageAssistant() {}
    
    public static StageAssistant getInstance() {
        if (instance == null) {
            instance = new StageAssistant();
        }
        return instance;
    }
    
    public <T> T openNewStage(String fxmlPath, String title, boolean isModal, 
            double minWidth, double minHeight) throws IOException 
    {        
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        this.loadCssStylesheet(scene);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.setMinWidth(minWidth);
        stage.setMinHeight(minHeight);
        if (isModal) {
            stage.initModality(Modality.APPLICATION_MODAL);
        }
        stage.show();
        return loader.getController();
    }
    
    public <T> T switchScene(String fxmlPath, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        this.loadCssStylesheet(scene);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        return loader.getController();
     }
    
    public void loadCssStylesheet(Scene scene) {
        scene.getStylesheets().add(getClass().getResource(AppConstants.PATH_CSS_STYLES).toExternalForm());
    }
}
