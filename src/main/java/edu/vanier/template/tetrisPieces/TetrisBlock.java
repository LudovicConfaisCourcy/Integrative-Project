package edu.vanier.template.tetrisPieces;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Anton Lisunov
 */
public class TetrisBlock extends Rectangle {

    private static final int BLOCK_SIZE = 30;

    // Speed
    private double speedX;
    private double speedY;

    public TetrisBlock(int x, int y, Color color) {
        super(x,y,BLOCK_SIZE, BLOCK_SIZE);
        setFill(color);
    }

    public TetrisBlock(int x, int y, int width, int height, Color color) {
        super(x,y,width, height);
        setFill(color);
    }

    // Getters and setters for speed
    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

}
