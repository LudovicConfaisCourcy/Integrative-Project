package edu.vanier.template.physicalLaws;

import edu.vanier.template.tetrisPieces.TetrisBlock;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * @author Anton Lisunov
 */
public class Physics {

    private final Pane simulationPane;
    private AnimationTimer physicsTimer;
    private final double deltaTime = 1.6E7 / 1_000_000_000;
    //private long previousTime = 0;
    public double gravity = 9.8;

    public Physics(Pane simulationPane) {
        this.simulationPane = simulationPane;
        start();
    }

    private void start() {
        physicsTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //now is equal to total time from start till end

                /*double time = (now - previousTime);
                previousTime = now;
                 */
                applyGravity();
                applyNormalForce();
                moveBlock();

            }

        };

    }

    private void applyGravity() {
        // Apply gravity to all Tetris blocks
        for (Node node : simulationPane.getChildren()) {
            if (node instanceof TetrisBlock) {
                TetrisBlock block = (TetrisBlock) node;
                block.getCurrentState().setAccY(gravity); // Add gravity acceleration
            }
        }
    }

    private void applyNormalForce() {
        double simulationPaneHeight = simulationPane.getHeight();

        for (Node node : simulationPane.getChildren()) {
            if (node instanceof TetrisBlock) {
                TetrisBlock block = (TetrisBlock) node;
                double blockBottomY = block.getTranslateY() + block.getHeight();

                if (blockBottomY >= simulationPaneHeight) {
                    // If the block hits the bottom, apply normal force
                    block.setTranslateY(simulationPaneHeight - block.getHeight());
                    block.getCurrentState().setSpeedY(0); // Reset vertical speed
                    block.getCurrentState().setAccY(0); // Reset vertical acceleration
                } else {
                    for (Node otherNode : simulationPane.getChildren()) {
                        if (otherNode instanceof TetrisBlock && otherNode != block) {
                            TetrisBlock otherBlock = (TetrisBlock) otherNode;
                            if (block.getBoundsInParent().intersects(otherBlock.getBoundsInParent())) {
                                // Determine relative positions of blocks
                                double deltaY = block.getTranslateY() - otherBlock.getTranslateY();

                                // Adjust positions and velocities based on relative positions
                                if (deltaY > 0) { // Block is above
                                    block.setTranslateY(otherBlock.getTranslateY() + otherBlock.getHeight());
                                    block.getCurrentState().setSpeedY(0); // Reset vertical speed
                                    block.getCurrentState().setAccY(gravity); // Apply gravity
                                } else { // Block is below
                                    block.setTranslateY(otherBlock.getTranslateY() - block.getHeight());
                                    block.getCurrentState().setSpeedY(0); // Reset vertical speed
                                    block.getCurrentState().setAccY(-gravity); // Apply upward force
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    private void moveBlock() {
        // Move Tetris blocks according to their speed and acceleration
        for (Node node : simulationPane.getChildren()) {
            if (node instanceof TetrisBlock) {
                TetrisBlock block = (TetrisBlock) node;
                block.addSpeed(block.getCurrentState().getAccX() * deltaTime, block.getCurrentState().getAccY() * deltaTime); // Update speed
                double newY = block.getTranslateY() + block.getCurrentState().getSpeedY() * deltaTime; // Update position
                block.setTranslateY(newY);
            }
        }
    }

    public void startPhysics() {
        if (physicsTimer != null) {
            physicsTimer.start();
        }
    }

    public void stopPhysics() {
        if (physicsTimer != null) {
            physicsTimer.stop();
        }
    }

    public void removeAllBlocks() {
        simulationPane.getChildren().removeIf(node -> node instanceof TetrisBlock);
    }

}
