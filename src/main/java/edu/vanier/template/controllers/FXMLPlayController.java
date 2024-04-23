package edu.vanier.template.controllers;

import edu.vanier.template.MainApp;
import edu.vanier.template.graphs.Graph1;
import edu.vanier.template.physicalLaws.Physics;
import edu.vanier.template.tetrisPieces.BlockState;
import edu.vanier.template.tetrisPieces.TetrisBlock;
import edu.vanier.template.tetrisPieces.TetrisGround;
import java.io.IOException;
import javafx.event.EventHandler; //Added
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode; //Added
import javafx.scene.input.KeyEvent; //Added
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
        /*
        BlockState groundState = new BlockState((int) 0,0,0,0,0,0);
        TetrisBlock ground = new TetrisBlock(groundState,Color.GREEN);
        
        pnBoard.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            ground.setWidth(newWidth.doubleValue() * 0.5);
            ground.setTranslateX(newWidth.doubleValue() * 0.25);
        });

        pnBoard.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            ground.setHeight(newHeight.doubleValue() * 0.3);
            ground.setTranslateY(newHeight.doubleValue() * 0.9);
        });
        
        pnBoard.getChildren().add(ground);
        */
        /*
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
        */
    }

    @FXML
    private void handleBtnPlay() {

        logger.info("Start button clicked");
        physics.startPhysics();
        BlockState initialState = new BlockState((int) pnBoard.getWidth() / 2 - 15, 0, 0, 0, 0, 0);
        TetrisBlock block = new TetrisBlock(initialState, Color.RED);
        pnBoard.getChildren().add(block);
        CursorChangeBlock(block);
        MoveBlock(block);
        
        Stage gameStage = (Stage) btnPlay.getScene().getWindow();
        
        EventHandler start_rotation = (EventHandler<KeyEvent>) (KeyEvent event) -> {
            if(KeyCode.R == event.getCode()){
             //logger.info("Rotation Activate");
               physics.stopPhysics();
               RotateBlock45(block);
            }
            if(KeyCode.E == event.getCode()){
               physics.stopPhysics();
               RotateBlock90(block);
            }
        };
        gameStage.getScene().setOnKeyPressed(start_rotation);
        
        EventHandler stop_rotation = (EventHandler<KeyEvent>) (KeyEvent event) -> {
            if(KeyCode.R == event.getCode()){
             //logger.info("Rotation Deactivate");
               physics.startPhysics();
               StopRotateBlock(block);
            }
            if(KeyCode.E == event.getCode()){
               physics.startPhysics();
               StopRotateBlock(block);
            }
        };
        gameStage.getScene().setOnKeyReleased(stop_rotation);

    }

    @FXML
    private void handleBtnStop() {
        logger.info("Stop button clicked");
        physics.stopPhysics();
    }

    @FXML
    private void handleBtnRestart() {
        logger.info("Restart button clicked");
        pnBoard.getChildren().clear();
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

        block.setOnMousePressed(event-> {
            
            BlockState state = new BlockState(block.getCurrentState().getPosX(), block.getCurrentState().getPosY(), block.getCurrentState().getSpeedX(), block.getCurrentState().getSpeedY(), 0, 0);
            block.addBlockState(state);
            BorderPane.setCursor(Cursor.CLOSED_HAND);
        });

        block.setOnMouseDragged(event-> {
            
            block.setManaged(false);
            BlockState state = new BlockState(event.getX() + block.getCurrentState().getPosX() - block.getWidth() / 2, event.getY() + block.getCurrentState().getPosY() - block.getHeight() / 2,block.getCurrentState().getSpeedX(), block.getCurrentState().getSpeedY(), 0, 0);
            block.addBlockState(state);
            event.consume();
            physics.stopPhysics();
        });

        block.setOnMouseReleased(event-> {

            // Calculate velocity based on the change in position
            double speedX = (block.getCurrentState().getPosX() - block.getPreviousState().getPosX()) / 0.16;
            double speedY = (block.getCurrentState().getPosY() - block.getPreviousState().getPosY()) / 0.16;
            BlockState state = new BlockState(block.getCurrentState().getPosX(), block.getCurrentState().getPosY(), speedX, speedY, 0, 0);
            block.addBlockState(state);
            physics.startPhysics();
            BorderPane.setCursor(Cursor.OPEN_HAND);           
            System.out.println(block.getCurrentState().getSpeedX());
            // Start physics simulation
        });
    }
    
    public void RotateBlock45(TetrisBlock block) {
  //logger.info("Activate Rotation");
    
    block.setOnMousePressed(event -> {
        block.setRotate(block.getRotate() + 45);
    });
    
    block.setOnScroll(event -> {
        block.setRotate(block.getRotate() + 45);
    });
    
    }
    
    public void RotateBlock90(TetrisBlock block) {
  //logger.info("Activate Rotation");
    
    block.setOnMousePressed(event -> {
        block.setRotate(block.getRotate() + 90);
    });
    
    block.setOnScroll(event -> {
        block.setRotate(block.getRotate() + 90);
    });
    
    }
       
    public void StopRotateBlock (TetrisBlock block) {
  //logger.info("Deactivate Rotation");
    
    block.setOnMousePressed(event -> {
        block.setRotate(block.getRotate());
    });
   
    block.setOnScroll(event -> {
        block.setRotate(block.getRotate());
    });
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
    
    block.setOnMouseExited(event -> {   
        BorderPane.setCursor(Cursor.DEFAULT);
    });
}
}
