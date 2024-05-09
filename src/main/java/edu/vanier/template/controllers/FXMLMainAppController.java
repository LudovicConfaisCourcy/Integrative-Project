package edu.vanier.template.controllers;

import edu.vanier.template.MainApp;
import java.io.IOException;
import java.nio.file.Paths;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * @authors Ludovic Confais Courcy Anton Lisunov Shyam Patel
 */
public class FXMLMainAppController{

    FXMLSettingsController music = new FXMLSettingsController();
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
    Pane introPane;


    @FXML
    public void initialize() {
        music.musicPlay();

    }


    @FXML
    public void switchScenes(ActionEvent event) throws IOException {
        Object clickButton = event.getSource();
        if (clickButton == btnPlay) {
            music.soundPlay();
            logger.info("Clicked Play Button");
            switchScene("/fxml/TetrisScene.fxml", new FXMLPlayController());
            music.musicStop();
        } else if (clickButton == btnSettings) {
            logger.info("Clicked Setting Button");
            music.soundPlay();
            switchScene("/fxml/SettingsScene.fxml", new FXMLSettingsController());
            music.musicStop();
        } else if (clickButton == btnCredits) {
            logger.info("Clicked Credit Button");
            music.soundPlay();
            switchScene("/fxml/CreditScene.fxml", new FXMLCreditsController());
            music.musicStop();
        } else if (clickButton == btnQuit) {
            logger.info("Clicked Quit Button");
            music.soundPlay();
            Stage currentStage = (Stage) btnPlay.getScene().getWindow();
            currentStage.close();
            Platform.exit();

        }
    }

    public void switchScene(String fxml, Object controller) throws IOException {
        logger.info("Stage is changed");
        Stage currentStage = (Stage) btnPlay.getScene().getWindow();
        MainApp mainApp = new MainApp();
        FXMLLoader loader = mainApp.loadFXML(fxml, controller);
        currentStage.setScene(new Scene(loader.load()));
    }



    @FXML
    private void CursorChange(){
        introPane.setCursor(Cursor.HAND);
    }
    @FXML
    private void CursorChangeExit(){
        introPane.setCursor(Cursor.DEFAULT);
    }
}
