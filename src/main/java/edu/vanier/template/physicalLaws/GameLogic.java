package edu.vanier.template.physicalLaws;

import edu.vanier.template.controllers.FXMLMainAppController;
import edu.vanier.template.controllers.FXMLPlayController;
import edu.vanier.template.tetrisPieces.TetrisBlock;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import library.dynamics.Body;
import library.math.Vectors2D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author anton
 */
public class GameLogic {

    private final static Logger logger = LoggerFactory.getLogger(GameLogic.class);
    private final Physics physics;
    private final FXMLPlayController controller;
    private int rand;

    private AnimationTimer timer;
    private long lastSpawnTime;
    private static final long SPAWN_INTERVAL = 7_000_000_000L;

    public GameLogic(FXMLPlayController controller, Physics physics) {
        this.controller = controller;
        this.physics = physics;
        this.lastSpawnTime = 0;
    }

    public void startGame() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastSpawnTime >= SPAWN_INTERVAL) {
                    spawnTetrisPiece();
                    lastSpawnTime = now;
                }
            }
        };
        timer.start();
    }

    public void stopGame() {
        if (timer != null) {
            timer.stop();
        }
    }

    public void spawnTetrisPiece() {
        rand += 1;
        rand %= 7;
        logger.info("Tetris Shape is created: " + rand);
        switch (rand) {
            case 0 -> {
                physics.addTetrisShape('I', new TetrisBlock(10, 10, Color.RED), 0, 150, controller.lbScore);
                controller.setInvisible();
                controller.GroupLBlock1.setVisible(true);
            }
            case 1 -> {
                physics.addTetrisShape('L', new TetrisBlock(10, 10, Color.RED), 0, 150, controller.lbScore);
                controller.setInvisible();
                controller.GroupJBlock.setVisible(true);
            }
            case 2 -> {
                physics.addTetrisShape('J', new TetrisBlock(10, 10, Color.RED), 0, 150, controller.lbScore);
                controller.setInvisible();
                controller.GroupTBlock.setVisible(true);
            }
            case 3 -> {
                physics.addTetrisShape('T', new TetrisBlock(10, 10, Color.RED), 0, 150, controller.lbScore);
                controller.setInvisible();
                controller.GroupSquareBlock.setVisible(true);
            }
            case 4 -> {
                physics.addTetrisShape('O', new TetrisBlock(10, 10, Color.RED), 0, 150, controller.lbScore);
                controller.setInvisible();
                controller.GroupSBlock1.setVisible(true);
            }
            case 5 -> {
                physics.addTetrisShape('S', new TetrisBlock(10, 10, Color.RED), 0, 150, controller.lbScore);
                controller.setInvisible();
                controller.GroupZBlock.setVisible(true);
            }
            case 6 -> {
                physics.addTetrisShape('Z', new TetrisBlock(10, 10, Color.RED), 0, 150, controller.lbScore);
                controller.setInvisible();
                controller.GroupLineBlock1.setVisible(true);
            }
            default ->
                logger.info("Wrong Tetris Shape Number");
        }

        for (int i = physics.getBodies().size()-4; i < physics.getBodies().size(); i += 4) {
            ArrayList blocks = new ArrayList<>(physics.getBlocks().subList(i, i + 4));
            ArrayList<Body> bodies = new ArrayList<>(physics.getBodies().subList(i, i + 4));

            moveBlock(blocks, bodies);
        }
    }

    public void moveBlock(ArrayList<TetrisBlock> blocks, ArrayList<Body> bodies) {
        double paneWidth = 400;
        double paneHeight = 380;
        double paneWidthHalf = paneWidth / 2;
        double paneHeightHalf = paneHeight / 2;

        for (int i = 0; i < blocks.size(); i++) {
            TetrisBlock block = blocks.get(i);
            Body[] relevantBodies = new Body[4];
            Vectors2D[] differences = new Vectors2D[3];

            relevantBodies[0] = bodies.get(i);
            for (int j = 1; j <= 3; j++) {
                int index = (i + j) % bodies.size();
                relevantBodies[j] = bodies.get(index);
                differences[j - 1] = relevantBodies[j].position.subtract(relevantBodies[0].position);
            }

            if (!relevantBodies[0].isStatic()) {
                block.setOnMousePressed(event -> {
                    block.getScene().setCursor(Cursor.CLOSED_HAND);
                });
            }

            block.setOnMouseDragged(event -> {
                handleMouseDragged(event, block, relevantBodies, differences, paneWidthHalf, paneHeightHalf);
            });

            block.setOnMouseReleased(event -> {
                block.getScene().setCursor(Cursor.OPEN_HAND);
            });
        }
    }

    private void handleMouseDragged(MouseEvent event, TetrisBlock block, Body[] bodies, Vectors2D[] differences, double paneWidthHalf, double paneHeightHalf) {
        bodies[0].position = new Vectors2D(event.getSceneX() - paneWidthHalf - block.getWidth() * 4, -event.getSceneY() + paneHeightHalf + block.getHeight() * 2);
        bodies[0].orientation = 0;
        for (int j = 1; j <= 3; j++) {
            bodies[j].position = new Vectors2D(bodies[0].position.x + differences[j - 1].x, bodies[0].position.y + differences[j - 1].y);
            bodies[j].orientation = 0;
        }

        event.consume();
    }

}
