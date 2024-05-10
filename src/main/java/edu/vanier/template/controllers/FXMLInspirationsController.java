package edu.vanier.template.controllers;

import edu.vanier.template.MainApp;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FXMLInspirationsController {
    
@FXML
Button btnBack;

@FXML
private void handleBtnBack() throws IOException {

Stage stage = (Stage) btnBack.getScene().getWindow();
MainApp mainApp = new MainApp();
FXMLLoader loader = mainApp.loadFXML("/fxml/CreditScene.fxml", new FXMLCreditsController());
Scene scene = new Scene(loader.load());
stage.setScene(scene);
}

}
