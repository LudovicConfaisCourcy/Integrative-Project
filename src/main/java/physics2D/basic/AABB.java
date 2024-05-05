package physics2D.basic;

import org.joml.Vector2f;
import physics2D.rigidbody.RigidBody2D;

/**
 *
 * @author anton
 *
 * AABB - Axis-Aligned Bounding Box Properly detects collisions of objects
 */
public class AABB {

    private Vector2f size = new Vector2f();
    private Vector2f halfSize;
    private RigidBody2D rigidBody = null;

    public AABB(Vector2f min, Vector2f max) {
        this.size = new Vector2f(max).sub(min); // size of the box
        this.halfSize = new Vector2f(max).sub(min); // half size of the box
    }

    //getters and setters
    public Vector2f getCenter() {
        return null;
    }

    public Vector2f getSize() {
        return size;
    }

    public Vector2f getMax() {
        return new Vector2f(this.rigidBody.getPosition().sub(this.halfSize));
    }

    public Vector2f getMin() {
        return new Vector2f(this.rigidBody.getPosition().sub(this.halfSize));
    }

}
