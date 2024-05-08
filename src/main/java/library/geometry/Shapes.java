package library.geometry;

import library.dynamics.Body;
import testbed.Camera;
import library.math.Matrix2D;
import testbed.ColourSettings;

import java.awt.Graphics2D;
import javafx.scene.canvas.GraphicsContext;

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

    public abstract void draw(Graphics2D g, ColourSettings paintSettings, Camera camera);
    
    public abstract void draw(GraphicsContext gc, ColourSettings paintSettings, Camera camera);

    
}
