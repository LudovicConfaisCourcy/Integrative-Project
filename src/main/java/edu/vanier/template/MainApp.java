package edu.vanier.template;

import edu.vanier.template.controllers.FXMLMainAppController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a JavaFX project template to be used for creating GUI applications.
 * The JavaFX GUI framework (version: 20.0.2) is linked to this project in the
 * build.gradle file.
 * @link: https://openjfx.io/javadoc/20/
 * @see: /Build Scripts/build.gradle
 * @author Sleiman Rabah.
 */
public class MainApp extends Application {

    private final static Logger logger = LoggerFactory.getLogger(MainApp.class);

    @Override
    public void start(Stage primaryStage) {
        try {
            logger.info("Bootstrapping the application...");
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/IntroScene.fxml"));
            loader.setController(new FXMLMainAppController());
            Pane root = loader.load();
        
            Scene scene = new Scene(root,600, 400);
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
        System.out.println("Hello");
        launch(args);
    }
}
