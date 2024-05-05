package physics2D.basic;

import org.joml.Vector2f;

/**
 *
 * @author anton
 */
public class Line2D {
    private Vector2f start;
    private Vector2f end;

    public Line2D(Vector2f from, Vector2f to) {
        this.start = from;
        this.end = to;
    }

    public Vector2f getStart() {
        return this.start;
    }

    public Vector2f getEnd() {
        return this.end;
    }
    
     public float lengthSquared() {
        return new Vector2f(start).sub(end).lengthSquared();
    }
}
