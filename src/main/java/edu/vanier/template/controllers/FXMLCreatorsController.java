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

public class FXMLCreatorsController {

@FXML
Button btnBack;

@FXML
private void openL(ActionEvent event) throws URISyntaxException, IOException {
Desktop.getDesktop().browse(new URI("https://github.com/LudovicConfaisCourcy"));
}

@FXML
private void openA(ActionEvent event) throws URISyntaxException, IOException {
Desktop.getDesktop().browse(new URI("https://github.com/AntoxaLis"));
}

@FXML
private void openS(ActionEvent event) throws URISyntaxException, IOException {
Desktop.getDesktop().browse(new URI("https://github.com/FBI-bot"));
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
