package edu.vanier.template.tetrisPieces;

import javafx.scene.paint.Color;
import library.dynamics.Body;
import library.geometry.Polygon;
import library.joints.Joint;
import library.joints.JointToBody;
import library.math.Vectors2D;

/**
 *
 * @author anton
 */
public class TetrisShapes {

    private static final Color COLOR_I = Color.CYAN;
    private static final Color COLOR_O = Color.YELLOW;
    private static final Color COLOR_T = Color.PURPLE;
    private static final Color COLOR_L = Color.ORANGE;
    private static final Color COLOR_J = Color.BLUE;
    private static final Color COLOR_S = Color.GREEN;
    private static final Color COLOR_Z = Color.RED;

    private final Body[] bodyList;
    private final JointToBody[] jointList;
    private final Color color;

    public TetrisShapes(Body[] bodyList, JointToBody[] jointList, Color color) {
        this.bodyList = bodyList;
        this.jointList = jointList;
        this.color = color;
    }

    public Body[] getBodyList() {
        return bodyList;
    }

    public JointToBody[] getJointList() {
        return jointList;
    }

    public Color getColor() {
        return color;
    }

    public static TetrisShapes Shape_T(Polygon cube, double X, double Y) {
        Body[] bodyList = new Body[4];
        JointToBody[] jointList = new JointToBody[6];

        double cubeWidth = cube.vertices[2].x;
        double cubeHeight = cube.vertices[2].y;

        bodyList[0] = new Body(new Polygon(cubeWidth, cubeHeight), X - cubeWidth * 2, Y);
        bodyList[1] = new Body(new Polygon(cubeWidth, cubeHeight), X, Y);
        bodyList[2] = new Body(new Polygon(cubeWidth, cubeHeight), X + cubeWidth * 2, Y);
        bodyList[3] = new Body(new Polygon(cubeWidth, cubeHeight), X, Y - cubeWidth * 2);

        jointList[0] = new JointToBody(bodyList[0], bodyList[1], 10, 1, 100, true, new Vectors2D(0, cubeHeight), new Vectors2D(0, cubeHeight));
        jointList[1] = new JointToBody(bodyList[0], bodyList[1], 10, 1, 100, true, new Vectors2D(0, -cubeHeight), new Vectors2D(0, -cubeHeight));

        jointList[2] = new JointToBody(bodyList[1], bodyList[2], 10, 1, 100, true, new Vectors2D(0, cubeHeight), new Vectors2D(0, cubeHeight));
        jointList[3] = new JointToBody(bodyList[1], bodyList[2], 10, 1, 100, true, new Vectors2D(0, -cubeHeight), new Vectors2D(0, -cubeHeight));

        jointList[4] = new JointToBody(bodyList[1], bodyList[3], 10, 1, 100, true, new Vectors2D(cubeWidth, 0), new Vectors2D(cubeWidth, 0));
        jointList[5] = new JointToBody(bodyList[1], bodyList[3], 10, 1, 100, true, new Vectors2D(-cubeWidth, 0), new Vectors2D(-cubeWidth, 0));

        return new TetrisShapes(bodyList, jointList, COLOR_T);
    }

