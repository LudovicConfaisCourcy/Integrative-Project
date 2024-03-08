package edu.vanier.template.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller class of the MainApp's UI.
 *
 * @author frostybee
 */
public class FXMLMainAppController {

    private final static Logger logger = LoggerFactory.getLogger(FXMLMainAppController.class);
    @FXML
    Button btnPlay;
    @FXML
    Button btnSettings;
    @FXML
    Button btnCredits;
    @FXML
    Button btnQuit;
    @FXML
    Label welcomeLabel;
    @FXML
    Label welcomeLabel1;
 
    @FXML
    public void initialize() {
        logger.info("Initializing MainAppController...");
        btnPlay.setOnAction((event) -> {
            handleClickMePlay();      
        });
        btnSettings.setOnAction((event) -> {
            handleClickMeSettings();      
        });
        btnCredits.setOnAction((event) -> {
            handleClickMeCredits();      
        });
        
    }
    @FXML
    private void handleClickMePlay() {
        logger.info("Clicked Play Button");    
         VBox mainVbox = new VBox();
        Scene secondScene = new Scene(mainVbox);
        Stage secondStage = new Stage();
        secondStage.setScene(secondScene);
        secondStage.setTitle("Play");
        secondStage.show();
    }
    private void handleClickMeSettings() {
        logger.info("Clicked Play Button");    
         VBox mainVbox = new VBox();
        Scene secondScene = new Scene(mainVbox);
        Stage secondStage = new Stage();
        secondStage.setScene(secondScene);
        secondStage.setTitle("Settings");
        secondStage.show();
    }
    private void handleClickMeCredits() {
        logger.info("Clicked Play Button");    
         VBox mainVbox = new VBox();
        Scene secondScene = new Scene(mainVbox);
        Stage secondStage = new Stage();
        secondStage.setScene(secondScene);
        secondStage.setTitle("Credits");
        secondStage.show();
    }
}