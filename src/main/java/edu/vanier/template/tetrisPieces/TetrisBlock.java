package edu.vanier.template.tetrisPieces;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Anton Lisunov
 */
public class TetrisBlock extends Rectangle {

    private static final int BLOCK_SIZE = 30;

    // Speed, Acceleration, Weight
    private double speedX;
    private double speedY;
    private double accX;
    private double accY;
    private double weight;

    public TetrisBlock(int x, int y, Color color) {
        super(BLOCK_SIZE, BLOCK_SIZE);
        setTranslateX(x);
        setTranslateY(y);
        this.weight = 1;
        setFill(color);
    }

    public TetrisBlock(int x, int y, int width, int height, Color color) {
        super(width, height);
        setTranslateX(x);
        setTranslateY(y);
        this.weight = 1;
        setFill(color);
    }

    public TetrisBlock(int x, int y, int width, int height, Color color, double weight) {
        super(width, height);
        setTranslateX(x);
        setTranslateY(y);
        this.weight = weight;
        setFill(color);
    }
    
    // Getters and setters for speed
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getAccX() {
        return accX;
    }

    public void setAccX(double accX) {
        this.accX = accX;
    }

    public double getAccY() {
        return accY;
    }

    public void setAccY(double accY) {
        this.accY = accY;
    }

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
