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
        TetrisGround ground = new TetrisGround(0, 0, 200, 100);
        //pnBoard.getChildren().add(ground);

        pnBoard.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            ground.setWidth(newWidth.doubleValue() * 0.5);
            ground.setTranslateX(newWidth.doubleValue() * 0.25);
        });

        pnBoard.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            ground.setHeight(newHeight.doubleValue() * 0.3);
            ground.setTranslateY(newHeight.doubleValue() * 0.9);
        });
        music.musicPlay();
        music.checkMute();
    }

    @FXML
    private void handleBtnPlay() {

        logger.info("Start button clicked");
        physics.startPhysics();
        //physics.addTetrisPeace();

    }

    @FXML
    private void handleBtnStop() {
        logger.info("Stop button clicked");
        //physics.stopPhysics();
    }

    @FXML
    private void handleBtnRestart() {
        logger.info("Restart button clicked");
        pnBoard.getChildren().clear();

    }

    @FXML
    private void handleBtnGraphics() {
        logger.info("Graphs button clicked");
        BlockState initialState = new BlockState((int) pnBoard.getWidth() / 2 - 15, 0, 0, 0, 0, 0);
        TetrisBlock block = new TetrisBlock(initialState, Color.RED);
        Graph1 testGraph = new Graph1(block, initialState);

    }

    @FXML
    private void handleBtnMenu() throws IOException {
        logger.info("Menu button clicked");

        physics.removeAllBlocks();

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

    public void MoveBlock(TetrisBlock block) {

        block.setOnMousePressed(event -> {

            BlockState state = new BlockState(block.getCurrentState().getPosX(), block.getCurrentState().getPosY(), block.getCurrentState().getSpeedX(), block.getCurrentState().getSpeedY(), 0, 0);
            block.addBlockState(state);
            BorderPane.setCursor(Cursor.CLOSED_HAND);
        });

        block.setOnMouseDragged(event -> {

            block.setManaged(false);
            BlockState state = new BlockState(event.getX() + block.getCurrentState().getPosX() - block.getWidth() / 2, event.getY() + block.getCurrentState().getPosY() - block.getHeight() / 2, block.getCurrentState().getSpeedX(), block.getCurrentState().getSpeedY(), 0, 0);
            block.addBlockState(state);
            event.consume();
            physics.stopPhysics();
        });

        block.setOnMouseReleased(event -> {

            // Calculate velocity based on the change in position
            double speedX = (block.getCurrentState().getPosX() - block.getPreviousState().getPosX()) / 0.16;
            double speedY = (block.getCurrentState().getPosY() - block.getPreviousState().getPosY()) / 0.16;
            BlockState state = new BlockState(block.getCurrentState().getPosX(), block.getCurrentState().getPosY(), speedX, speedY, 0, 0);
            block.addBlockState(state);
            physics.startPhysics();
            BorderPane.setCursor(Cursor.OPEN_HAND);
            // Start physics simulation
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
