package edu.vanier.template.controllers;

import edu.vanier.template.MainApp;
import edu.vanier.template.graphs.Graph1;
import edu.vanier.template.physicalLaws.Physics;
import edu.vanier.template.tetrisPieces.TetrisBlock;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    public void initialize() {
        physics = new Physics(pnBoard);
    }

    @FXML
    private void handleBtnPlay() {
        
        logger.info("Start button clicked");
       
        TetrisBlock block = new TetrisBlock((int) pnBoard.getWidth() / 2 - 15, 0,Color.RED );
        pnBoard.getChildren().add(block);
        

    }

    @FXML
    private void handleBtnStop() {
        logger.info("Stop button clicked");
        physics.stopGravity();
        // Implement the functionality to stop the game
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
}
