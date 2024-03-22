
package edu.vanier.template.controllers;


import java.io.IOException;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

/**
 * @authors
 *  Ludovic Confais Courcy
 *  Anton Lisunov
 *  Shyam Patel
 */

public class FXMLSettingsController{
@FXML 
Group GroupSquareBlock;
@FXML 
Group GroupLineBlock;
@FXML
Rectangle Square;
@FXML
AnchorPane SettingsAnchorPane;

@FXML 
private double dragStartX, dragStartY;
   /* public void main(String[] args) throws IOException {
     MoveBlock1(GroupSquareBlock);
     MoveBlock2(GroupLineBlock);
     MoveBlock3(Square);
    }*/
@FXML
  public void MoveBlock1(){
     
           double Y = GroupSquareBlock.getTranslateY();
            double X = GroupSquareBlock.getTranslateX();
            GroupSquareBlock.setTranslateX(X);
            GroupSquareBlock.setTranslateY(Y);
            //SettingsAnchorPane.getChildren().add(GroupSquareBlock);     
 } 
 /* @FXML
  public void MoveBlock3(Rectangle group) throws IOException {
       
    group.setOnMousePressed(event -> {
            dragStartX = event.getSceneX() - group.getTranslateX();
            dragStartY = event.getSceneY() - group.getTranslateY();
        });
      group.setOnMouseDragged(event -> {
            double offsetX = event.getSceneX();
            double offsetY = event.getSceneY();
            group.setTranslateX(offsetX);
            group.setTranslateY(offsetY);
        });
 } 
        
 private Group MoveBlock2(Group group) {

        // Set up event handlers for dragging the group
        double[] dragStart = new double[2];
        group.setOnMousePressed(event -> {
            dragStart[0] = event.getSceneX() - group.getTranslateX();
            dragStart[1] = event.getSceneY() - group.getTranslateY();
        });

        group.setOnMouseDragged(event -> {
            double offsetX = event.getSceneX() - dragStart[0];
            double offsetY = event.getSceneY() - dragStart[1];
            group.setTranslateX(offsetX);
            group.setTranslateY(offsetY);
        });

        return group;
    }*/
}
