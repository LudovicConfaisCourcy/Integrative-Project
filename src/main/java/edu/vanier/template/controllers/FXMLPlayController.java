package edu.vanier.template.controllers;
import javafx.scene.paint.Color;
import library.dynamics.Body;
import library.geometry.Polygon;
import library.joints.Joint;
import library.joints.JointToBody;
import library.math.Vectors2D;
import edu.vanier.template.MainApp;
import edu.vanier.template.graphs.Graph1;
import edu.vanier.template.tetrisPieces.TetrisShapes;
import edu.vanier.template.physicalLaws.Physics;
import edu.vanier.template.tetrisPieces.BlockState;
import edu.vanier.template.tetrisPieces.TetrisBlock;
import edu.vanier.template.tetrisPieces.TetrisGround;
import java.io.IOException;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import library.dynamics.Body;
import library.geometry.Polygon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anton Lisunov
 */
public class FXMLPlayController {

    private final static Logger logger = LoggerFactory.getLogger(FXMLMainAppController.class);
    Physics physics;
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
    Label lbScore;
    @FXML
    Pane pnNext;
    @FXML
    Pane pnBoard;
    @FXML
    BorderPane BorderPane;
    Random random = new Random();
    int rand = random.nextInt(6);
    @FXML
    Group GroupLBlock1;
    @FXML
    Group GroupSquareBlock;
    @FXML
    Group GroupSBlock1;
    @FXML
    Group GroupLineBlock1;
    @FXML
    Group GroupTBlock;
    @FXML
    Group GroupZBlock;
    @FXML
    Group GroupJBlock;
    @FXML
    Label PlayScreenLabel;
    double width;
    double height;
    @FXML
    public void initialize() {

        physics = new Physics(pnBoard);
        for (int i = 0; i < 1; i++) {
            physics.addGround(new TetrisGround(60, 40, Color.GREEN), 0, -150);
        }


        /*if(  this.position.x >= pnBoard.getWidth()  || pnBoard.getHeight() >= 100){
            System.out.println("yes");
        }*/
        /*pnBoard.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            ground.setWidth(newWidth.doubleValue() * 0.5);
            ground.setTranslateX(newWidth.doubleValue() * 0.25);
        });

        pnBoard.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            ground.setHeight(newHeight.doubleValue() * 0.3);
            ground.setTranslateY(newHeight.doubleValue() * 0.9);
        });*/
        setInvisible();
        music.musicPlay();

    }

    public void setInvisible(){
        GroupSquareBlock.setVisible(false);
        GroupLBlock1.setVisible(false);
        GroupSBlock1.setVisible(false);
        GroupTBlock.setVisible(false);
        GroupLineBlock1.setVisible(false);
        GroupZBlock.setVisible(false);
        GroupJBlock.setVisible(false);
    }

    @FXML
    private void handleBtnPlay() {
        logger.info("Start button clicked");
        PlayScreenLabel.setText("Click Stop to Pause the Game");
        physics.startPhysics();
        if(physics.GameLostVerifier() == true){
            PlayScreenLabel.setText("!!You Lost!! Click Play to ReStart a Game");
        }
        music.soundPlay();
        rand += 1;
        rand %= 7;
        System.out.println(rand);

        switch(rand){
            case 0 : physics.addTetrisShape('I', new TetrisBlock(10, 10, Color.RED), 0, 150, lbScore);
                setInvisible();
                GroupLBlock1.setVisible(true);
                break;
            case 1 : physics.addTetrisShape('L', new TetrisBlock(10, 10, Color.RED), 0, 150, lbScore);
                setInvisible();
                GroupJBlock.setVisible(true);
                break;
            case 2 : physics.addTetrisShape('J', new TetrisBlock(10, 10, Color.RED), 0, 150,lbScore);
                setInvisible();
                GroupTBlock.setVisible(true);
                break;
            case 3 : physics.addTetrisShape('T', new TetrisBlock(10, 10, Color.RED), 0, 150,lbScore);
                setInvisible();
                GroupSquareBlock.setVisible(true);
                break;
            case 4 : physics.addTetrisShape('O', new TetrisBlock(10, 10, Color.RED), 0, 150,lbScore);
                setInvisible();
                GroupSBlock1.setVisible(true);
                break;
            case 5 : physics.addTetrisShape('S', new TetrisBlock(10, 10, Color.RED), 0, 150,lbScore);
                setInvisible();
                GroupZBlock.setVisible(true);
                break;
            case 6 : physics.addTetrisShape('Z', new TetrisBlock(10, 10, Color.RED), 0, 150,lbScore);
                setInvisible();
                GroupLineBlock1.setVisible(true);
                break;
            default : System.out.println("error");
        }

    }

    @FXML
    private void handleBtnStop() {
        logger.info("Stop button clicked");
        PlayScreenLabel.setText("Click Play to UnPause the Game");
        music.soundPlay();
        physics.stopPhysics();

    }

    @FXML
    private void handleBtnRestart() {
        logger.info("Restart button clicked");
        PlayScreenLabel.setText("Click Play to ReStart the Game");
        music.soundPlay();
        physics.removeAll();
        for (int i = 0; i < 1; i++) {
            physics.addGround(new TetrisGround(60, 40, Color.GREEN), 0, -150);

        }

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

    public void MoveBlock(TetrisBlock block) {

        /* block.setOnMousePressed(event -> {

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
         */
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
