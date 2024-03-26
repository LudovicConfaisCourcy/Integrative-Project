package edu.vanier.template.physicalLaws;

import edu.vanier.template.tetrisPieces.TetrisBlock;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

/**
 * @author Anton Lisunov
 */
public class Physics {

    private final Pane simulationPane;
    private AnimationTimer gravityTimer;
    public double gravity = 0.98;

    public Physics(Pane simulationPane) {
        this.simulationPane = simulationPane;
        start();
    }

    private void start() {
        gravityTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                applyGravity();
            }

        };
        gravityTimer.start();
    }

    public void applyGravity() {
        for (javafx.scene.Node node : simulationPane.getChildren()) {
            if (node instanceof TetrisBlock block) {
                block.setTranslateY(block.getTranslateY() + gravity);
            }
        }

    }

    public void stopGravity() {
        if (gravityTimer != null) {
            gravityTimer.stop();
        }
    }
    public void startGravity() {
        if (gravityTimer != null) {
            gravityTimer.start();
        }
    }

    public void removeAllBlocks() {
        simulationPane.getChildren().removeIf(node -> node instanceof TetrisBlock);
    }

}
