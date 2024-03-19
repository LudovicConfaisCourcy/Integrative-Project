package edu.vanier.template.controllers;

import java.io.IOException;
import java.util.logging.Level;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @authors
 *  Ludovic Confais Courcy
 *  Anton Lisunov
 *  Shyam Patel
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
    public void initialize() {
        logger.info("Initializing MainAppController...");
        
        btnPlay.setOnAction((event) -> {
            try {      
                handleClickMePlay();
            } catch (IOException ex) {
                 logger.error(ex.getMessage(), ex);
            }
        });
        btnSettings.setOnAction((event) -> {
            try {      
                handleClickMeSettings();
            } catch (IOException ex) {
                logger.error(ex.getMessage(), ex);
            }
        });
        btnCredits.setOnAction((event) -> {
            try {      
                handleClickMeCredits();
            } catch (IOException ex) {
                logger.error(ex.getMessage(), ex);
            }
        });
        btnQuit.setOnAction((event) -> {
            try {      
                handleClickMeQuit();
            } catch (IOException ex) {
                logger.error(ex.getMessage(), ex);
            }
        });
    }

    @FXML
    private void handleClickMePlay() throws IOException {
        logger.info("Clicked Play Button");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TetrisScene.fxml"));
        loader.setController(new FXMLPlayController());
        Pane root = loader.load();
        
        Scene secondScene = new Scene(root,400, 400);
        Stage secondStage = new Stage();
        
        secondStage.setScene(secondScene);
        secondStage.setTitle("Play");
        secondStage.show();
    }
 
    @FXML
    private void handleClickMeSettings() throws IOException {
        logger.info("Clicked Settings Button");    
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SettingsScene.fxml"));
        loader.setController(new FXMLSettingsController());
        Pane root = loader.load();
        
        Scene secondScene = new Scene(root,400, 400);
        Stage secondStage = new Stage();
        
        secondStage.setScene(secondScene);
        secondStage.setTitle("Settings");
        secondStage.show();
    }
    @FXML
    private void handleClickMeCredits() throws IOException {
        logger.info("Clicked Credit Button");    
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CreditScene.fxml"));
        loader.setController(new FXMLCreditsController());
        Pane root = loader.load();
        
        Scene secondScene = new Scene(root,400, 400);
        Stage secondStage = new Stage();
        
        secondStage.setScene(secondScene);
        secondStage.setTitle("Credit");
        secondStage.show();
    }
    
    @FXML
    private void handleClickMeQuit() throws IOException {
        logger.info("Clicked Quit Button"); 
        
        Stage primaryStage = (Stage) btnQuit.getScene().getWindow();
        primaryStage.close();
    }
}