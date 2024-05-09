package edu.vanier.template.physicalLaws;

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
import library.dynamics.Settings;
import library.geometry.Polygon;

public class Physics {
    private final Pane simulationPane;
    private AnimationTimer physicsTimer;
    private Vectors2D gravity;
    private ArrayList<Body> bodies = new ArrayList<>();
    private ArrayList<Joint> joints = new ArrayList<>();
    private ArrayList<Arbiter> contacts = new ArrayList<>();

    public Physics(Pane simulationPane) {
        this.simulationPane = simulationPane;
        
        Rectangle rect2 = new Rectangle(20, 20);
        Rectangle rect4 = new Rectangle(40, 40);
        Polygon boxShape2 = new Polygon(rect2);
        Body cubeBody2 = new Body(boxShape2, -20, 40);
        cubeBody2.setStatic();

        simulationPane.getChildren().add(rect4);
        addBody(cubeBody2);
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
            physicsTimer.start();
        }
    }

    public void stopPhysics() {
        if (physicsTimer != null) {
            physicsTimer.stop();
        }
    }

    public void addTetrisPiece() {
        Rectangle rect1 = new Rectangle(20, 20);
        Rectangle rect3 = new Rectangle(40, 40);

        Polygon boxShape1 = new Polygon(rect1);
        Body cubeBody1 = new Body(boxShape1, 0, 120);

        simulationPane.getChildren().add(rect3);
        addBody(cubeBody1);
    }

    public void removeAllBlocks() {
        simulationPane.getChildren().removeIf(node -> node instanceof Rectangle);
    }

    private void updatePhysics() {
        double paneWidth = simulationPane.getWidth();
        double paneHeight = simulationPane.getHeight();

        for (int i = 0; i < simulationPane.getChildren().size(); i++) {
            Body body = bodies.get(i);
            Rectangle rect = (Rectangle) simulationPane.getChildren().get(i);

            double rectWidth = rect.getWidth();
            double rectHeight = rect.getHeight();

            double translatedX = (body.position.x - rectWidth / 2) + paneWidth / 2;
            double translatedY = paneHeight / 2 - (body.position.y - rectHeight / 2);

            rect.setTranslateX(translatedX);
            rect.setTranslateY(translatedY);
            rect.setRotate(-Math.toDegrees(body.orientation));
        }

        step(1.0 / 60.0);
    }

    public void setGravity(Vectors2D gravity) {
        this.gravity = gravity;
    }

    public Body addBody(Body b) {
        bodies.add(b);
        return b;
    }

    public void addBodies(Body... bodiesToAdd) {
        bodies.addAll(Arrays.asList(bodiesToAdd));
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

    public void clearWorld() {
        bodies.clear();
        contacts.clear();
        joints.clear();
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
