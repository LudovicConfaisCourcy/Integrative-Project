package edu.vanier.template.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @authors Ludovic Confais Courcy Anton Lisunov Shyam Patel
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
    
    public void initialize() {
    }

    @FXML
    void switchScenes(ActionEvent event) throws IOException {
        Object clickButton = event.getSource();
        if (clickButton == btnPlay) {
            logger.info("Clicked Play Button");
            switchScene("/fxml/TetrisScene.fxml", new FXMLPlayController());
        } else if (clickButton == btnSettings) {
            logger.info("Clicked Settng Button");
            switchScene("/fxml/SettingsScene.fxml", new FXMLSettingsController());
        } else if (clickButton == btnCredits) {
            logger.info("Clicked Credit Button");
        switchScene("/fxml/CreditScene.fxml", new FXMLCreditsController());
        } else if (clickButton == btnQuit) {
            logger.info("Clicked Quit Button");

            Stage currentStage = (Stage) btnPlay.getScene().getWindow();
            currentStage.close();

        }
    }

    public void switchScene(String fxml, Object controller) throws IOException {

        logger.info("Stage is changed");

        Stage currentStage = (Stage) btnPlay.getScene().getWindow();
        Pane root = loadFXML(fxml, controller).load();
        Scene scene = new Scene(root);
        currentStage.setScene(scene);

    }

    public FXMLLoader loadFXML(String fxml, Object controller) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        loader.setController(controller);
        return loader;
    }
}
