
package edu.vanier.template.controllers;


import edu.vanier.template.MainApp;
import javafx.scene.control.CheckBox;
import java.io.IOException;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import static javafx.scene.Cursor.cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @authors
 *  Ludovic Confais Courcy
 */

public class FXMLSettingsController{

    private final static Logger logger = LoggerFactory.getLogger(FXMLMainAppController.class);

    @FXML
    CheckBox MusicCheckBox;
    @FXML
    CheckBox SoundCheckBox;
    @FXML
    TextField frictionTextField = new TextField("1");
    @FXML
    TextField gravityTextField = new TextField("1");
    @FXML
    TextField soundTextField = new TextField("1");
    @FXML
    TextField musicTextField = new TextField("1");
    @FXML
    AnchorPane SettingsAnchorPane;
    @FXML
    Slider musicSlider;
    @FXML
    Slider soundSlider;
    @FXML
    Slider gravitySlider;
    @FXML
    Slider frictionSlider;
    @FXML
    Button btnPlay;
    @FXML
    Button btnMenu;
    @FXML
    Button btnExit;

    @FXML
    public void initialize() {

        musicSlider.setShowTickMarks(true);
        musicSlider.setMajorTickUnit(5);
        musicSlider.setMinorTickCount(5);
        musicSlider.setShowTickLabels(true);
        musicSlider.setSnapToTicks(true);
        musicSlider.setValue(10);
        musicSlider.setMax(20);

        musicTextField.setText(String.valueOf(musicSlider.getValue()));
        musicSlider.valueProperty().addListener((observable, oldvalue, newvalue)->{
            musicTextField.setText(String.format("%.2f", newvalue.doubleValue()));
        });

        MusicCheckBox.selectedProperty().addListener(event -> {
            if (MusicCheckBox.isSelected()) {
                musicSlider.setValue(0);
            }
        });


        soundSlider.setShowTickMarks(true);
        soundSlider.setMajorTickUnit(5);
        soundSlider.setMinorTickCount(5);
        soundSlider.setShowTickLabels(true);
        soundSlider.setSnapToTicks(true);
        soundSlider.setValue(10);
        soundSlider.setMax(20);

        soundTextField.setText(String.valueOf(soundSlider.getValue()));
        soundSlider.valueProperty().addListener((observable, oldvalue, newvalue)->{
            soundTextField.setText(String.format("%.2f", newvalue.doubleValue()));
        });

        SoundCheckBox.selectedProperty().addListener(event -> {
            if (SoundCheckBox.isSelected()) {
                soundSlider.setValue(0);
            }
        });


        gravitySlider.setShowTickMarks(true);
        gravitySlider.setMajorTickUnit(5);
        gravitySlider.setMinorTickCount(5);
        gravitySlider.setShowTickLabels(true);
        gravitySlider.setSnapToTicks(true);
        gravitySlider.setValue(10);
        gravitySlider.setMax(20);

        gravityTextField.setText(String.valueOf(gravitySlider.getValue()));
        gravitySlider.valueProperty().addListener((observable, oldValue, newValue) ->
                gravityTextField.setText(String.format("%.2f", newValue.doubleValue())));

        frictionSlider.setShowTickMarks(true);
        frictionSlider.setMajorTickUnit(5);
        frictionSlider.setMinorTickCount(5);
        frictionSlider.setShowTickLabels(true);
        frictionSlider.setSnapToTicks(true);
        frictionSlider.setValue(10);
        frictionSlider.setMax(20);

        frictionTextField.setText(String.valueOf(gravitySlider.getValue()));
        frictionSlider.valueProperty().addListener((observable, oldvalue, newvalue)->{
            frictionTextField.setText(String.format("%.2f", newvalue.doubleValue()));
        });
    }

    @FXML
    private void handleBtnPlay() throws IOException {
        logger.info("Stage is changed");
        Stage currentStage = (Stage) btnPlay.getScene().getWindow();
        MainApp mainApp = new MainApp();
        FXMLLoader loader = mainApp.loadFXML("/fxml/TetrisScene.fxml", new FXMLPlayController());
        currentStage.setScene(new Scene(loader.load()));

    }

    @FXML
    private void handleBtnMenu() throws IOException {
        logger.info("Menu button clicked");
        Stage currentStage = (Stage) btnPlay.getScene().getWindow();
        MainApp mainApp = new MainApp();
        FXMLLoader loader = mainApp.loadFXML("/fxml/IntroScene.fxml", new FXMLMainAppController());
        Scene scene = new Scene(loader.load());
        currentStage.setScene(scene);

    }

    @FXML
    private void handleBtnExit() throws IOException {
        logger.info("Quit Button Clicked");
        Stage currentStage = (Stage) btnPlay.getScene().getWindow();
        currentStage.close();
    }




    @FXML
    private void CursorChange(){
        SettingsAnchorPane.setCursor(Cursor.HAND);
    }
    @FXML
    private void CursorChangeExit(){
        SettingsAnchorPane.setCursor(Cursor.DEFAULT);
    }
}
