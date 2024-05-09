package edu.vanier.template.controllers;

import edu.vanier.template.MainApp;
import java.awt.Desktop;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMLScienceController {

@FXML
Button btnBack;

@FXML
private void openLink(ActionEvent event) throws URISyntaxException, IOException {
Desktop.getDesktop().browse(new URI("https://natureofcode.com/"));
}

@FXML
private void handleBtnBack() throws IOException {

Stage stage = (Stage) btnBack.getScene().getWindow();
MainApp mainApp = new MainApp();
FXMLLoader loader = mainApp.loadFXML("/fxml/CreditScene.fxml", new FXMLCreditsController());
Scene scene = new Scene(loader.load());
stage.setScene(scene);
}

}
