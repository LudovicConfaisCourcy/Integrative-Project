package edu.vanier.template.tetrisPieces;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Anton Lisunov
 */

public class TetrisBlock extends Rectangle {

    private static final int BLOCK_SIZE = 30;
    private List<BlockState> blockStates = new ArrayList<>();
    private int weight;
    private boolean active = true;

    public TetrisBlock(BlockState state, Color color) {
        super(BLOCK_SIZE, BLOCK_SIZE);
        setTranslateX(state.getPosX());
        setTranslateY(state.getPosY());
        this.weight = 1;
        addBlockState(state);
        setFill(color);
    }

    public final void addBlockState(BlockState state) {
        if (blockStates.size() >= 2) {
            blockStates.remove(0);
        }
        blockStates.add(state);
        this.setTranslateX(state.getPosX());
        this.setTranslateY(state.getPosY());
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
    

    //***************
    public void setActive() {
        this.active = true;
    }

    public void setDisActive() {
        this.active = false;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