    public static TetrisShapes Shape_I(Polygon cube, double X, double Y) {
        Body[] bodyList = new Body[4];
        JointToBody[] jointList = new JointToBody[6];

        double cubeWidth = cube.vertices[2].x;
        double cubeHeight = cube.vertices[2].y;

        bodyList[0] = new Body(new Polygon(cubeWidth, cubeHeight), X - cubeWidth * 3, Y);
        bodyList[1] = new Body(new Polygon(cubeWidth, cubeHeight), X - cubeWidth, Y);
        bodyList[2] = new Body(new Polygon(cubeWidth, cubeHeight), X + cubeWidth, Y);
        bodyList[3] = new Body(new Polygon(cubeWidth, cubeHeight), X + cubeWidth * 3, Y);

        jointList[0] = new JointToBody(bodyList[0], bodyList[1], 10, 1, 100, true, new Vectors2D(0, cubeHeight), new Vectors2D(0, cubeHeight));
        jointList[1] = new JointToBody(bodyList[0], bodyList[1], 10, 1, 100, true, new Vectors2D(0, -cubeHeight), new Vectors2D(0, -cubeHeight));

        jointList[2] = new JointToBody(bodyList[1], bodyList[2], 10, 1, 100, true, new Vectors2D(0, cubeHeight), new Vectors2D(0, cubeHeight));
        jointList[3] = new JointToBody(bodyList[1], bodyList[2], 10, 1, 100, true, new Vectors2D(0, -cubeHeight), new Vectors2D(0, -cubeHeight));

        jointList[4] = new JointToBody(bodyList[2], bodyList[3], 10, 1, 100, true, new Vectors2D(0, cubeHeight), new Vectors2D(0, cubeHeight));
        jointList[5] = new JointToBody(bodyList[2], bodyList[3], 10, 1, 100, true, new Vectors2D(0, -cubeHeight), new Vectors2D(0, -cubeHeight));

        return new TetrisShapes(bodyList, jointList, COLOR_I);
    }

    public static TetrisShapes Shape_L(Polygon cube, double X, double Y) {
        Body[] bodyList = new Body[4];
        JointToBody[] jointList = new JointToBody[6];

        double cubeWidth = cube.vertices[2].x;
        double cubeHeight = cube.vertices[2].y;

        bodyList[0] = new Body(new Polygon(cubeWidth, cubeHeight), X - cubeWidth * 2, Y);
        bodyList[1] = new Body(new Polygon(cubeWidth, cubeHeight), X, Y);
        bodyList[2] = new Body(new Polygon(cubeWidth, cubeHeight), X + cubeWidth * 2, Y);
        bodyList[3] = new Body(new Polygon(cubeWidth, cubeHeight), X + cubeWidth * 2, Y + cubeHeight * 2);

        jointList[0] = new JointToBody(bodyList[0], bodyList[1], 10, 1, 100, true, new Vectors2D(0, cubeHeight), new Vectors2D(0, cubeHeight));
        jointList[1] = new JointToBody(bodyList[0], bodyList[1], 10, 1, 100, true, new Vectors2D(0, -cubeHeight), new Vectors2D(0, -cubeHeight));

        jointList[2] = new JointToBody(bodyList[1], bodyList[2], 10, 1, 100, true, new Vectors2D(0, cubeHeight), new Vectors2D(0, cubeHeight));
        jointList[3] = new JointToBody(bodyList[1], bodyList[2], 10, 1, 100, true, new Vectors2D(0, -cubeHeight), new Vectors2D(0, -cubeHeight));

        jointList[4] = new JointToBody(bodyList[2], bodyList[3], 10, 1, 100, true, new Vectors2D(cubeWidth, 0), new Vectors2D(cubeWidth, 0));
        jointList[5] = new JointToBody(bodyList[2], bodyList[3], 10, 1, 100, true, new Vectors2D(-cubeWidth, -0), new Vectors2D(-cubeWidth, -0));

        return new TetrisShapes(bodyList, jointList, COLOR_L);
    }

