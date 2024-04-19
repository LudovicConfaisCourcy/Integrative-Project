package edu.vanier.template.physicalLaws;

import edu.vanier.template.tetrisPieces.BlockState;
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
                applyGravity();
                applyNormalForce();
                moveBlock();
               rotateBlock();

            }

        };

    }

    private void applyGravity() {
        // Apply gravity to all Tetris blocks
        for (Node node : simulationPane.getChildren()) {
            if (node instanceof TetrisBlock block) {
                block.getCurrentState().setAccY(gravity); // Add gravity acceleration
            }
        }
    }

    private void applyNormalForce() {
        double simulationPaneHeight = simulationPane.getHeight();

        for (Node node : simulationPane.getChildren()) {
            if (node instanceof TetrisBlock block) {

                double blockBottomY = block.getCurrentState().getPosY() + block.getHeight();

                if (blockBottomY >= simulationPaneHeight) {

                    block.getCurrentState().setPosY(simulationPaneHeight - block.getHeight());
                    block.getCurrentState().setSpeedY(0);
                    block.getCurrentState().setAccY(0);

                } else {
                    for (Node otherNode : simulationPane.getChildren()) {
                        if (otherNode instanceof TetrisBlock && otherNode != block) {
                            TetrisBlock otherBlock = (TetrisBlock) otherNode;
                            if (block.getBoundsInParent().intersects(otherBlock.getBoundsInParent())) {
                                // Determine relative positions of blocks
                                double deltaY = block.getCurrentState().getPosY() - otherBlock.getCurrentState().getPosY();

                                // Adjust positions and velocities based on relative positions
                                if (deltaY > 0) { // Block is above
                                    block.getCurrentState().setPosY(otherBlock.getCurrentState().getPosY() + otherBlock.getHeight());
                                    block.getCurrentState().setSpeedY(0);
                                    block.getCurrentState().setAccY(gravity);
                                } else { // Block is below
                                    block.getCurrentState().setPosY(otherBlock.getCurrentState().getPosY() - block.getHeight());
                                    block.getCurrentState().setSpeedY(0);
                                    block.getCurrentState().setAccY(-gravity);
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
        for (Node node : simulationPane.getChildren()) {
            if (node instanceof TetrisBlock block) {
                block.addSpeed(block.getCurrentState().getAccX() * deltaTime, block.getCurrentState().getAccY() * deltaTime);
                double newY = block.getCurrentState().getPosY() + block.getCurrentState().getSpeedY() * deltaTime;
                double newX = block.getCurrentState().getPosX() + block.getCurrentState().getSpeedX() * deltaTime;
                block.addBlockState(new BlockState(newX, newY, block.getCurrentState().getSpeedX(), block.getCurrentState().getSpeedY(), block.getCurrentState().getAccX(), block.getCurrentState().getAccY()));
                System.out.println(block.getCurrentState().getSpeedX() + "___" + block.getCurrentState().getSpeedY());
            }
        }
    }

    private void rotateBlock() {
        for (Node node : simulationPane.getChildren()) {
            if (node instanceof TetrisBlock block) {
                
                block.getCurrentState().setPosA(block.getPreviousState().getPosA() + block.getCurrentState().getSpeedA() * deltaTime);
                block.addBlockState(block.getBlockState());
                block.setRotate(block.getCurrentState().getPosA());
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
