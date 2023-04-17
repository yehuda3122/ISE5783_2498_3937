package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void testTestEquals() {
    }

    @Test
    void testTestToString() {
    }

    @Test
    void testDistanceSquared() {
    }

    @Test
    void testDistance() {
    }

    @Test
    void testAdd() {
        Point p1 = new Point(1, 2, 3);
        assertEquals(
                new Point(0,0,0),
                p1.add(new Vector(-1,-2,-3)),
                "ERROR: Point + Vector does not work correctly");
        //p1.add(new Vector(-1, -2, -3)).equals(new Point(0, 0, 0));
    }

    @Test
    void testSubtract() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Point(1,1,1).subtract(new Point(1,1,1)));
    }
}