    public static TetrisShapes Shape_J(Polygon cube, double X, double Y) {
        Body[] bodyList = new Body[4];
        JointToBody[] jointList = new JointToBody[6];

        double cubeWidth = cube.vertices[2].x;
        double cubeHeight = cube.vertices[2].y;

        bodyList[0] = new Body(new Polygon(cubeWidth, cubeHeight), X - cubeWidth * 2, Y);
        bodyList[1] = new Body(new Polygon(cubeWidth, cubeHeight), X, Y);
        bodyList[2] = new Body(new Polygon(cubeWidth, cubeHeight), X + cubeWidth * 2, Y);
        bodyList[3] = new Body(new Polygon(cubeWidth, cubeHeight), X - cubeWidth * 2, Y + cubeHeight * 2);

        jointList[0] = new JointToBody(bodyList[0], bodyList[1], 10, 1, 100, true, new Vectors2D(0, cubeHeight), new Vectors2D(0, cubeHeight));
        jointList[1] = new JointToBody(bodyList[0], bodyList[1], 10, 1, 100, true, new Vectors2D(0, -cubeHeight), new Vectors2D(0, -cubeHeight));

        jointList[2] = new JointToBody(bodyList[1], bodyList[2], 10, 1, 100, true, new Vectors2D(0, cubeHeight), new Vectors2D(0, cubeHeight));
        jointList[3] = new JointToBody(bodyList[1], bodyList[2], 10, 1, 100, true, new Vectors2D(0, -cubeHeight), new Vectors2D(0, -cubeHeight));

        jointList[4] = new JointToBody(bodyList[0], bodyList[3], 10, 1, 100, true, new Vectors2D(cubeWidth, 0), new Vectors2D(cubeWidth, 0));
        jointList[5] = new JointToBody(bodyList[0], bodyList[3], 10, 1, 100, true, new Vectors2D(-cubeWidth, -0), new Vectors2D(-cubeWidth, -0));

        return new TetrisShapes(bodyList, jointList, COLOR_J);
    }

    public static TetrisShapes Shape_O(Polygon cube, double X, double Y) {
        Body[] bodyList = new Body[4];
        JointToBody[] jointList = new JointToBody[8];

        double cubeWidth = cube.vertices[2].x;
        double cubeHeight = cube.vertices[2].y;

        bodyList[0] = new Body(new Polygon(cubeWidth, cubeHeight), X - cubeWidth, Y);
        bodyList[1] = new Body(new Polygon(cubeWidth, cubeHeight), X + cubeWidth, Y);
        bodyList[2] = new Body(new Polygon(cubeWidth, cubeHeight), X - cubeWidth, Y + cubeHeight * 2);
        bodyList[3] = new Body(new Polygon(cubeWidth, cubeHeight), X + cubeWidth, Y + cubeHeight * 2);

        jointList[0] = new JointToBody(bodyList[0], bodyList[1], 10, 1, 100, true, new Vectors2D(0, cubeHeight), new Vectors2D(0, cubeHeight));
        jointList[1] = new JointToBody(bodyList[0], bodyList[1], 10, 1, 100, true, new Vectors2D(0, -cubeHeight), new Vectors2D(0, -cubeHeight));

        jointList[2] = new JointToBody(bodyList[0], bodyList[2], 10, 1, 100, true, new Vectors2D(cubeWidth, 0), new Vectors2D(cubeWidth, 0));
        jointList[3] = new JointToBody(bodyList[0], bodyList[2], 10, 1, 100, true, new Vectors2D(-cubeWidth, 0), new Vectors2D(-cubeWidth, 0));

        jointList[4] = new JointToBody(bodyList[1], bodyList[3], 10, 1, 100, true, new Vectors2D(cubeWidth, 0), new Vectors2D(cubeWidth, 0));
        jointList[5] = new JointToBody(bodyList[1], bodyList[3], 10, 1, 100, true, new Vectors2D(-cubeWidth, 0), new Vectors2D(-cubeWidth, 0));

        jointList[6] = new JointToBody(bodyList[2], bodyList[2], 10, 1, 100, true, new Vectors2D(0, cubeHeight), new Vectors2D(0, cubeHeight));
        jointList[7] = new JointToBody(bodyList[2], bodyList[2], 10, 1, 100, true, new Vectors2D(0, -cubeHeight), new Vectors2D(0, -cubeHeight));

        return new TetrisShapes(bodyList, jointList, COLOR_O);
    }

