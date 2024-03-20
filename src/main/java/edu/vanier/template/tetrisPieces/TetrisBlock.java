package edu.vanier.template.tetrisPieces;

import static javafx.scene.paint.Color.RED;
import javafx.scene.shape.Rectangle;

/**
 * @author Anton Lisunov
 */

public class TetrisBlock extends Rectangle{
    
    private static final int BLOCK_SIZE = 30;
    
    public TetrisBlock(int x, int y){
        super(BLOCK_SIZE,BLOCK_SIZE);
        setTranslateX(x);
        setTranslateY(y);
        setFill(RED);
    }
    
    
}
