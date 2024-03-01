package edu.vanier.template.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    public void initialize() {
        logger.info("Initializing MainAppController...");
        btnPlay.setOnAction((event) -> {
            handleClickMe();
        });
    }
    @FXML
    private void handleClickMe() {
        logger.info("Clicked Play Button");        
    }
}