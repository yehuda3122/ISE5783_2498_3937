package geometries;

import primitives.*;

import geometries.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {
    Vector direction = new Vector(1, 0, 0);
    Ray ray1 = new Ray(new Point(0, 0, 0), direction);

    @Test
    void testgetNormalEP() {
        Tube tube = new Tube(ray1, 1);
        assertEquals(new Vector(0, 0, 1), tube.getNormal(new Point(5, 0, 1)),
                "normal vector returned is incorrect");
    }

    @Test
    void testgetNormalBVE() {
        Tube tube = new Tube(ray1, 1);
        assertThrows(IllegalArgumentException.class,
                () -> tube.getNormal(new Point(2, 0, 0)),
                "GetNormal() does not work for point on axis ray");
    }
}

