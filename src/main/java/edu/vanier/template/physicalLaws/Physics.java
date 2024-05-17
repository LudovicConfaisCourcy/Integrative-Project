package edu.vanier.template.physicalLaws;

import edu.vanier.template.tetrisPieces.TetrisBlock;
import edu.vanier.template.tetrisPieces.TetrisGround;
import edu.vanier.template.tetrisPieces.TetrisShapes;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import library.collision.Arbiter;
import library.collision.AABB;
import library.dynamics.Body;
import library.joints.Joint;
import library.math.Vectors2D;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import library.dynamics.Settings;
import library.geometry.Polygon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anton Lisunov
 */
public class Physics {

    private final static Logger logger = LoggerFactory.getLogger(Physics.class);
    boolean lostVerifier = false;
    private int lifes = 12;
    private Pane simulationPane;
    private AnimationTimer physicsTimer;
    private Vectors2D gravity;
    private ArrayList<Body> bodies = new ArrayList<>();
    private ArrayList<Joint> joints = new ArrayList<>();
    private ArrayList<Arbiter> contacts = new ArrayList<>();

    public Physics(Pane simulationPane) {
        this.simulationPane = simulationPane;
        simulationPane.setVisible(false);
        this.gravity = new Vectors2D(0, -9.81);
        start();
    }

