package library.geometry;

import library.dynamics.Body;
import library.math.Matrix2D;

/**
 * Abstract class presenting a geometric shape.
 */
public abstract class Shapes{
    public Body body;
    public Matrix2D orient;

    Shapes() {
        orient = new Matrix2D();
        body = null;
    }

    public abstract void calcMass(double density);

    public abstract void createAABB();
    
}
