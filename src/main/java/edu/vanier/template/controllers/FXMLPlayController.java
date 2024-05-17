package edu.vanier.template.controllers;

import library.math.Vectors2D;
import edu.vanier.template.MainApp;
import edu.vanier.template.graphs.Graph;
import edu.vanier.template.physicalLaws.GameLogic;
import edu.vanier.template.physicalLaws.Physics;
import edu.vanier.template.tetrisPieces.TetrisBlock;
import edu.vanier.template.tetrisPieces.TetrisGround;
import java.beans.XMLDecoder; //Added
import java.beans.XMLEncoder; //Added
import java.io.BufferedInputStream; //Added
import java.io.BufferedOutputStream; //Added
import java.io.IOException;
import java.util.Random;
import java.nio.file.Files; //Added
import java.nio.file.Paths; //Added
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node; //Added
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import library.dynamics.Body;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anton Lisunov
 */
public class FXMLPlayController {

    private final static Logger logger = LoggerFactory.getLogger(FXMLMainAppController.class);
    Random random = new Random();
    private int rand = random.nextInt(6);

    Physics physics;
    GameLogic gameLogic;
    Body body;
    FXMLSettingsController music = new FXMLSettingsController();

    @FXML
    Button btnPlay;
    @FXML
    Button btnStop;
    @FXML
    Button btnRestart;
    @FXML
    Button btnMenu;
    @FXML
    Button btnHelp;
    @FXML
    public Label lbScore;
    @FXML
    Pane pnNext;
    @FXML
    Pane pnBoard;
    @FXML
    BorderPane BorderPane;

    @FXML
    public Group GroupLBlock1;
    @FXML
    public Group GroupSquareBlock;
    @FXML
    public Group GroupSBlock1;
    @FXML
    public Group GroupLineBlock1;
    @FXML
    public Group GroupTBlock;
    @FXML
    public Group GroupZBlock;
    @FXML
    public Group GroupJBlock;
    @FXML
    Label PlayScreenLabel;

    @FXML
    public void initialize() {

        physics = new Physics(pnBoard);
        gameLogic = new GameLogic(this, physics);

        for (int i = 0; i < 5; i++) {
            physics.addGround(new TetrisGround(60, 40, Color.GREEN), 0, -150);
        }

        setInvisible();
        music.musicPlay();

    }

    public void setInvisible() {
        GroupSquareBlock.setVisible(false);
        GroupLBlock1.setVisible(false);
        GroupSBlock1.setVisible(false);
        GroupTBlock.setVisible(false);
        GroupLineBlock1.setVisible(false);
        GroupZBlock.setVisible(false);
        GroupJBlock.setVisible(false);
    }

    @FXML
    private void handleBtnPlay() throws IOException {

        logger.info("Start button clicked");
        PlayScreenLabel.setText("Click Stop to Pause the Game");
        physics.startPhysics();

        if (physics.GameLostVerifier() == true) {
            PlayScreenLabel.setText("!!You Lost!! Click Play to ReStart a Game");
        }
        music.soundPlay();
        gameLogic.startGame();

    }

    @FXML
    private void handleBtnStop() {
        logger.info("Stop button clicked");
        PlayScreenLabel.setText("Click Play to UnPause the Game");
        music.soundPlay();
        physics.stopPhysics();
        gameLogic.stopGame();
        gameLogic.closeGraph();


    }

    @FXML
    private void handleBtnRestart() {
        logger.info("Restart button clicked");
        PlayScreenLabel.setText("Click Play to ReStart the Game");
        physics.startPhysics();
        lbScore.setText("0000");
        music.soundPlay();
        physics.removeAll();
        for (int i = 0; i < 4; i++) {
            physics.addGround(new TetrisGround(60, 40, Color.GREEN), 0, -150);

        }
        gameLogic.startGame();

    }

    @FXML
    private void handleBtnGraphics() {
        logger.info("Graphs button clicked");
        music.soundPlay();
        gameLogic.summonGraph();

    }

    @FXML
    private void handleBtnMenu() throws IOException {
        logger.info("Menu button clicked");
        music.soundPlay();
        physics.removeAll();

        Stage currentStage = (Stage) btnPlay.getScene().getWindow();
        MainApp mainApp = new MainApp();
        FXMLLoader loader = mainApp.loadFXML("/fxml/IntroScene.fxml", new FXMLMainAppController());
        Scene scene = new Scene(loader.load());
        currentStage.setScene(scene);
        music.musicStop();

        //Save
        save();

    }

    @FXML
    private void handleBtnSettings() throws IOException {
        logger.info("Settings button clicked");
        music.soundPlay();
        pnBoard.getChildren().clear();

        Stage currentStage = (Stage) btnPlay.getScene().getWindow();
        MainApp mainApp = new MainApp();
        FXMLLoader loader = mainApp.loadFXML("/fxml/SettingsScene.fxml", new FXMLSettingsController());
        Scene scene = new Scene(loader.load());
        currentStage.setScene(scene);
        music.musicStop();

    }

    @FXML
    private void handleBtnHelp() {
        logger.info("Help button clicked");
        music.soundPlay();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HelpScene.fxml"));
            Stage helpStage = new Stage();
            helpStage.setScene(new Scene(loader.load()));
            helpStage.setTitle("Help");
            helpStage.show();
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    //Help using "https://stackoverflow.com/questions/64824378/save-load-a-pane-drawing-on-javafx"
    private static final java.nio.file.Path SAVE_FILE_LOCATION = Paths.get(System.getProperty("user.home"), "pnBoard.xml");

    public void save() throws IOException {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(Files.newOutputStream(SAVE_FILE_LOCATION)))) {

            encoder.setExceptionListener(e -> {
                throw new RuntimeException(e);
            });

            encoder.writeObject(pnBoard.getChildren().toArray(new Node[0]));
        }
    }

    public void load() throws IOException {
        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(Files.newInputStream(SAVE_FILE_LOCATION)))) {

            decoder.setExceptionListener(e -> {
                throw new RuntimeException(e);
            });

            pnBoard.getChildren().setAll((Node[]) decoder.readObject());
        }
    }

    @FXML
    private void CursorChange() {
        BorderPane.setCursor(Cursor.HAND);
    }

    @FXML
    private void CursorChangeExit() {
        BorderPane.setCursor(Cursor.DEFAULT);
    }

    public void CursorChangeBlock(TetrisBlock block) {

        block.setOnMouseEntered(event -> {
            BorderPane.setCursor(Cursor.OPEN_HAND);
        });

        block.setOnMouseExited(event -> {
            BorderPane.setCursor(Cursor.DEFAULT);
        });
    }


}
