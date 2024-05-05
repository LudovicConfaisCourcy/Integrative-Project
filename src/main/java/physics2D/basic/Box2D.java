/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package physics2D.basic;

import org.joml.Vector2f;
import physics2D.rigidbody.RigidBody2D;

/**
 *
 * @author anton
 */
public class Box2D {

    private Vector2f size = new Vector2f();
    private Vector2f halfSize;
    private RigidBody2D rigidBody = null;

    public Box2D(Vector2f min, Vector2f max) {
        this.size = new Vector2f(max).sub(min);
        this.halfSize = new Vector2f(max).sub(min);
    }

    public Vector2f getMax() {
        return new Vector2f(this.rigidBody.getPosition().sub(this.halfSize));
    }

    public Vector2f getMin() {
        return new Vector2f(this.rigidBody.getPosition().sub(this.halfSize));
    }

    public Vector2f[] getVertices() {
        Vector2f max = getMax();
        Vector2f min = getMin();

        Vector2f[] vertices = {
            new Vector2f(min.x, min.y), new Vector2f(min.x, max.y),
            new Vector2f(max.x, min.y), new Vector2f(max.x, max.y)
        };

        if (rigidBody.getRotation() != 0.0f) {
            for (Vector2f vert : vertices) {
                // Rotates point(Vector2f) about center(Vector2f) by rotation(float in degrees)
                //JMath.rotate(vert, this.rigidbody.getPosition(), this.rigidbody.getRotation());
            }
        }

        return vertices;
    }

    public RigidBody2D getRigidbody() {
        return this.rigidBody;
    }
}
