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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
 *         Ludovic Confais Courcy
 */
public class FXMLPlayController {

    private final static Logger logger = LoggerFactory.getLogger(FXMLMainAppController.class);
    Physics physics;

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
    BorderPane TetrisBorderPane;
    
    
    @FXML
    public void initialize() {
        physics = new Physics(pnBoard);
        TetrisGround ground = new TetrisGround(0,0, 200, 100);
        System.out.println(pnBoard.getMaxHeight());
        pnBoard.getChildren().add(ground);
        
        pnBoard.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            ground.setWidth(newWidth.doubleValue() * 0.5);
            ground.setTranslateX(newWidth.doubleValue() * 0.25);
        });

        pnBoard.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            ground.setHeight(newHeight.doubleValue() * 0.3);
            ground.setTranslateY(newHeight.doubleValue() * 0.9);
        });
    }

    @FXML
    private void handleBtnPlay() {

        logger.info("Start button clicked");
        physics.startPhysics();
        BlockState initialState = new BlockState((int) pnBoard.getWidth() / 2 - 15, 0, 0, 0, 0, 0);
        TetrisBlock block = new TetrisBlock(initialState, Color.RED);
        pnBoard.getChildren().add(block);
        MoveBlock(block);
        CursorChangeBlock(block);
    }

    @FXML
    private void handleBtnStop() {
        logger.info("Stop button clicked");
        physics.stopPhysics();
    }

    @FXML
    private void handleBtnRestart() {
        logger.info("Restart button clicked");
        // Implement the functionality to restart the game
    }

    @FXML
    private void handleBtnGraphics() {
        logger.info("Graphs button clicked");
        Graph1 testGraph = new Graph1();

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
        block.setOnMousePressed(event
                -> {
            // Store the initial position when mouse is pressed
            block.getCurrentState().setPosX(block.getTranslateX());
            block.getCurrentState().setPosY(block.getTranslateY());
        }
        );

        block.setOnMouseDragged(event
                -> {
            block.getCurrentState().setAccX(0);
            block.getCurrentState().setAccY(0);
            block.getCurrentState().setSpeedX(0);
            block.getCurrentState().setSpeedY(0);
            block.setManaged(false);
            block.setTranslateX(event.getX() + block.getTranslateX() - block.getWidth() / 2);
            block.setTranslateY(event.getY() + block.getTranslateY() - block.getHeight() / 2);
            event.consume();
            physics.stopPhysics();
        }
        );

        block.setOnMouseReleased(event
                -> {
            double posX = block.getTranslateX();
            double posY = block.getTranslateY();

            // Calculate velocity based on the change in position
            double speedX = (posX - block.getCurrentState().getPosX()) / 0.16;
            double speedY = (posY - block.getCurrentState().getPosY()) / 0.16;

            // Store the current position, velocity, and acceleration as previous values
            block.getCurrentState().setPosX(posX);
            block.getCurrentState().setPosY(posY);
            block.getCurrentState().setSpeedX(speedX);
            block.getCurrentState().setSpeedY(speedY);

            // Start physics simulation
            physics.startPhysics();
        }
        );
    }

    /*
     Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Block Information");
            alert.setHeaderText(null);
            alert.setContentText("Position: (" + posX + ", " + posY + ")\n"
                    + "Speed: (" + speedX + ", " + speedY + ")\n"
                    + "Acceleration: (" + accelerationX + ", " + accelerationY + ")");
            alert.showAndWait();
     */


    @FXML
    private void CursorChange(){
        BorderPane.setCursor(Cursor.HAND);
    }
    @FXML
    private void CursorChangeExit(){
        BorderPane.setCursor(Cursor.DEFAULT);
    }
    
    public void CursorChangeBlock(TetrisBlock block){
          
    block.setOnMouseEntered(event -> {
        BorderPane.setCursor(Cursor.OPEN_HAND);
        });
    
    block.setOnMousePressed(event -> {
        BorderPane.setCursor(Cursor.CLOSED_HAND);
        });
    
    block.setOnMouseReleased(event -> {
        BorderPane.setCursor(Cursor.OPEN_HAND);
        });
    
    block.setOnMouseExited(event -> {   
        BorderPane.setCursor(Cursor.DEFAULT);
    });
}
}