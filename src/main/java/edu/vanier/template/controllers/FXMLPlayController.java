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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node; //Added
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
        rand += 1;
        rand %= 7;
        System.out.println(rand);

        switch (rand) {
            case 0:
                physics.addTetrisShape('I', new TetrisBlock(10, 10, Color.RED), 0, 150, lbScore);
                setInvisible();
                GroupLBlock1.setVisible(true);
                break;
            case 1:
                physics.addTetrisShape('L', new TetrisBlock(10, 10, Color.RED), 0, 150, lbScore);
                setInvisible();
                GroupJBlock.setVisible(true);
                break;
            case 2:
                physics.addTetrisShape('J', new TetrisBlock(10, 10, Color.RED), 0, 150, lbScore);
                setInvisible();
                GroupTBlock.setVisible(true);
                break;
            case 3:
                physics.addTetrisShape('T', new TetrisBlock(10, 10, Color.RED), 0, 150, lbScore);
                setInvisible();
                GroupSquareBlock.setVisible(true);
                break;
            case 4:
                physics.addTetrisShape('O', new TetrisBlock(10, 10, Color.RED), 0, 150, lbScore);
                setInvisible();
                GroupSBlock1.setVisible(true);
                break;
            case 5:
                physics.addTetrisShape('S', new TetrisBlock(10, 10, Color.RED), 0, 150, lbScore);
                setInvisible();
                GroupZBlock.setVisible(true);
                break;
            case 6:
                physics.addTetrisShape('Z', new TetrisBlock(10, 10, Color.RED), 0, 150, lbScore);
                setInvisible();
                GroupLineBlock1.setVisible(true);
                break;
            default:
                System.out.println("error");
        }

        for (int i = 5; i < physics.getBodies().size(); i += 4) {
            ArrayList<Rectangle> blocks = new ArrayList<>(physics.getBlocks().subList(i, i + 4));
            ArrayList<Body> bodies = new ArrayList<>(physics.getBodies().subList(i, i + 4));

            moveBlock(blocks, bodies);
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
        physics.startPhysics();
        lbScore.setText("0000");
        music.soundPlay();
        physics.removeAll();
        for (int i = 0; i < 5; i++) {
            physics.addGround(new TetrisGround(60, 40, Color.GREEN), 0, -150);

        }

    }

    @FXML
    private void handleBtnGraphics() {
        logger.info("Graphs button clicked");
        music.soundPlay();
        Graph1 testGraph = new Graph1();

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

    public void moveBlock(ArrayList<Rectangle> blocks, ArrayList<Body> bodies) {

        double paneWidth = 400;
        double paneHeight = 380;
        double paneWidthHalf = paneWidth / 2;
        double paneHeightHalf = paneHeight / 2;

        for (int i = 0; i < blocks.size(); i++) {
            Rectangle block = blocks.get(i);
            Body[] relevantBodies = new Body[4];
            Vectors2D[] differences = new Vectors2D[3];

            relevantBodies[0] = bodies.get(i);
            for (int j = 1; j <= 3; j++) {
                int index = (i + j) % bodies.size();
                relevantBodies[j] = bodies.get(index);
                differences[j - 1] = relevantBodies[j].position.subtract(relevantBodies[0].position);
            }

            if (!relevantBodies[0].isStatic()) {
                block.setOnMousePressed(event -> {
                    block.getScene().setCursor(Cursor.CLOSED_HAND);
                });
            }

            block.setOnMouseDragged(event -> {
                handleMouseDragged(event, block, relevantBodies, differences, paneWidthHalf, paneHeightHalf);
            });

            block.setOnMouseReleased(event -> {
                block.getScene().setCursor(Cursor.OPEN_HAND);
            });
        }
    }

    private void handleMouseDragged(MouseEvent event, Rectangle block, Body[] bodies, Vectors2D[] differences, double paneWidthHalf, double paneHeightHalf) {
        bodies[0].position = new Vectors2D(event.getSceneX() - paneWidthHalf - block.getWidth() * 4, -event.getSceneY() + paneHeightHalf + block.getHeight() * 2);
        bodies[0].orientation = 0;
        for (int j = 1; j <= 3; j++) {
            bodies[j].position = new Vectors2D(bodies[0].position.x + differences[j - 1].x, bodies[0].position.y + differences[j - 1].y);
            bodies[j].orientation = 0;
        }

        event.consume();
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

    public void StopRotateBlock(TetrisBlock block) {

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
