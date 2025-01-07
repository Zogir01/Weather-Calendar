package com.zogirdex.weather_calendar;

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
public class WindowManager {
    //private static final String CALENDAR_SCENE_FXML = "test.fxml";
    //private static final String SECONDARY_SCENE_FXML = "secondary.fxml";
    
    /**
     * Singleton pattern. Instance of WindowManager
     */
    private static WindowManager instance;

     /**
     * Singleton pattern. Private constructor to prevent new instances from being created
     */
    private WindowManager() {}
    
    /**
     * Singleton pattern. Method to get instance of WindowManager, if instance is null, new instance will be created.
     * 
     * @return Instance of WindowManager.
     */
    public static WindowManager getInstance() {
        if (instance == null) {
            instance = new WindowManager();
        }
        return instance;
    }
    
    /**
     * Open a new fxml window called "Stage" and assign a scene to it. Scene is created under FXMLLoader class by 
     * assigning path to .fxml file. New window can be "modal", which means that primary window is not available while
     * showing second window.
     * 
     * @param fxmlPath  Path to FXML file.
     * @param title Title of new window.
     * @param isModal  Specifies whether the window should be modal.
     * @param <T> Controller class assigned to input fxml file.
     * @return Controller of new opened window assigned to input fxml file.
     * @throws IOException if FXML file wasn't loaded correctly.
     */
    public <T> T openNewWindow(String fxmlPath, String title, boolean isModal) throws IOException {        
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
        if (isModal) {
            stage.initModality(Modality.APPLICATION_MODAL);
        }
        stage.show();
        return loader.getController();
    }
    
     /**
     * Switch Scene in current Stage that is correlated with event that occured. 
     * Stage is accessed by event.getSource() method.
     * 
     * @param fxmlPath  Path to FXML file.
     * @param event ActionEvent from which Stage can be loaded.
     * @param <T> Controller class assigned to input fxml file.
     * @return Controller of new opened window assigned to input fxml file.
     * @throws IOException if FXML file wasn't loaded correctly.
     */
    public <T> T switchScene(String fxmlPath, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        return loader.getController();
     }

////////////////////////////////////////////////// OPCJA Z ExtController ////////////////////////////////////////////////////////
//        // tworząc nowe okno (openNewWindow()):
//        T controller = loader.getController();
//        // jeśli dziedziczy po ExtController, ustawiamy Stage
//        if(controller instanceof ExtController) {
//            ((ExtController)controller).setStage(stage);
//        }
//    public <T> T switchScene(String fxmlPath, Stage stage) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
//        Parent root = loader.load();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        return loader.getController();
//    }
//     public <T> T switchScene(String fxmlPath, ActionEvent event) throws IOException {
//          return switchScene(fxmlPath, this.getStage(event));
//     }
//        private Stage getStage(ActionEvent event) {
//        return (Stage)((Node)event.getSource()).getScene().getWindow();
//    }
////////////////////////////////////////////////// OPCJA Z ExtController ////////////////////////////////////////////////////////
}
