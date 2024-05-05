/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package physics2D.rigidbody;

import java.util.ArrayList;
import java.util.List;
import org.joml.Vector2f;

/**
 *
 * @author anton
 */
public class Collision {
      private boolean isColliding;
    private Vector2f normal;
    private List<Vector2f> contactPoints;
    private float depth;

    public Collision() {
        normal = new Vector2f();
        depth = 0.0f;
        isColliding = false;
    }

    public Collision(Vector2f normal, float depth) {
        this.normal = normal;
        this.contactPoints = new ArrayList<>();
        this.depth = depth;
        isColliding = true;
    }

    public void addContactPoint(Vector2f contact) {
        this.contactPoints.add(contact);
    }

    public Vector2f getNormal() {
        return normal;
    }

    public List<Vector2f> getContactPoints() {
        return contactPoints;
    }

    public float getDepth() {
        return depth;
    }

    public boolean isColliding() {
        return this.isColliding;
    }
}
