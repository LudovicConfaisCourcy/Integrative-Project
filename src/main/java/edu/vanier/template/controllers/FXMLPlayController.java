package edu.vanier.template.controllers;

import edu.vanier.template.MainApp;
import edu.vanier.template.graphs.Graph1;
import edu.vanier.template.physicalLaws.Physics;
import edu.vanier.template.tetrisPieces.BlockState;
import edu.vanier.template.tetrisPieces.TetrisBlock;
import edu.vanier.template.tetrisPieces.TetrisGround;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import library.dynamics.Body;
import library.math.Vectors2D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anton Lisunov
 */
public class FXMLPlayController {

    private final static Logger logger = LoggerFactory.getLogger(FXMLMainAppController.class);
    Physics physics;

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
    Label lbScore;
    @FXML
    Pane pnNext;
    @FXML
    Pane pnBoard;
    @FXML
    BorderPane BorderPane;

    @FXML
    public void initialize() {
        physics = new Physics(pnBoard);
        for (int i = 0; i < 4; i++) {
            physics.addGround(new TetrisGround(60, 40, Color.GREEN), 0, -150);

        }


        /*pnBoard.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            ground.setWidth(newWidth.doubleValue() * 0.5);
            ground.setTranslateX(newWidth.doubleValue() * 0.25);
        });

        pnBoard.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            ground.setHeight(newHeight.doubleValue() * 0.3);
            ground.setTranslateY(newHeight.doubleValue() * 0.9);
        });*/
        music.musicPlay();

    }

    @FXML
    private void handleBtnPlay() {

        logger.info("Start button clicked");
        music.soundPlay();
        physics.startPhysics();
        physics.addTetrisShape('I', new TetrisBlock(10, 10, Color.RED), 0, 150);
        physics.addTetrisShape('L', new TetrisBlock(10, 10, Color.RED), 0, 250);
        physics.addTetrisShape('J', new TetrisBlock(10, 10, Color.RED), 0, 350);
        physics.addTetrisShape('T', new TetrisBlock(10, 10, Color.RED), 0, 450);
        physics.addTetrisShape('O', new TetrisBlock(10, 10, Color.RED), 0, 550);
        physics.addTetrisShape('S', new TetrisBlock(10, 10, Color.RED), 0, 650);
        physics.addTetrisShape('Z', new TetrisBlock(10, 10, Color.RED), 0, 750);

    }

    @FXML
    private void handleBtnStop() {
        logger.info("Stop button clicked");
        music.soundPlay();
        physics.stopPhysics();
    }

    @FXML
    private void handleBtnRestart() {
        logger.info("Restart button clicked");
        music.soundPlay();
        pnBoard.getChildren().clear();

    }

    @FXML
    private void handleBtnGraphics() {
        logger.info("Graphs button clicked");
        music.soundPlay();
        /* BlockState initialState = new BlockState((int) pnBoard.getWidth() / 2 - 15, 0, 0, 0, 0, 0);
        TetrisBlock block = new TetrisBlock(initialState, Color.RED);
        Graph1 testGraph = new Graph1(block, initialState);*/

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

    public void moveBlock(TetrisBlock block, Body body) {
        block.setOnMousePressed(event -> {
            // Set cursor to closed hand while dragging
            BorderPane.setCursor(Cursor.CLOSED_HAND);
            // Store initial position of the mouse
            block.setUserData(new Vectors2D(event.getSceneX(), event.getSceneY()));
            // Stop physics simulation while dragging
            physics.stopPhysics();
        });

        block.setOnMouseDragged(event -> {
            // Get the stored initial position of the mouse
            Vectors2D initialPosition = (Vectors2D) block.getUserData();
            // Calculate the delta movement
            double deltaX = event.getSceneX() - initialPosition.x;
            double deltaY = event.getSceneY() - initialPosition.y;
            // Calculate the new position of the body
            Vectors2D newPosition = new Vectors2D(body.position.x + deltaX, body.position.y + deltaY);
            // Set the new position of the body
            body.position  = newPosition;
            // Update the stored initial position of the mouse for the next movement
            block.setUserData(new Vectors2D(event.getSceneX(), event.getSceneY()));
            // Consume the event
            event.consume();
        });

        block.setOnMouseReleased(event -> {
            // Set cursor to open hand after release
            BorderPane.setCursor(Cursor.OPEN_HAND);
            // Restart physics simulation after release
            physics.startPhysics();
        });
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
