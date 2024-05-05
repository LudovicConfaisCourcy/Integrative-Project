/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package physics2D.laws;

import org.joml.Vector2f;
import physics2D.rigidbody.RigidBody2D;

/**
 *
 * @author anton
 */
public class Gravity2D implements ForceGenerator {

    private Vector2f gravity;

    public Gravity2D(Vector2f force) {
        this.gravity = new Vector2f(force);
    }

    @Override
    public void updateForce(RigidBody2D body, float dt) {
        body.addForce(new Vector2f(gravity).mul(body.getMass()));
    }
}
