package edu.vanier.template.physicalLaws;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import library.dynamics.Body;
import library.dynamics.World;
import library.geometry.Polygon;
import library.math.Vectors2D;

public class Physics {

    private final Pane simulationPane;
    private AnimationTimer physicsTimer;
    private World world = new World(new Vectors2D(0, -9.81));

    public Physics(Pane simulationPane) {
        this.simulationPane = simulationPane;

        Rectangle rect2 = new Rectangle(20, 20);
        Rectangle rect4 = new Rectangle(40, 40);
        Polygon boxShape2 = new Polygon(rect2);
        Body cubeBody2 = new Body(boxShape2, 0, 40);
        cubeBody2.setStatic();

        simulationPane.getChildren().add(rect4);
        world.addBody(cubeBody2);

        /*Rectangle rect1 = new Rectangle(20, 20);
        Rectangle rect3 = new Rectangle(40, 40);

        Polygon boxShape1 = new Polygon(rect1);

        Body cubeBody1 = new Body(boxShape1, 0, 120);*/

        

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

                    double translatedX = (body.position.x - rectWidth / 2) + paneWidth / 2;
                    double translatedY = paneHeight / 2 - (body.position.y - rectHeight / 2);

                    rect.setTranslateX(translatedX);
                    rect.setTranslateY(translatedY);
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

    public void addTetrisPeace() {
        System.out.println("Adding figure");
        
       

        Rectangle rect1 = new Rectangle(20, 20);
        Rectangle rect3 = new Rectangle(40, 40);

        Polygon boxShape1 = new Polygon(rect1);

        Body cubeBody1 = new Body(boxShape1, 0, 120);
        
         simulationPane.getChildren().add(rect3);
        world.addBody(cubeBody1);
    }

    public void removeAllBlocks() {
        simulationPane.getChildren().removeIf(node -> node instanceof Rectangle);
    }
}
