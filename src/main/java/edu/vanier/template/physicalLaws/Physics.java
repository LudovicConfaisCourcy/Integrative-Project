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
                previousTime = now;*/
                applyGravity();

            }

        };
        physicsTimer.start();
    }

    private void applyGravity() {
        // Vf = Vi + at

        for (javafx.scene.Node node : simulationPane.getChildren()) {
            if (node instanceof TetrisBlock block) {
                //1.60159055E7 average frame time
                double newSpeed = (block.getSpeedY() + gravity * 1.60159055E7/1_000_000_000);
                block.setSpeedY(newSpeed);
                double newY = block.getTranslateY() + block.getSpeedY();
                block.setTranslateY(newY);
                //if(block.getTranslateY() >= 1000){System.out.println(newSpeed);}
                
            }
        }

    }

    public void stopGravity() {
        if (physicsTimer != null) {
            physicsTimer.stop();
        }
    }

    public void removeAllBlocks() {
        simulationPane.getChildren().removeIf(node -> node instanceof TetrisBlock);
    }

}
