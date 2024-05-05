/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package physics2D.rigidbody;

import org.joml.Vector2f;
import physics2D.basic.AABB;
import physics2D.basic.Box2D;
import physics2D.basic.Line2D;
import physics2D.util.UtilMath;

/**
 *
 * @author anton
 *
 * Detects intersection of objects
 */
public class Intersection2D {

    public static boolean lineDotCollision(Vector2f point, Line2D line) {
        float dy = line.getEnd().y - line.getStart().y;
        float dx = line.getEnd().x - line.getStart().x;

        float a = dy / dx;
        float b = line.getEnd().y - (a * line.getEnd().x);

        //Equation of line function : y = ax+b
        return point.y == a * point.x + b;
    }

    public static boolean PointAABBCollision(Vector2f point, AABB box) {

        //AABB have 4 point: X1Y1, X1Y2, X2Y1, X2Y2
        Vector2f min = box.getMin();
        Vector2f max = box.getMax();

        // if point's coordinats are inside AABB zone, the collision is true.
        return point.x <= max.x && min.x <= point.x && point.y <= max.y && min.y <= point.y;
    }

    public static boolean PointBoxCollision(Vector2f point, Box2D box) {

        // Translate the point into local space
        Vector2f pointLocalBoxSpace = new Vector2f(point);
        UtilMath.rotate(pointLocalBoxSpace, box.getRigidbody().getRotation(),
                box.getRigidbody().getPosition());

        Vector2f min = box.getMin();
        Vector2f max = box.getMax();

        return pointLocalBoxSpace.x <= max.x && min.x <= pointLocalBoxSpace.x && pointLocalBoxSpace.y <= max.y && min.y <= pointLocalBoxSpace.y;
    }
    
     public static boolean LineAABBCollision(Line2D line, AABB box) {
         
        //We can easily check if one of end of line collides with AABB and skip further verification
        if (PointAABBCollision(line.getStart(), box) || PointAABBCollision(line.getEnd(), box)) {
            return true;
        }

        Vector2f unitVector = new Vector2f(line.getEnd()).sub(line.getStart());
        unitVector.normalize();
        unitVector.x = (unitVector.x != 0) ? 1.0f / unitVector.x : 0f;
        unitVector.y = (unitVector.y != 0) ? 1.0f / unitVector.y : 0f;

        Vector2f boxMin = box.getMin();
        boxMin.sub(line.getStart()).mul(unitVector);
        Vector2f boxMax = box.getMax();
        boxMax.sub(line.getStart()).mul(unitVector);

        float tmin = Math.max(Math.min(boxMin.x, boxMax.x), Math.min(boxMin.y, boxMax.y));
        float tmax = Math.min(Math.max(boxMin.x, boxMax.x), Math.max(boxMin.y, boxMax.y));
        if (tmax < 0 || tmin > tmax) {
            return false;
        }

        float t = (tmin < 0f) ? tmax : tmin;
        return t > 0f && t * t < line.lengthSquared();
    }

    public static boolean LineBoxCollision(Line2D line, Box2D box) {
        float theta = -box.getRigidbody().getRotation();
        Vector2f center = box.getRigidbody().getPosition();
        Vector2f localStart = new Vector2f(line.getStart());
        Vector2f localEnd = new Vector2f(line.getEnd());
        UtilMath.rotate(localStart, theta, center);
        UtilMath.rotate(localEnd, theta, center);

        Line2D localLine = new Line2D(localStart, localEnd);
        AABB aabb = new AABB(box.getMin(), box.getMax());

        return LineAABBCollision(localLine, aabb);
    }
    
    public static boolean AABBAndAABBB(AABB b1, AABB b2) {
        Vector2f axesToTest[] = {new Vector2f(0, 1), new Vector2f(1, 0)};
        for (Vector2f axesToTest1 : axesToTest) {
            if (!overlapOnAxis(b1, b2, axesToTest1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean AABBAndBox2D(AABB b1, Box2D b2) {
        Vector2f axesToTest[] = {
                new Vector2f(0, 1), new Vector2f(1, 0),
                new Vector2f(0, 1), new Vector2f(1, 0)
        };
        UtilMath.rotate(axesToTest[2], b2.getRigidbody().getRotation(), new Vector2f());
        UtilMath.rotate(axesToTest[3], b2.getRigidbody().getRotation(), new Vector2f());
        for (Vector2f axesToTest1 : axesToTest) {
            if (!overlapOnAxis(b1, b2, axesToTest1)) {
                return false;
            }
        }
        return true;
    }

    private static boolean overlapOnAxis(AABB b1, AABB b2, Vector2f axis) {
        Vector2f interval1 = getInterval(b1, axis);
        Vector2f interval2 = getInterval(b2, axis);
        return ((interval2.x <= interval1.y) && (interval1.x <= interval2.y));
    }

    private static boolean overlapOnAxis(AABB b1, Box2D b2, Vector2f axis) {
        Vector2f interval1 = getInterval(b1, axis);
        Vector2f interval2 = getInterval(b2, axis);
        return ((interval2.x <= interval1.y) && (interval1.x <= interval2.y));
    }

    private static boolean overlapOnAxis(Box2D b1, Box2D b2, Vector2f axis) {
        Vector2f interval1 = getInterval(b1, axis);
        Vector2f interval2 = getInterval(b2, axis);
        return ((interval2.x <= interval1.y) && (interval1.x <= interval2.y));
    }

    private static Vector2f getInterval(AABB rect, Vector2f axis) {
        Vector2f result = new Vector2f(0, 0);

        Vector2f min = rect.getMin();
        Vector2f max = rect.getMax();

        Vector2f vertices[] = {
            new Vector2f(min.x, min.y), new Vector2f(min.x, max.y),
            new Vector2f(max.x, min.y), new Vector2f(max.x, max.y)
        };

        result.x = axis.dot(vertices[0]);
        result.y = result.x;
        for (int i=1; i < 4; i++) {
            float projection = axis.dot(vertices[i]);
            if (projection < result.x) {
                result.x = projection;
            }
            if (projection > result.y) {
                result.y = projection;
            }
        }
        return result;
    }

    private static Vector2f getInterval(Box2D rect, Vector2f axis) {
        Vector2f result = new Vector2f(0, 0);

        Vector2f vertices[] = rect.getVertices();

        result.x = axis.dot(vertices[0]);
        result.y = result.x;
        for (int i=1; i < 4; i++) {
            float projection = axis.dot(vertices[i]);
            if (projection < result.x) {
                result.x = projection;
            }
            if (projection > result.y) {
                result.y = projection;
            }
        }
        return result;
    }

}
