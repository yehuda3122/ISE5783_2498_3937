package geometries;
import primitives.*;

import geometries.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    /// three points to configure plane
    Point p1 = new Point(1, 2, 3);
    Point p2 = new Point(4, 7, 11);
    Point p3 = new Point(2, -3, 4);

    // two extra points that are on same line as p1
    Point p4 = new Point(2, 4, 6);
    Point p5 = new Point(4, 8, 12);

    Plane plane = new Plane(p1, p2, p3);
    Plane pl = new Plane(new Point(0, 0, 1), new Vector(1, 1, 1));
    @Test
    void getNormal() {
// ============ Equivalence Partitions Tests ==============
        // test that normal of plane returned is equal to expected normal vector
        double x = 9 / (7 * Math.sqrt(2));
        double y = 1 / (7 * Math.sqrt(2));
        double z = (-2) * Math.sqrt(2) / 7;

        assertEquals(new Vector(x, y, z),
                plane.getNormal(), "normal vector is not returned correctly");
    }
}