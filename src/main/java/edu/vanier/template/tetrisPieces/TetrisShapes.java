package edu.vanier.template.tetrisPieces;

import javafx.scene.paint.Color;

/**
 *
 * @author anton
 */
public class TetrisShapes {
    
    private static final Color COLOR_I = Color.CYAN;
    private static final Color COLOR_O = Color.YELLOW;
    private static final Color COLOR_T = Color.PURPLE;
    private static final Color COLOR_L = Color.ORANGE;
    private static final Color COLOR_J = Color.BLUE;
    private static final Color COLOR_S = Color.GREEN;
    private static final Color COLOR_Z = Color.RED;
    
    public static final int[][] SHAPE_I = {
            {1, 1, 1, 1}
    };

    public static final int[][] SHAPE_O = {
            {1, 1},
            {1, 1}
    };

    public static final int[][] SHAPE_T = {
            {0, 1, 0},
            {1, 1, 1}
    };

    public static final int[][] SHAPE_L = {
            {1, 0},
            {1, 0},
            {1, 1}
    };

    public static final int[][] SHAPE_J = {
            {0, 1},
            {0, 1},
            {1, 1}
    };

    public static final int[][] SHAPE_S = {
            {0, 1, 1},
            {1, 1, 0}
    };

    public static final int[][] SHAPE_Z = {
            {1, 1, 0},
            {0, 1, 1}
    };

    
}
