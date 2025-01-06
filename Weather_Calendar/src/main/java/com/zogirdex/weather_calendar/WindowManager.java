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
 * POMYSŁY NA TĄ KLASĘ:
 * - można by dodać zapisywanie otwieranych okien do jakieś hashMap
 * - w stage.setOnCloseRequest( handler -> {}); usuwać te okno z hashMapy
 * - można by zrobić funkcję do zamykania określonego okna, oraz funkcje do zamykania wszystkich otwartych okien.
 * - funkcję, która umożliwiałaby ustawianie różnych stylów wszystkich/określonych okien
 * - można by stworzyć globalny handler, z którego mogłyby korzystać klasy takie jak "Logger", który
 * zarejestrował by ten handler i w przypadku jego wykonania, logowane byłyby informacje na temat zamkniętego okna.
 * - dodać mechanizm przechwytywania błędów podczas otwierania okien
 * - można by dodać animacje otwierania/zamykania okien
 * 
 * 
 */
public class WindowManager {
    //private static final String CALENDAR_SCENE_FXML = "test.fxml";
    //private static final String SECONDARY_SCENE_FXML = "secondary.fxml";
    
    private static WindowManager instance;

    // Prywatny konstruktor, aby uniemożliwić tworzenie nowych instancji
    private WindowManager() {}
    
    public static WindowManager getInstance() {
        if (instance == null) {
            instance = new WindowManager();
        }
        return instance;
    }
    
    /**
     * Metoda do otwierania nowego okna z kontrolerem.
     * 
     * @param   fxmlPath           Path to FXML file.
     * @param   title                  Title of new window
     * @param   isModal            determines window modality
     * @param   <T>                 controller class type
     * @return                          controller of new opened window
     * @throws                         IOException if FXML file wasn't loaded correctly
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
///////////////////////////////// OPCJA Z ExtController ////////////////////////////////////////////
//        T controller = loader.getController();
//        // jeśli dziedziczy po ExtController, ustawiamy Stage
//        if(controller instanceof ExtController) {
//            ((ExtController)controller).setStage(stage);
//        }
///////////////////////////////// OPCJA Z ExtController ////////////////////////////////////////////
        stage.show();
        return loader.getController();
    }
    
    public <T> T switchScene(String fxmlPath, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        return loader.getController();
     }

////////////////////////////////////////////////// OPCJA Z ExtController ////////////////////////////////////////////////////////
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
