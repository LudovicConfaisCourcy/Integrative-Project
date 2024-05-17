package edu.vanier.template.tetrisPieces;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Anton Lisunov
 */
public class TetrisBlock extends Rectangle {
    
    public TetrisBlock(double width, double height) {
        super(width, height);
        setFill(Color.BLACK);
    }


    public TetrisBlock(double width, double height,Color color) {
        super(width, height);
        setFill(color);
    }
    
}
