package edu.vanier.template.physicalLaws;

import edu.vanier.template.tetrisPieces.BlockState;
import edu.vanier.template.tetrisPieces.TetrisBlock;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import library.dynamics.Body;
import library.dynamics.World;
import library.geometry.Polygon;
import library.math.Vectors2D;
import testbed.demo.TestBedWindow;

/**
 * @author Anton Lisunov
 */
public class Physics {

    private final Pane simulationPane;
    private AnimationTimer physicsTimer;
    public World world = new World(new Vectors2D(0, -9.81));
    //double paneWidth = simulationPane.getWidth();
    //double paneHeight = simulationPane.getHeight();

    public Physics(Pane simulationPane) {
        this.simulationPane = simulationPane;

        Rectangle rect1 = new Rectangle(20, 20);
        Rectangle rect2 = new Rectangle(10, 10);

        Polygon boxShape = new Polygon(rect1);
        Body cubeBody = new Body(boxShape, 0, 80);

        Polygon boxShape2 = new Polygon(rect2);

        Body cubeBody2 = new Body(boxShape2, 0, 40);
        cubeBody2.setStatic();

        simulationPane.getChildren().add(rect1);
        simulationPane.getChildren().add(rect2);
        world.addBody(cubeBody);
        world.addBody(cubeBody2);

        /*TestBedWindow demoWindow = new TestBedWindow(true);
        TestBedWindow.showWindow(demoWindow, "2D Physics Engine Demo", 1280, 720);
        demoWindow.startThread();*/
        start();
    }

    private void start() {
        physicsTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double paneWidth = simulationPane.getWidth();
                double paneHeight = simulationPane.getHeight();

                for (int i = 0; i < simulationPane.getChildren().size(); i++) {
                    Body body = world.bodies.get(i);

                    Rectangle rect = (Rectangle) simulationPane.getChildren().get(i);
                    double rectWidth = rect.getWidth();
                    double rectHeight = rect.getHeight();

                    // Calculate translated coordinates
                    double translatedX = (body.position.x-rectWidth/2) + paneWidth / 2;
                    double translatedY = paneHeight / 2 - (body.position.y-rectHeight/2);
                    
                    System.out.println("Y = "+translatedY);

                    simulationPane.getChildren().get(i).setTranslateX(translatedX);
                    simulationPane.getChildren().get(i).setTranslateY(translatedY);
                }

                world.step(1.0 / 60.0);

            }

        };

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
