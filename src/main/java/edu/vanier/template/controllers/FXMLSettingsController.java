
package edu.vanier.template.controllers;


import edu.vanier.template.MainApp;
import javafx.scene.control.CheckBox;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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

    double volume;



    @FXML
    public void initialize() {

        setVolume1(0.5);
        musicSlider.setShowTickMarks(true);
        musicSlider.setMajorTickUnit(25);
        musicSlider.setMinorTickCount(25);
        musicSlider.setShowTickLabels(true);
        musicSlider.setSnapToTicks(true);
        musicSlider.setValue(volume * 100);
        musicSlider.setMax(100);
        MusicCheckBox.setSelected(true);

        musicTextField.setText(String.valueOf(musicSlider.getValue()));

        musicSlider.valueProperty().addListener((observable, oldvalue, newvalue)->{
            musicTextField.setText(String.format("%.2f", newvalue.doubleValue()));
            volume = (musicSlider.getValue() / 100) ;
            mediaPlayer.setVolume(volume);
            setVolume1(volume);
            System.out.println(getVolume());
            if(musicSlider.getValue() == 0){
                MusicCheckBox.setSelected(false);
            }
            else{
                MusicCheckBox.setSelected(true);
            }
        });

        MusicCheckBox.selectedProperty().addListener(event -> {
            if (MusicCheckBox.isSelected() == false) {
                musicSlider.setValue(0);
            }
        });

        slider(soundSlider,soundTextField,25,25,50,100,SoundCheckBox);
        slider(gravitySlider,gravityTextField,5,5,10,20);
        slider(frictionSlider,frictionTextField,5,5,10,20);
        musicPlay();
    }

    @FXML
    private void handleBtnPlay() throws IOException {
        logger.info("Stage is changed");
        soundPlay();
        Stage currentStage = (Stage) btnPlay.getScene().getWindow();
        MainApp mainApp = new MainApp();
        FXMLLoader loader = mainApp.loadFXML("/fxml/TetrisScene.fxml", new FXMLPlayController());
        currentStage.setScene(new Scene(loader.load()));
        musicStop();
    }

    @FXML
    private void handleBtnMenu() throws IOException {
        logger.info("Menu button clicked");
        soundPlay();
        Stage currentStage = (Stage) btnPlay.getScene().getWindow();
        MainApp mainApp = new MainApp();
        FXMLLoader loader = mainApp.loadFXML("/fxml/IntroScene.fxml", new FXMLMainAppController());
        Scene scene = new Scene(loader.load());
        currentStage.setScene(scene);
        musicStop();
    }

    @FXML
    private void handleBtnExit() throws IOException {
        logger.info("Quit Button Clicked");
        soundPlay();
        Stage currentStage = (Stage) btnPlay.getScene().getWindow();
        currentStage.close();
    }

    MediaPlayer mediaPlayer;

    public void musicStop(){
        mediaPlayer.stop();
    }

    public void musicPlay(){
        String a = "Original Tetris theme (Tetris Soundtrack).mp3";
        Media media = new Media(Paths.get(a).toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    mediaPlayer.setOnEndOfMedia(new Runnable() {
       public void run() {
         a.seek(Duration.ZERO);
       }
        });
        // mediaPlayer.setVolume(musicSlider.getValue()/100);
        mediaPlayer.play();

    }

    MediaPlayer mediaPlayer2;

    public void soundPlay(){
        String a = "mixkit-interface-click-1126.mp3";
        Media media = new Media(Paths.get(a).toUri().toString());
        mediaPlayer2 = new MediaPlayer(media);
        mediaPlayer2.play();
    }



    public void setVolume1(double volume){
        this.volume = volume;
    }

    public double getVolume(){
        return musicSlider.getValue() /100;
    }

    private void slider(Slider frictionSlider, TextField frictionTextField, int MajorTick, int MinorTick, int initialValue, int MaxValue){
        frictionSlider.setShowTickMarks(true);
        frictionSlider.setMajorTickUnit(MajorTick);
        frictionSlider.setMinorTickCount(MinorTick);
        frictionSlider.setShowTickLabels(true);
        frictionSlider.setSnapToTicks(true);
        frictionSlider.setValue(initialValue);
        frictionSlider.setMax(MaxValue);

        frictionTextField.setText(String.valueOf(frictionSlider.getValue()));
        frictionSlider.valueProperty().addListener((observable, oldvalue, newvalue)->{
            frictionTextField.setText(String.format("%.2f", newvalue.doubleValue()));
        });
    }

    private void slider(Slider frictionSlider, TextField frictionTextField, int MajorTick, int MinorTick, int initialValue, int MaxValue, CheckBox SoundCheckBox){
        frictionSlider.setShowTickMarks(true);
        frictionSlider.setMajorTickUnit(MajorTick);
        frictionSlider.setMinorTickCount(MinorTick);
        frictionSlider.setShowTickLabels(true);
        frictionSlider.setSnapToTicks(true);
        frictionSlider.setValue(initialValue);
        frictionSlider.setMax(MaxValue);
        SoundCheckBox.setSelected(true);

        frictionTextField.setText(String.valueOf(frictionSlider.getValue()));
        frictionSlider.valueProperty().addListener((observable, oldvalue, newvalue)->{
            frictionTextField.setText(String.format("%.2f", newvalue.doubleValue()));
            if(frictionSlider.getValue() == 0){
                SoundCheckBox.setSelected(false);
            }
            else{
                SoundCheckBox.setSelected(true);
            }
        });

        SoundCheckBox.selectedProperty().addListener(event -> {
            if (SoundCheckBox.isSelected() == false) {
                frictionSlider.setValue(0);
            }
        });
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


