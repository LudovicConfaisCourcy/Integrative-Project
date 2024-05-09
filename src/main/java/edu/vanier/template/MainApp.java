package edu.vanier.template;

import edu.vanier.template.controllers.FXMLMainAppController;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp extends Application {

    private final static Logger logger = LoggerFactory.getLogger(MainApp.class);

    @Override
    public void start(Stage primaryStage) {
        try {
            logger.info("Bootstrapping the application...");
            Pane root = loadFXML("/fxml/IntroScene.fxml", new FXMLMainAppController()).load();
            Scene scene = new Scene(root);
            
            Media media = new Media(getClass().getResource("/music/MenuTheme.wav").toExternalForm());
            MediaPlayer player = new MediaPlayer(media);
            player.setAutoPlay(true);
            player.setCycleCount(MediaPlayer.INDEFINITE);
            
            primaryStage.setScene(scene);
            primaryStage.sizeToScene();
            primaryStage.setAlwaysOnTop(true);
            primaryStage.show();
            primaryStage.setAlwaysOnTop(false);
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

    //Loads the fxml
    public FXMLLoader loadFXML(String fxml, Object controller) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        loader.setController(controller);
        return loader;
    }
}
