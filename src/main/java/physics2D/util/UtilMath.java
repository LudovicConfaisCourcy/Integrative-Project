/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package physics2D.util;

import org.joml.Vector2f;

/**
 *
 * @author anton
 */
public class UtilMath {

    public static void rotate(Vector2f vec, float angleDeg, Vector2f origin) {
        float x = vec.x - origin.x;
        float y = vec.y - origin.y;

        float cos = (float) Math.cos(Math.toRadians(angleDeg));
        float sin = (float) Math.sin(Math.toRadians(angleDeg));
        
        //x' = xcos@-ysin@
        //y' = xsin@+ycos@
        float xPrime = (x * cos) - (y * sin);
        float yPrime = (x * sin) + (y * cos);

        xPrime += origin.x;
        yPrime += origin.y;

        vec.x = xPrime;
        vec.y = yPrime;
    }

    public static boolean compare(float x, float y) {
        return Math.abs(x - y) <= Float.MIN_VALUE * Math.max(1.0f, Math.max(Math.abs(x), Math.abs(y)));
    }

    public static boolean compare(Vector2f vec1, Vector2f vec2) {
        return compare(vec1.x, vec2.x) && compare(vec1.y, vec2.y);
    }
    
    public static boolean compare(float x, float y, float epsilon) {
        //If numbers are not equal, but have simular value. In order to compare them and consider them equal eplsilon is needed.
        return Math.abs(x - y) <= epsilon* Math.max(1.0f, Math.max(Math.abs(x), Math.abs(y)));
    }
    
    public static boolean compare(Vector2f vecA, Vector2f vecB, float epsilon) {
       return compare(vecA.x, vecB.x, epsilon) && compare(vecA.y, vecB.y, epsilon);
    }

}