    public static TetrisShapes Shape_S(Polygon cube, double X, double Y) {
        Body[] bodyList = new Body[4];
        JointToBody[] jointList = new JointToBody[6];

        double cubeWidth = cube.vertices[2].x;
        double cubeHeight = cube.vertices[2].y;

        bodyList[0] = new Body(new Polygon(cubeWidth, cubeHeight), X - cubeWidth * 3, Y);
        bodyList[1] = new Body(new Polygon(cubeWidth, cubeHeight), X - cubeWidth, Y);
        bodyList[2] = new Body(new Polygon(cubeWidth, cubeHeight), X + cubeWidth, Y);
        bodyList[3] = new Body(new Polygon(cubeWidth, cubeHeight), X + cubeWidth * 3, Y);

        jointList[0] = new JointToBody(bodyList[0], bodyList[1], 10, 1, 100, true, new Vectors2D(0, cubeHeight), new Vectors2D(0, cubeHeight));
        jointList[1] = new JointToBody(bodyList[0], bodyList[1], 10, 1, 100, true, new Vectors2D(0, -cubeHeight), new Vectors2D(0, -cubeHeight));

        jointList[2] = new JointToBody(bodyList[1], bodyList[2], 10, 1, 100, true, new Vectors2D(0, cubeHeight), new Vectors2D(0, cubeHeight));
        jointList[3] = new JointToBody(bodyList[1], bodyList[2], 10, 1, 100, true, new Vectors2D(0, -cubeHeight), new Vectors2D(0, -cubeHeight));

        jointList[4] = new JointToBody(bodyList[2], bodyList[3], 10, 1, 100, true, new Vectors2D(0, cubeHeight), new Vectors2D(0, cubeHeight));
        jointList[5] = new JointToBody(bodyList[2], bodyList[3], 10, 1, 100, true, new Vectors2D(0, -cubeHeight), new Vectors2D(0, -cubeHeight));

        return new TetrisShapes(bodyList, jointList, COLOR_I);
    }

    public static TetrisShapes Shape_Z(Polygon cube, double X, double Y) {
        Body[] bodyList = new Body[4];
        JointToBody[] jointList = new JointToBody[6];

        double cubeWidth = cube.vertices[2].x;
        double cubeHeight = cube.vertices[2].y;

        bodyList[0] = new Body(new Polygon(cubeWidth, cubeHeight), X - cubeWidth * 3, Y);
        bodyList[1] = new Body(new Polygon(cubeWidth, cubeHeight), X - cubeWidth, Y);
        bodyList[2] = new Body(new Polygon(cubeWidth, cubeHeight), X + cubeWidth, Y);
        bodyList[3] = new Body(new Polygon(cubeWidth, cubeHeight), X + cubeWidth * 3, Y);

        jointList[0] = new JointToBody(bodyList[0], bodyList[1], 10, 1, 100, true, new Vectors2D(0, cubeHeight), new Vectors2D(0, cubeHeight));
        jointList[1] = new JointToBody(bodyList[0], bodyList[1], 10, 1, 100, true, new Vectors2D(0, -cubeHeight), new Vectors2D(0, -cubeHeight));

        jointList[2] = new JointToBody(bodyList[1], bodyList[2], 10, 1, 100, true, new Vectors2D(0, cubeHeight), new Vectors2D(0, cubeHeight));
        jointList[3] = new JointToBody(bodyList[1], bodyList[2], 10, 1, 100, true, new Vectors2D(0, -cubeHeight), new Vectors2D(0, -cubeHeight));

        jointList[4] = new JointToBody(bodyList[2], bodyList[3], 10, 1, 100, true, new Vectors2D(0, cubeHeight), new Vectors2D(0, cubeHeight));
        jointList[5] = new JointToBody(bodyList[2], bodyList[3], 10, 1, 100, true, new Vectors2D(0, -cubeHeight), new Vectors2D(0, -cubeHeight));

        return new TetrisShapes(bodyList, jointList, COLOR_I);
    }

}
