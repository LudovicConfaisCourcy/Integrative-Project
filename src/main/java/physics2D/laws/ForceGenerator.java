/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package physics2D.laws;

import physics2D.rigidbody.RigidBody2D;

/**
 *
 * @author anton
 */
public interface ForceGenerator {
    void updateForce(RigidBody2D body, float dt);
}
