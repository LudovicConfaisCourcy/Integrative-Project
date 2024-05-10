package edu.vanier.template.controllers;

import edu.vanier.template.MainApp;
import edu.vanier.template.graphs.Graph1;
import edu.vanier.template.physicalLaws.Physics;
import edu.vanier.template.tetrisPieces.BlockState;
import edu.vanier.template.tetrisPieces.TetrisBlock;
import edu.vanier.template.tetrisPieces.TetrisGround;
import java.beans.XMLDecoder; //Added
import java.beans.XMLEncoder; //Added
import java.io.BufferedInputStream; //Added
import java.io.BufferedOutputStream; //Added
import java.io.IOException;
import java.nio.file.Files; //Added
import java.nio.file.Paths; //Added
import javafx.event.EventHandler; 
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node; //Added
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        });*/

        music.musicPlay();


    }


    @FXML
    private void handleBtnPlay() throws IOException {
    
        logger.info("Start button clicked");
        music.soundPlay();
        physics.startPhysics();
        BlockState initialState = new BlockState((int) pnBoard.getWidth() / 2 - 15, 0, 0, 0, 0, 0);
        TetrisBlock block = new TetrisBlock(initialState, Color.RED);
        pnBoard.getChildren().add(block);
        CursorChangeBlock(block);
        MoveBlock(block);
        
        Stage gameStage = (Stage) btnPlay.getScene().getWindow();
        
        EventHandler start_movement = (EventHandler<KeyEvent>) (KeyEvent event) -> {
            //block rotation
            if(KeyCode.F == event.getCode()){
               physics.stopPhysics();
               RotateBlock45(block);
            }
            if(KeyCode.G == event.getCode()){
               physics.stopPhysics();
               RotateBlock90(block);
            }
            //block movement
            if(KeyCode.A == event.getCode()){
               block.getCurrentState().setPosX(block.getCurrentState().getPosX() - 10);
            }
            if(KeyCode.S == event.getCode()){
               block.getCurrentState().setPosY(block.getCurrentState().getPosY() + 10);
            }
            if(KeyCode.D == event.getCode()){
               block.getCurrentState().setPosX(block.getCurrentState().getPosX() + 10);
            }
        };
        gameStage.getScene().setOnKeyPressed(start_movement);
        
        EventHandler stop_movement = (EventHandler<KeyEvent>) (KeyEvent event) -> {
            //block rotation
            if(KeyCode.F == event.getCode()){
               physics.startPhysics();
               StopRotateBlock(block);
            }
            if(KeyCode.G == event.getCode()){
               physics.startPhysics();
               StopRotateBlock(block);
            }
        };
        gameStage.getScene().setOnKeyReleased(stop_movement);
        
        //Load
        //load();  
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
        physics.removeAllBlocks();

        pnBoard.getChildren().clear();

    }

    @FXML
    private void handleBtnGraphics() {
        logger.info("Graphs button clicked");
        music.soundPlay();
        BlockState initialState = new BlockState((int) pnBoard.getWidth() / 2 - 15, 0, 0, 0, 0, 0);
        TetrisBlock block = new TetrisBlock(initialState, Color.RED);
        Graph1 testGraph = new Graph1 (block, initialState);

    }

    @FXML
    private void handleBtnMenu() throws IOException {
        logger.info("Menu button clicked");
        music.soundPlay();
        physics.removeAllBlocks();

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
        physics.removeAllBlocks();

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
            // Start physics simulation
        });
    }
    
    public void RotateBlock45(TetrisBlock block) {
    
    block.setOnMousePressed(event -> {
        block.setRotate(block.getRotate() + 45);
    });
    
    block.setOnScroll(event -> {
        block.setRotate(block.getRotate() + 45);
    });
    
    }
    
    public void RotateBlock90(TetrisBlock block) {
    
    block.setOnMousePressed(event -> {
        block.setRotate(block.getRotate() + 90);
    });
    
    block.setOnScroll(event -> {
        block.setRotate(block.getRotate() + 90);
    });
    
    }
       
    public void StopRotateBlock (TetrisBlock block) {
    
    block.setOnMousePressed(event -> {
        block.setRotate(block.getRotate());
    });
   
    block.setOnScroll(event -> {
        block.setRotate(block.getRotate());
    });
    }
    
    //Help using "https://stackoverflow.com/questions/64824378/save-load-a-pane-drawing-on-javafx"
    private static final java.nio.file.Path SAVE_FILE_LOCATION = Paths.get(System.getProperty("user.home"), "pnBoard.xml");
    
    public void save() throws IOException {
        try (XMLEncoder encoder = new XMLEncoder( new BufferedOutputStream(Files.newOutputStream(SAVE_FILE_LOCATION)))) {

            encoder.setExceptionListener(e -> {
                throw new RuntimeException(e);
            });

            encoder.writeObject(pnBoard.getChildren().toArray(new Node[0]));
        }
    }
    public void load() throws IOException {
        try (XMLDecoder decoder = new XMLDecoder( new BufferedInputStream(Files.newInputStream(SAVE_FILE_LOCATION)))) {

        decoder.setExceptionListener(e -> {
            throw new RuntimeException(e);
        });

        pnBoard.getChildren().setAll((Node[]) decoder.readObject());
    }
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
