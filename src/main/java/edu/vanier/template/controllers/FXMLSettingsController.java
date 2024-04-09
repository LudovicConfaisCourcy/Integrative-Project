
package edu.vanier.template.controllers;


import java.awt.Checkbox;
import java.io.IOException;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import static javafx.scene.Cursor.cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.event.Event;
import javafx.scene.control.Button;

/**
 * @authors
 *  Ludovic Confais Courcy
 *  Anton Lisunov
 *  Shyam Patel
 */

public class FXMLSettingsController{

    @FXML
    Checkbox MusicCheckBox;
    @FXML
    Checkbox SoundCheckBox;
    @FXML
    AnchorPane SettingsAnchorPane;
    @FXML
    Button testbtn;
    
    
    @FXML
    private void CursorChange(){
        SettingsAnchorPane.setCursor(Cursor.HAND);
    }
    @FXML
    private void CursorChangeExit(){
        SettingsAnchorPane.setCursor(Cursor.DEFAULT);
    }
    
}
