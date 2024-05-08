package testbed.demo.tests;

import javafx.scene.shape.Rectangle;
import library.dynamics.Body;
import library.dynamics.World;
import library.geometry.Circle;
import library.geometry.Polygon;
import library.joints.Joint;
import library.joints.JointToBody;
import library.math.Vectors2D;
import testbed.demo.TestBedWindow;

public class Chains {

    public static final String[] text = {"Chains:"};

    public static void load(TestBedWindow testBedWindow) {
        testBedWindow.setWorld(new World(new Vectors2D(0, -9.81)));
        World world = testBedWindow.getWorld();
        testBedWindow.setCamera(new Vectors2D(0, -50), 1.4);

        Rectangle rect1 = new Rectangle(100, 50, 20, 20);
        Rectangle rect2 = new Rectangle(100, 80, 10, 10);

        Polygon boxShape = new Polygon(rect1);
        Body cubeBody = new Body(boxShape, 100, 80);

        Polygon boxShape2 = new Polygon(rect2);

        Body cubeBody2 = new Body(boxShape2, 90, 40);
        cubeBody2.setStatic();
        world.addBody(cubeBody);
        world.addBody(cubeBody2);

        //Body b = new Body(new Polygon(40.0,40.0), 0, 0);
        //b.setDensity(0);
        //temp.addBody(b);
        /*Rectangle rect = new Rectangle(40,40);
        Body c = new Body(new Polygon(rect), 100, 0);
        c.setDensity(0);
        temp.addBody(c);

       /* int maxChainLength = 20;
        Body[] bodyList = new Body[maxChainLength];
        for (int i = 0; i < maxChainLength; i++) {
            Body b2 = new Body(new Polygon(20.0, 5.0), -20 + 40.0 * maxChainLength / 2 - (40 * i), 200);
            temp.addBody(b2);
            bodyList[i] = b2;

            if (i != 0) {
                Joint j1 = new JointToBody(bodyList[i - 1], bodyList[i], 1, 200, 10, true, new Vectors2D(-20, 0), new Vectors2D(20, 0));
                temp.addJoint(j1);
            }
        }*/
    }
}
