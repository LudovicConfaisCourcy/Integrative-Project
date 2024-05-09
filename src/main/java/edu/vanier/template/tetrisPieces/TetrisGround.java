package edu.vanier.template.tetrisPieces;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Anton Lisunov
 */
public class TetrisGround extends Rectangle {

    public TetrisGround(double width, double height,Color color) {
        super(width, height);
        setFill(color);
    }

}
