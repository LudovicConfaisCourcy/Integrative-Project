/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.dynamics;

import javafx.scene.paint.Color;
import library.dynamics.Body;
import library.geometry.Polygon;
import library.math.Vectors2D;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anton
 */
public class CompositeBody extends Body {

    private List<Body> parts;
    private Color color;

    public CompositeBody(List<Body> parts, Color color) {
        super(parts.get(0).shape, parts.get(0).position.x, parts.get(0).position.y);
        this.parts = parts;
        this.color = color;
        calculateCompositeProperties();
    }

    private void calculateCompositeProperties() {
        // Compute composite properties such as centroid, moment of inertia, etc.
        // Here, just an example calculation for mass center and centroid.
        double totalMass = 0;
        Vectors2D centroid = new Vectors2D(0, 0);

        for (Body part : parts) {
            double mass = part.mass;
            Vectors2D position = part.position;
            centroid = centroid.add(position.scalar(mass));
            totalMass += mass;
        }

        centroid = centroid.scalar(1/totalMass);
        mass = totalMass;
        position = centroid;
    }

    public List<Body> getParts() {
        return parts;
    }

    public Color getColor() {
        return color;
    }
}
