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
    

    /* public final void addBlockState(BlockState state) {
        if (blockStates.size() >= 2) {
            blockStates.remove(0);
        }
        blockStates.add(state);
        this.setTranslateX(state.getPosX());
        this.setTranslateY(state.getPosY());
    }

    public BlockState getBlockState() {
        if (this.blockStates.size() > 1) {
            return this.blockStates.get(1);
        }
        return this.blockStates.get(0);
    }

    public BlockState getCurrentState() {
        return blockStates.get(blockStates.size() - 1);
    }

    public BlockState getPreviousState() {
        if (blockStates.size() >= 2) {
            return blockStates.get(blockStates.size() - 2);
        } else {
            return null;
        }
    }

    public void addSpeed(double speedX, double speedY) {
        this.getCurrentState().setSpeedX(this.getCurrentState().getSpeedX() + speedX);
        this.getCurrentState().setSpeedY(this.getCurrentState().getSpeedY() + speedY);
    }
     */
    //***************
    /*public void setActive() {
        this.active = true;
    }

    public void setDisActive() {
        this.active = false;
    }
    /*

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
     */
}
