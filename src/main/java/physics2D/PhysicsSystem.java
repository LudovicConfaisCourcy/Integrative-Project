package physics2D;

import java.util.ArrayList;
import java.util.List;
import org.joml.Vector2f;
import physics2D.basic.Collider2D;
import physics2D.laws.ForceRegistry;
import physics2D.laws.Gravity2D;
import physics2D.rigidbody.Collision;
import physics2D.rigidbody.RigidBody2D;

/**
 *
 * @author anton
 */
public class PhysicsSystem {
    private ForceRegistry forceRegistry;
    private Gravity2D gravity;

    private List<RigidBody2D> rigidbodies;
    private List<RigidBody2D> bodies1;
    private List<RigidBody2D> bodies2;
    private List<Collision> collisions;

    private float fixedUpdate;
    private int impulseIterations = 6;

    public PhysicsSystem(float fixedUpdateDt, Vector2f gravity) {
        this.forceRegistry = new ForceRegistry();
        this.gravity = new Gravity2D(gravity);

        this.rigidbodies = new ArrayList<>();
        this.bodies1 = new ArrayList<>();
        this.bodies2 = new ArrayList<>();
        this.collisions = new ArrayList<>();

        this.fixedUpdate = fixedUpdateDt;
    }

    public void update(float dt) {
        fixedUpdate();
    }

    public void fixedUpdate() {
        bodies1.clear();
        bodies2.clear();
        collisions.clear();

        // Find any collisions
        int size = rigidbodies.size();
        for (int i=0; i < size; i++) {
            for (int j=i; j < size; j++) {
                if (i == j) continue;

                Collision result = new Collision();
                RigidBody2D r1 = rigidbodies.get(i);
                RigidBody2D r2 = rigidbodies.get(j);
                Collider2D c1 = r1.getCollider();
                Collider2D c2 = r2.getCollider();

                if (result.isColliding()) {
                    bodies1.add(r1);
                    bodies2.add(r2);
                    collisions.add(result);
                }
            }
        }

        // Update the forces
        forceRegistry.updateForces(fixedUpdate);

        // Resolve collisions via iterative impulse resolution
        // iterate a certain amount of times to get an approximate solution
        for (int k=0; k < impulseIterations; k++) {
            for (int i=0; i < collisions.size(); i++) {
                int jSize = collisions.get(i).getContactPoints().size();
                for (int j=0; j < jSize; j++) {
                    RigidBody2D r1 = bodies1.get(i);
                    RigidBody2D r2 = bodies2.get(i);
                    applyImpulse(r1, r2, collisions.get(i));
                }
            }
        }

        // Update the velocities of all rigidbodies
        for (int i=0; i < rigidbodies.size(); i++) {
            rigidbodies.get(i).physicsUpdate(fixedUpdate);
        }

        // Apply linear projection
    }

    private void applyImpulse(RigidBody2D a, RigidBody2D b, Collision m) {
        // Linear velocity
        float invMass1 = a.getInverseMass();
        float invMass2 = b.getInverseMass();
        float invMassSum = invMass1 + invMass2;
        if (invMassSum == 0f) {
            return;
        }

        // Relative velocity
        Vector2f relativeVel = new Vector2f(b.getVelocity()).sub(a.getVelocity());
        Vector2f relativeNormal = new Vector2f(m.getNormal()).normalize();
        // Moving away from each other? Do nothing
        if (relativeVel.dot(relativeNormal) > 0.0f) {
            return;
        }

        float e = Math.min(a.getCor(), b.getCor());
        float numerator = (-(1.0f + e) * relativeVel.dot(relativeNormal));
        float j = numerator / invMassSum;
        if (!m.getContactPoints().isEmpty() && j != 0.0f) {
            j /= (float)m.getContactPoints().size();
        }

        Vector2f impulse = new Vector2f(relativeNormal).mul(j);
        a.setVelocity(
                new Vector2f(a.getVelocity()).add(new Vector2f(impulse).mul(invMass1).mul(-1f)));
        b.setVelocity(
                new Vector2f(b.getVelocity()).add(new Vector2f(impulse).mul(invMass2).mul(1f)));
    }

    public void addRigidbody(RigidBody2D body, boolean addGravity) {
        this.rigidbodies.add(body);
        if (addGravity)
            this.forceRegistry.add(body, gravity);
    }
}
