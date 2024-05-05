package edu.vanier.template;

import edu.vanier.template.controllers.FXMLMainAppController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import library.dynamics.Body;
import library.dynamics.World;
import library.geometry.Polygon;
import library.math.Vectors2D;

public class MainApp extends Application {

    private final static Logger logger = LoggerFactory.getLogger(MainApp.class);

    @Override
    public void start(Stage primaryStage) {
        try {
            logger.info("Bootstrapping the application...");
            Pane root = loadFXML("/fxml/IntroScene.fxml", new FXMLMainAppController()).load();
            Scene scene = new Scene(root);
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
        //launch(args);
          // Create a new world with gravity
        World world = new World(new Vectors2D(0, -9.81));

        // Define the dimensions of the cube
        double width = 1.0;
        double height = 1.0;

        // Create a Box shape for the cube
        Polygon boxShape = new Polygon(width, height);

        // Create a Body and associate it with the Box shape
        Body cubeBody = new Body(boxShape, 0, 0); // Initial position (0, 0)

        // Set other properties of the body if needed, e.g., density, friction, restitution

        // Add the body to the world
        world.addBody(cubeBody);

        // Run the simulation for some time steps
        for (int i = 0; i < 100; i++) {
            // Update the world (simulate physics)
            world.step(1.0 / 60.0); // Assuming 60 frames per second

            // Access and print the position of the cube
            System.out.println("Cube position: " + cubeBody.position);
        }
    }

    //Loads the fxml
    public FXMLLoader loadFXML(String fxml, Object controller) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        loader.setController(controller);
        return loader;
    }
}
