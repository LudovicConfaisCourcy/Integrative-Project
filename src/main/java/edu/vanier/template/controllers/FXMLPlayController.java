
package edu.vanier.template.controllers;

import edu.vanier.template.controllers.FXMLMainAppController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @authors 
 *  Anton Lisunov
 */
public class FXMLPlayController {

    private final static Logger logger = LoggerFactory.getLogger(FXMLMainAppController.class);
    @FXML
    Button btnPlay;
    @FXML
    Button btnStop;
    @FXML
    Button btnRestarts;
    @FXML
    Button btnMenu;
    @FXML
    Button btnHelp;
    @FXML
    Label lbScore;
    @FXML
    Pane pnNext;
    
    @FXML
    public void initialize() {

    }
    
    @FXML
    private void handleBtnPlay(ActionEvent event) {
        logger.info("Play button clicked");
        // Implement the functionality to stop the game or any other action
    }
    
    @FXML
    private void handleBtnStop(ActionEvent event) {
        logger.info("Stop button clicked");
        // Implement the functionality to stop the game or any other action
    }
    
    @FXML
    private void handleBtnRestart(ActionEvent event) {
        logger.info("Restart button clicked");
        // Implement the functionality to restart the game or any other action
    }
    
    @FXML
    private void handleBtnMenu(ActionEvent event) {
        logger.info("Menu button clicked");
        // Implement the functionality to go to the menu or any other action
    }
    
    @FXML
    private void handleBtnHelp(ActionEvent event) {
        logger.info("Help button clicked");
        // Implement the functionality to show help or any other action
    }
    
    
}
