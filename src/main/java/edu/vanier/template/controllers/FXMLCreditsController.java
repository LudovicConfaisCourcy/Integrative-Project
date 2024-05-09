
package edu.vanier.template.controllers;


import edu.vanier.template.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FXMLCreditsController {

    private final static Logger logger = LoggerFactory.getLogger(FXMLMainAppController.class);
    FXMLSettingsController music = new FXMLSettingsController();

    @FXML
    Button btnCreators;
    @FXML
    Button btnInspirations;
    @FXML
    Button btnScience;
    @FXML
    Button btnDescription;
    @FXML
    Pane CreditsPane;

    public void initialize() {
        music.musicPlay();

    }

    @FXML
    private void handleBtnCreators() throws IOException {
<<<<<<< HEAD
        logger.info("Creators button clicked");
        music.musicStop();
        music.soundPlay();
        Stage stage = (Stage) btnCreators.getScene().getWindow();
        MainApp mainApp = new MainApp();
        FXMLLoader loader = mainApp.loadFXML("/fxml/CreatorsScene.fxml", new FXMLMainAppController());
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);

=======
    logger.info("Creators button clicked");  
    
    Stage stage = (Stage) btnCreators.getScene().getWindow();
    MainApp mainApp = new MainApp();
    FXMLLoader loader = mainApp.loadFXML("/fxml/CreatorsScene.fxml", new FXMLCreatorsController());
    Scene scene = new Scene(loader.load());
    stage.setScene(scene);
>>>>>>> Shyam
    }

    @FXML
    private void handleBtnInspirations() throws IOException {
<<<<<<< HEAD
        logger.info("Inspirations button clicked");
        music.musicStop();
        music.soundPlay();
        Stage stage = (Stage) btnCreators.getScene().getWindow();
        MainApp mainApp = new MainApp();
        FXMLLoader loader = mainApp.loadFXML("/fxml/InspirationsScene.fxml", new FXMLMainAppController());
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
=======
    logger.info("Inspirations button clicked"); 
    
    Stage stage = (Stage) btnCreators.getScene().getWindow();
    MainApp mainApp = new MainApp();
    FXMLLoader loader = mainApp.loadFXML("/fxml/InspirationsScene.fxml", new FXMLInspirationsController());
    Scene scene = new Scene(loader.load());
    stage.setScene(scene);
>>>>>>> Shyam
    }

    @FXML
    private void handleBtnScience() throws IOException {
<<<<<<< HEAD
        logger.info("Science button clicked");
        music.musicStop();
        music.soundPlay();
        Stage stage = (Stage) btnCreators.getScene().getWindow();
        MainApp mainApp = new MainApp();
        FXMLLoader loader = mainApp.loadFXML("/fxml/ScienceScene.fxml", new FXMLMainAppController());
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
=======
    logger.info("Science button clicked"); 
    
    Stage stage = (Stage) btnCreators.getScene().getWindow();
    MainApp mainApp = new MainApp();
    FXMLLoader loader = mainApp.loadFXML("/fxml/ScienceScene.fxml", new FXMLScienceController());
    Scene scene = new Scene(loader.load());
    stage.setScene(scene);
>>>>>>> Shyam
    }

    @FXML
    private void handleBtnMenu() throws IOException {
        logger.info("Description button clicked");
        music.musicStop();
        music.soundPlay();
        Stage stage = (Stage) btnCreators.getScene().getWindow();
        MainApp mainApp = new MainApp();
        FXMLLoader loader = mainApp.loadFXML("/fxml/IntroScene.fxml", new FXMLMainAppController());
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    @FXML
    private void CursorChange(){
        CreditsPane.setCursor(Cursor.HAND);
    }
    @FXML
    private void CursorChangeExit(){
        CreditsPane.setCursor(Cursor.DEFAULT);
    }

}
