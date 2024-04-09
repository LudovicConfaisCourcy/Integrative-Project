
package edu.vanier.template.controllers;


import edu.vanier.template.MainApp;
import java.io.IOException;
import java.util.logging.Level;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

   
public class FXMLCreditsController {

    private final static Logger logger = LoggerFactory.getLogger(FXMLMainAppController.class);

    @FXML
    Button btnCreators;
    @FXML
    Button btnInspirations;
    @FXML
    Button btnScience;
    @FXML
    Button btnDescription;
    
    @FXML
    private void handleBtnCreators() throws IOException {
    logger.info("Creators button clicked");  
    
    Stage stage = (Stage) btnCreators.getScene().getWindow();
    MainApp mainApp = new MainApp();
    FXMLLoader loader = mainApp.loadFXML("/fxml/CreatorsScene.fxml", new FXMLMainAppController());
    Scene scene = new Scene(loader.load());
    stage.setScene(scene);
    }
     
    @FXML
    private void handleBtnInspirations() throws IOException {
    logger.info("Inspirations button clicked"); 
    
    Stage stage = (Stage) btnCreators.getScene().getWindow();
    MainApp mainApp = new MainApp();
    FXMLLoader loader = mainApp.loadFXML("/fxml/InspirationsScene.fxml", new FXMLMainAppController());
    Scene scene = new Scene(loader.load());
    stage.setScene(scene);
    }
    
    @FXML
    private void handleBtnScience() throws IOException {
    logger.info("Science button clicked"); 
    
    Stage stage = (Stage) btnCreators.getScene().getWindow();
    MainApp mainApp = new MainApp();
    FXMLLoader loader = mainApp.loadFXML("/fxml/ScienceScene.fxml", new FXMLMainAppController());
    Scene scene = new Scene(loader.load());
    stage.setScene(scene);
    }
    
    @FXML
    private void handleBtnDescription() throws IOException { 
    logger.info("Description button clicked");  
    
    Stage stage = (Stage) btnCreators.getScene().getWindow();
    MainApp mainApp = new MainApp();
    FXMLLoader loader = mainApp.loadFXML("/fxml/DescriptionScene.fxml", new FXMLMainAppController());
    Scene scene = new Scene(loader.load());
    stage.setScene(scene);
    }
    
    
}
