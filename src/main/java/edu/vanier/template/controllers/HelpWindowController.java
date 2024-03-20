package edu.vanier.template.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/*
 * @authors Anton Lisunov
 */

public class HelpWindowController {

    @FXML
    private TextArea txtHelp;

    public void initialize() {
        // Set help information
        txtHelp.setText("This is the help information.\nYou can add more information here.");
    }
}