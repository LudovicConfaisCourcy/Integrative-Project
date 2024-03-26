
package edu.vanier.template.controllers;


import edu.vanier.template.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMLCreditsController {
    
    @FXML
    Button btnCreators;
    @FXML
    Button btnInspirations;
    @FXML
    Button btnScience;
    @FXML
    Button btnDescription;
    
    
    @FXML
    public void switchScenes(ActionEvent event) throws IOException {        
        Object clickButton = event.getSource();
        
        if (clickButton == btnCreators) {
            switchScene("/fxml/CreatorsScene.fxml", new FXMLCreatorsController());
            
        } 
        else if (clickButton == btnInspirations) {
            switchScene("/fxml/InspirationsScene.fxml", new FXMLInspirationsController());
            
        }
        else if (clickButton == btnScience) {
            switchScene("/fxml/ScienceScene.fxml", new FXMLScienceController());
            
        }
        else if (clickButton == btnDescription) {
            switchScene("/fxml/DescriptionScene.fxml", new FXMLDescriptionController());
            
        }
    }
    
    @FXML
    public void switchScene(String fxml, Object controller) throws IOException {
        Stage currentStage = (Stage) btnCreators.getScene().getWindow();
        MainApp mainApp = new MainApp();
        FXMLLoader loader = mainApp.loadFXML(fxml, controller);
        currentStage.setScene(new Scene(loader.load()));
    }

}
