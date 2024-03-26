
package edu.vanier.template.controllers;


import edu.vanier.template.MainApp;
import java.io.IOException;
import java.util.logging.Level;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 /* @authors
 *  Ludovic Confais Courcy
 *  Anton Lisunov
 *  Shyam Patel
 */

   
public class FXMLCreditsController {
    private final static Logger logger = LoggerFactory.getLogger(FXMLMainAppController.class);
    @FXML
    Button btnCreators;
    @FXML
    Button btnInspirations;
    @FXML
    Button btnScience;
    @FXML
    Button btnDescription;   

    @FXML
    public void switchScenes(ActionEvent event) throws IOException {
        Object clickButton = event.getSource();
        if (clickButton == btnCreators) {
            logger.info("Clicked Play Button");
            switchScene("/fxml/TetrisScene.fxml", new FXMLPlayController());
        } else if (clickButton == btnInspirations) {
            logger.info("Clicked Settng Button");
            switchScene("/fxml/SettingsScene.fxml", new FXMLSettingsController());
        } else if (clickButton == btnScience) {
            logger.info("Clicked Credit Button");
            switchScene("/fxml/CreditScene.fxml", new FXMLCreditsController());
        } else if (clickButton == btnDescription) {
            logger.info("Clicked Quit Button");
            Stage currentStage = (Stage) btnCreators.getScene().getWindow();
        }
    }

    public void switchScene(String fxml, Object controller) throws IOException {
        logger.info("Stage is changed");
        Stage currentStage = (Stage) btnCreators.getScene().getWindow();
        MainApp mainApp = new MainApp();
        FXMLLoader loader = mainApp.loadFXML(fxml, controller);
        currentStage.setScene(new Scene(loader.load()));
    
}}
