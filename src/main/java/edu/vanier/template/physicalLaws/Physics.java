package edu.vanier.template.physicalLaws;

import edu.vanier.template.tetrisPieces.TetrisBlock;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

/**
 * @author Anton Lisunov
 */
public class Physics {

    private final Pane simulationPane;
    private AnimationTimer physicsTimer;
    private final double deltaTime =  1.6E7 / 1_000_000_000;
    //private long previousTime = 0;
    public double gravity = 0.98;

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
    for (javafx.scene.Node node : simulationPane.getChildren()) {
        if (node instanceof TetrisBlock) {
            TetrisBlock block = (TetrisBlock) node;
            block.addAcc(0, gravity); // Add gravity acceleration
        }
    }
}

private void applyNormalForce() {
    // Loop through all Tetris blocks in the simulation pane
    for (javafx.scene.Node node : simulationPane.getChildren()) {
        if (node instanceof TetrisBlock) {
            TetrisBlock block = (TetrisBlock) node;
            double blockBottomY = block.getTranslateY() + block.getHeight();

            if (blockBottomY >= simulationPane.getHeight()) {
                // If the block hits the bottom, apply normal force
                block.setTranslateY(simulationPane.getHeight() - block.getHeight());
                block.setSpeedY(0); // Reset vertical speed
                block.setAccY(0); // Reset vertical acceleration
            } else {
                // Check for collisions with other blocks
                for (javafx.scene.Node otherNode : simulationPane.getChildren()) {
                    if (otherNode instanceof TetrisBlock && otherNode != block) {
                        TetrisBlock otherBlock = (TetrisBlock) otherNode;
                        if (block.getBoundsInParent().intersects(otherBlock.getBoundsInParent())) {
                            // If there's a collision, adjust positions and velocities
                            block.setTranslateY(otherBlock.getTranslateY() - block.getHeight());
                            block.setSpeedY(0); // Reset vertical speed
                            block.setAccY(0); // Reset vertical acceleration
                            break; // No need to check further collisions for this block
                        }
                    }
                }
            }
        }
    }
}

private void moveBlock() {
    // Move Tetris blocks according to their speed and acceleration
    for (javafx.scene.Node node : simulationPane.getChildren()) {
        if (node instanceof TetrisBlock) {
            TetrisBlock block = (TetrisBlock) node;
            block.addSpeed(0, block.getAccY() * deltaTime); // Update speed
            double newY = block.getTranslateY() + block.getSpeedY() * deltaTime; // Update position
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
