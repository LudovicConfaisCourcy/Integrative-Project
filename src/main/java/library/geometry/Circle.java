package library.geometry;

import library.collision.AABB;
import library.math.Vectors2D;

/**
 * Circle class to create a circle object.
 */
public class Circle extends Shapes {
    public double radius;

    /**
     * Constructor for a circle.
     *
     * @param radius Desired radius of the circle.
     */
    public Circle(double radius) {
        this.radius = radius;
    }

    /**
     * Calculates the mass of a circle.
     *
     * @param density The desired density to factor into the calculation.
     */
    @Override
    public void calcMass(double density) {
        body.mass = StrictMath.PI * radius * radius * density;
        body.invMass = (body.mass != 0.0f) ? 1.0f / body.mass : 0.0f;
        body.I = body.mass * radius * radius;
        body.invI = (body.I != 0.0f) ? 1.0f / body.I : 0.0f;
    }

    /**
     * Generates an AABB and binds it to the body.
     */
    @Override
    public void createAABB() {
        body.aabb = new AABB(new Vectors2D(-radius, -radius), new Vectors2D(radius, radius));
    }
}
