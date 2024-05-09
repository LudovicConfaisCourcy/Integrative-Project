package edu.vanier.template.controllers;

import edu.vanier.template.MainApp;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FXMLInspirationsController {
    
@FXML
Button btnBack;

@FXML
private void openLink(ActionEvent event) throws URISyntaxException, IOException {
Desktop.getDesktop().browse(new URI("https://www.trickytowers.com/"));
}

@FXML
private void openLink1(ActionEvent event) throws URISyntaxException, IOException {
Desktop.getDesktop().browse(new URI("https://github.com/Przemekkkth/gravity-tetris"));
}

@FXML
private void openLink2(ActionEvent event) throws URISyntaxException, IOException {
Desktop.getDesktop().browse(new URI("https://www.ventrella.com/GravityTetris/"));
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
