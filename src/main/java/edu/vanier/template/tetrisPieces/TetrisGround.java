package edu.vanier.template.tetrisPieces;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Anton Lisunov
 */
public class TetrisGround extends Rectangle {

    public TetrisGround(int x, int y, int widht, int height) {
        super(x, y, widht, height);
        //setArcHeight(50);
        //setArcWidth(50);
        setFill(Color.GREEN);    
    }
}