    private void start() {
        physicsTimer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                updatePhysics();
            }
        };
    }

    public void startPhysics() {
        if (physicsTimer != null) {
            simulationPane.setVisible(true);
            physicsTimer.start();
        }
    }

    public void stopPhysics() {
        if (physicsTimer != null) {
            physicsTimer.stop();
            
        }
    }

    public void addTetrisPiece(TetrisBlock block, double x, double y) {

        Body body = new Body(new Polygon(block), x, y);
        body.setDensity(400);
        bodies.add(body);
        simulationPane.getChildren().add(new TetrisBlock(block.getWidth() * 2, block.getHeight() * 2, (Color) block.getFill()));
    }

    public void addGround(TetrisGround block, double x, double y) {

        double randomX = Settings.generateRandomNoInRange(x - 50, x + 50);
        double randomY = Settings.generateRandomNoInRange(y - 20, y + 20);
        double randomO = Settings.generateRandomNoInRange(-Math.PI / 9, Math.PI / 9);

        Body body = new Body(new Polygon(block), randomX, randomY);
        body.setOrientation(randomO);
        body.setStatic();
        bodies.add(body);
        simulationPane.getChildren().add(new TetrisGround(block.getWidth() * 2, block.getHeight() * 2, (Color) block.getFill()));
    }

    public void addTetrisShape(char type, TetrisBlock block, double x, double y, Label label) {
        int value = Integer.valueOf(label.getText());
        value += 10;
        label.setText(String.valueOf(value));
        TetrisShapes shape = null;
        switch (type) {
            case 'I' ->
                shape = TetrisShapes.Shape_I(new Polygon(block), x, y);
            case 'J' ->
                shape = TetrisShapes.Shape_J(new Polygon(block), x, y);
            case 'L' ->
                shape = TetrisShapes.Shape_L(new Polygon(block), x, y);
            case 'O' ->
                shape = TetrisShapes.Shape_O(new Polygon(block), x, y);
            case 'S' ->
                shape = TetrisShapes.Shape_S(new Polygon(block), x, y);
            case 'T' ->
                shape = TetrisShapes.Shape_T(new Polygon(block), x, y);
            case 'Z' ->
                shape = TetrisShapes.Shape_Z(new Polygon(block), x, y);
            default ->
                logger.info("Invalid shape type: " + type);
        }

        Joint[] jointArray;
        jointArray = shape.getJointList();
        joints.addAll(Arrays.asList(jointArray));

        Body[] bodyArray = shape.getBodyList();
        for (Body body : bodyArray) {
            bodies.add(body);
            simulationPane.getChildren().add(new TetrisBlock(block.getWidth() * 2, block.getHeight() * 2, shape.getColor()));
        }

    }

    public ArrayList<Body> getBodies() {
        return bodies;
    }

    public ArrayList<Rectangle> getBlocks() {
        ArrayList<Rectangle> blocks = new ArrayList();
        for (int i = 0; i < simulationPane.getChildren().size(); i++) {
            blocks.add((Rectangle) simulationPane.getChildren().get(i));
        }
        return blocks;
    }

    public void removeAll() {
        simulationPane.getChildren().removeIf(node -> node instanceof Rectangle);
        bodies.clear();
        contacts.clear();
        joints.clear();
    }

    private void updatePhysics() {
        double paneWidth = 400;
        double paneHeight = 380;

        for (int i = 0; i < simulationPane.getChildren().size(); i++) {
            Body body = bodies.get(i);
            Rectangle rect = (Rectangle) simulationPane.getChildren().get(i);

            double rectWidth = rect.getWidth();
            double rectHeight = rect.getHeight();

            double translatedX = (body.position.x - rectWidth / 2) + paneWidth / 2;
            double translatedY = paneHeight / 2 - (body.position.y + rectHeight / 2);

            rect.setTranslateX(translatedX);
            rect.setTranslateY(translatedY);
            rect.setRotate(-Math.toDegrees(body.orientation));

            int pos = i;
            if (!body.isStatic()) {
                for (Body staticBody : bodies) {
                    if (staticBody != body && staticBody.isStatic()) {
                        if (AABB.AABBOverLap(body, staticBody) && Math.abs(body.velocity.x) < 10 && Math.abs(body.velocity.y) < 10) {
                            //Future Feature

                        }
                    }
                }
            }

            if (translatedY > simulationPane.getHeight() + 250) {
                bodies.remove(i);
                simulationPane.getChildren().remove(i);
                lifes--;
                if (lifes < 0) {
                    lostVerifier = true;
                    stopPhysics();
                }
            }
        }

        step(1.0 / 60.0);
    }

    public boolean GameLostVerifier() {
        if (lostVerifier == true) {
            lostVerifier = false;
            return true;
        }
        return false;
    }

    public void setGravity(Vectors2D gravity) {
        this.gravity = gravity;
    }

    public void addBody(Body b) {
        bodies.add(b);

    }

    public void removeBody(Body b) {
        bodies.remove(b);
    }

    public Joint addJoint(Joint j) {
        joints.add(j);
        return j;
    }

    public void removeJoint(Joint j) {
        joints.remove(j);
    }

    public void step(double dt) {
        contacts.clear();

        broadPhaseCheck();

        semiImplicit(dt);

        for (Arbiter contact : contacts) {
            contact.penetrationResolution();
        }
    }

    private void semiImplicit(double dt) {
        applyForces(dt);

        solve(dt);

        for (Body b : bodies) {
            if (b.invMass == 0) {
                continue;
            }

            b.position.add(b.velocity.scalar(dt));
            b.setOrientation(b.orientation + (dt * b.angularVelocity));

            b.force.set(0, 0);
            b.torque = 0;
        }
    }

    private void applyForces(double dt) {
        for (Body b : bodies) {
            if (b.invMass == 0) {
                continue;
            }

            applyLinearDrag(b);

            if (b.affectedByGravity) {
                b.velocity.add(gravity.scalar(dt));
            }

            b.velocity.add(b.force.scalar(b.invMass).scalar(dt));
            b.angularVelocity += dt * b.invI * b.torque;
        }
    }

    private void solve(double dt) {
        for (Joint j : joints) {
            j.applyTension();
        }

        for (int i = 0; i < Settings.ITERATIONS; i++) {
            for (Arbiter contact : contacts) {
                contact.solve();
            }
        }
    }

    private void applyLinearDrag(Body b) {
        double velocityMagnitude = b.velocity.length();
        double dragForceMagnitude = velocityMagnitude * velocityMagnitude * b.linearDampening;
        Vectors2D dragForceVector = b.velocity.getNormalized().scalar(-dragForceMagnitude);
        b.applyForceToCentre(dragForceVector);
    }

    private void broadPhaseCheck() {
        for (int i = 0; i < bodies.size(); i++) {
            Body a = bodies.get(i);

            for (int x = i + 1; x < bodies.size(); x++) {
                Body b = bodies.get(x);

                if (a.invMass == 0 && b.invMass == 0 || a.particle && b.particle) {
                    continue;
                }

                if (AABB.AABBOverLap(a, b)) {
                    narrowPhaseCheck(a, b);
                }
            }
        }
    }

    private void narrowPhaseCheck(Body a, Body b) {
        Arbiter contactQuery = new Arbiter(a, b);
        contactQuery.narrowPhase();
        if (contactQuery.contactCount > 0) {
            contacts.add(contactQuery);
        }
    }

    public void gravityBetweenObj() {
        for (int a = 0; a < bodies.size(); a++) {
            Body A = bodies.get(a);
            for (int b = a + 1; b < bodies.size(); b++) {
                Body B = bodies.get(b);
                double distance = A.position.distance(B.position);
                double force = Math.pow(6.67, -11) * A.mass * B.mass / (distance * distance);
                Vectors2D direction = new Vectors2D(B.position.x - A.position.x, B.position.y - A.position.y);
                direction = direction.scalar(force);
                Vectors2D oppositeDir = new Vectors2D(-direction.x, -direction.y);
                A.force.addi(direction);
                B.force.addi(oppositeDir);
            }
        }
    }

}
