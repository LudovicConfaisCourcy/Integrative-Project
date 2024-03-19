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

            handleClickMeQuit();

        });
    }

    @FXML
    private void handleClickMePlay() throws IOException {

        logger.info("Clicked Play Button");
        switchScenes("/fxml/TetrisScene.fxml", new FXMLPlayController());
    }

    @FXML
    private void handleClickMeSettings() throws IOException {

        logger.info("Clicked Settng Button");
        switchScenes("/fxml/SettingsScene.fxml", new FXMLSettingsController());
    }

    @FXML
    private void handleClickMeCredits() throws IOException {
        logger.info("Clicked Credit Button");
        switchScenes("/fxml/CreditScene.fxml", new FXMLCreditsController());

    }

    @FXML
    private void handleClickMeQuit() {

        logger.info("Clicked Quit Button");

        Stage currentStage = (Stage) btnPlay.getScene().getWindow();
        currentStage.close();

    }

    public void switchScenes(String fxml, Object controller) throws IOException {

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
