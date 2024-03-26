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

            }

        };

    }

    private void applyGravity() {
        // Vf = Vi + at

        for (javafx.scene.Node node : simulationPane.getChildren()) {
            if (node instanceof TetrisBlock block) {
                //1.6E7 average frame time in ns
                double newSpeed = (block.getSpeedY() + gravity * 1.6E7 / 1_000_000_000);
                block.setSpeedY(newSpeed);
                double newY = block.getTranslateY() + block.getSpeedY();
                block.setTranslateY(newY);
                //if(block.getTranslateY() >= 1000){System.out.println(newSpeed);}

            }
        }

    }

    private void applyNormalForce() {
        // Loop through all objects in the simulation pane
        for (javafx.scene.Node node : simulationPane.getChildren()) {
            if (node instanceof TetrisBlock block) {

                // Check if the block is in contact with the ground or any other surface
                // You may need to implement collision detection logic here
                // For simplicity, let's assume a basic condition for collision with the bottom of the pane
                if (block.getTranslateY() + block.getHeight() >= simulationPane.getHeight()) {
                    // Calculate the normal force based on the weight of the block and the coefficient of friction
                    // For simplicity, let's assume the coefficient of friction is constant
                    double normalForce = block.getWeight() * gravity; // F = m * g

                    // Apply the normal force to counteract gravity
                    block.setSpeedY(0);
                }
                for (javafx.scene.Node otherNode : simulationPane.getChildren()) {
                    if (otherNode instanceof TetrisBlock && otherNode != block) {
                        TetrisBlock otherBlock = (TetrisBlock) otherNode;
                        if (block.getBoundsInParent().intersects(otherBlock.getBoundsInParent())) {
                             block.setSpeedY(0);
                        }
                    }
                }
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
