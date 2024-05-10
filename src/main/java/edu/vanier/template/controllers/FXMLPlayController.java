package edu.vanier.template.controllers;

import edu.vanier.template.MainApp;
import edu.vanier.template.graphs.Graph1;
import edu.vanier.template.physicalLaws.Physics;
import edu.vanier.template.tetrisPieces.TetrisBlock;
import edu.vanier.template.tetrisPieces.TetrisGround;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import library.dynamics.Body;
import library.math.Vectors2D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Controller class for the Play scene in the Tetris game.
 * Handles user interactions and game logic.
 * @author Anton Lisunov, Ludovic Confiais-Courcy
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

    /**
     * Initializes the controller.
     */
    @FXML
    public void initialize() {
        physics = new Physics(pnBoard);
        for (int i = 0; i < 5; i++) {
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
        /* physics.addTetrisShape('L', new TetrisBlock(10, 10, Color.RED), 0, 250);
        physics.addTetrisShape('J', new TetrisBlock(10, 10, Color.RED), 0, 350);
        physics.addTetrisShape('T', new TetrisBlock(10, 10, Color.RED), 0, 450);
        physics.addTetrisShape('O', new TetrisBlock(10, 10, Color.RED), 0, 550);
        physics.addTetrisShape('S', new TetrisBlock(10, 10, Color.RED), 0, 650);
        physics.addTetrisShape('Z', new TetrisBlock(10, 10, Color.RED), 0, 750);*/

        System.out.println(physics.getBlocks().size());

        for (int i = 5; i < physics.getBodies().size(); i += 4) {
            ArrayList<Rectangle> blocks = new ArrayList<>(physics.getBlocks().subList(i, i + 4));
            ArrayList<Body> bodies = new ArrayList<>(physics.getBodies().subList(i, i + 4));

            moveBlock(blocks, bodies);
        }
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

    public void moveBlock(ArrayList<Rectangle> blocks, ArrayList<Body> bodies) {
        double paneWidth = 400;
        double paneHeight = 380;

        for (int i = 0; i < blocks.size(); i++) {
            Rectangle block = blocks.get(i);
            Body body1 = bodies.get(i);
            Body body2 = bodies.get(Math.abs(i + 1 - 4));
            Body body3 = bodies.get(Math.abs(i + 2 - 4));
            Body body4 = bodies.get(Math.abs(i + 3 - 4));

            Vectors2D difference2 = body2.position.subtract(body1.position);
            Vectors2D difference3 = body3.position.subtract(body1.position);
            Vectors2D difference4 = body4.position.subtract(body1.position);

            if (!body1.isStatic()) {
                block.setOnMousePressed(event -> {
                    BorderPane.setCursor(Cursor.CLOSED_HAND);
                });
            }

            block.setOnMouseDragged(event -> {
                body1.position = new Vectors2D(event.getSceneX() - paneWidth / 2 - block.getWidth() * 4,
                        -event.getSceneY() + paneHeight / 2 + block.getHeight() * 2);

                body2.position = new Vectors2D(body1.position.x+difference2.x, body1.position.y+difference2.y);
                body3.position = new Vectors2D(body1.position.x+difference3.x, body1.position.y+difference3.y);
                body4.position = new Vectors2D(body1.position.x+difference4.x, body1.position.y+difference4.y);

                event.consume();

            });

            block.setOnMouseReleased(event -> {
                BorderPane.setCursor(Cursor.OPEN_HAND);
            });
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
